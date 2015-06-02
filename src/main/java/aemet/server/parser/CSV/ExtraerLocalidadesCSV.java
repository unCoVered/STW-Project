/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 05-02-15
 * Fecha modificacion: 05-02-15
 * Tiempo invertido: .
 */
package aemet.server.parser.CSV;

import java.io.File;
import java.util.Scanner;
import java.util.SortedMap;

public class ExtraerLocalidadesCSV
{

	public static void readCSV(File fileCSV, SortedMap<Integer, String> localidades)
	{
		String id = "";
		String nombre = "";

		try
		{
			Scanner scanner = new Scanner(fileCSV);

			while (scanner.hasNextLine())
			{
				scanner.useDelimiter(",");
				id = scanner.next() + scanner.next().substring(0,3);
				if(id.substring(0,1).equals("\n"))
					id = id.substring(1, id.length());

				scanner.useDelimiter("\n");
				nombre = scanner.next();
				nombre = nombre.substring(1, nombre.length());

				localidades.put(Integer.parseInt(id), nombre);
			}

			scanner.close();
		}
		catch(Exception e)
		{
			System.out.println("Error leer CSV");
			System.out.println(id);
			System.out.println(nombre);
			e.printStackTrace();
		}
	}
}
