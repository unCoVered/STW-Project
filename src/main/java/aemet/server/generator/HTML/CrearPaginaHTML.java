/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-31-15
 * Fecha modificacion: 04-06-15
 * Tiempo invertido: 30min
 */
package aemet.server.generator.HTML;

import aemet.server.parser.datos.Dia;

/**
 * Clase encargada de crear una pagina HTML.
 */
public class CrearPaginaHTML
{
	/**
	 * Dada una lista de Dias, inctroduce los datos en un fichero HTML
	 * de forma que se muestren los datos al ejecutar la pagina en un
	 * navegador web
	 *
	 * @param diasPrediccion
	 */
	public static String escribeDatosDia(EncapsulaDias diasPrediccion)
	{
		String stringOut = new String();

		try
		{
			//Cuerpo
			stringOut += printBody(diasPrediccion);

			return stringOut;
		} catch (Exception ex)
		{
			System.out.println("Excepcion en leeDias de CrearPaginaHTML");
			ex.printStackTrace();

			return stringOut;
		}
	}

	/**
	 * Escribe la cabecera del html
	 */
	private static String printCabecera()
	{
		String outString = "<!DOCTYPE html> \n"
				+ "<html lang=\"es\">\n"
				+ "<!-- Cabecera: Nombre de la pagina y llamada a CSS --> \n"
				+ "<head>\n"
				+ "\t<title>Tiempo Zaragoza</title>\n"
				+ "</head>\n";

		return outString;
	}

	/**
	 * Escribe el cuerpo del html
	 */
	private static String printBody(EncapsulaDias diasPrediccion)
	{
		String outString = new String();

		outString += printDatos(outString, diasPrediccion);

		return outString;
	}


	/**
	 * Escribe las etiquetas de cierre del html
	 */
	private static String printCierreFichero()
	{
		String outString = "</body>\n";
		outString += "</html>";

		return outString;
	}

	/**
	 * Escribe los datos en el html
	 */
	private static String printDatos(String outString, EncapsulaDias diasPrediccion)
	{
		//Sacamos los dias
		Dia diaPrediccion1 = diasPrediccion.getListaDias().get(0);

		try
		{
			//Inicio tabla
			outString = outString + "\t<table border=1 align=\"center\" cellspacing=\"0\"  style=\"width: 75%\" class=\"tabla\">\n";

			//Fila fecha y periodos
			outString = outString + "\t\t<tr>\n";
			outString = outString + "\t\t\t<th rowspan=2 align=\"center\" class=\"cabecera\"><h5>Fecha</h5></th>\n";
			outString = outString + "\t\t\t<td colspan=3 align=\"center\" class=\"cabecera\"><font size=\"2\"> Prediccion del dia " + diaPrediccion1.getFecha().toString().substring(4, 10) + "</font></td>\n";

			outString = outString + "\t\t</tr>\n";

			outString = outString + "\t\t<tr>\n";
			outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">6-12</font></td>\n";
			outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">12-18</font></td>\n";
			outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">18-24</font></td>\n";

			outString = outString + "\t\t</tr>\n";

			//Fila Estado Cielo
			outString = outString + "\t\t<tr>\n";
			outString = outString + "\t\t\t<th class=\"cabecera\"><h5>Estado cielo</h5></th>\n";
			switch(diaPrediccion1.getEstadoCielo().get("00-06")[0].toLowerCase())
			{
				case "despejado":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/11.gif\"></font></td>\n";
					break;
				}

				case "poco nuboso":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/12.gif\"></font></td>\n";
					break;
				}

				case "intervalos nubosos":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/13.gif\"></font></td>\n";
					break;
				}
				case "nuboso":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/14.gif\"></font></td>\n";
					break;
				}
				case "muy nuboso":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/15.gif\"></font></td>\n";
					break;
				}
				case "cubierto":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/15.gif\"></font></td>\n";
					break;
				}
				case "intervalos nubosos con lluvia escasa":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/43.gif\"></font></td>\n";
					break;
				}
				case "nuboso con lluvia escasa":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/44.gif\"></font></td>\n";
					break;
				}
				case "muy nuboso con lluvia escasa":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes_gcd/_iconos_localidades/45n.gif\"></font></td>\n";
					break;
				}
				case "cubierto con lluvia escasa":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes_gcd/_iconos_localidades/46n.gif\"></font></td>\n";
					break;
				}
				case "intervalos nubosos con lluvia":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/23.gif\"></font></td>\n";
					break;
				}
				case "nuboso con lluvia":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/24.gif\"></font></td>\n";
					break;
				}
				case "muy nuboso con lluvia":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes_gcd/_iconos_localidades/25.GIF\"></font></td>\n";
					break;
				}
				case "cubierto con lluvia":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes_gcd/_iconos_localidades/26.GIF\"></font></td>\n";
					break;
				}
				default:
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">" +  diaPrediccion1.getEstadoCielo().get("00-06")[0] + "</font></td>\n";
					break;
				}
			}
			switch(diaPrediccion1.getEstadoCielo().get("12-18")[0].toLowerCase())
			{
				case "despejado":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/11.gif\"></font></td>\n";
					break;
				}

				case "poco nuboso":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/12.gif\"></font></td>\n";
					break;
				}

				case "intervalos nubosos":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/13.gif\"></font></td>\n";
					break;
				}
				case "nuboso":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/14.gif\"></font></td>\n";
					break;
				}
				case "muy nuboso":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/15.gif\"></font></td>\n";
					break;
				}
				case "cubierto":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/15.gif\"></font></td>\n";
					break;
				}
				case "intervalos nubosos con lluvia escasa":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/43.gif\"></font></td>\n";
					break;
				}
				case "nuboso con lluvia escasa":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/44.gif\"></font></td>\n";
					break;
				}
				case "muy nuboso con lluvia escasa":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes_gcd/_iconos_localidades/45n.gif\"></font></td>\n";
					break;
				}
				case "cubierto con lluvia escasa":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes_gcd/_iconos_localidades/46n.gif\"></font></td>\n";
					break;
				}
				case "intervalos nubosos con lluvia":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/23.gif\"></font></td>\n";
					break;
				}
				case "nuboso con lluvia":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/24.gif\"></font></td>\n";
					break;
				}
				case "muy nuboso con lluvia":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes_gcd/_iconos_localidades/25.GIF\"></font></td>\n";
					break;
				}
				case "cubierto con lluvia":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes_gcd/_iconos_localidades/26.GIF\"></font></td>\n";
					break;
				}
				default:
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">" +  diaPrediccion1.getEstadoCielo().get("00-06")[0] + "</font></td>\n";
					break;
				}
			}
			switch(diaPrediccion1.getEstadoCielo().get("18-24")[0].toLowerCase())
			{
				case "despejado":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/11.gif\"></font></td>\n";
					break;
				}

				case "poco nuboso":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/12.gif\"></font></td>\n";
					break;
				}

				case "intervalos nubosos":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/13.gif\"></font></td>\n";
					break;
				}
				case "nuboso":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/14.gif\"></font></td>\n";
					break;
				}
				case "muy nuboso":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/15.gif\"></font></td>\n";
					break;
				}
				case "cubierto":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/15.gif\"></font></td>\n";
					break;
				}
				case "intervalos nubosos con lluvia escasa":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/43.gif\"></font></td>\n";
					break;
				}
				case "nuboso con lluvia escasa":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/44.gif\"></font></td>\n";
					break;
				}
				case "muy nuboso con lluvia escasa":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes_gcd/_iconos_localidades/45n.gif\"></font></td>\n";
					break;
				}
				case "cubierto con lluvia escasa":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes_gcd/_iconos_localidades/46n.gif\"></font></td>\n";
					break;
				}
				case "intervalos nubosos con lluvia":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/23.gif\"></font></td>\n";
					break;
				}
				case "nuboso con lluvia":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/estado_cielo/24.gif\"></font></td>\n";
					break;
				}
				case "muy nuboso con lluvia":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes_gcd/_iconos_localidades/25.GIF\"></font></td>\n";
					break;
				}
				case "cubierto con lluvia":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes_gcd/_iconos_localidades/26.GIF\"></font></td>\n";
					break;
				}
				default:
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">" +  diaPrediccion1.getEstadoCielo().get("00-06")[0] + "</font></td>\n";
					break;
				}
			}

			outString = outString + "\t\t</tr>\n";

			//Fila Prob. Preci
			outString = outString + "\t\t<tr>\n";
			outString = outString + "\t\t\t<th class=\"cabecera\"><h5>Prob. precip.</h5></th>\n";
			outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">" +  diaPrediccion1.getProbPrecipitacion().get("00-06") + "%" + "</font></td>\n";
			outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">" +  diaPrediccion1.getProbPrecipitacion().get("12-18") + "%" + "</font></td>\n";
			outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">" +  diaPrediccion1.getProbPrecipitacion().get("18-24") + "%" + "</font></td>\n";

			outString = outString + "\t\t</tr>\n";

			//Fila Cota Nieve
			outString = outString + "\t\t<tr>\n";
			outString = outString + "\t\t\t<th class=\"cabecera\"><h5>Cota nieve</h5></th>\n";
			outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">" +  diaPrediccion1.getCotaNieveProv().get("00-06") + "m" + "</font></td>\n";
			outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">" +  diaPrediccion1.getCotaNieveProv().get("12-18") + "m" + "</font></td>\n";
			outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">" +  diaPrediccion1.getCotaNieveProv().get("18-24") + "m" + "</font></td>\n";

			outString = outString + "\t\t</tr>\n";

			//Fila Temp. min
			outString = outString + "\t\t<tr>\n";
			outString = outString + "\t\t\t<th class=\"cabecera\"><h5>Temperatura</h5></th>\n";
			outString = outString + "\t\t\t<td colspan=3 align=\"center\">"
					+ "<font size=\"2\" color=\"blue\">" +  diaPrediccion1.getTemperatura().getMinima() + "</font>"
					+ "<font size=\"2\" color=\"black\">" + "/" + "</font>"
					+ "<font size=\"2\" color=\"red\">" + diaPrediccion1.getTemperatura().getMaxima() + "</font></td>\n";

			outString = outString + "\t\t</tr>\n";

			//Fila Viento
			outString = outString + "\t\t<tr>\n";
			outString = outString + "\t\t\t<th rowspan=2 class=\"cabecera\"><h5>Viento</h5></th>\n";
			switch(diaPrediccion1.getViento().get("00-06")[0].toUpperCase())
			{
				case "E":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/E.gif\"></font></td>\n";
					break;
				}
				case "NE":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/NE.gif\"></font></td>\n";
					break;
				}
				case "SE":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/SE.gif\"></font></td>\n";
					break;
				}
				case "O":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/O.gif\"></font></td>\n";
					break;
				}
				case "NO":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/NO.gif\"></font></td>\n";
					break;
				}
				case "SO":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/SO.gif\"></font></td>\n";
					break;
				}
				case "N":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/N.gif\"></font></td>\n";
					break;
				}
				case "S":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/S.gif\"></font></td>\n";
					break;
				}
				default:
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes_gcd/_iconos_localidades/C.GIF\"></font></td>\n";
					break;
				}
			}
			switch(diaPrediccion1.getViento().get("12-18")[0].toUpperCase())
			{
				case "E":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/E.gif\"></font></td>\n";
					break;
				}
				case "NE":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/NE.gif\"></font></td>\n";
					break;
				}
				case "SE":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/SE.gif\"></font></td>\n";
					break;
				}
				case "O":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/O.gif\"></font></td>\n";
					break;
				}
				case "NO":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/NO.gif\"></font></td>\n";
					break;
				}
				case "SO":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/SO.gif\"></font></td>\n";
					break;
				}
				case "N":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/N.gif\"></font></td>\n";
					break;
				}
				case "S":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/S.gif\"></font></td>\n";
					break;
				}
				default:
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">" +  diaPrediccion1.getViento().get("12-18")[0] + "</font></td>\n";
					break;
				}
			}
			switch(diaPrediccion1.getViento().get("18-24")[0].toUpperCase())
			{
				case "E":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/E.gif\"></font></td>\n";
					break;
				}
				case "NE":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/NE.gif\"></font></td>\n";
					break;
				}
				case "SE":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/SE.gif\"></font></td>\n";
					break;
				}
				case "O":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/O.gif\"></font></td>\n";
					break;
				}
				case "NO":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/NO.gif\"></font></td>\n";
					break;
				}
				case "SO":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/SO.gif\"></font></td>\n";
					break;
				}
				case "N":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/N.gif\"></font></td>\n";
					break;
				}
				case "S":
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\"><img src=\"http://www.aemet.es/imagenes/gif/iconos_viento/S.gif\"></font></td>\n";
					break;
				}
				default:
				{
					outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">" +  diaPrediccion1.getViento().get("18-24")[0] + "</font></td>\n";
					break;
				}
			}

			outString = outString + "\t\t</tr>\n";

			outString = outString + "\t\t<tr>\n";
			outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">" +  diaPrediccion1.getViento().get("00-06")[1] + "</font></td>\n";
			outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">" +  diaPrediccion1.getViento().get("12-18")[1] + "</font></td>\n";
			outString = outString + "\t\t\t<td align=\"center\"><font size=\"2\">" +  diaPrediccion1.getViento().get("18-24")[1] + "</font></td>\n";

			outString = outString + "\t\t</tr>\n";

			//Fila indice UV
			outString = outString + "\t\t<tr>\n";
			outString = outString + "\t\t\t<th class=\"cabecera\"><h5>UVMaximo</h5></th>\n";
			outString = outString + "\t\t\t<td colspan=3 align=\"center\"><font size=\"2\" class=\"temperaturas\">" +  diaPrediccion1.getUvMax() + "</font></td>\n";

			outString = outString + "\t\t</tr>\n";

			//Fin tabla
			outString = outString + "\t</table>";

			return outString;
		} catch (Exception ex)
		{
			System.out.println("Excepcion en printDatos");
			ex.printStackTrace();

			return outString;
		}
	}
}
