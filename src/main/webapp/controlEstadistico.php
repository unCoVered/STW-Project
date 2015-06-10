<!DOCTYPE html>
<html>
<head>
    <title>Estad&iacute;sticas - Informaci&oacute;n Zaragoza</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="/js/chart_js/Chart.js"></script>
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

<h1><br><br>Control estad&iacute;stico</h1>

<div class="stats">
<?php
include 'graficas.php';

require 'api/Slim/Slim.php';
require 'vendor/autoload.php';

use Parse\ParseClient;
use Parse\ParseQuery;

date_default_timezone_set('UTC');


    //INICIALIZAMOS SLIM
    \Slim\Slim::registerAutoloader();

    //INICIALIZAMOS PARSE
    ParseClient::initialize('3HK1lMBy58qDeMYh10OcyVfnmfg9925wiVi7DRw3', 'NbME567OjRN3hvBcYcvJdgTkWF3C84T5IYugPyI9',
        'RNWeZ06zKCbQA0tivKYq4lytpsGyZzDfaDaQFltT');

    //CREAMOS OBJETO SLIM
    $app = new \Slim\Slim();

    //DEFINIMOS REGLAS
    $app->get('/','getEstadisticas');
    $app->get('/estadisticas','getEstadisticas');
    $app->get('/numeroUsos','getNumeroUsos');
    $app->get('/petMeteorologia', 'getPeticionesMeteorologia');
    $app->get('/peticionesMeteorologia', 'getPeticionesMeteorologia');
    $app->get('/peticionesMeteo', 'getPeticionesMeteorologia');
    $app->get('/petBizi', 'getPeticionesBizi');
    $app->get('/peticionesBizi', 'getPeticionesBizi');
    $app->get('/peticionesBici', 'getPeticionesBizi');
    $app->get('/comparacion', 'getComparacion');
    $app->get('/extension', 'getExtension');

    //EJECUTAMOS SLIM
    $app->run();

    //FUNCIONES A EJECUTAR DEPENDIENDO DE LA REGLA

    function getEstadisticas()
    {
        try
        {
            //Devuelve todas las estadisticas

            echo getNumeroUsos();
            echo getPeticionesMeteorologia();
            echo getPeticionesBizi();
            echo getComparacion();
            echo getExtension();
        }
        catch (Exception $e)
        {
            echo "<h2>Error de conexion</h2>";
        }
    }

    function getNumeroUsos()
    {
        try
        {
            // Evolucion diaria del numero de usos de la web

            //Dia actual y pasado
            $dia_pasado = strtotime('-7 days');
            $dia_actual = time();

            $dias = array();
            $usos = array();

            //Mientras el dia pasado no sea el actual
            while($dia_pasado < $dia_actual){
                $query = new ParseQuery("AccionUsuario");
                $query->equalTo("fechaAccion", date("y-m-d", $dia_pasado));
                $results = $query->find();
                $accionesDia = count($results);

                $dias[] = date("y-m-d", $dia_pasado);
                $usos[] = $accionesDia;

                $dia_pasado = strtotime('+1 day', $dia_pasado);
            }

            //Obtenemos el dia actual
            $query2 = new ParseQuery("AccionUsuario");
            $query2->equalTo("fechaAccion", date("y-m-d", $dia_actual));
            $results2 = $query2->find();
            $accionesDia = count($results2);

            $dias[] = date("y-m-d", $dia_actual);
            $usos[] = $accionesDia;

            $string = "<h2>Numero de usos de la pagina web";
            $string .= "<br>";
            $string .= getGraficaUsosDia($dias, $usos);

            return $string;
        }
        catch (Exception $e)
        {
            return "<h2>Error de conexion</h2>";
        }

    }

    function getPeticionesMeteorologia()
    {
        try
        {
            //Devuelve la evolucion del numero de peticiones al servicio de meteorologia

            //Dia actual y pasado
            $dia_pasado = strtotime('-7 days');
            $dia_actual = time();

            $dias = array();
            $usos = array();

            //Mientras el dia pasado no sea el actual
            while($dia_pasado < $dia_actual){
                $query1 = new ParseQuery("AccionUsuario");
                $query1->equalTo("accion", "prevision_metereologica");

                $query = ParseQuery::orQueries([$query1]);
                $query->equalTo("fechaAccion", date("y-m-d", $dia_pasado));
                $results = $query->find();
                $accionesMeteorologia = count($results);

                $dias[] = date("y-m-d", $dia_pasado);
                $usos[] = $accionesMeteorologia;

                $dia_pasado = strtotime('+1 day', $dia_pasado);
            }
            //Obtenemos el dia actual
            $query1 = new ParseQuery("AccionUsuario");
            $query1->equalTo("accion", "prevision_metereologica");

            $query = ParseQuery::orQueries([$query1]);
            $query->equalTo("fechaAccion", date("y-m-d", $dia_actual));
            $results = $query->find();
            $accionesMeteorologia = count($results);

            $dias[] = date("y-m-d", $dia_actual);;
            $usos[] = $accionesMeteorologia;

            $string = "<h2>Numero de usos del servicio de Meteorolog&iacute;a -- Bici";
            $string .= "<br>";
            $string .= getGraficaEvolucionMeteorologia($dias, $usos);

            return $string;
        }
        catch (Exception $e)
        {
            return "<h2>Error de conexion</h2>";
        }
    }

    function getPeticionesBizi()
    {
        try
        {
            //Devuelve la evolucion del numero de peticiones al servicio de bici

            //Dia actual y pasado
            $dia_pasado = strtotime('-7 days');
            $dia_actual = time();

            $dias = array();
            $usos = array();

            //Mientras el dia pasado no sea el actual
            while($dia_pasado < $dia_actual){
                $query = new ParseQuery("AccionUsuario");
                $query->equalTo("fechaAccion", date("y-m-d", $dia_pasado));
                $query->equalTo("accion", "servicio_bici");
                $results = $query->find();
                $accionesBici = count($results);

                $dias[] = date("y-m-d", $dia_pasado);
                $usos[] = $accionesBici;

                $dia_pasado = strtotime('+1 day', $dia_pasado);
            }

            //Obtenemos el dia actual
            $query = new ParseQuery("AccionUsuario");
            $query->equalTo("fechaAccion", date("y-m-d", $dia_actual));
            $query->equalTo("accion", "servicio_bici");
            $results = $query->find();
            $accionesBici = count($results);

            $dias[] = date("y-m-d", $dia_actual);;
            $usos[] = $accionesBici;

            $string = getGraficaEvolucionBici($dias, $usos);

            return $string;
        }
        catch (Exception $e)
        {
            return "<h2>Error de conexion</h2>";
        }
    }

    function getComparacion()
    {
        try
        {
            //Compara el numero de peticiones totales del servicio de meteorologia y del servicio de bici

            $query = new ParseQuery("AccionUsuario");
            $query->equalTo("accion", "prevision_metereologica");
            $results = $query->find();

            $numMeteo = count($results);

            $query = new ParseQuery("AccionUsuario");
            $query->equalTo("accion", "servicio_bici");
            $results = $query->find();

            $numBici = count($results);

            $string =  "<h2>Comparaci&oacute;n del uso de los servicios";
            $string .=  "<br>";
            $string .=  getGraficaComparacionServicios($numMeteo, $numBici);

            return $string;
        }
        catch (Exception $e)
        {
            return "<h2>Error de conexion</h2>";
        }
    }

    function getExtension()
    {
        try
        {
            //Devuelve graficas con el porcentaje de JSONs/XMLs utilizados

            $query = new ParseQuery("AccionUsuario");
            $results = $query->find();

            $total = count($results);
            $numXML = 0;
            $numJSON = 0;

            for($i = 0; $i < $total; $i++) {
                $object = $results[$i];

                if($object->get('extension') == "XML")
                    $numXML += 1;
                elseif($object->get('extension') == "JSON")
                    $numJSON += 1;
            }

            $string =  "<h2>Porcentaje de usos de la extensi&oacute;n XML -- JSON";
            $string .=  "<br>";
            $string .=  getGraficaPorcentajeXML($total, $numXML);
            $string .=  getGraficaPorcentajeJSON($total, $numJSON);

            return $string;
        }
        catch (Exception $e)
        {
            return "<h2>Error de conexion</h2>";
        }
    }
?>
</div>

<footer id="main-footer">
    <p>&copy; 2015 Alejandro G&aacute;lvez y Sandra Campos</p>
</footer> <!-- / #main-footer -->
</body>
</html>
