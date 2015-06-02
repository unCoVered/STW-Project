<?php

/**
 * Devuelve el tiempo de la localidad con la extension dada
 */
function obtenerTiempo($extension, $localidad, $ipaddress, $accion, $fecha)
{
    try
    {
        $wsdl = "http://localhost:8080/axis/services/WebServicesServer?wsdl";
        $trace = true;
        $exceptions = true;

        $client = new SoapClient($wsdl, array('trace' => $trace, 'exceptions' => $exceptions));

        //Extension JSON
        if ($extension == "JSON")
        {
            //CREAMOS STRING JSON
            $stringJSON = "{\n string:".$localidad."\n}";

            //LLAMADA A JSON HANDLER
            $result = $client->JSONHandler($stringJSON);

            //PARSEO DE LA RESPUESTA DEL SERVER
            $json = json_decode($result);
            return $json->{'string'};
        }
        //Extension XML
        else if ($extension == "XML")
        {
            //CREAMOS STRING XML
            $stringXML = "<?xml version=\"1.0\" standalone=\"yes\"?>\n";
            $stringXML .= "<!DOCTYPE id [\n";
            $stringXML .= "<!ELEMENT id (#PCDATA)> \n";
            $stringXML .= "]>\n";
            $stringXML .= "<id>".$localidad."</id>";

            //LLAMADA A XML HANDLER
            $result = $client->XMLHandler($stringXML);

            //PARSEO DE LA RESPUESTA DEL SERVER

            $xmlEncoded = base64_decode($result, false);

            $xml = new SimpleXMLElement($xmlEncoded);
            $htmlEncoded = base64_decode($xml, false);

            //VALIDAMOS XML
            $dom = new DOMDocument;
            $dom->Load($xml);

            return $htmlEncoded;
        }
        //ERROR
        else
        {
            return "Error!";
        }
    }
    catch(Exception $e)
    {
        echo "Error conectando con el servicio AEMET";

        //ESTADISTICAS
        $estado = "error";
        guardarAccionUsuario($ipaddress, $accion, $extension, $estado, $fecha);
    }
}
?>