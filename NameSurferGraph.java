/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		map = new HashMap<String,NameSurferEntry>();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		map.clear();
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		map.put (entry.getName(),entry);
		update();
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		drawGrid();
		drawMap();
	}
	
	
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private HashMap<String, NameSurferEntry> map;
	private void drawMap()
	{
		
		
		for (String name: map.keySet())
		{
			
			switch (k % 4)
			{
			case 0:
				color = Color.BLACK;
				break;
			case 1:
				color = Color.RED;
				break;
			case 2:
				color = Color.GREEN;
				break;
			case 3:
				color = Color.BLUE;
				break;
			}
			k++;
			
			for (int i = 0; i < NDECADES; i++)
				{
				GLabel label;
				GLine line;
					int last = map.get(name).getRank(i);
					
					if(last == 0)
					{
						last = 1000;
						
						label = new GLabel(map.get(name).getName() + "*");
					}
					else label = new GLabel(map.get(name).getName() +" "+ last);
					label.setColor(color);
					last = last * (getHeight()- 2*GRAPH_MARGIN_SIZE)/1000;
					add(label, getWidth()/NDECADES * i , last + GRAPH_MARGIN_SIZE);
					
					if(i < NDECADES-1)
					{
						int next = map.get(name).getRank(i+1);
						if(next == 0)
							{
								next = 1000;
							}
						next = next * (getHeight() - 2* GRAPH_MARGIN_SIZE)/1000;
						line = new GLine(getWidth()/NDECADES * i , last + GRAPH_MARGIN_SIZE, getWidth()/NDECADES * (i+1), next + GRAPH_MARGIN_SIZE);
						line.setColor(color);
						add(line);
					}
					
				}
			
		}
	}
	
	private Color color;
	private int k = 0;
	private void drawGrid()
	{
		for (int i = 0; i < NDECADES; i++)
		{
			add(new GLine(getWidth()/NDECADES * i, 0, getWidth()/NDECADES * i, getHeight()));
		}
		add(new GLine (0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
		add(new GLine (0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE));
		
		for (int i = 0; i < NDECADES; i++)
		{
			int date = START_DECADE + i*10;
			add(new GLabel(date+""),getWidth()/NDECADES * i + 5, getHeight() - 5);
		}
	}
}
