package es.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.alee.laf.table.WebTable;

import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class JIFEmployees extends JInternalFrame {
	private  WebTable table; ;
	private JComboBox comboBoxOrder;
	private JComboBox comboBoxSort;
	private DefaultComboBoxModel<String> dcmOrder;
	private DefaultComboBoxModel<String> dcmSort;
	private JMenuItem mntmAddRow;
	private JMenuItem mntmDeleteRow;
	private JPopupMenu popupMenu;
	private JPanel panelSuperior;
	private JButton btnRemove;
	private JButton btnAdd;
	

	/**
	 * Create the frame.
	 */
	public JIFEmployees() {
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelCentral, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelSuperior, GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
						.addComponent(panelInferior, GroupLayout.PREFERRED_SIZE, 831, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelSuperior, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
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
		
		mntmAddRow = new JMenuItem("Add row");
		popupMenu.add(mntmAddRow);
		
		mntmDeleteRow = new JMenuItem("Delete row");
		popupMenu.add(mntmDeleteRow);
		scrollPane.setViewportView(table);
	
		
		panelSuperior.setLayout(new MigLayout("", "[][][112.00][10][][][117.00]", "[]"));
		
		JLabel lblOrderBy = new JLabel("Clasificar por");
		panelSuperior.add(lblOrderBy, "cell 0 0");
		
		comboBoxOrder = new JComboBox();
		comboBoxOrder.setModel(new DefaultComboBoxModel(new String[] {"DNI", "Nombre", "Domicilio", "CP", "Email", "FechaNac", "Cargo"}));
		panelSuperior.add(comboBoxOrder, "cell 1 0 2 1,growx");
		
		JLabel lblClasificar = new JLabel("Ordenar por");
		panelSuperior.add(lblClasificar, "cell 5 0,alignx trailing");
		
		comboBoxSort = new JComboBox();
		comboBoxSort.setModel(new DefaultComboBoxModel(new String[] {"Ascendente", "Descendente"}));
		panelSuperior.add(comboBoxSort, "cell 6 0,growx");
		getContentPane().setLayout(groupLayout);

	}

	public JMenuItem getMntmAddRow() {
		return mntmAddRow;
	}


	public JMenuItem getMntmDeleteRow() {
		return mntmDeleteRow;
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


	public JComboBox getComboBoxOrder() {
		return comboBoxOrder;
	}


	public JComboBox getComboBoxSort() {
		return comboBoxSort;
	}
}
