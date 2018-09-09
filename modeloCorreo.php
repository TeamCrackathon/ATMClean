<?php
require_once 'lib/dompdf/dompdf_config.inc.php';

$dompdf = new DOMPDF();
$dompdf->load_html( file_get_contents( 'file:///home/nicoracal/Escritorio/reto/ATMClean/modeloCorreo.html' ) );
$dompdf->render();
$dompdf->stream("comprobante.pdf");

<a href="output.php?t=pdf" target="_blank">Pdf</a>

?>
