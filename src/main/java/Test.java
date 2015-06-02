import aemet.server.WebServicesServer;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.utils.Options;

import javax.xml.namespace.QName;

/**
 * Clase para testear
 */
public class Test
{
	public static void main(String[] args)
	{
		WebServicesServer webServicesServer = new WebServicesServer();

		String stringPrueba = new String();

		stringPrueba = "<?xml version=\"1.0\" standalone=\"yes\"?>\n";
		stringPrueba += "<!DOCTYPE id [\n";
		stringPrueba += "\t<!ELEMENT id (#PCDATA)> \n";
		stringPrueba += "]>\n";
		stringPrueba += "<id>50297</id>";

		String stringPrueba2 = new String();
		stringPrueba2 = stringPrueba2 + "{\n string:50297\n}";

		String respuesta = webServicesServer.XMLHandler(stringPrueba);
		String respuesta2 = webServicesServer.JSONHandler(stringPrueba2);
		String respuesta3 = new String();

		try
		{
			Options options = new Options(args);
			String endpointURL = options.getURL();

			Service service = new Service();
			Call call = (Call) service.createCall();

			call.setTargetEndpointAddress(new java.net.URL(endpointURL));
			call.setOperationName(new QName("WebServicesServer", "XMLHandler"));

			respuesta3 = (String) call.invoke(new Object[]{"50297"});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(respuesta);
//		System.out.println(respuesta2);
//		System.out.println(respuesta3);
	}

}
