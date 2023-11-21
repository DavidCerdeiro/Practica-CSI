package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Stadium;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IfrStadium extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private Stadium _stadium = null;

	/**
	 * Create the frame.
	 */
	public IfrStadium() {
		setTitle("Estadio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 0, 45, 19);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(10, 21, 96, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JButton butSave = new JButton("Guardar");
		
		butSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(_stadium == null) 
						_stadium = new Stadium(txtName.getText());
					else
						_stadium.setName(txtName.getText());
					_stadium.Save();
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		butSave.setBounds(10, 50, 85, 21);
		contentPane.add(butSave);
	}
}
