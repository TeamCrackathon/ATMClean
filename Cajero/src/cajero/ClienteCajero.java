package cajero;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ClienteCajero {

  private String numeroTelefono;
  private String nip;
  private String hostname;
  private int port;

  public ClienteCajero() {
    this.numeroTelefono = null;
    this.nip = null;
    this.hostname = null;
    this.port = -1;
  }

  public ClienteCajero(String hostname, int port) {
    this.hostname = hostname;
    this.port = port;
  }

  public ClienteCajero(String numeroTelefono, String nip, String hostname, int port) {
    this.numeroTelefono = numeroTelefono;
    if(nip.length()!=4)
      throw new IllegalArgumentException("El nip debe ser de 4 digitos");
    this.nip = nip;
    this.hostname = hostname;
    this.port = port;
  }

  public String getNumeroTelefono() {
    if(this.numeroTelefono==null)
      throw new NullPointerException("No se ha asignado número de teléfono");
    return this.numeroTelefono;
  }

  public String getNip() {
    if(this.nip==null)
      throw new NullPointerException("No se ha asignado un nip");
    return this.nip;
  }

  public String getHostname() {
    if(this.hostname==null)
      throw new NullPointerException("No se ha asignado un hostname");
    return this.hostname;
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

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public void sendData(){
    if(hostname==null || port ==-1)
      throw new NullPointerException("No es posible conectarse al servidor porque no se ha establecido el host o el puerto");
    try (Socket socket = new Socket(hostname, port)) {
      OutputStream output = socket.getOutputStream();
      PrintWriter writer = new PrintWriter(output, true);
      writer.println(this.getNumeroTelefono() + ":" + this.getNip());
      socket.close();
    } catch (NullPointerException ex) {
      System.out.println("Error al mandar datos: " + ex.getMessage());
    } catch (UnknownHostException ex) {
        System.out.println("Servidor no encontrado: " + ex.getMessage());
    } catch (IOException ex) {
      System.out.println("Error de entrada/salida: " + ex.getMessage());
    }
  }

    public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      System.out.println("Ingrese el host:");
      String host = in.nextLine();
      System.out.println("Ingrese el puerto:");
      int port = Integer.parseInt(in.nextLine());
      ClienteCajero cajero = new ClienteCajero(host,port);

      System.out.println("Ingrese su numero telefonico:");
      cajero.setNumeroTelefono(in.nextLine());
      System.out.println("Ingrese un nip único de cuatro digitos:");
      cajero.setNip(in.nextLine());

      cajero.sendData();
    }
}
