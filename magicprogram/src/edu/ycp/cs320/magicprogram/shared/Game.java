package edu.ycp.cs320.magicprogram.shared;

import java.util.ArrayList;

public class Game {
	// CONSTANTS
	public final int ROW = 25;
	public final int COL = 25;
	
	private final Point BOUNDS = new Point(500.0, 500.0);
	
	// FIELDS
	private int life = 10;
	private ArrayList<Creep> creeps;
	private ArrayList<Structure> structures;
	private Terrain[][] map = new Terrain[ROW][COL];
	private ArrayList<Point> waypoints;
	private double gridUnit;
	
	public Game() {
		// WAYPOINTS
		waypoints = new ArrayList<Point>();

		waypoints.add(new Point(50.0,0.0));
		waypoints.add(new Point(50.0,50.0));
		
		// CREEPS
		setCreeps(new ArrayList<Creep>());
		setPath(new ArrayList<Point>());
		
		// TERRAIN
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				map[row][col] = Terrain.grass;
			}
		}
		map[0][0] = Terrain.road;
		map[1][0] = Terrain.road;
		map[2][0] = Terrain.road;
		map[3][0] = Terrain.road;
		map[4][0] = Terrain.road;
		map[5][0] = Terrain.road;
		map[5][0] = Terrain.road;
		map[5][0] = Terrain.road;
		map[5][1] = Terrain.road;
		map[5][2] = Terrain.road;
		map[5][3] = Terrain.road;
		map[5][4] = Terrain.road;
		map[5][5] = Terrain.road;
		map[5][6] = Terrain.road;
		map[5][7] = Terrain.road;
		map[5][8] = Terrain.road;
		map[5][9] = Terrain.road;
		map[5][10] = Terrain.road;
		map[5][11] = Terrain.road;
		map[5][12] = Terrain.road;
		map[5][13] = Terrain.road;
		map[5][14] = Terrain.road;
		map[5][15] = Terrain.road;
		map[5][16] = Terrain.road;
		map[5][17] = Terrain.road;
		map[5][18] = Terrain.road;
		map[5][19] = Terrain.road;
		map[5][20] = Terrain.road;
		map[5][21] = Terrain.road;
		map[5][22] = Terrain.road;
		map[5][23] = Terrain.road;
		map[5][24] = Terrain.road;
		map[6][24] = Terrain.road;
		map[7][24] = Terrain.road;
		map[8][24] = Terrain.road;
		map[9][24] = Terrain.road;
		map[10][24] = Terrain.road;
		map[11][24] = Terrain.road;
		map[12][24] = Terrain.road;
		map[13][24] = Terrain.road;
		map[14][24] = Terrain.road;
		map[15][24] = Terrain.road;
		map[16][24] = Terrain.road;
		map[17][24] = Terrain.road;
		map[18][24] = Terrain.road;
		map[19][24] = Terrain.road;
		map[20][24] = Terrain.road;
		map[21][24] = Terrain.road;
		map[22][24] = Terrain.road;
		map[23][24] = Terrain.road;
		map[24][24] = Terrain.road;
		
		gridUnit = BOUNDS.x() / COL;
		
		structures = new ArrayList<Structure>();
		structures.add(new Structure( Structure.Type.base, new Point(BOUNDS.x() - gridUnit, BOUNDS.y() - gridUnit), ROW));
	}
	
	public boolean buildStructure(Structure.Type type, int row, int col) {
		Structure newStruct = new Structure(type, new Point(col * COL, row * ROW), col);
		if (canBuildStructure(newStruct)) {
			structures.add(newStruct);
			return true;
		}
		return false;
	}
	
	public boolean canBuildStructure(Structure newStruct) {
		Point gp = newStruct.getGridPoint();
		// check if there is another structure at the location
		for (Structure s : structures) {
			if (newStruct.getTopLeft().equals(s.getTopLeft())) {
				return false;
			}
		}
		// check if the terrain is buildable
		return (map[(int) gp.y()][(int) gp.x()] == Terrain.grass);
	}
	
	// Methods
	/**
	 * Adds a default creep to the board. The creep is given a path to follow
	 */
	public void addCreep() {
		creeps.add(new Creep(new Point(10, 10)));
	}
	
	public void update() {
		for (Creep creep : creeps){					// CREEPS
			creep.move();								// move creeps
		}
		for (Structure structure : structures) {	// STRUCTURES
				switch (structure.getType()) {
					case base:							// BASE
						break;								// do nothing
					case spawner:						// SPAWNER
						addCreep();
						break;
					case tower:							// TOWER
						for (Creep creep : creeps) {		// look at creeps
							if (structure.getCenter().distanceTo(creep.getCenter()) <= structure.getRange());	// if creep is in range
//							creep.setHp(creep.getHP() - structure.getDamage());
						}									
						break;
					default:
						break;
				}
			
		}
	}
	
	public boolean buildStructure(Structure newStruct) {
		if (canBuildStructure(newStruct)) {
			structures.add(new Structure(newStruct));
			return true;
		}
		return false;
	}
	
//	public boolean canBuildStructure(Structure newStruct) {
//		// check if there is another structure at the location
//		for (Structure s : structures) {
//			if (newStruct.getTopLeft().equalTo(s.getTopLeft())) {
//				return false;
//			}
//		}
//		// check if the terrain is buildable
//		System.out.println("Grid point of new structure = (" + newStruct.gp().x() +  ", " + newStruct.gp().y() + ")");
//		return (map[(int)newStruct.gp().y()][(int)newStruct.gp().x()] == Terrain.grass);
//	}
	
	// Getters/Setters
	public ArrayList<Creep> getCreeps() {
		return creeps;
	}
	public void setCreeps(ArrayList<Creep> creeps) {
		this.creeps = creeps;
	}
	public ArrayList<Structure> getStructures() {
		return structures;
	}
	public void setStructures(ArrayList<Structure> structures) {
		this.structures = structures;
	}
	public ArrayList<Point> getPath() {
		return waypoints;
	}
	public void setPath(ArrayList<Point> waypoints) {
		this.waypoints = waypoints;
	}
	public ArrayList<Point> getWaypoints() {
		return waypoints;
	}
	public Terrain[][] getMap() {
		return map;
	}
	public void setMap(Terrain[][] map) {
		this.map = map;
	}
	public ArrayList<Structure> getStructs() {
		return structures;
	}
	public double getGridUnit() {
		return gridUnit;
	}
	public void setGridUnit(double gridUnit) {
		this.gridUnit = gridUnit;
	}
}
