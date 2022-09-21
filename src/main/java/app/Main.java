package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import controller.VendingMachineController;
import dao.VendingMachineAuditDao;
import dao.VendingMachineAuditDaoImpl;
import dao.VendingMachineDao;
import dao.VendingMachineDaoImpl;
import service.VendingMachineServiceLayer;
import service.VendingMachineServiceLayerImpl;
import ui.UserIO;
import ui.UserIOImpl;
import ui.VendingMachineView;

/**
 * Entry point of our vending machine app.
 * The Main class acts as the application assembler, it chooses the implementations of the dependencies and 
 * wires them together.
 * @author benat
 *
 */
public class Main {

	/**
	 * Main method. Use dependency injection and wire the entire application. Follow MVC tier application
	 */
	public static void main(String[] args) throws Exception {
		//Use the XML file for configuration and inject objects into classes
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
		controller.run();

}
}
