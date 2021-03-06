package haja.pta.desktop;

import haja.pta.desktop.cli.DesktopCli;
import haja.pta.desktop.communication.IPhoneCommunicationManagement;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @author Harald Jagenteufel
 *
 */
public class Desktop {
	private static Logger s_log = Logger.getLogger(Desktop.class);;

    public static void main(String[] args) {
		s_log.info("desktop startup...");

		ApplicationContext ctx = new GenericXmlApplicationContext("beans.xml");
		
		IPhoneCommunicationManagement phoneCommMan = ctx.getBean(IPhoneCommunicationManagement.class);
		phoneCommMan.startListening();
		s_log.info("startup finished");
		
		DesktopCli cli = ctx.getBean(DesktopCli.class);
		cli.getReader().startReadingFromCLI();
	}
}
