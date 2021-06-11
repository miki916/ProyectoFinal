package es.mordor.mordorLloguer.app;

import java.awt.EventQueue;

import com.alee.laf.WebLookAndFeel;

import es.mordor.morderLloguer.model.BBDD.*;
import es.mordor.mordorLloguer.controladores.MainController;
import es.mordor.mordorLloguer.vistas.JFMain;

public class App {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WebLookAndFeel.install();
					JFMain frame = new JFMain();
					AlmacenDatosDB modelo = new MyOracleDataBase();

					MainController c = new MainController(frame, modelo);
					c.go();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
