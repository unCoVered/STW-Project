package aemet.server.comunicacion.MensajeXML;

import org.apache.axis.encoding.Base64;

/**
 * Crea un mensaje XML que contiene el String devuelto
 * por el WebService solicitado
 */
public class XMLcreator
{
	public static String crearRespuestXML(String stringHTML)
	{
		String stringXML = new String();

		try
		{
			//String XML que contiene el codigo HTML
			stringXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
			stringXML += "<!DOCTYPE message SYSTEM \"/dtds/codigo.dtd\">";
			stringXML += "<codigo>\n" + Base64.encode(stringHTML.getBytes()) + "\n</codigo>";

			return Base64.encode(stringXML.getBytes());
		}
		catch(Exception e)
		{
			System.out.println("Error crear respuesta XML");
			e.printStackTrace();

			return stringXML;
		}
	}
}
