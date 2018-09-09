package server;

import java.sql.*;
import java.util.Calendar;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.Scanner;

public class ServerCajero {
  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost/Prueba";
  private static final String USER = "root";
  private static final String PASS = "";
  private String numeroTelefono;
  private String nip;
  private int port;

  public ServerCajero() {
    this.numeroTelefono = null;
    this.nip = null;
    this.port = -1;
  }

  public ServerCajero(int port) {
    this.numeroTelefono = null;
    this.nip = null;
    this.port = port;
  }

  public ServerCajero(String numeroTelefono, String nip, int port) {
    this.numeroTelefono = numeroTelefono;
    this.nip = nip;
    this.port = port;
  }

  public String getNumeroTelefono() {
    if(this.numeroTelefono==null)
      throw new NullPointerException("No se ha asignado un numero de telefono");
    return this.numeroTelefono;
  }

  public String getNip() {
    if(this.nip==null)
      throw new NullPointerException("No se ha asignado un nip");
    return this.nip;
  }

  public int getPort() {
    if(this.port==-1)
      throw new NullPointerException("No se ha asignado un puerto");
    return this.port;
  }

  public void setNumeroTelefono(String numeroTelefono) {
    this.numeroTelefono = numeroTelefono;
  }

  public void setNip(String nip) {
    if(nip.length()!=4)
      throw new IllegalArgumentException("El nip debe ser de 4 digitos");
    this.nip = nip;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public void splitData(String data) {
    setNumeroTelefono(data.substring(0,data.indexOf(":")));
    setNip(data.substring(data.indexOf(":")+1,data.length()));
  }

  public void sendToMySQL(String telefono, String nip){
    Connection conn = null;
    Statement stmt = null;
    try{
      Class.forName("com.mysql.jdbc.Driver");

      System.out.println("Conectando a la base de datos...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Base de datos conectada exitosamente...");

      System.out.println("Insertando datos en la tabla...");
      stmt = conn.createStatement();

      String sql = "INSERT INTO NoUser VALUES ('" + telefono + "', '" + nip + "')";
      stmt.executeUpdate(sql);
      System.out.println("Los datos han sido insertados en la tabla");

   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            conn.close();
      } catch(SQLException se){
      }
      try{
         if(conn!=null)
            conn.close();
      } catch(SQLException se){
         se.printStackTrace();
      }
   }
 }

  public void startServer() {
    if(this.port==-1)
      throw new NullPointerException("No es posible conectarse al servidor porque no se ha definido un puerto");
    try (ServerSocket server = new ServerSocket(this.port)){
      while (true) {
        Socket socket = server.accept();
        System.out.println(socket.getInetAddress().getHostName() + ":" + socket.getPort());
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String data = input.readLine();
        System.out.println("Recibido: " + data);
        splitData(data);
        sendToMySQL(this.numeroTelefono,this.nip);
      }
    } catch (NullPointerException ex) {
      System.out.println("Error al recibir datos: " + ex.getMessage());
    } catch (UnknownHostException ex) {
        System.out.println("Servidor no encontrado: " + ex.getMessage());
    } catch (IOException ex) {
      System.out.println("Error de entrada/salida: " + ex.getMessage());
    }
  }

  public static void main(String[] args) {
    Scanner io = new Scanner(System.in);
    System.out.println("Ingrese el puerto: ");
    int port = io.nextInt();
    ServerCajero server = new ServerCajero(port);
    System.out.println("Server conectado...");
    server.startServer();
  }
}
