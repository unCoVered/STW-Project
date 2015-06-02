/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-04-15
 * Fecha modificacion: 03-31-15
 * Tiempo invertido: 15m
 */
package aemet.server.parser.XML;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("hiding")
public class ListaDia
{

	private List<Dia> diasPrediccion;

	/**
	 * Constructor sin parametros
	 */
	public ListaDia()
	{
		this.diasPrediccion = new ArrayList<Dia>();
	}

	/**
	 * Constructor con parametros
	 *
	 * @param lista
	 */
	public ListaDia(List<Dia> lista)
	{
		this.diasPrediccion = lista;
	}

	public List<Dia> getDiasPrediccion()
	{
		return diasPrediccion;
	}

	public void setDiasPrediccion(List<Dia> lista)
	{
		this.diasPrediccion = lista;
	}
}
