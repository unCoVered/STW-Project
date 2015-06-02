package aemet.server.comunicacion.MensajeJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Si el mensaje recibido es un JSON se llama a esta clase que:
 * - Parsea el contenido del mensaje
 * - Crea un objeto String con la localidad
 */
public class JSONparser
{
	public static String leeMensajeJSONRecibido(String stringRecibido)
	{
		String localidad = new String();

		try
		{
			Gson gson = new GsonBuilder().setPrettyPrinting().create();


			EnvelopeJSON loc = gson.fromJson(stringRecibido, EnvelopeJSON.class);

			localidad = loc.getString();

			return localidad;
		}
		catch(Exception e)
		{
			System.out.println("Excepcion en JSONParser");
			e.printStackTrace();
			return localidad;
		}
	}
}
