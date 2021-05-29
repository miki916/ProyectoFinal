package es.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.*;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

public class JIFVehicles extends JInternalFrame {
	private JPVehicles panelCar;
	private JPVehicles panelVan;
	private JPVehicles panelTruck;
	private JPVehicles panelMiniBus;
	private JTabbedPane tabbedPane;

	/**
	 * Create the frame.
	 */
	public JIFVehicles() {
		
		setBounds(100, 100, 867, 556);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		panelCar = new JPVehicles();
		tabbedPane.addTab("Coche", null, panelCar, null);
		
		
			
		panelVan = new JPVehicles();
		tabbedPane.addTab("Furgoneta", null, panelVan, null);
		
		panelTruck = new JPVehicles();
		tabbedPane.addTab("Camion", null, panelTruck, null);
		
		panelMiniBus = new JPVehicles();
		tabbedPane.addTab("MiniBus", null, panelMiniBus, null);
	
		
	}
	
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}


	public JPVehicles getPanelCar() {
		return panelCar;
	}


	public JPVehicles getPanelVan() {
		return panelVan;
	}


	public JPVehicles getPanelTruck() {
		return panelTruck;
	}


	public JPVehicles getPanelMiniBus() {
		return panelMiniBus;
	}
	
	

}
