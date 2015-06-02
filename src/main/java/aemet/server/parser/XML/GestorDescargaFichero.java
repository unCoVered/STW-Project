/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-31-15
 * Fecha modificacion: 04-05-15
 * Tiempo invertido: 30min
 */
package aemet.server.parser.XML;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

public class GestorDescargaFichero
{
	/**
	 * Dada una ruta, genera un objeto file con el fichero descargado
	 *
	 * @param fichero
	 * @param urlFichero
	 * @return
	 */
	public static File descargarFichero(File fichero, String urlFichero)
	{
		URL ruta = null;
		boolean error = false;

		try
		{
			ruta = new URL(urlFichero);

			FileUtils.copyURLToFile(ruta, fichero);

			return fichero;
		}
		catch (Exception e)
		{
			System.out.println("Error descargarFichero: " + urlFichero.substring(urlFichero.length() - 19));

			error = true;

			return fichero;
		}
	}
}
