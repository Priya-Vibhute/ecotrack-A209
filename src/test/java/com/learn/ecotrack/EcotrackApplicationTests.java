package com.learn.ecotrack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EcotrackApplicationTests {

	@Test
	void contextLoads() {
	}
	
	private Calculator calculator;
	
	@BeforeEach
	void setUp()
	{
		calculator=new Calculator();
	}
	
	@Test
	void testAdd()
	{
		int result = calculator.add(8, 2);
		assertEquals(10,result);
		assertEquals(16,calculator.add(7, 9));
	}
	
	@Test
	void testDivide()
	{
		assertEquals(3, calculator.divide(6, 2));
		assertEquals(6, calculator.divide(6, 1));
		
		assertThrows(IllegalArgumentException.class, ()->{
			calculator.divide(5, 0);
		});
	}
	
	@Test
	void testProcessOrder()
	{
		 Order order=new Order();
		 assertThrows(NullPointerException.class,()->order.processOrder(null, 3, 400));
		 assertThrows(NullPointerException.class,()->order.processOrder("", 3, 400));
		 assertThrows(IllegalArgumentException.class, ()->order.processOrder("P1", -8, 78));
         assertEquals("Order placed successfully", order.processOrder("P1", 50, 2500));
		
	}
	
	@Test
	void testIsEven()
	{
		assertTrue(calculator.isEven(8));
		assertTrue(calculator.isEven(6));
		assertTrue(calculator.isEven(2));
		assertFalse(calculator.isEven(5));
		assertFalse(calculator.isEven(3));
		assertFalse(calculator.isEven(1));
	}
	
	

}
