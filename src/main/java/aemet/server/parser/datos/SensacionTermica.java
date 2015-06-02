/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-04-15
 * Fecha modificacion: 03-31-15
 * Tiempo invertido: 25m
 */
package aemet.server.parser.datos;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase con todos los datos necesarios para introducir el campo Sensacion Termica
 * en el dia
 */
public class SensacionTermica
{
	private int maxima;
	private int minima;
	private Map<String, Integer> dato;

	public SensacionTermica()
	{
		this.maxima = 0;
		this.minima = 0;
		this.dato = new HashMap<String, Integer>();
	}

	public SensacionTermica(int maxima, int minima, Map<String, Integer> dato)
	{
		this.maxima = maxima;
		this.minima = minima;
		this.dato = dato;
	}

	public int getMaxima()
	{
		return maxima;
	}

	public void setMaxima(int maxima)
	{
		this.maxima = maxima;
	}

	public int getMinima()
	{
		return minima;
	}

	public void setMinima(int minima)
	{
		this.minima = minima;
	}

	public Map<String, Integer> getDato()
	{
		return dato;
	}

	public void setDato(Map<String, Integer> dato)
	{
		this.dato = dato;
	}
}
