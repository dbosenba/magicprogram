/*
package edu.ycp.cs320.magicprogram.shared;

public class Creep {
	// Physical attributes
	private Rectangle body;
	private double range;
	private boolean dead;
	private double speed;
	private Point waypoint;
	
	
	// Constructors
	public Creep(Rectangle body, double range, double speed, Point waypoint) {
		setBody(body);
		setRange(range);
		setDead(false);
		this.speed = speed;
		this.setWaypoint(waypoint);
	}
	
	
	// Getters/Setters
	public Rectangle getBody() {
		return body;
	}
	public void setBody(Rectangle body) {
		this.body = body;
	}
	public double getRange() {
		return range;
	}
	public void setRange(double range) {
		this.range = range;
	}
	public Point getCenter() {
		return body.getCenter();
	}
	public Point getWaypoint() {
		return waypoint;
	}
	public void setWaypoint(Point waypoint) {
		this.waypoint = waypoint;
	}
	

	//Methods
	/**
	 * Move towards the waypoint
	 * @param wayPoint
	 *//*

	public void move(Point wayPoint) {
		double newX = body.getTopLeft().getX();
		double newY = body.getTopLeft().getY();
		if (body.getCenter().getX() < wayPoint.getX()) {
			newX += speed;			
		}
		else {
			newX -= speed;
		}
		if (body.getCenter().getY() < wayPoint.getY()) {
			newY += speed;		
		}
		else {
			newY -= speed;
		}
		body.setTopLeft(new Point(newX, newY));
	}
	
	public void kill() {
		setDead(true);
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
}
*/
package edu.ycp.cs320.magicprogram.shared;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Creep {
	// Physical attributes
	private Point position;
	private int size;
	private double range = 1;
	private double speed = 1;
	private int hp = 10;
	private Stack<Point> path;
	private Rectangle body;
	private Point waypoint;
	
	// Constructors
	public Creep(Point position, ArrayList<Point> waypoints) {
		System.out.println("making new creep");
		speed = 5;
		
		this.position = position;
		
		size = 50;
		
		path = new Stack<Point>();
		Collections.reverse(waypoints);
		for (Point waypoint : waypoints) {
			System.out.println("Added waypoint");
			path.push(waypoint);
		}
		Collections.reverse(waypoints);
	}
	
	// Getters/Setters
	public Rectangle getBody() {
		return body;
	}
	public void setBody(Rectangle body) {
		this.body = body;
	}
	public double getRange() {
		return range;
	}
	public void setRange(double range) {
		this.range = range;
	}
	public Point getCenter() {
		return body.getCenter();
	}
	public Point getWaypoint() {
		return waypoint;
	}
	public void setWaypoint(Point waypoint) {
		this.waypoint = waypoint;
	}
	public int getHP() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public Point getPos() {
		return position;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	//Methods
	/**
	 * Move towards the next waypoint
	 */
	public void move() {
		if (!path.isEmpty()) {
			System.out.println("next point in path: " + path.peek().getX()+ ", " + path.peek().getY());
			
			if (speed > position.distanceTo(path.peek())) {
				System.out.println("too close to point" + path.peek().getX()+ ", " + path.peek().getY());
				// the creep will overshoot the point
				// solution: the creep goes to the point
				position = path.pop();
			}
			else {
//				 Full step needed on x-axis
				if (position.getX() < path.peek().getX()) {
					System.out.println("moving towards " + path.peek().getX()+ ", " + path.peek().getY());
					position.addX(speed);
				}
				else {
					position.addX(-1 * speed);
				}
				// Full step needed on y-axis
				if (position.getY() < path.peek().getY()) {
					System.out.println("moving towards " + path.peek().getX()+ ", " + path.peek().getY());
					position.addY(speed);	
				}
				else {
					position.addY(-1 * speed);
				}
			}
		}
		else {
			System.out.println("Path is empty");
			
		}
	}
	
	public void kill() {
		this.hp = 0;
	}
}

