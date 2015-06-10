<?php

class MyAPI {
    function hello() {
        return "Hello";
    }
}

class estadisticas {
    function getEstadisticasSOAP()
    {
        include 'controlEstadistico.php';

        $string = getNumeroUsos();
        $string .= getPeticionesMeteorologia();
        $string .= getPeticionesBizi();
        $string .= getComparacion();
        $string .= getExtension();

        return $string;
    }
}

$options=array('uri'=>'http://localhost/');
$server = new SoapServer(null,$options);
$server->setClass('MyAPI');
$server->setClass('estadisticas');
$server->handle();

echo "Server ON";
?>
