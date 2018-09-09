<?php
ob_start();
?>
<?php
$content

  $content = (modeloCorreo.html)
  require_once(dirname(__FILE__).'/../vendor/autoload.php');
  try
  {
      $html2pdf = new HTML2PDF('P', 'A4', 'es', true, 'UTF-8', 3);
      $html2pdf->pdf->SetDisplayMode('fullpage');
      $html2pdf->writeHTML($content, isset($_GET['vuehtml']));
      $html2pdf->Output('plantillas.pdf');
  }
  catch(HTML2PDF_exception $e) {
      echo $e;
      exit;
  }
