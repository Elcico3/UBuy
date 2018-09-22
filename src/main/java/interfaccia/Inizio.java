package interfaccia;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Inizio extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JPanel pannello = new JPanel();
	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();

	public JButton bRegistrazione = new JButton("Registrazione");
	public JButton bLogin = new JButton("Login");

	private AscoltatoreLogin ascoltatoreBottoneLogin;
	private AscoltatoreRegistrazione ascoltatoreBottoneRegistrazione;

	public static JFrame Confine;

	public JLabel titolo = new JLabel();
	public JLabel titolo2 = new JLabel();

	public Inizio() {

		Confine = this;
		this.setTitle("Benvenuto in U-Buy");

		Confine.setLayout(null);
		final int BASECONFINE = 900;
		final int ALTEZZACONFINE = 600;
		setSize(BASECONFINE, ALTEZZACONFINE);
		Dimension dim = getToolkit().getScreenSize();
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		pannello.setSize(Confine.getWidth(), Confine.getHeight());
		this.add(pannello);
		pannello.setLayout(null);

		final int BORDO = 5;
		final int ALTEZZATITOLO = 30;

		panelTitolo.setLayout(null);
		panelTitolo.setSize(this.getWidth(), ALTEZZATITOLO * 3);
		panelTitolo.setLocation(BORDO, BORDO);
		// panelTitolo.setBounds(0, 0, 800, 400);
		panelTitolo.add(titolo);
		panelTitolo.add(titolo2);

		titolo.setFont(new Font("Verdana", 0, 20));
		titolo.setLocation(BORDO, BORDO);
		titolo.setSize(panelTitolo.getWidth(), 30);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Benvenuti in U-Buy");

		titolo2.setFont(new Font("Verdana", 0, 16));
		titolo2.setLocation(BORDO, ALTEZZATITOLO);
		titolo2.setSize(panelTitolo.getWidth(), 50);
		titolo2.setHorizontalAlignment(JLabel.CENTER);
		titolo2.setVerticalAlignment(JLabel.CENTER);
		titolo2.setText("Effettua il Login oppure se non sei ancora registrato registrati!");

		panelButtons.setLayout(null);
		panelButtons.setSize(getWidth(), 150);
		panelButtons.setLocation(BORDO, ALTEZZATITOLO);

		panelButtons.add(bRegistrazione);

		bRegistrazione.setBorderPainted(false);
		bRegistrazione.setMargin(new Insets(0, 0, 0, 0));
		bRegistrazione.setContentAreaFilled(false);
		bRegistrazione.setOpaque(true);
		bRegistrazione.setBounds(95, 100, 250, 50);

		panelButtons.add(bLogin);

		bLogin.setBorderPainted(false);
		bLogin.setMargin(new Insets(0, 0, 0, 0));
		bLogin.setContentAreaFilled(false);
		bLogin.setOpaque(true);
		bLogin.setBounds(545, 100, 250, 50);

		pannello.add(panelTitolo);
		pannello.add(panelButtons);

		// creaBottoniBase

		// Definisci ascoltatori di bottoni e relative azioni
		ascoltatoreBottoneRegistrazione = new AscoltatoreRegistrazione();
		ascoltatoreBottoneLogin = new AscoltatoreLogin();

		// Associa ascoltatori e bottoni
		bRegistrazione.addActionListener(ascoltatoreBottoneRegistrazione);
		bLogin.addActionListener(ascoltatoreBottoneLogin);



		// Rendi visibile questo frame;
		this.setVisible(true);
		pannello.setVisible(true);
	}

	private class AscoltatoreLogin implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				new Login();
				pannello.setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class AscoltatoreRegistrazione implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				new Registrazione();
				pannello.setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
