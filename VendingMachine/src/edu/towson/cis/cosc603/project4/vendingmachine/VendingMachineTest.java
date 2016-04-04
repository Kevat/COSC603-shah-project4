package edu.towson.cis.cosc603.project4.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The unit test Class for VendingMachine.
 */
public class VendingMachineTest {
	/** Declare necessary test objects for {@link VendingMachine} */
	VendingMachineItem vmItem1, vmItem2, vmItem3, vmItem4;
	VendingMachine vm1;
	
	/**
	 * Initializes the necessary test objects for the test cases to use.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		vm1 = new VendingMachine();
		vmItem1 = new VendingMachineItem("Item 1", 10);
		vmItem2 = new VendingMachineItem("Item 2", 5.50);
		vmItem3 = new VendingMachineItem("Item 2", 5.50);
		vmItem4 = new VendingMachineItem("Item 2", 5.50);
	}
	
	
	/**
	 * Test for the addItem() method of the {@link VendingMachineItem} class
	 * when given a valid code for an unoccupied slot.
	 */
	@Test
	public void testAddItemValid(){
		//Check precondition
		assertNull(vm1.getItem("A"));
		//Add Item
		vm1.addItem(vmItem1, "A");
		assertNotNull(vm1.getItem("A"));
		assertEquals(vmItem1, vm1.getItem("A"));		
		
		//Check precondition
		assertNull(vm1.getItem("B"));
		//Add Item
		vm1.addItem(vmItem2, "B");
		assertNotNull(vm1.getItem("B"));
		assertEquals(vmItem2, vm1.getItem("B"));

		//Check precondition
		assertNull(vm1.getItem("C"));
		//Add Item
		vm1.addItem(vmItem3, "C");
		assertNotNull(vm1.getItem("C"));
		assertEquals(vmItem3, vm1.getItem("C"));

		//Check precondition
		assertNull(vm1.getItem("D"));
		//Add Item
		vm1.addItem(vmItem4, "D");
		assertNotNull(vm1.getItem("D"));
		assertEquals(vmItem4, vm1.getItem("D"));
	}

	
	/**
	 * Test for the addItem() method of the {@link VendingMachineItem} class
	 * when given invalid code.
	 */
	@Test(expected=VendingMachineException.class)
	public void testAddItemInvalidCode(){
		vm1.addItem(vmItem1, "!@#");
	}
	
	
	/**
	 * Test for the addItem() method of the {@link VendingMachineItem} class
	 * when adding item to an occupied slot.
	 */
	@Test(expected=VendingMachineException.class)
	public void testAddItemOccupied(){
		vm1.addItem(vmItem1, "A");
		vm1.addItem(vmItem2, "A");
	}
	

	/**
	 * Test for the removeItem() method of the {@link VendingMachineItem} class
	 * when given a valid code for an occupied slot.
	 */
	@Test
	public void testRemoveItemValid(){
		vm1.addItem(vmItem1, "A");
		vm1.addItem(vmItem2, "B");
		
		assertEquals(vmItem1, vm1.removeItem("A"));
		assertNull(vm1.getItem("A"));
		assertNotNull(vm1.getItem("B"));
		
		assertEquals(vmItem2, vm1.removeItem("B"));
		assertNull(vm1.getItem("B"));
	}

	
	/**
	 * Test for the removeItem() method of the {@link VendingMachineItem} class
	 * when given invalid code.
	 */
	@Test(expected=VendingMachineException.class)
	public void testRemoveItemInvalidCode(){
		vm1.addItem(vmItem1, "A");
		vm1.removeItem("!@#");
	}
	
	
	/**
	 * Test for the removeItem() method of the {@link VendingMachineItem} class
	 * when removing item from an unoccupied slot.
	 */
	@Test(expected=VendingMachineException.class)
	public void testRemoveItemUnoccupied(){
		//Ensure that slot A is empty
		assertNull(vm1.getItem("A"));
		//Remove item
		vm1.removeItem("A");
	}
	
	
	/**
	 * Test for the insertMoney() method of the {@link VendingMachineItem} class
	 * when given a valid amount (greater than or equal to 0).
	 */
	@Test
	public void testInsertMoneyValid(){
		//Ensure that the starting balance is 0
		assertEquals(0, vm1.balance, 0.001);
		
		vm1.insertMoney(10);
		assertEquals(10, vm1.balance,0.001);
		
		vm1.insertMoney(0);
		assertEquals(10, vm1.balance,0.001);
		
		vm1.insertMoney(5);
		assertEquals(15, vm1.balance,0.001);	
	}

	
	/**
	 * Test for the insertMoney() method of the {@link VendingMachineItem} class
	 * when given an invalid amount (less than 0).
	 */
	@Test(expected=VendingMachineException.class)
	public void testInsertMoneyInvalid(){
		vm1.insertMoney(-1);
	}
	

	/**
	 * Test for the getBalance() method of the {@link VendingMachineItem} class
	 */
	@Test
	public void testgetBalance(){
		//Ensure that the starting balance is 0
		assertEquals(0, vm1.balance, 0.001);
				
		vm1.insertMoney(10);
		assertEquals(10, vm1.getBalance(),0.001);
	}


	/**
	 * Test for the makePurchase() method of the {@link VendingMachineItem} class
	 * for a valid scenario (slot not empty, enough balance to purchase
	 */
	@Test
	public void testMakePurchaseValid(){
		vm1.addItem(vmItem1, "A");
		
		//Ensure that the starting balance is 0
		assertEquals(0, vm1.balance, 0.001);
				
		vm1.insertMoney(10);
		//Validate preconditions
		assertTrue(vm1.getBalance() >= 0);
		//Validate purchase
		assertTrue(vm1.makePurchase("A"));		
		//Assert postconditions
		assertEquals(0,vm1.getBalance(),0.001);
		
		vm1.addItem(vmItem2, "B");
		vm1.insertMoney(6);
		//Validate preconditions
		assertTrue(vm1.getBalance() >= 0);
		//Validate purchase
		assertTrue(vm1.makePurchase("B"));		
		//Assert postconditions
		assertEquals(0.5,vm1.getBalance(),0.001);
	}

	

	/**
	 * Test for the makePurchase() method of the {@link VendingMachineItem} class
	 * when balance is insufficient to make purchase
	 */
	@Test
	public void testMakePurchaseInsufficientBalance(){
		vm1.addItem(vmItem1, "A");
		//Ensure that the starting balance is 0
		assertEquals(0, vm1.balance, 0.001);
				
		vm1.insertMoney(5);
		//Validate preconditions
		assertTrue(vm1.getBalance() >= 0);
		//Validate purchase failed
		assertFalse(vm1.makePurchase("A"));		
		//Assert postconditions
		assertEquals(5,vm1.getBalance(),0.001);
	}

	
	/**
	 * Test for the makePurchase() method of the {@link VendingMachineItem} class
	 * when slot is empty
	 */
	@Test
	public void testMakePurchaseEmpty(){
		vm1.addItem(vmItem1, "A");
		//Ensure that the starting balance is 0
		assertEquals(0, vm1.balance, 0.001);
		
		vm1.insertMoney(10);
		//Validate preconditions
		assertTrue(vm1.getBalance() >= 0);
		//Validate purchase failed
		assertFalse(vm1.makePurchase("B"));		
		//Assert postconditions
		assertEquals(10,vm1.getBalance(),0.001);
	}
	
	

	/**
	 * Test for the returnChange() method of the {@link VendingMachineItem} class
	 */
	@Test
	public void testReturnChange(){
		//Ensure that the starting balance is 0
		assertEquals(0, vm1.balance, 0.001);
				
		vm1.insertMoney(5.50);
		assertEquals(5.50, vm1.returnChange(),0.001);
		assertEquals(0,vm1.getBalance(),0.001);
	}
	
	
	/**
	 * Cleans up test objects after a test case is executed.
	 */
	@After
	public void tearDown(){
		vmItem1 = null;
		vmItem2 = null;
		vmItem3 = null;
		vmItem4 = null;
		vm1 = null;
	}
}
