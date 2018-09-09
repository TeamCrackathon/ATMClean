<?php
    require 'phpmailer/class.phpmailer.php';
    require 'phpmailer/class.smtp.php';
    try{
        $num = $_POST["num"];
        $pass = $_POST["contra"];
    }catch(Exception $e){
        header("Location: InterfazWeb.php");
    }
    $conn = mysqli_connect("localhost", "root", "", "Prueba");

    if(!$conn){
        die("Connection failed: ".mysqli_connect_error());
    }

    $consulta = "SELECT * FROM NoUser";
    $result = mysqli_query($conn, $consulta);
    while($fila = mysqli_fetch_array($result)){
        if($fila["Telefono"] == $num && $fila["NIP"] == $pass){
            try{
                $correo = $_POST["correo"];
            }catch(Exception $e){
                header("Location: InterfazWeb.php");
            }
            $cuerpo = '
            <!DOCTYPE html>
              <html>
                <head>
                  <title>BBVA Bancomer </title>
                  <meta charset="utf-8">
                   <meta name="viewport" content="width=device-width, initial-scale=1">
                   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
                   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
                   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
                   <link rel="stylesheet" href="css/estilo.css">


                  <link href="https://fonts.googleapis.com/css?family=Crimson+Text|Cutive|Hind+Si
                  liguri|Palanquin|Rokkitt|Tenor+Sans" rel="stylesheet">
              </head>

              <body>
                <div class="container">
                  <header>
                    <img src="images/1.png" class="img-fluid">
                  </header>
                </div>
                <div class="container">
                  <div class= "row">
                    <div class="col-sm-6">
                  <h1>Notificación</h1>
                  <div style="line-height:15px">
                    <section id="correo"><p> <b>De: Alertas Bancomer <<u>notificacionesdelbanco@serviciobancomer.com</u>></b></p>
                    <p>Fecha:09 de Septiembre de 2018, 13:00</p>
                    <p>Asunto: DOMICILIACIÓN</p>
                    <p>Para:<b><u>mireyaberenice@gmail.com</u></b></p></section>
                  </div>
                  <img src="images/bbvalogo.jpg">

                  <p><dt>Cajeros Automáticos: DOMICILIACIÓN</dt></p>

                  <div style="line-height:20px">
                    <p>Titular de la cuenta de retiro:</p>
                    <p>Cuenta retiro:</p>
                    <p>Tarjeta Domiciliada:</p>
                    <p>Pago Domiciliado:</p>
                    <p>Fecha y Hora de la operación:</p>
                    <p>Número de Cajero:</p>
                    <p>Folio de la operación:</p>
                  </div>

                  <br><p>Sí requieres más información comunícate a Línea Bancomer 01800-2262663
                     Larga distancia sin costo o al 5226-2663 desde la Ciudad de México.</p></br>

                  <br><p>En caso de no reconocer esta operación favor de comunicarse al 01800-1226626</p></br>
                  <b><br><p> BBVA Bancomer, S.A, Institución de Banca Múltiple, Grupo Financiero BBVA servicio
                      bancomer</p></br></b>
                  <p>_______________________________________</p>

                  <section id="promocional"><p>Envía Dinero móvil a cualquier parte de la República ¡SIN COSTO!
                     Más información en www.bancomer.com</p></section>

                  <section id="condiciones"><p>Este correo electrónico constituye una notificación de los términos en que
                     se realizó la operación, el único Comprobante oficial es el estado de cuenta
                     que emite BBVA Bancomer.</p></section>
                </div>

                <div class="col-sm-6">
                  <div style="line-height:10px">
                    <h2>Comprobante Digital</h2>
                    <p style="line-height:20px"><section id="correo"><b>De: Comprobante digital.Cajero automático Bancomer</b> <<b><u>equis@serviciobancomer.com</u></b>></p>
                    <p>Fecha:09 de Septiembre de 2018, 13:00</p>
                    <p>Asunto: Comprobante Digital-Alta de pago automático de Tarjeta de Crédito</p>
                    <p>Para: <b><u>mireyaberenice@gmail.com</u></b></p></section>
                  </div>

                  <img src="images/bbvalogo.jpg">


                    <section id="name"><br>NOMBRE EN MAYUSCULAS</b></br></p></section>
                    <section id="promocional"><p>Comprar/Contratar-Pago automático de Tarjeta de Crédito</p></section>
                    <p><dt>Comprobante digital-Cajero Automático</dt></p>
                    <section id="comprobante"><div style="line-height:20px">
                      <p>No. de Cajero:</p>
                      <p>Ubicación de Cajero: &nbsp;D001</p>
                      <p>Cuenta de retiro: &nbsp;swe23</p>
                      <p>Tarjeta domiciliada: &nbsp;12345678909876</p>
                      <p>Pago domiciliado:</p>
                      <p>Fecha de operación:</p>
                      <p>Hora de operación:</p>
                      <p>Folio de operación:</p>
                      <p>Autorización de la operación:</p></section>
                   <div class="container fluid">
                   <div class="row">

                  <div id="recuadro"class="col-sm-6" style="background-color:#e1fbff;"><p>En caso de no reconocer esta operación, comunícate al 01800 2262 663</p>

                  <p>Este correo electrónico constituye una notificación de los términos en que
                  se realizó la operación, el único comprobante oficial es el estado de Cuenta
                  que emite BBVA Bancomer</p></div>
                </div>
              </div>


                  <section id="datos"><br><p>Alertas Bancomer te protege, te mantiene informado de la actividad de tus
                  cuentas, conoce más en <u><b>wwww.bancomer.com</b></u></p></br></section>


              </body>
              </html>
            ';
            // Crear una nueva  instancia de PHPMailer habilitando el tratamiento de excepciones
            $mail = new PHPMailer();
            $mail->IsHTML(true);
            $mail->IsSMTP();
            $mail->Host = "smtp.gmail.com";
            $mail->SMTPAuth = true;
            $mail->Username = "crackathonservices@gmail.com";
            $mail->Password = "computologos98";
            $mail->SMTPSecure = "tls";
            $mail->Port = 587;
            $mail->CharSet = "UTF-8";
            $mail->Encoding = "quoted-printable";
            // Configuración cabeceras del mensaje
            $mail->setFrom("crackathonservices@gmail.com","BBVA Bancomer");
            $mail->addAddress($correo);
            $mail->Subject = "Comprobante Digital";
            $mail->MsgHTML($cuerpo);
            // Enviar el correo
            if(!$mail->Send()){
              echo "<br><a href='InterfazWeb.php'>Regresar</a><br>";
            }
            try{
              $consulta2 = "DELETE FROM NoUser WHERE Telefono='".$num."' and NIP='".$pass."'";
              $conn->query($consulta2);
              mysqli_close($conn);
            }catch(PDOException $e){
              echo $consulta2 . "<br>" . $e->getMessage();
            }
            header("Location: sucess.php");
        }
    }
    mysqli_close($conn);
    header("Location: failure.php");
?>
