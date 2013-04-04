package edu.ycp.cs320.magicprogram.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;

import edu.ycp.cs320.magicprogram.shared.*;

public class GameView extends Composite{
	// Constants
	private final int WIDTH = 500;
	private final int HEIGHT = 500;
	
	// Widgets and Panels
	public Canvas canvas;
	
	// Fields
	private Game model;
	private int selectionX;
	private int selectionY;
	private boolean showGrid;
	private int unitX;
	private int unitY;
	
	private Button btnAddCreepTo;
	
	public GameView(Game game) {
		// GAME
		model = game;
		showGrid = true;
		
		// Calculate UNITS
		unitX = WIDTH / model.getTowers()[0].length;
		unitY = HEIGHT / model.getTowers().length;
		
		// CANVAS
		canvas = Canvas.createIfSupported();
		canvas.setSize(WIDTH + "px", HEIGHT + "px");
		canvas.setCoordinateSpaceHeight(HEIGHT);
		canvas.setCoordinateSpaceWidth(WIDTH);
		
		// MOUSE MOVE HANDLER
		canvas.addMouseMoveHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				selectionX = event.getX() - (event.getX() % unitX);	//snap selector to grid squares
				selectionY = event.getY() - (event.getY() % unitY);
			}
		});
		
		// MOUSE DOWN HANDLER
		canvas.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				model.getTowers()[selectionY / unitY][selectionX / unitX] = new Tower();
				model.setTerrain((selectionY / unitY), (selectionX / unitX), Terrain.tower);
				//Add location of tower to an arraylist of points to compare against creep points
				//model.setTowpoint((selectionY/unitY), (selectionX / unitX));
			}
		});
		
		// LAYOUT PANEL
		VerticalPanel panel = new VerticalPanel();
		panel.add(canvas);
		
		
		// INIT WIDGET
		initWidget(panel);
		
		Button btnNewButton = new Button("Add a creep");
		panel.add(btnNewButton);
		setSize(WIDTH + "px", HEIGHT + "px");
	    btnNewButton.addClickHandler(new ClickHandler() {
	    	public void onClick(ClickEvent event) {
	    		model.addCreep();
	    	}
	    });
		
	}
	
	public void start() {
	    // setup timer
	    Timer timer = new Timer() {
	      @Override
	      public void run() {
	    	  model.update();
	    	  update();
	      }
	    };
	    timer.scheduleRepeating(25);
	}
	
	public void setModel(Game game) {
		this.model = game;
	}
	
	public void update() {
		Context2d context = canvas.getContext2d();
		context.beginPath();
		
		// REFRESH
		context.clearRect(0, 0, WIDTH, HEIGHT);
		
		// ----GRID
		if (showGrid) {
			context.setStrokeStyle("#000000");
			for (int y = 0; y < HEIGHT; y += unitY) {
				context.moveTo(0, y - 0.5);
				context.lineTo(WIDTH, y - 0.5);
				context.stroke();
			}
			for (int x = 0; x < WIDTH; x += unitX) {
				context.moveTo(x - 0.5, 0);
				context.lineTo(x - 0.5, HEIGHT);
				context.stroke();
			}
		
			// DRAW SELECTOR
			context.setFillStyle("#0000FF");
			context.fillRect(selectionX, selectionY, unitX, unitY);
		}
		
//		// draw TOWERS
//		Tower towers[][] = model.getTowers();
//		context.setFillStyle("#FF0000");
//		for (int row = 0; row < towers.length; row++) {
//			for (int col = 0; col < towers[row].length; col++) {
//				if (towers[row][col] != null) {
//					context.fillRect(col * unitX, row * unitY, unitX, unitY);
//					
//				}
//				
//			}
//		}
		Terrain grid[][] = model.getTerrain();
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				if (grid[row][col] == Terrain.tower) {
					//Range outline
					context.setFillStyle("red");
					context.fillRect(col * unitX-15, row * unitY-15, unitX+30, unitY+30);
					//Inner range
					context.setFillStyle("white"); 
					context.fillRect(col * unitX-13, row * unitY-13, unitX+26, unitY+26);
					
					//Towers
					context.setFillStyle("blue");
					context.fillRect(col * unitX, row * unitY, unitX, unitY);
					
				}
				
			}
		}
		
		
		// DRAW CREEPS
		context.setFillStyle("#FFE303");
		for (Creep curr : model.getCreeps()) {
			context.fillRect(curr.getPos().getX(), curr.getPos().getY(), curr.getSize(), curr.getSize());
		}
	}
	
	public void toggleGrid() {
		showGrid = !showGrid;
	}
	
}
