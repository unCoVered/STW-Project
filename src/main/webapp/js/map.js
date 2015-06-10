/*
 Forzamos una descarga nada mas empezar para comprobar si hay conexion,
 en caso de no haberla, se muestra un mensaje de error.
 */
document.write('<img src="http://titulaciones.unizar.es/img/logo_unizar.jpg?' +
'" style="display:none" onerror="alert(&quot;Servicio no disponible. Por favor, intentelo mas tarde&quot;)" />');
/* Variables globales */
var myCenter = new google.maps.LatLng(41.650505, -0.889144);
var map;
var directionsDisplay;
var directionsService;
var markerArray = [];
var start;
function initialize() {
    var mapProp = {
        center: myCenter,
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
    var xmlhttp = new XMLHttpRequest();
    var url = "http://www.zaragoza.es/api/recurso/urbanismo-infraestructuras/estacion-bicicleta.json" +
        "?srsname=wgs84&start=0&rows=150&distance=1000";
    // Instantiate a directions service.
    directionsService = new google.maps.DirectionsService();

    /* Create autocomplete */
    var input = (document.getElementById('start'));
    var autocomplete = new google.maps.places.Autocomplete(input);
    autocomplete.bindTo('bounds', map);

    google.maps.event.addListener(autocomplete, 'place_changed', function () {
        var place = autocomplete.getPlace();
        console.log(place.geometry);
        if (!place.geometry) {
            start = "no_location";
        }
        else start = place.geometry.location;

    });

    // Create a renderer for directions and bind it to the map.
    var rendererOptions = {
        map: map
    }
    directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
    directionsDisplay.setMap(map);
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            console.log("bizis");
            var myArr = JSON.parse(xmlhttp.responseText);
            var lista = "<option value='inicial' selected='selected'>Selecciona estacion de bizi</option>";
            $.each(myArr.result, function (posicion, elemento) {
                var id = elemento.id;
                var calle = elemento.title;
                lista = lista.concat("<option value='" + id + "'>" + calle + "</option>");
                var bizis = elemento.bicisDisponibles;
                var anclajes = elemento.anclajesDisponibles;
                var datos = elemento.geometry;
                var icono = elemento.icon;
                $.each(datos, function (posicion1, elemento1) {
                    var coord = datos.coordinates;
                    var coord1 = coord[0];
                    var coord2 = coord[1];
                    var marker = new google.maps.Marker({
                        position: new google.maps.LatLng(coord2, coord1),
                        icon: icono
                    });
                    marker.setMap(map);
                    var infowindow = new google.maps.InfoWindow({
                        content: calle + "<br>Bizis disponibles: " + bizis + "<br>Anclajes disponibles: " + anclajes
                    });

                    google.maps.event.addListener(marker, 'click', function () {
                        infowindow.open(map, marker);
                    });
                });
            });
            document.getElementById("estacionesBizi").innerHTML = lista;
        }
    }

}
function calcRoute() {

    // First, remove any existing markers from the map.
    for (var i = 0; i < markerArray.length; i++) {
        markerArray[i].setMap(null);
    }

    // Now, clear the array itself.
    markerArray = [];

    // Retrieve the start and end locations and create
    // a DirectionsRequest using WALKING directions.
    var idEnd = document.getElementById('estacionesBizi').value;
    var end;
    if (start == "no_location") {
        alert("La localizacion no existe");
    }
    else {
        var xmlhttp = new XMLHttpRequest();
        var tipoRuta;
        if(document.formulario.ruta[0].checked){
            tipoRuta = google.maps.TravelMode.WALKING;
        }
        else tipoRuta = google.maps.TravelMode.DRIVING;
        if(document.formulario.extension[0].checked){
            /* La extension del fichero sera JSON */
            var url = "http://www.zaragoza.es/api/recurso/urbanismo-infraestructuras/" +
                "estacion-bicicleta/" + idEnd + ".json?srsname=wgs84";
            xmlhttp.open("GET", url, true);
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    var myArr = JSON.parse(xmlhttp.responseText);
                    var datos = myArr.geometry;
                    var coord = datos.coordinates;
                    end = new google.maps.LatLng(coord[1], coord[0]);
                    var request = {
                        origin: start,
                        destination: end,
                        travelMode: tipoRuta
                    };

                    // Route the directions and pass the response to a
                    // function to create markers for each step.
                    directionsService.route(request, function (response, status) {
                        if (status == google.maps.DirectionsStatus.OK) {
                            directionsDisplay.setDirections(response);
                        }
                    });
                }
            };
        }else{
            /* La extension del fichero sera XML */
            var url2 = "http://www.zaragoza.es/api/recurso/urbanismo-infraestructuras/" +
                "estacion-bicicleta/" + idEnd + ".xml?srsname=wgs84";
            xmlhttp.open("GET", url2, true);
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    xmlDoc = $.parseXML(xmlhttp.responseText);
                    $xml = $(xmlDoc);
                    $geometry = $xml.find("geometry");
                    $coordinates = $geometry.find("coordinates");
                    var coord = $coordinates.prop('outerHTML').slice(13,-14).split(",");
                    end = new google.maps.LatLng(coord[1], coord[0]);
                    var request = {
                        origin: start,
                        destination: end,
                        travelMode: tipoRuta
                    };

                    // Route the directions and pass the response to a
                    // function to create markers for each step.
                    directionsService.route(request, function (response, status) {
                        if (status == google.maps.DirectionsStatus.OK) {
                            directionsDisplay.setDirections(response);
                        }
                    });
                }
            }

        }

        xmlhttp.send();
    }


}

google.maps.event.addDomListener(window, 'load', initialize);

