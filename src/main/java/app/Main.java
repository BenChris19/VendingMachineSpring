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

public class Main {

	public static void main(String[] args) throws Exception {
		//Use the XML file for configuration and inject objects into classes
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
		controller.run();

}
}
