package aemet.server.comunicacion.MensajeJSON;

/**
 * Clase 'envelope'.
 * Envuelve el String para que sea mas facil parsear y crear los
 * string JSON
 */
public class EnvelopeJSON
{
	private String string;

	/**
	 * Constructor
	 *
	 * @param string
	 */
	public EnvelopeJSON(String string)
	{
		this.string = string;
	}

	/**
	 * Devuelve el String
	 *
	 * @return
	 */
	public String getString()
	{
		return this.string;
	}
}
