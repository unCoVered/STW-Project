/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 04-18-15
 * Fecha modificacion:
 * Tiempo invertido:
 */
package aemet.server.parser.XML;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import aemet.server.parser.datos.Dia;
import aemet.server.parser.datos.HumedadRelativa;
import aemet.server.parser.datos.SensacionTermica;
import aemet.server.parser.datos.Temperatura;

/**
 * Dada una lista de dias, extraer sus datos para generar objetos datos.Dia
 */
public class ExtraerDiasLista
{

	// Atributos con los que cuenta un dia
	private static String fecha;
	private static Map<String, String> probPrecipitacion;
	private static Map<String, String> cotaNieveProv;
	private static Map<String, String[]> estadoCielo;
	private static Map<String, String[]> viento;
	private static Map<String, String> rachaMax;
	private static Temperatura temperatura;
	private static SensacionTermica sensacionTermica;
	private static HumedadRelativa humedadRelativa;
	private static int uvMax;

	public static List<Dia> extraerDias(ListaDia diasPrediccion)
	{
		List<Dia> listaDias = new ArrayList<Dia>();

		try
		{
			for (aemet.server.parser.XML.Dia dia : diasPrediccion.getDiasPrediccion())
			{
				listaDias.add(extraeDatosDia(dia));
			}

			return listaDias;
		} catch (Exception ex)
		{
			System.out.println("Excepcion en extraerDias");
			ex.printStackTrace();

			return listaDias;
		}
	}

	private static Dia extraeDatosDia(aemet.server.parser.XML.Dia dia)
	{
		Dia diaCreado = new Dia();
		try
		{
			fecha = dia.dia.getFecha();
			probPrecipitacion = dia.dia.getProbPrecipitacion();
			cotaNieveProv = dia.dia.getCotaNieveProv();
			estadoCielo = dia.dia.getEstadoCielo();
			viento = dia.dia.getViento();
			rachaMax = dia.dia.getRachaMax();
			temperatura = dia.dia.getTemperatura();
			sensacionTermica = dia.dia.getSensacionTermica();
			humedadRelativa = dia.dia.getHumedadRelativa();
			uvMax = dia.dia.getUvMax();

			diaCreado.setFecha(fecha);
			diaCreado.setProbPrecipitacion(probPrecipitacion);
			diaCreado.setCotaNieveProv(cotaNieveProv);
			diaCreado.setEstadoCielo(estadoCielo);
			diaCreado.setViento(viento);
			diaCreado.setRachaMax(rachaMax);
			diaCreado.setTemperatura(temperatura);
			diaCreado.setSensacionTermica(sensacionTermica);
			diaCreado.setHumedadRelativa(humedadRelativa);
			diaCreado.setUvMax(uvMax);

			return diaCreado;

		} catch (Exception ex)
		{
			System.out.println("Excepcion en extraeDatosDia");
			ex.printStackTrace();

			return diaCreado;
		}
	}
}
