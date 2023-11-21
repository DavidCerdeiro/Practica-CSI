package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMain {

	private JFrame frmFtbolFantstico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain window = new FrmMain();
					window.frmFtbolFantstico.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public FrmMain() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFtbolFantstico = new JFrame();
		frmFtbolFantstico.setTitle("Fútbol Fantástico");
		frmFtbolFantstico.setBounds(500, 170, 511, 518);
		frmFtbolFantstico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFtbolFantstico.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 497, 22);
		frmFtbolFantstico.getContentPane().add(menuBar);
		
		JMenu mnuNew = new JMenu("Nuevo");
		
		menuBar.add(mnuNew);
		
		JMenuItem mitNewStadium = new JMenuItem("Stadium");
		mitNewStadium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ShowInternalFrame(new IfrStadium(), 100, 270, 400, 192);
				new IfrStadium().show();		//Se utiliza show ya que a nosotros no nos funciona InternalFrame (puesto arriba).
			}
		});
		mnuNew.add(mitNewStadium);
		
		JMenu mnuSearch = new JMenu("Buscar");
		menuBar.add(mnuSearch);
		
		JMenuItem mitSearchStadium = new JMenuItem("Stadium");
		mnuSearch.add(mitSearchStadium);
	}
	
	public void ShowInternalFrame(JFrame ifr, int iX, int iY, int iWidth, int iHeight) {
		ifr.setBounds(iX, iY, iWidth, iHeight);
		frmFtbolFantstico.getContentPane().add(ifr);
		ifr.setVisible(true);
	}

}
