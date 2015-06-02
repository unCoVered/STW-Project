/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-04-15
 * Fecha modificacion: 03-31-15
 * Tiempo invertido: 5h
 */
package aemet.server.parser.XML;

import org.jdom.Element;
import aemet.server.parser.datos.DiaUtils;

import java.util.ArrayList;
import java.util.List;

public class Dia extends Element
{

	private static final long serialVersionUID = 1L;

	aemet.server.parser.datos.Dia dia;

	Element probPrecipitacion;
	Element cotaNieveProv;
	Element estadoCielo;
	Element viento;
	Element rachaMax;
	Element temperatura;
	Element sensacionTermica;
	Element humedadRelativa;
	Element uvMax;

	/**
	 * Crea un objeto dia que usaremos para generar los datos a utilizar
	 * Utiizamos un objeto Dia del paquete datos
	 *
	 * @param dia
	 */
	public Dia(aemet.server.parser.datos.Dia dia)
	{
		super(aemet.server.common.CodeNames.DIA_ATR);
		this.dia = dia;
		this.setAttribute(aemet.server.common.CodeNames.FECHA_ATR, dia.getFecha());

		//Inicializamos listas
		List<String> periodos = new ArrayList<String>();
		periodos = DiaUtils.rellenarPeriodos();
		List<String> horas = new ArrayList<String>();
		horas = DiaUtils.rellenarHoras();

		// Inicializamos los atributos
		this.probPrecipitacion = new Element(aemet.server.common.CodeNames.PROB_PRECIPITACION);
		this.cotaNieveProv = new Element(aemet.server.common.CodeNames.COTA_NIEVE_PROV);
		this.estadoCielo = new Element(aemet.server.common.CodeNames.ESTADO_CIELO);
		this.viento = new Element(aemet.server.common.CodeNames.VIENTO);
		this.rachaMax = new Element(aemet.server.common.CodeNames.RACHA_MAX);
		this.temperatura = new Element(aemet.server.common.CodeNames.TEMPERATURA);
		this.sensacionTermica = new Element(aemet.server.common.CodeNames.SENS_TERMICA);
		this.humedadRelativa = new Element(aemet.server.common.CodeNames.HUMEDAD_RELATIVA);
		this.uvMax = new Element(aemet.server.common.CodeNames.UV_MAX);

		// Asignamos valores a cada atributo
		rellenarPeriodos(probPrecipitacion, periodos);
		rellenarPeriodos(cotaNieveProv, periodos);
		rellenarPeriodos(estadoCielo, periodos);
		rellenarPeriodos(viento, periodos);
		rellenarPeriodos(rachaMax, periodos);
		rellenaObjetos(temperatura, horas);
		rellenaObjetos(sensacionTermica, horas);
		rellenaObjetos(humedadRelativa, horas);
		rellenarUVMax(uvMax);

		// Anyadimos los atributos con datos
		this.addContent(probPrecipitacion);
		this.addContent(cotaNieveProv);
		this.addContent(estadoCielo);
		this.addContent(viento);
		this.addContent(rachaMax);
		this.addContent(temperatura);
		this.addContent(sensacionTermica);
		this.addContent(humedadRelativa);
		this.addContent(uvMax);
	}

	/**
	 * Rellena los atributos que representan a campos que contienen el atributo
	 * 'periodo'.
	 *
	 * @param element
	 */
	private void rellenarPeriodos(Element element, List<String> periodos)
	{
		for (String periodo : periodos)
		{
			element.setAttribute(aemet.server.common.CodeNames.PERIODO_ATR, periodo);

			switch (element.getName())
			{
			case "prob_precipitacion":
			{
				try
				{
					String elementText = dia.getProbPrecipitacion().get(periodo);

					// Si el string existe
					if (elementText != null)
					{
						element.setText(dia.getProbPrecipitacion().get(periodo));
					}
				} catch (Exception ex)
				{
					System.out.println("Excepcion rellenar ProbPrecipitacion");
					System.out.println(ex.getMessage());
				}

				break;
			}
			case "cota_nieve_prov":
			{
				try
				{
					String elementText = dia.getCotaNieveProv().get(periodo);

					//Si el string existe
					if (elementText != null)
					{
						element.setText(dia.getCotaNieveProv().get(periodo));
					}
				} catch (Exception ex)
				{
					System.out.println("Excepcion rellenar Cota nieve prov");
					System.out.println(ex.getMessage());
				}

				break;
			}
			case "estado_cielo":
			{
				try
				{
					String[] datosEstadoCielo = dia.getEstadoCielo().get(periodo);

					//Si el string existe
					if (datosEstadoCielo != null)
					{
						element.setAttribute("descripcion", datosEstadoCielo[0]);
						element.setText(datosEstadoCielo[1]);
					}
				} catch (Exception ex)
				{
					System.out.println("Excepcion rellenar estado cielo");
					System.out.println(ex.getMessage());
				}
				break;
			}
			case "viento":
			{
				try
				{
					String[] datosViento = dia.getViento().get(periodo);

					//Si el string existe
					if (datosViento != null)
					{
						this.viento.addContent(new Element(aemet.server.common.CodeNames.DIRECCION_ATR).setText(datosViento[0]));
						this.viento.addContent(new Element(aemet.server.common.CodeNames.VELOCIDAD_ATR).setText(datosViento[1]));
					}
				} catch (Exception ex)
				{
					System.out.println("Excepcion rellenar viento");
					System.out.println(ex.getMessage());
				}

				break;
			}
			case "racha_max":
			{
				try
				{
					String elementText = dia.getRachaMax().get(periodo);

					//Si el string existe
					if (elementText != null)
					{
						element.setText(dia.getRachaMax().get(periodo));
					}
				} catch (Exception ex)
				{
					System.out.println("Excepcion rellenar rachaMax");
					System.out.println(ex.getMessage());
				}
				break;
			}
			default:
				System.out.println(aemet.server.common.CodeNames.NO_IMPLEMENTADO_ERROR);
			}
		}
	}

	/**
	 * Rellena los atributos que se almacenan mediante objetos
	 */
	private void rellenaObjetos(Element objeto, List<String> horas)
	{
		switch (objeto.getName())
		{
		case "temperatura":
		{
			try
			{
				//Si existe el objeto
				if (dia.getTemperatura() != null)
				{
					this.temperatura.addContent(new Element(aemet.server.common.CodeNames.MAXIMA_ATR)
							.setText(Integer.toString(dia.getTemperatura().getMaxima())));
					this.temperatura.addContent(new Element(aemet.server.common.CodeNames.MINIMA_ATR)
							.setText(Integer.toString(dia.getTemperatura().getMinima())));

					//Si el hash no esta vacio
					if (dia.getTemperatura().getDato().size() != 0)
					{
						for (String hora : horas)
						{
							this.temperatura.addContent(
									new Element(aemet.server.common.CodeNames.DATO_ATR).setAttribute(aemet.server.common.CodeNames.HORA_ATR, hora)
											.setText(
													Integer.toString(dia.getTemperatura().getDato().get(hora))));
						}
					}

				}
			} catch (Exception ex)
			{
				System.out.println("Excepcion rellenar temperatura");
				System.out.println(ex.getMessage());
			}
			break;
		}
		case "sens_termica":
		{
			try
			{
				//Si el objeto existe
				if (dia.getSensacionTermica() != null)
				{
					this.sensacionTermica.addContent(new Element(aemet.server.common.CodeNames.MAXIMA_ATR)
							.setText(Integer.toString(dia.getSensacionTermica().getMaxima())));
					this.sensacionTermica.addContent(new Element(aemet.server.common.CodeNames.MINIMA_ATR)
							.setText(Integer.toString(dia.getSensacionTermica().getMinima())));

					//Si el hash no esta vacio
					if (dia.getSensacionTermica().getDato().size() != 0)
					{
						for (String hora : horas)
						{
							this.sensacionTermica.addContent(
									new Element(aemet.server.common.CodeNames.DATO_ATR).setAttribute(aemet.server.common.CodeNames.HORA_ATR, hora)
											.setText(
													Integer.toString(dia.getSensacionTermica().getDato().get(hora))));
						}
					}
				}
			} catch (Exception ex)
			{
				System.out.println("Excepcion rellenar sensacion termica");
				System.out.println(ex.getMessage());
			}
			break;
		}
		case "humedad_relativa":
		{
			try
			{
				//Si el objeto existe
				if (dia.getHumedadRelativa() != null)
				{
					this.humedadRelativa.addContent(new Element(aemet.server.common.CodeNames.MAXIMA_ATR)
							.setText(Integer.toString(dia.getHumedadRelativa().getMaxima())));
					this.humedadRelativa.addContent(new Element(aemet.server.common.CodeNames.MINIMA_ATR)
							.setText(Integer.toString(dia.getHumedadRelativa().getMinima())));

					// Si el tamanyo del hash es mayor de cero
					if (dia.getHumedadRelativa().getDato().size() != 0)
					{
						for (String hora : horas)
						{
							this.humedadRelativa.addContent(
									new Element(aemet.server.common.CodeNames.DATO_ATR).setAttribute(aemet.server.common.CodeNames.HORA_ATR, hora)
											.setText(
													Integer.toString(dia.getHumedadRelativa().getDato().get(hora))));
						}
					}
				}
			} catch (Exception ex)
			{
				System.out.println("Excepcion rellenar humedad relativa");
				System.out.println(ex.getMessage());
			}
			break;
		}
		default:
		{
			System.out.println(objeto.getName());
			System.out.println(aemet.server.common.CodeNames.NO_IMPLEMENTADO_ERROR);
			break;
		}
		}

	}

	/**
	 * Rellena el atributo asociado al campo uv_max
	 *
	 * @param uvMax
	 */
	private void rellenarUVMax(Element uvMax)
	{
		uvMax.setText(Integer.toString(dia.getUvMax()));
	}
}
