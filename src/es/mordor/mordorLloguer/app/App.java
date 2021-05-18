package es.mordor.mordorLloguer.app;	

import java.awt.EventQueue;

import com.alee.laf.WebLookAndFeel;

import es.mordor.morderLloguer.model.BBDD.*;
import es.mordor.mordorLloguer.controladores.ControladorPrincipal;
import es.mordor.mordorLloguer.vistas.JFPrincipal;


public class App {

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WebLookAndFeel.install();
					JFPrincipal frame = new JFPrincipal();
					AlmacenDatosDB modelo=new MyOracleDataBase();

					ControladorPrincipal c = new ControladorPrincipal(frame,modelo);
					c.go();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
