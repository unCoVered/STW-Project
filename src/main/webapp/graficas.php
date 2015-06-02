<?php
/**
 * Clase encargada de la gestion de graficas
 */

//Devuelve la evolucion del numero de usos de los servicios de la web en la ultima semana
function getGraficaUsosDia($arrayDias, $arrayUsos)
{
    //LINE    echo "<h2>Numero de usos del servicio de bici";
    echo "<br>";

    $string = "
        <canvas id=\"graficaUsos\" width=\"750\" height=\"400\"></canvas>
        <script>
            var ctx = document.getElementById(\"graficaUsos\").getContext(\"2d\");
            var data = {
                labels: [\"$arrayDias[0]\",\"$arrayDias[1]\",\"$arrayDias[2]\",\"$arrayDias[3]\",\"$arrayDias[4]\",\"$arrayDias[5]\",\"$arrayDias[6]\",\"$arrayDias[7]\"],
                datasets: [
                    {
                        label: \"My First dataset\",
                        fillColor: \"rgba(220,220,220,0.2)\",
                        strokeColor: \"rgba(220,220,220,1)\",
                        pointColor: \"rgba(220,220,220,1)\",
                        pointStrokeColor: \"#fff\",
                        pointHighlightFill: \"#fff\",
                        pointHighlightStroke: \"rgba(220,220,220,1)\",
                        data: [$arrayUsos[0],$arrayUsos[1],$arrayUsos[2],$arrayUsos[3],$arrayUsos[4],$arrayUsos[5],$arrayUsos[6],$arrayUsos[7]]
                    }
                ]
            };
            var myLineChart = new Chart(ctx).Line(data);
        </script>
    ";

    return $string;
}

//Devuelve la evolucion del numero de usos del servicio de meteorologia en la ultima semana
function getGraficaEvolucionMeteorologia($arrayDias, $usosMeteo)
{
    //BARRAS

    $string = "
        <canvas id=\"graficaEvoMeteo\" width=\"400\" height=\"400\"></canvas>
        <script>
            var ctx = document.getElementById(\"graficaEvoMeteo\").getContext(\"2d\");
            var data = {
                labels: [\"$arrayDias[0]\",\"$arrayDias[1]\",\"$arrayDias[2]\",\"$arrayDias[3]\",\"$arrayDias[4]\",\"$arrayDias[5]\",\"$arrayDias[6]\",\"$arrayDias[7]\"],
                datasets: [
                    {
                        label: \"Servicio Meteorología\",
                        fillColor: \"rgba(220,220,220,0.5)\",
                        strokeColor: \"rgba(220,220,220,0.8)\",
                        highlightFill: \"rgba(220,220,220,0.75)\",
                        highlightStroke: \"rgba(220,220,220,1)\",
                        data: [$usosMeteo[0],$usosMeteo[1],$usosMeteo[2],$usosMeteo[3],$usosMeteo[4],$usosMeteo[5],$usosMeteo[6],$usosMeteo[7]]
                    }
                ]
            };

            new Chart(ctx).Bar(data);
        </script>
    ";

    return $string;
}

//Devuelve la evolucion del numero de usos del servicio de bici en la ultima semana
function getGraficaEvolucionBici($arrayDias, $usosBici)
{
    //BARRAS

    $string = "
        <canvas id=\"graficaEvoBici\" width=\"400\" height=\"400\"></canvas>
        <script>
            var ctx = document.getElementById(\"graficaEvoBici\").getContext(\"2d\");
            var data = {
                labels: [\"$arrayDias[0]\",\"$arrayDias[1]\",\"$arrayDias[2]\",\"$arrayDias[3]\",\"$arrayDias[4]\",\"$arrayDias[5]\",\"$arrayDias[6]\",\"$arrayDias[7]\"],
                datasets: [
                    {
                        label: \"Servicio Meteorología\",
                        fillColor: \"rgba(220,220,220,0.5)\",
                        strokeColor: \"rgba(220,220,220,0.8)\",
                        highlightFill: \"rgba(220,220,220,0.75)\",
                        highlightStroke: \"rgba(220,220,220,1)\",
                        data: [$usosBici[0],$usosBici[1],$usosBici[2],$usosBici[3],$usosBici[4],$usosBici[5],$usosBici[6],$usosBici[7]]
                    }
                ]
            };

            new Chart(ctx).Bar(data);
        </script>
    ";

    return $string;
}

////Devuelve la grafica con la comparacion entre XML y JSON
function getGraficaComparacionServicios($usosMeteo, $usosBici)
{
    //PIE CHART

    $string = "
        <canvas id=\"graficaComparacion\" width=\400\" height=\"400\"></canvas>

        <script>
            var ctx = document.getElementById(\"graficaComparacion\").getContext(\"2d\");
            var data = [
                {
                    value: $usosMeteo,
                    color:\"#F7464A\",
                    highlight: \"#FF5A5E\",
                    label: \"Usos servicio meteorología\"
                },
                {
                    value: $usosBici,
                    color: \"#46BFBD\",
                    highlight: \"#5AD3D1\",
                    label: \"Usos servicio Bici\"
                },
            ]
            new Chart(ctx).Pie(data);
        </script>
    ";

    return $string;
}

//Devuelve la grafica con el numero de XML respecto al total
function getGraficaPorcentajeXML($total, $numXML)
{
    //PIE CHART

    $string = "
        <canvas id=\"graficaPorcenXML\" width=\400\" height=\"400\"></canvas>

        <script>
            var ctx = document.getElementById(\"graficaPorcenXML\").getContext(\"2d\");
            var data = [
                {
                    value: $total,
                    color:\"#F7464A\",
                    highlight: \"#FF5A5E\",
                    label: \"Acciones totales\"
                },
                {
                    value: $numXML,
                    color: \"#46BFBD\",
                    highlight: \"#5AD3D1\",
                    label: \"Extension XML\"
                },
            ]
            new Chart(ctx).Pie(data);
        </script>
    ";

    return $string;
}

//Devuelve la grafica con el numero de JSON respecto al total
function getGraficaPorcentajeJSON($total, $numJSON)
{
    //PIE CHART

    $string = "
        <canvas id=\"graficaPorcenJSON\" width=\400\" height=\"400\"></canvas>

        <script>
            var ctx = document.getElementById(\"graficaPorcenJSON\").getContext(\"2d\");
            var data = [
                {
                    value: $total,
                    color:\"#F7464A\",
                    highlight: \"#FF5A5E\",
                    label: \"Acciones totales\"
                },
                {
                    value: $numJSON,
                    color: \"#46BFBD\",
                    highlight: \"#5AD3D1\",
                    label: \"Extension JSON\"
                },
            ]
            new Chart(ctx).Pie(data);
        </script>
    ";

    return $string;
}
?>