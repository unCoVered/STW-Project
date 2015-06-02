package aemet.server;

import aemet.server.common.CodeNames;
import aemet.server.comunicacion.MensajeJSON.JSONcreator;
import aemet.server.comunicacion.MensajeJSON.JSONparser;
import aemet.server.comunicacion.MensajeXML.XMLcreator;
import aemet.server.comunicacion.MensajeXML.XMLparser;
import aemet.server.generator.HTML.CrearPaginaHTML;
import aemet.server.generator.HTML.EncapsulaDias;
import aemet.server.parser.XML.ExtraerDiasLista;
import aemet.server.parser.XML.GestorDescargaFichero;
import aemet.server.parser.XML.ListaDia;
import aemet.server.parser.XML.ProcesadorFicheroXML;

import java.io.File;

public class WebServicesServer
{

	private String RUTA_RESOURCES = "";

	public WebServicesServer(){}

	/**
	 * - Llama a metodo parser
	 * - Obtiene la informacion metereologica de la id indicada en el string parseado
	 * - Genera String con el codigo HTML de la prediccion deseada
	 * - Envia la respuesta al frontend
	 *
	 * @param codigoFichero
	 * @return
	 */
	public String XMLHandler(String codigoFichero)
	{
		String respuestaXML = new String();

		try
		{
			//Parseamos codigo XML recibido desde el cliente
			String localidad = XMLparser.leeMensajeXMLRecibido(codigoFichero);

			// Descargamos informacion del tiempo de esa localidad y obtenemos codigo HTML
			String codigoHTML = descargarInfoTiempo(localidad);

			// Creamos JSON con el codigo HTML
			respuestaXML = XMLcreator.crearRespuestXML(codigoHTML);

			return respuestaXML;
		}
		catch(Exception e)
		{
			e.printStackTrace();

			return respuestaXML;
		}
	}

	/**
	 * - Llama a metodo parser
	 * - Obtiene la informacion metereologica de la id indicada en el string parseado
	 * - Genera String con el codigo HTML de la prediccion deseada
	 * - Envia la respuesta al frontend
	 *
	 * @param codigoFichero
	 * @return
	 */
	public String JSONHandler(String codigoFichero)
	{
		String respuestaJSON = new String();

		try
		{
			// Parseamos codigo JSON recibido desde el cliente
			String localidad = JSONparser.leeMensajeJSONRecibido(codigoFichero);

			// Descargamos informacion del tiempo de esa localidad y obtenemos codigo HTML
			String codigoHTML = descargarInfoTiempo(localidad);

			// Creamos JSON con el codigo HTML
			respuestaJSON = JSONcreator.creaRespuestaJSON(codigoHTML);

			return respuestaJSON;
		}
		catch(Exception e)
		{
			e.printStackTrace();

			return respuestaJSON;
		}
	}

	/**
	 * Descarga la informacion metereologica del municipio deseado
	 * y llama al metodo encargado de generar el codigo HTML
	 *
	 * @param idlocalidadRecibo
	 * @return
	 */
	public String descargarInfoTiempo(String idlocalidadRecibo)
	{
		File f = null;
		String codigoHTML = new String();

		try
		{
			f = File.createTempFile("tmp", ".xml", null);
			RUTA_RESOURCES = f.getAbsolutePath();

			Integer idlocalidad = Integer.parseInt(idlocalidadRecibo);

			GestorDescargaFichero.descargarFichero(f,
					"http://www.aemet.es/xml/municipios/localidad_" + idlocalidad + ".xml");

			codigoHTML = this.generarHTML();

			return codigoHTML;
		}
		catch(Exception e)
		{
			e.printStackTrace();

			return codigoHTML;
		}
	}

	/**
	 * Dada una localidad, genera el codigo HTML para mostrar
	 * su informacion metereologica
	 *
	 * @return
	 */
	public String generarHTML()
	{
		ListaDia listaDia = new ListaDia();

		ProcesadorFicheroXML.leePrediccion(listaDia, RUTA_RESOURCES);

		EncapsulaDias diasEncapsulados = new EncapsulaDias(ExtraerDiasLista.extraerDias(listaDia));

		String stringHTML = CrearPaginaHTML.escribeDatosDia(diasEncapsulados);

		return stringHTML;
	}
}
