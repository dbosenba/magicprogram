package edu.ycp.cs320.magicprogram.shared;

import java.util.ArrayList;

public class Game {
	public static final double WIDTH = 900;
	//default: 640 x 480 (w x h)
	public static final double HEIGHT = 660;
	
	//fields
	private Rectangle goal;
	private Board board;
	private int life;
	private Creep creep;

	private ArrayList<Creep> creeps;
	private ArrayList<Tower> towers;
	private String[][] grid = new String[10][10];
	private ArrayList<Point> waypoints;
	
	

	public Game() {

		goal = new Rectangle(new Point(430, 300), 50, 50);
		board = new Board();
		life = 20;

		waypoints = new ArrayList<Point>();
		waypoints.add(new Point(50.0,0.0));
		waypoints.add(new Point(50.0,50.0));
		
		//setCreeps(new ArrayList<Creep>());
		
		//setTowers(new ArrayList<Tower>());
		
		//setPath(new ArrayList<Point>());
		
		life = 20;
		
		
	}
	
	
	
	// Methods
	/**
	 * Adds a default creep to the board. The creep is given a path to follow
	 */
	public void addCreep() {
		creeps.add(new Creep(new Point(0.0, 0.0), waypoints));

	}
	
	public Creep getCreeps(){
		return creep;
	}
	
	public void update() {
		if (life > 0) {

			for (Creep creep : board.getCreeps()){
				
				//creep.move(board.getGoal().getCenter());
				
				if(creep.getBody().overlaps(goal)){
					board.getCreeps().remove(creep);
					life=life-1;
				}

			for (Creep creepb : creeps){
				System.out.println("moving creep");
				creep.move();

			}
		}
	}
	}
	
	public Board getBoard(){
		return board;
	}



	public ArrayList<Point> getWaypoints() {
		// TODO Auto-generated method stub
		return waypoints;
	}
}
