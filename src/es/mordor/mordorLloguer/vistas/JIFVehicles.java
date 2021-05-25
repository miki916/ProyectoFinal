package es.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.*;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

public class JIFVehicles extends JInternalFrame {


	/**
	 * Create the frame.
	 */
	public JIFVehicles() {
		
		setBounds(100, 100, 645, 501);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPVehicles panelCar = new JPVehicles();
		tabbedPane.addTab("Coche", null, panelCar, null);
		
		
			
		JPVehicles panelVan = new JPVehicles();
		tabbedPane.addTab("Furgonea", null, panelVan, null);
		
		JPVehicles panelTruck = new JPVehicles();
		tabbedPane.addTab("Camion", null, panelTruck, null);
		
		JPVehicles panelMiniBus = new JPVehicles();
		tabbedPane.addTab("MiniBus", null, panelMiniBus, null);
	
		
	}

}
