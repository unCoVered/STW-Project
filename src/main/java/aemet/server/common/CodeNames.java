/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-04-15
 * Fecha modificacion:
 * Tiempo invertido:
 */
package aemet.server.common;

/**
 * Strings utilizados a lo largo del programa
 */
public class CodeNames
{
	/**
	 * STRINGS utilizados en ProcesadorFicheroXML
	 */
	
	//Campos de un dia
	public static String PROB_PRECIPITACION = "prob_precipitacion";
	public static String COTA_NIEVE_PROV = "cota_nieve_prov";
	public static String ESTADO_CIELO = "estado_cielo";
	public static String VIENTO = "viento";
	public static String RACHA_MAX = "racha_max";
	public static String TEMPERATURA = "temperatura";
	public static String SENS_TERMICA = "sens_termica";
	public static String HUMEDAD_RELATIVA = "humedad_relativa";
	public static String UV_MAX = "uv_max";

	//Atributos y etiquetas
	public static String ROOT_ATR = "root";
	public static String DIA_ATR = "dia";
	public static String PREDICCION_STRING = "prediccion";
	public static String FECHA_ATR = "fecha";
	public static String PERIODO_ATR = "periodo";
	public static String HORA_ATR = "hora";
	public static String DESCRIPCION_ATR = "descripcion";
	public static String MAXIMA_ATR = "maxima";
	public static String MINIMA_ATR = "minima";
	public static String DATO_ATR = "dato";
	public static String DIRECCION_ATR = "direccion";
	public static String VELOCIDAD_ATR = "velocidad";
	
	//Error messages
	public static String NO_IMPLEMENTADO_ERROR = "No implementado";
	public static String ERROR = "Error";
	
	//////////////////////////////////////////////////////////////////////////

	//Botones, extensiones de ficheros, etc.
	public static String HTML_BUTTON = "Obtener HTML";
	public static String JSON_BUTTON = "Obtener JSON";
	public static String LOCALIDAD_STRING = "localidad_";
	public static String XML_EXTENSION = ".xml";
	public static String HTML_EXTENSION = ".html";
	public static String JSON_EXTENSION = ".json";

}
