package aemet.server.comunicacion.MensajeJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.PrintWriter;

/**
 * Crea un mensaje JSON que contiene el String devuelto
 * por el WebService solicitado
 */
public class JSONcreator
{
	public static String creaRespuestaJSON(String stringHTML)
	{
		String stringJSON = new String();

		try
		{
			//JSON builder

			EnvelopeJSON envelopeJSON = new EnvelopeJSON(stringHTML);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			stringJSON = gson.toJson(envelopeJSON);

			return stringJSON;
		}
		catch(Exception e)
		{
			System.out.println("Error crear respuesta JSON");
			e.printStackTrace();

			return stringJSON;
		}
	}
}
