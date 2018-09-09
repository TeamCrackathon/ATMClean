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
                    <meta charset="utf-8">
                    <title>BBVA Bancomer</title>
                </head>
                <body>
                    <div>Aquí debería estar el comprobante</div>
                </body>
                </html>
            ';
            // Crear una nueva  instancia de PHPMailer habilitando el tratamiento de excepciones
            $mail = new PHPMailer();
            $mail->IsHTML(true);
            $mail->CharSet = "UTF-8";
            $mail->Encoding = "quoted-printable"; 
            // Configuración cabeceras del mensaje
            $mail->setFrom("giovanny.m.t@ciencias.unam.mx","BBVA Bancomer");
            $mail->addAddress($correo);
            $mail->Subject = "Comprobante Digital";
            $mail->MsgHTML($cuerpo);
            // Enviar el correo
            $mail->Send();
            echo "Correo enviado";
            $num = var_dump($num);
            $pass = var_dump($pass);
            $consulta2 = "DELETE FROM NoUser WHERE Telefono='$num' and NIP='$pass'";
            mysqli_query($conn, $consulta2);
            if(mysqli_query($conn, $consulta2)){
                echo "Borrado satisfactoriamente";
            }else{
                echo "No se pudo borrar";
            }
            header("Location: InterfazWeb.php");
            break;
        }
    }
    mysqli_close($conn);
?>