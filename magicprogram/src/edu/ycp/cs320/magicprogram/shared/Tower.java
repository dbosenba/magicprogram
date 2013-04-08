package edu.ycp.cs320.magicprogram.shared;


import java.util.ArrayList;
import java.util.Stack;


public class Tower {
	// Physical attributes
	
	//private Point position;
	// 
	private Rectangle towerBlock;
	private double range;
	
	
	/*
	public Tower(Point inPoint){
		this.towerBlock.topLeft = inPoint;
		//this.range = inputRange;
	}*/
	
	
	
	//Goal:
	//Create a way of comparing tower locations in the array to creep points. 
	//
	//Plan:
	//When towers are made, add x/y coordinates in array to a stack of points which will be compared against creep points.
	//If the creeps are within range, remove them.
	


	public Rectangle getBlock() {
		return this.towerBlock;
	}
	
	public double getRange() {
		return this.range;
	}
	

	

	
}

	
