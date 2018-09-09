<?php
  require 'phpmailer/class.phpmailer.php';
  session_start();
  $token = $_POST['token'];
  if($_SESSION['token'] == $token){
	try{
      $nom = htmlspecialchars($_POST['nombre']);
      $pat = htmlspecialchars($_POST['appat']);
      $mat = htmlspecialchars($_POST['apmat']) ;
      $tel = htmlspecialchars($_POST['tel']);
      $email = htmlspecialchars($_POST['email']);
      $lvl = htmlspecialchars($_POST['radio']);
      $cuerpo = '
        <!DOCTYPE html>
        <html>
          <head>
            <meta charset="utf-8">
            <title>'.$lvl.'</title>
          </head>
          <body>
            <h1>Información sobre '. $lvl .'</h1>
            <p><strong>Nombre: </strong>'. $nom .'</p>
            <p><strong>Apellido Paterno: </strong>'. $pat .'</p>
            <p><strong>Apellido Materno: </strong>'. $mat .'</p>
            <p><strong>Telefono: </strong>'. $tel .'</p>
            <p><strong>Email: </strong>'. $email .'</p>
          </body>
        </html>
      ';
	}catch(Exception $e){
		header('Location: index.php');
	}
    // Crear una nueva  instancia de PHPMailer habilitando el tratamiento de excepciones
    $mail = new PHPMailer();
    $mail->IsHTML(true);
    $mail->CharSet = "UTF-8";
    $mail->Encoding = "quoted-printable"; 
    // Configuración cabeceras del mensaje
    $mail->setFrom("web@internacional.edu.mx","WEB");
    $mail->addAddress("giovanny.m.t11@gmail.com");
    $mail->addAddress("mercadotecnia@internacional.edu.mx");
    $mail->addAddress("informes@internacional.edu.mx");
    $mail->addAddress("saulromero94@outlook.com");
    $mail->addAddress("web@internacional.edu.mx");
    $mail->Subject = "Información sobre ". $lvl ."";
	  $mail->MsgHTML($cuerpo);
    // Enviar el correo
    $mail->Send();
	header('Location: index.php');
  }else{
	  //session_destroy();
      header('Location: index.php');
  }