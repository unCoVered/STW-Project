/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-04-15
 * Fecha modificacion: 03-31-15
 * Tiempo invertido: 30m
 */
package aemet.server.parser.datos;

import java.util.ArrayList;
import java.util.List;

public class DiaUtils
{

	private static final String periodo0024 = "00-24";
	private static final String periodo0012 = "00-12";
	private static final String periodo1224 = "12-24";
	private static final String periodo0006 = "00-06";
	private static final String periodo1218 = "12-18";
	private static final String periodo1824 = "18-24";

	private static final String hora06 = "06";
	private static final String hora12 = "12";
	private static final String hora18 = "18";
	private static final String hora24 = "24";

	private static List<String> periodos = new ArrayList<String>();
	private static List<String> horas = new ArrayList<String>();
	private static List<String> periodosParciales = new ArrayList<String>();
	private static List<String> periodosVacios = new ArrayList<String>();
	private static List<String> horasVacias = new ArrayList<String>();

	/**
	 * Devuelve una lista con todos los periodos de tiempo que mostrara el dia
	 * actual al generar el fichero HTML
	 *
	 * @return
	 */
	public static List<String> rellenarPeriodos()
	{
		if (periodos.size() != 6)
		{
			periodos.add(periodo0024);
			periodos.add(periodo0012);
			periodos.add(periodo1224);
			periodos.add(periodo0006);
			periodos.add(periodo1218);
			periodos.add(periodo1824);
		}

		return periodos;
	}

	/**
	 * Devuelve una lista con todas las horas que mostrara el dia actual al
	 * generar el fichero HTML
	 *
	 * @return
	 */
	public static List<String> rellenarHoras()
	{
		if (horas.size() != 4)
		{
			horas.add(hora06);
			horas.add(hora12);
			horas.add(hora18);
			horas.add(hora24);
		}
		return horas;
	}

	/**
	 * Devuelve una lista con los 3 periodos del dias mas amplios
	 *
	 * @return
	 */
	public static List<String> rellenarPeriodosParciales()
	{
		periodosParciales.add(periodo0024);
		periodosParciales.add(periodo0012);
		periodosParciales.add(periodo1224);

		return periodosParciales;
	}

	/**
	 * Devuelve una lista de periodos vacia para los ultimos dos dias mostrados
	 * por la pagina HTML
	 *
	 * @return
	 */
	public static List<String> rellenarPeriodosVacios()
	{
		periodosVacios.add("");

		return periodosVacios;
	}

	/**
	 * Devuelve una lista de horas vacia para los ultimos tres dias mostrados
	 * por la pagina HTML
	 */
	public static List<String> rellenarHorasVacias()
	{
		horasVacias.add("");

		return horasVacias;
	}

	/**
	 * Devuelve la lista de periodos
	 *
	 * @return
	 */
	public static List<String> getPeriodos()
	{
		return periodos;
	}

	/**
	 * Devuelve la lista de horas
	 *
	 * @return
	 */
	public static List<String> getHoras()
	{
		return horas;
	}

}
