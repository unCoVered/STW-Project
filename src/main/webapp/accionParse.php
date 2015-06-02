<?php
/**
 * Crea un objeto Parse
 */
require 'vendor/autoload.php';

use Parse\ParseClient;
use Parse\ParseObject;

ParseClient::initialize('3HK1lMBy58qDeMYh10OcyVfnmfg9925wiVi7DRw3', 'NbME567OjRN3hvBcYcvJdgTkWF3C84T5IYugPyI9',
    'RNWeZ06zKCbQA0tivKYq4lytpsGyZzDfaDaQFltT');


//Genera un nuevo objeto del tipo "AccionUsuario" y lo almacena en Parse
function guardarAccionUsuario($ip, $accion, $extension, $estado, $fechaAccion)
{
    $accionUsuario = ParseObject::create("AccionUsuario");
    $accionUsuario->set("ip", $ip);
    $accionUsuario->set("accion", $accion);
    $accionUsuario->set("extension", $extension);
    $accionUsuario->set("estado", $estado);
    $accionUsuario->set("fechaAccion", $fechaAccion);
    $accionUsuario->save();
}
?>