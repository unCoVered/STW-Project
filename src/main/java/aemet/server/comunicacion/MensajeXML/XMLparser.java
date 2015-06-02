package aemet.server.comunicacion.MensajeXML;

import aemet.server.common.CodeNames;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.StringReader;
import java.lang.annotation.Documented;
import java.util.List;

/**
 * Si el mensaje recibido es un XML se llama a esta clase que:
 * - Parsea el contenido del mensaje
 * - Crea un objeto MensajeRecibido con los datos extraidos
 */
public class XMLparser
{
	public static String leeMensajeXMLRecibido(String stringRecibido)
	{
		String localidad = new String();

		try
		{
			SAXBuilder constructor = new SAXBuilder(true);
			Document doc = constructor.build(new StringReader(stringRecibido));

			//Raiz -> Deberia ser la localidad
			Element raiz = doc.getRootElement();

			if(raiz.getName().equals("id"))
			{
				localidad = raiz.getText();
			}

			return localidad;
		}
		catch(Exception e)
		{
			System.out.println("Error en XMLParser");
			e.printStackTrace();

			return localidad;
		}
	}
}
