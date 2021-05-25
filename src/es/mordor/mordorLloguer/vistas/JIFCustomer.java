package es.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.alee.laf.table.WebTable;
import com.alee.laf.text.WebTextField;

import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextField;

public class JIFCustomer extends JInternalFrame {
	private  WebTable table; 
	private JPopupMenu popupMenu;
	private JPanel panelSuperior;
	private JButton btnRemove;
	private JButton btnAdd;
	private WebTextField textFieldDni;
	private WebTextField textFieldName;
	private WebTextField textFieldSurname;
	private JButton btnPrint;
	private JComboBox<String> comboBox;
	

	/**
	 * Create the frame.
	 */
	public JIFCustomer() {
		setClosable(true);
		setBounds(100, 100, 867, 556);
		
		JPanel panelCentral = new JPanel();
		
		panelSuperior = new JPanel();
		
		JPanel panelInferior = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelInferior.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelSuperior, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
						.addComponent(panelCentral, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelInferior, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 831, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelSuperior, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelCentral, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelInferior, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		btnAdd = new JButton("A\u00F1adir");
		panelInferior.add(btnAdd);
		
		btnRemove = new JButton("Eliminar");
		panelInferior.add(btnRemove);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelCentral.add(scrollPane, BorderLayout.CENTER);
		
		table = new WebTable();
		table.setAutoResizeMode ( JTable.AUTO_RESIZE_LAST_COLUMN );
        table.setVisibleRowCount ( 5 );
        table.optimizeColumnWidths ( true );
        table.setOptimizeRowHeight ( true );
        table.setEditable ( true );
        popupMenu = new JPopupMenu();
		

		scrollPane.setViewportView(table);
	
		
		panelSuperior.setLayout(new MigLayout("", "[200][grow][200][grow][200][20][][100][grow][50][50]", "[]"));
		
		textFieldDni = new WebTextField();
		textFieldDni.setInputPrompt("Dni");
		panelSuperior.add(textFieldDni, "cell 0 0,growx");
		textFieldDni.setColumns(10);
		
		textFieldName = new WebTextField();
		textFieldName.setInputPrompt("Nombre");
		panelSuperior.add(textFieldName, "cell 2 0,growx");
		textFieldName.setColumns(10);
		
		textFieldSurname = new WebTextField();
		textFieldSurname.setInputPrompt("Apellidos");
		panelSuperior.add(textFieldSurname, "cell 4 0,growx");
		textFieldSurname.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Carnet conducir");
		panelSuperior.add(lblNewLabel, "cell 6 0");
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("All");
		comboBox.addItem("A");
		comboBox.addItem("B");
		comboBox.addItem("C");
		comboBox.addItem("D");
		comboBox.addItem("E");
		comboBox.addItem("Z");
		panelSuperior.add(comboBox, "cell 7 0,growx");
		
		btnPrint = new JButton();
		btnPrint.setIcon(new ImageIcon(JFMain.class.getResource("/es/mordor/mordorLloguer/recursos/printer.png")));
		panelSuperior.add(btnPrint, "cell 10 0");
		getContentPane().setLayout(groupLayout);

	}
	
	

	public JPanel getPanelSuperior() {
		return panelSuperior;
	}



	public WebTextField getTextFieldDni() {
		return textFieldDni;
	}



	public WebTextField getTextFieldName() {
		return textFieldName;
	}



	public WebTextField getTextFieldSurname() {
		return textFieldSurname;
	}



	public JButton getBtnPrint() {
		return btnPrint;
	}



	public JComboBox<String> getComboBox() {
		return comboBox;
	}



	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}


	public JButton getBtnAdd() {
		return btnAdd;
	}


	public JButton getBtnRemove() {
		return btnRemove;
	}


	public WebTable getTable() {
		return table;
	}
	
}


