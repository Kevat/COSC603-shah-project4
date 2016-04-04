package edu.towson.cis.cosc603.project4.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The unit test Class for VendingMachineItem.
 */
public class VendingMachineItemTest {
	/** Declare necessary test objects for {@link VendingMachineItem} */
	VendingMachineItem vmItem1, vmItem2;
	
	/**
	 * Initializes the necessary test objects for the test cases to use.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
	
	}
	
	/**
	 * Test for the constructor method of the {@link VendingMachineItem} class
	 * when given valid input (price greater than or equal to 0).
	 */
	@Test
	public void testConstructorValid() {
		vmItem1 = new VendingMachineItem("New Item 1", 10.23);
		assertNotNull(vmItem1);
		
		vmItem2 = new VendingMachineItem("New Item 2", 0);
		assertNotNull(vmItem2);
	}
	
	/**
	 * Test for the getName() method of the {@link VendingMachineItem} class
	 */
	@Test
	public void testGetName() {
		vmItem1 = new VendingMachineItem("New Item 1", 10.23);
		assertEquals("New Item 1", vmItem1.getName());
		
		vmItem2 = new VendingMachineItem("New Item 2", 0);
		assertEquals("New Item 2", vmItem2.getName());
	}
	
	
	/**
	 * Test for the constructor method of the {@link VendingMachineItem} class
	 * when given valid input (price greater than or equal to 0).
	 */
	@Test
	public void testGetPrice() {
		vmItem1 = new VendingMachineItem("New Item 1", 10.23);
		assertEquals(10.23, vmItem1.getPrice(), 0.000);
		
		vmItem2 = new VendingMachineItem("New Item 2", 0);
		assertEquals(0, vmItem2.getPrice(), 0.000);
	}
		
	
	/**
	 * Test for the constructor method of the {@link VendingMachineItem} class
	 * when given invalid input (price less than 0).
	 */
	@Test(expected=VendingMachineException.class)
	public void testConstructorInvalid() {
		vmItem1 = new VendingMachineItem("New Item 1", -1);
	}
	
	
	
	/**
	 * Cleans up test objects after a test case is executed.
	 */
	@After
	public void tearDown(){
		vmItem1 = null;
		vmItem2 = null;
	}
}
