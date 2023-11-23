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
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IfrStadiums extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTable tabResult;

	/**
	 * Create the frame.
	 */
	public IfrStadiums(FrmMain frmMain) {
		setTitle("Estadios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblName = new JLabel("Name");
		panel.add(lblName);
		
		txtName = new JTextField();
		panel.add(txtName);
		txtName.setColumns(10);
		
		JButton butSearch = new JButton("Buscar");
		butSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tabResult.setModel( new StadiumsTableModel(Stadium.Search(txtName.getText())));
				} catch (IOException | SQLException ex) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} }
		});
		
		panel.add(butSearch);
		
		tabResult = new JTable();
		tabResult.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			 if (e.getClickCount() == 2) { 
				 int iRow = ((JTable)e.getSource()).getSelectedRow();
				 Stadium stadium = ((StadiumsTableModel)tabResult.getModel()).getData(iRow);
				 if (stadium != null)
					 //frmMain.ShowInternalFrame(new IfrStadium(stadium), 10, 27, 400, 100);
					 new IfrStadium(stadium).show(); //Se utiliza show ya que a nosotros no nos funciona InternalFrame (puesto arriba).
			 }
		}
		});
		contentPane.add(tabResult, BorderLayout.WEST);

	}
}

