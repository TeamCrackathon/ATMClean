import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;

public class ServerCajero {
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

  public void startServer() {
    if(this.port==-1)
      throw new NullPointerException("No es posible conectarse al servidor porque no se ha definido un puerto");
    try (ServerSocket server = new ServerSocket(this.port)){
      while (true) {
        Socket socket = server.accept();
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String data = input.readLine();
        System.out.println(data);
        splitData(data);
        System.out.println(this.numeroTelefono);
        System.out.println(this.nip);
      }
    } catch (NullPointerException ex) {
      System.out.println("Error al recibir datos: " + ex.getMessage());
    } catch (UnknownHostException ex) {
        System.out.println("Servidor no encontrado: " + ex.getMessage());
    } catch (IOException ex) {
      System.out.println("Error de entrada/salida: " + ex.getMessage());
    }
  }

  public void splitData(String data) {
    setNumeroTelefono(data.substring(0,data.indexOf(":")));
    setNip(data.substring(data.indexOf(":")+1,data.length()));
  }

  public static void main(String[] args) {
    if(args.length!=1)
     throw new IllegalArgumentException("No se ha ingresado el puerto");
    ServerCajero server = new ServerCajero(Integer.parseInt(args[0]));
    server.startServer();
  }
}
