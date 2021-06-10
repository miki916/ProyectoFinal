package es.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JIFCargar extends JInternalFrame {
	private JLabel lblTask;
	private JButton btnCancel;
	private JProgressBar progressBar;
	private SwingWorker<?,?> task;
	/**
	 * Create the frame.
	 */
	public JIFCargar() {
		
		
		setBounds(100, 100, 412, 304);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setForeground(Color.GREEN);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				task.cancel(true);
				dispose();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(147)
					.addComponent(btnCancel)
					.addContainerGap(157, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnCancel)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		lblTask = new JLabel("");
		lblTask.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblPleaswWait = new JLabel("Please wait");
		lblPleaswWait.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaswWait.setFont(new Font("DejaVu Serif", Font.BOLD, 12));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(lblTask, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
				.addComponent(lblPleaswWait, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(48)
					.addComponent(lblTask)
					.addGap(42)
					.addComponent(lblPleaswWait)
					.addContainerGap(62, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
	

	public JIFCargar(SwingWorker<?, ?> task) {
		this();
		this.task = task;
	}


	public JLabel getLblTask() {
		return lblTask;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}


	public JProgressBar getProgressBar() {
		return progressBar;
	}
}
