<!DOCTYPE html>
<html>
<head>
    <title>Estad&iacute;sticas - Informaci&oacute;n Zaragoza</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>

<header id="main-header">

    <a id="logo-header" href="index.php">
        <span class="site-name">VivaZaragoza</span>
        <span class="site-desc">La mejor informaci&oacute;n meteorol&oacute;gica y de bizis que podr&aacute;s encontrar</span>
    </a> <!-- / #logo-header -->

    <nav>
        <ul>
            <li>
                <a href="#">Estad&iacute;sticas</a>
                <ul>
                    <li><a href="./controlEstadisticoSOAP.php">V&iacute;a SOAP</a></li>
                    <li><a href="#">V&iacute;a REST</a></li>
                </ul>
            </li>
            <li><a href="manual.html">&#191;Problemas&#63;</a></li>
        </ul>
    </nav><!-- / nav -->

</header><!-- / #main-header -->

<div class="stats">
<?php
try{

    include 'controlEstadistico.php';

    $client = new SoapClient(null, array(
        'uri'=> 'http://localhost/',
        'location' => 'http://localhost/serverSOAP.php',
        'trace' => true,
        'exceptions' => true));

    echo $client->getEstadisticasSOAP();
}
catch(Exception $e)
{
    echo "Error SOAP";
    echo "<br>";
    echo $e;
}
?>
</div>

<footer id="main-footer">
    <p>&copy; 2015 Alejandro G&aacute;lvez y Sandra Campos</p>
</footer> <!-- / #main-footer -->

</body>
</html>
