/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-04-15
 * Fecha modificacion: 03-31-15
 * Tiempo invertido: 12h
 */
package aemet.server.parser.XML;

import aemet.server.common.CodeNames;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.util.List;

public class ProcesadorFicheroXML
{
	/**
	 * Metodo estatico encargado de leer y parsear el contenido de un fichero
	 * XML previamente indicado como atributo de la clase
	 */
	public static void leePrediccion(ListaDia dias, String ficheroLectura)
	{
		try
		{
			// Elegimos aemet.server.parser
			SAXBuilder constructor = new SAXBuilder();

			// Construimos arbol
			Document doc = constructor.build(ficheroLectura);

			// Obtenemos raiz
			Element raiz = doc.getRootElement();

			if (raiz.getName().equals(CodeNames.ROOT_ATR))
			{
				// Lista de secciones
				List<Element> secciones = raiz.getChildren();

				for (Element seccion : secciones)
				{
					if (seccion.getName().equals(CodeNames.PREDICCION_STRING))
					{
						leerSeccionPrediccion(seccion, dias);

					}
				}
			}

		} catch (Exception ex)
		{
			System.out.println("Exception leer prediccion");
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Metodo privado encargado de leer la seccion 'Prediccion' del fichero XML
	 *
	 * @param seccion a recorrer
	 */
	private static void leerSeccionPrediccion(Element seccion, ListaDia dias)
	{
		try
		{
			// Recorremos la seccion prediccion
			List<Element> subSecciones = seccion.getChildren();

			for (Element subseccion : subSecciones)
			{
				if (subseccion.getName().equals(CodeNames.DIA_ATR))
				{
					leerPrediccionDia(subseccion, dias);
				}
			}
		} catch (Exception ex)
		{
			System.out.println("Excepcion leerSeccionPrediccion");
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Metodo privado encargado de leer los datos de un determinado dia Dicho
	 * dia se pasa por parametro
	 */
	private static void leerPrediccionDia(Element subseccion, ListaDia dias)
	{
		try
		{
			aemet.server.parser.datos.Dia dia = new aemet.server.parser.datos.Dia();
			String fechaDia = subseccion.getAttributeValue(CodeNames.FECHA_ATR);

			dia.setFecha(fechaDia);

			List<Element> seccionesDia = subseccion.getChildren();

			for (Element atDia : seccionesDia)
			{
				// parseo att de un dia
				switch (atDia.getName())
				{
				case "prob_precipitacion":
				{
					mapProbPrecipitacion(atDia, dia);
					break;
				}
				case "cota_nieve_prov":
				{
					mapCotaNieveProv(atDia, dia);
					break;
				}
				case "estado_cielo":
				{
					mapEstadoCielo(atDia, dia);
					break;
				}
				case "viento":
				{
					mapViento(atDia, dia);
					break;
				}
				case "racha_max":
				{
					mapRachaMax(atDia, dia);
					break;
				}
				case "temperatura":
				{
					mapTemperatura(atDia, dia);
					break;
				}
				case "sens_termica":
				{
					mapSensacionTermica(atDia, dia);
					break;
				}
				case "humedad_relativa":
				{
					mapHumedadRelativa(atDia, dia);
					break;
				}
				case "uv_max":
				{
					mapUVMax(atDia, dia);
					break;
				}
				default:
					System.out.println("Error en el parseo o campo inesperado");
					System.out.println(atDia.getName());
					break;
				}
			}

			Dia diaMappeado = new Dia(dia);
			dias.getDiasPrediccion().add(diaMappeado);
		} catch (Exception ex)
		{
			System.out.println("Excepcion leerPrediccionDia");
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Mappea los atributos y valores del campo precipitaciones
	 *
	 * @param atDia
	 * @param dia
	 */
	private static void mapProbPrecipitacion(Element atDia, aemet.server.parser.datos.Dia dia)
	{
		try
		{
			dia.getProbPrecipitacion()
					.put(atDia.getAttributeValue(CodeNames.PERIODO_ATR),
							atDia.getText());

		} catch (Exception ex)
		{
			System.out.println("Excepcion mapProbPrecipitacion");
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Mapea los atributos y valores del campo cota_nieve_prov
	 *
	 * @param atDia
	 * @param dia
	 */
	private static void mapCotaNieveProv(Element atDia, aemet.server.parser.datos.Dia dia)
	{
		try
		{
			dia.getCotaNieveProv()
					.put(atDia.getAttributeValue(CodeNames.PERIODO_ATR),
							atDia.getText());

		} catch (Exception ex)
		{
			System.out.println("Excepcion mapCotaNieve");
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Mapea los atributos y valores del campo estado_cielo
	 *
	 * @param atDia
	 * @param dia
	 */
	private static void mapEstadoCielo(Element atDia, aemet.server.parser.datos.Dia dia)
	{
		try
		{
			String[] array = new String[2];
			//			array[0] = atDia.getAttributeValue(CodeNames.DESCRIPCION_ATR);
			array[0] = atDia.getAttribute(CodeNames.DESCRIPCION_ATR).getValue();
			array[1] = atDia.getText();
			dia.getEstadoCielo().put(
					atDia.getAttributeValue(CodeNames.PERIODO_ATR), array);

		} catch (Exception ex)
		{
			System.out.println("Excepcion mapEstadoCielo");
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Mapea los atributos y valores del campo viento
	 *
	 * @param atDia
	 * @param dia
	 */
	private static void mapViento(Element atDia, aemet.server.parser.datos.Dia dia)
	{
		try
		{
			String[] array = new String[2];
			array[0] = atDia.getChild("direccion").getValue();
			array[1] = atDia.getChild("velocidad").getValue();
			dia.getViento().put(atDia.getAttributeValue(CodeNames.PERIODO_ATR),
					array);

		} catch (Exception ex)
		{
			System.out.println("Excepcion mapViento");
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Mapea los atributos y valores del campo racha_max
	 *
	 * @param atDia
	 * @param dia
	 */
	private static void mapRachaMax(Element atDia, aemet.server.parser.datos.Dia dia)
	{
		try
		{
			dia.getRachaMax().put(atDia.getAttributeValue(CodeNames.PERIODO_ATR),
					atDia.getText());

		} catch (Exception ex)
		{
			System.out.println("Excepcion mapRachaMax");
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Mapea los atributos y valores del campo temperatura
	 *
	 * @param atDia
	 * @param dia
	 */
	private static void mapTemperatura(Element atDia, aemet.server.parser.datos.Dia dia)
	{
		try
		{
			List<Element> seccionesTemperatura = atDia.getChildren();

			for (Element datoHora : seccionesTemperatura)
			{
				switch (datoHora.getName())
				{
				case "maxima":
				{
					dia.getTemperatura().setMaxima(
							Integer.parseInt(datoHora.getValue()));
					break;
				}
				case "minima":
				{
					dia.getTemperatura().setMinima(
							Integer.parseInt(datoHora.getValue()));
					break;
				}
				case "dato":
				{
					if (datoHora.getValue().isEmpty())
					{
						dia.getTemperatura()
								.getDato()
								.put(datoHora
										.getAttributeValue(
												CodeNames.HORA_ATR), 0);
					} else
					{
						dia.getTemperatura()
								.getDato()
								.put(datoHora.getAttributeValue(
												CodeNames.HORA_ATR),
										Integer.parseInt(
												datoHora.getValue()));
					}
				}
				}
			}
		} catch (Exception ex)
		{
			System.out.println("Excepcion mapTemperatura");
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Mapea los atributos y valores del campo sens_termica
	 *
	 * @param atDia
	 * @param dia
	 */
	private static void mapSensacionTermica(Element atDia, aemet.server.parser.datos.Dia dia)
	{
		try
		{
			List<Element> seccionesSensacion = atDia.getChildren();

			for (Element datoHora : seccionesSensacion)
			{
				switch (datoHora.getName())
				{
				case "maxima":
				{
					dia.getSensacionTermica().setMaxima(
							Integer.parseInt(datoHora.getValue()));
					break;
				}
				case "minima":
				{
					dia.getSensacionTermica().setMinima(
							Integer.parseInt(datoHora.getValue()));
					break;
				}
				case "dato":
				{
					if (datoHora.getValue().isEmpty())
					{
						dia.getSensacionTermica()
								.getDato()
								.put(datoHora
										.getAttributeValue(
												CodeNames.HORA_ATR), 0);
					} else
					{
						dia.getSensacionTermica()
								.getDato()
								.put(datoHora.getAttributeValue(
												CodeNames.HORA_ATR),
										Integer.parseInt(
												datoHora.getValue()));
					}
				}
				}
			}
		} catch (Exception ex)
		{
			System.out.println("Excepcion mapSensacionTermica");
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Mapea los atributos y valores del campo humedad_relativa
	 *
	 * @param atDia
	 * @param dia
	 */
	private static void mapHumedadRelativa(Element atDia, aemet.server.parser.datos.Dia dia)
	{
		try
		{
			List<Element> seccionesHumedad = atDia.getChildren();

			for (Element datoHora : seccionesHumedad)
			{

				switch (datoHora.getName())
				{
				case "maxima":
				{
					dia.getHumedadRelativa().setMaxima(
							Integer.parseInt(datoHora.getValue()));
					break;
				}
				case "minima":
				{
					dia.getHumedadRelativa().setMinima(
							Integer.parseInt(datoHora.getValue()));
					break;
				}
				case "dato":
				{
					if (datoHora.getValue().isEmpty())
					{
						dia.getHumedadRelativa()
								.getDato()
								.put(datoHora.getAttributeValue(
										CodeNames.HORA_ATR), 0);
					} else
					{
						dia.getHumedadRelativa()
								.getDato()
								.put(datoHora.getAttributeValue(
												CodeNames.HORA_ATR),
										Integer.parseInt(
												datoHora.getValue()));
					}
				}
				}
			}
		} catch (Exception ex)
		{
			System.out.println("Excepcion mapHumedadRelativa");
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Mapea los atributos y valores del campo uv_max
	 *
	 * @param atDia
	 * @param dia
	 */
	private static void mapUVMax(Element atDia, aemet.server.parser.datos.Dia dia)
	{
		try
		{
			dia.setUvMax(Integer.parseInt(atDia.getText()));
		} catch (Exception ex)
		{
			System.out.println("Excepcion mapUVMax");
			System.out.println(ex.getMessage());
		}
	}
}
