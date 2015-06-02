/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-04-15
 * Fecha modificacion: 03-31-15
 * Tiempo invertido: 1h 30m
 */
package aemet.server.parser.datos;

import java.util.HashMap;
import java.util.Map;

public class Dia
{

	// Atributos con los que cuenta un dia
	private String fecha;
	private Map<String, String> probPrecipitacion;
	private Map<String, String> cotaNieveProv;
	private Map<String, String[]> estadoCielo;
	private Map<String, String[]> viento;
	private Map<String, String> rachaMax;
	private Temperatura temperatura;
	private SensacionTermica sensacionTermica;
	private HumedadRelativa humedadRelativa;
	private int uvMax;

	/**
	 * Objeto constructor sin parametros. Solo incializa los atributos.
	 */
	public Dia()
	{
		this.fecha = "";
		this.probPrecipitacion = new HashMap<String, String>();
		this.cotaNieveProv = new HashMap<String, String>();
		this.estadoCielo = new HashMap<String, String[]>();
		this.viento = new HashMap<String, String[]>();
		this.rachaMax = new HashMap<String, String>();
		this.temperatura = new Temperatura();
		this.sensacionTermica = new SensacionTermica();
		this.humedadRelativa = new HumedadRelativa();
		this.uvMax = 0;
	}

	/**
	 * Objeto constructor con parametros. Los atributos de la clase toman el
	 * valor de los parametros.
	 *
	 * @param fecha
	 * @param probPrecipitacion
	 * @param cotaNieveProv
	 * @param estadoCielo
	 * @param viento
	 * @param rachaMax
	 * @param temperatura
	 * @param sensacionTermica
	 * @param humedadRelativa
	 * @param uvMax
	 */
	public Dia(String fecha, Map<String, String> probPrecipitacion,
			Map<String, String> cotaNieveProv,
			Map<String, String[]> estadoCielo, Map<String, String[]> viento,
			Map<String, String> rachaMax, Temperatura temperatura,
			SensacionTermica sensacionTermica, HumedadRelativa humedadRelativa,
			int uvMax)
	{
		this.fecha = fecha;
		this.probPrecipitacion = probPrecipitacion;
		this.cotaNieveProv = cotaNieveProv;
		this.estadoCielo = estadoCielo;
		this.viento = viento;
		this.rachaMax = rachaMax;
		this.temperatura = temperatura;
		this.sensacionTermica = sensacionTermica;
		this.humedadRelativa = humedadRelativa;
		this.uvMax = uvMax;
	}

	// Getters y setter de la clase

	public String getFecha()
	{
		return fecha;
	}

	public void setFecha(String fecha)
	{
		this.fecha = fecha;
	}

	public Map<String, String> getProbPrecipitacion()
	{
		return probPrecipitacion;
	}

	public void setProbPrecipitacion(Map<String, String> probPrecipitacion)
	{
		this.probPrecipitacion = probPrecipitacion;
	}

	public Map<String, String> getCotaNieveProv()
	{
		return cotaNieveProv;
	}

	public void setCotaNieveProv(Map<String, String> cotaNieveProv)
	{
		this.cotaNieveProv = cotaNieveProv;
	}

	public Map<String, String[]> getEstadoCielo()
	{
		return estadoCielo;
	}

	public void setEstadoCielo(Map<String, String[]> estadoCielo)
	{
		this.estadoCielo = estadoCielo;
	}

	public Map<String, String[]> getViento()
	{
		return viento;
	}

	public void setViento(Map<String, String[]> viento)
	{
		this.viento = viento;
	}

	public Map<String, String> getRachaMax()
	{
		return rachaMax;
	}

	public void setRachaMax(Map<String, String> rachaMax)
	{
		this.rachaMax = rachaMax;
	}

	public Temperatura getTemperatura()
	{
		return temperatura;
	}

	public void setTemperatura(Temperatura temperatura)
	{
		this.temperatura = temperatura;
	}

	public SensacionTermica getSensacionTermica()
	{
		return sensacionTermica;
	}

	public void setSensacionTermica(SensacionTermica sensacionTermica)
	{
		this.sensacionTermica = sensacionTermica;
	}

	public HumedadRelativa getHumedadRelativa()
	{
		return humedadRelativa;
	}

	public void setHumedadRelativa(HumedadRelativa humedadRelativa)
	{
		this.humedadRelativa = humedadRelativa;
	}

	public int getUvMax()
	{
		return uvMax;
	}

	public void setUvMax(int uvMax)
	{
		this.uvMax = uvMax;
	}
}
