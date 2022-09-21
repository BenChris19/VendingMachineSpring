package controller;

import dao.VendingMachinePersistenceException;
import service.DataValidationException;
import service.InsufficientFundsException;
import service.InvalidItemException;
import service.NoItemInventoryException;
import service.VendingMachineServiceLayer;
import ui.VendingMachineView;
import dto.Change;
import dto.Item;
import dto.OptionEnter;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Controller for vending machine. Handles user interaction
 * @author benat
 *
 */
@Component
public class VendingMachineController {
	
	@Autowired
	private VendingMachineView view;
	private VendingMachineServiceLayer serviceLayer;
	
	/**
	 * Constructor for Vending machine controller, connected with both service layer and view
	 * @param serviceLayer
	 * @param view
	 */
	public VendingMachineController(VendingMachineServiceLayer serviceLayer, VendingMachineView view) {
		this.serviceLayer = serviceLayer;
		this.view = view;
	}
	
	/**
	 * Start the controller application, prompts the user with the items in the vending machine and
	 * a choice whether to buy or not buy an item from the vending machine.
	 * 
	 * @throws InvalidItemException
	 * @throws VendingMachinePersistenceException
	 * @throws InsufficientFundsException
	 * @throws NoItemInventoryException
	 * @throws DataValidationException
	 */
	public void run() throws InvalidItemException, VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException, DataValidationException {
		boolean carryOn = true;
		OptionEnter selection = null;
		
		//try catch
		while(carryOn) {
			selection = getMenuSelection(returnAllItems());
			switch(selection) {
				case YES:
					selectItem();
					break;
				case NO:
					carryOn = false;
					break;
			}		
		}
		exitMessage();
	}
	
	/**
	 * Select an item from the vending machine. Throw error if insufficient funds, item is currently unavailable or item does not exist.
	 * 
	 * @throws InsufficientFundsException
	 * @throws NoItemInventoryException
	 * @throws VendingMachinePersistenceException
	 * @throws DataValidationException
	 * @throws InvalidItemException
	 */
	public void selectItem() throws InsufficientFundsException, NoItemInventoryException, VendingMachinePersistenceException, DataValidationException, InvalidItemException {
		BigDecimal userFunds = view.insertFunds();
		String userSelection = view.selectItem();
		Change change = serviceLayer.buyItems(userSelection, userFunds);
		view.displayChange(change.getCoins());
		view.displaySuccesFullPurchase();
	}

	/**
	 * Get all items from vending machine and show them to the user.
	 * @return
	 * @throws VendingMachinePersistenceException
	 */	
	public List<Item> returnAllItems() throws VendingMachinePersistenceException {
		List<Item> items = serviceLayer.getAllItems();
		return items;
	}
	
	/**
	 * Get the option from the user if they want to buy an item from the vending machine or leave
	 * @param items
	 * @return
	 */
	public OptionEnter getMenuSelection(List<Item> items) {
		return view.menuAndShowItems(items);
	}
	
	/**
	 * Exit the programme if the user desires to.
	 */
	public void exitMessage() {
		view.displayExitMessage();
	}
}
