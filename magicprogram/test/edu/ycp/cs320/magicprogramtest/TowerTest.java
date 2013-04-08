package edu.ycp.cs320.magicprogramtest;

import static junit.framework.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.magicprogram.shared.*;

public class TowerTest {
	private Tower a;
	private Rectangle testBody, setRect;
	private Circle testRange, range2; 
	private double range;
	private Point topLeft;
	
	@Before
	public void SetUp(){
		
		//range = new Circle(getCenter());
		testBody = new Rectangle(new Point(5,5), 5,5);
		setRect = new Rectangle(new Point(6,6), 5,5);
		testRange = new Circle(new Point(5,5),10);
		range2 = new Circle(new Point(8,8), 10);
		topLeft= new Point(1,1);
		range = 5;
		//a = new Tower(topLeft, range);
	}/*
	@Test
	public void testGetBody() {
		assertEquals(testBody, a.getBody());
	}
	
	@Test
	public void testSetBody(){
		a.setBody(setRect);
		assertEquals(setRect, a.getBody());
	}
	
	@Test
	public void testGetRange(){
		assertEquals(testRange, a.getRange());
	}
	
	@Test
	public void testSetRange(){
		a.setRange(range2);
		assertEquals(range2, a.getRange());
		
	}*/
}
