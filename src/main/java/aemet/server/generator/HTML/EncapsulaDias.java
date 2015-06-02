/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 04-06-15
 * Fecha modificacion: 04-06-15
 * Tiempo invertido: 5m
 */
package aemet.server.generator.HTML;

import aemet.server.parser.datos.Dia;

import java.util.List;

/**
 * Encapsula la lista de Dias para generar el fichero JSON
 */
public class EncapsulaDias
{

	private List<Dia> listaDias;

	public EncapsulaDias(List<Dia> listaDias)
	{
		this.listaDias = listaDias;
	}

	public List<Dia> getListaDias()
	{
		return listaDias;
	}

	public void setListaDias(List<Dia> listaDiast)
	{
		this.listaDias = listaDias;
	}
}
