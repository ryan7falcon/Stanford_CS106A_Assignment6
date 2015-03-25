/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;

import java.awt.event.*;

import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/**
	 * 
	 */
	private static final long serialVersionUID = 8220435172668894131L;
	public static void main(String[] args)
	{
		new NameSurfer().start(args);
	}
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		add(new JLabel("Name"),SOUTH);
		
		nameField = new JTextField(20);
		nameField.addActionListener(this);
		nameField.setActionCommand("Graph");
		add(nameField,SOUTH);
		
		add(new JButton("Graph"), SOUTH);
		
		add(new JButton("Clear"), SOUTH);
		
		addActionListeners();
		
		database = new NameSurferDataBase("names-data.txt");
		graph = new NameSurferGraph();
		add(graph);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Graph"))
			if (database.findEntry(nameField.getText()) != null)
				graph.addEntry(database.findEntry(nameField.getText()));
				//println(database.findEntry(nameField.getText()).toString());
		if (e.getActionCommand().equals("Clear"))
			graph.clear();
			//println("Clear");
	}
	
	/*ivars*/
	private JTextField nameField;
	private NameSurferDataBase database;
	private NameSurferGraph graph;
}
