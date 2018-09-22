package interfaccia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.GestoreCompratore;
import eccezioni.CredenzialiVuoteException;

import java.awt.Font;

public class Registrazione extends JFrame {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	public static JLabel testoPresentazione = new JLabel();
	public JPanel pannelloRegistrazione;
	public JPanel panelTitolo = new JPanel();
	public JPanel panelUser = new JPanel();
	public JPanel panelPsw = new JPanel();
	public JPanel panelNome = new JPanel();
	public JPanel panelCognome = new JPanel();
	public JPanel panelButtonRegistrazione = new JPanel();

	private AscoltatoreBottoneRegistrazione ascoltatoreBtRegistrazione;

	public JButton bRegistrazione;
	public JLabel userLabel = new JLabel();
	public JTextField userF = new JTextField();
	public JLabel passwordLabel = new JLabel();
	public JPasswordField passwordF = new JPasswordField();
	public JLabel nomeLabel = new JLabel();
	public JTextField nomeF = new JTextField();
	public JLabel cognomeLabel = new JLabel();
	public JTextField cognomeF = new JTextField();
	public JLabel titolo = new JLabel();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Registrazione() {
		int border = 5;

		pannelloRegistrazione = new JPanel();
		pannelloRegistrazione.setSize(Inizio.Confine.getWidth(), Inizio.Confine.getHeight());
		Inizio.Confine.add(pannelloRegistrazione);
		pannelloRegistrazione.setLayout(null);

		panelTitolo.setLayout(null);
		panelTitolo.setSize(Inizio.Confine.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		// panelTitolo.setBounds(0, 0, 800, 400);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Verdana", Font.BOLD, 20));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Inserire le proprie credenziali: Username, Password, nome, cognome");

		pannelloRegistrazione.add(panelTitolo);
		userLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		userLabel.setLocation(150, 50);
		userLabel.setSize(panelTitolo.getWidth() / 2, 30);
		userLabel.setText("Username: ");

		userF = new JTextField("", 60);
		userF.setFont(new Font("Verdana", 0, 15));
		userF.setLocation(300, 50);
		userF.setSize(panelTitolo.getWidth() / 2, 30);

		passwordLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		passwordLabel.setLocation(150, 50);
		passwordLabel.setSize(panelTitolo.getWidth() / 2, 30);
		passwordLabel.setText("Password: ");

		passwordF = new JPasswordField("", 60);
		passwordF.setLocation(300, 50);
		passwordF.setSize(panelTitolo.getWidth() / 2, 30);
		passwordF.setFont(new Font("Verdana", 0, 15));

		nomeLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		nomeLabel.setLocation(150, 50);
		nomeLabel.setSize(panelTitolo.getWidth() / 2, 30);
		nomeLabel.setText("Nome: ");

		nomeF = new JTextField("", 60);
		nomeF.setLocation(300, 50);
		nomeF.setSize(panelTitolo.getWidth() / 2, 30);
		nomeF.setFont(new Font("Verdana", 0, 15));

		cognomeLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		cognomeLabel.setLocation(150, 50);
		cognomeLabel.setSize(panelTitolo.getWidth() / 2, 30);
		cognomeLabel.setText("Conome: ");

		cognomeF = new JTextField("", 60);
		cognomeF.setLocation(300, 50);
		cognomeF.setSize(panelTitolo.getWidth() / 2, 30);
		cognomeF.setFont(new Font("Verdana", 0, 15));

		// Creazione bottone

		bRegistrazione = new JButton("Registrazione");
		bRegistrazione.setLocation(300, 50);
		bRegistrazione.setSize(panelTitolo.getWidth() / 6, 50);
		bRegistrazione.setFont(new Font("Verdana", 0, 20));

		bRegistrazione.setBorderPainted(false);
		bRegistrazione.setContentAreaFilled(false);
		bRegistrazione.setText("Registrati");
		bRegistrazione.setBounds(325, 40, 250, 50);

		panelUser.setLayout(null);
		panelUser.setSize(Inizio.Confine.getWidth(), Inizio.Confine.getHeight() / 7);
		panelUser.setLocation(5, (Inizio.Confine.getHeight() / 7));
		panelUser.add(userLabel);
		panelUser.add(userF);

		panelPsw.setLayout(null);
		panelPsw.setSize(Inizio.Confine.getWidth(), Inizio.Confine.getHeight() / 7);
		panelPsw.setLocation(5, (Inizio.Confine.getHeight() / 7 * 2));
		panelPsw.add(passwordLabel);
		panelPsw.add(passwordF);

		panelNome.setLayout(null);
		panelNome.setSize(Inizio.Confine.getWidth(), Inizio.Confine.getHeight() / 7);
		panelNome.setLocation(5, (Inizio.Confine.getHeight() / 7 * 3));
		panelNome.add(nomeLabel);
		panelNome.add(nomeF);

		panelCognome.setLayout(null);
		panelCognome.setSize(Inizio.Confine.getWidth(), Inizio.Confine.getHeight() / 7);
		panelCognome.setLocation(5, (Inizio.Confine.getHeight() / 7 * 4));
		panelCognome.add(cognomeLabel);
		panelCognome.add(cognomeF);

		panelButtonRegistrazione.setLayout(null);
		panelButtonRegistrazione.setSize(Inizio.Confine.getWidth(), Inizio.Confine.getHeight() / 7);
		panelButtonRegistrazione.setLocation(5, (Inizio.Confine.getHeight() / 7 * 5));
		panelButtonRegistrazione.add(bRegistrazione);

		pannelloRegistrazione.add(panelUser);
		pannelloRegistrazione.add(panelPsw);
		pannelloRegistrazione.add(panelNome);
		pannelloRegistrazione.add(panelCognome);
		pannelloRegistrazione.add(panelButtonRegistrazione);

		// Ascoltatore di bottone Login e relativa azione
		ascoltatoreBtRegistrazione = new AscoltatoreBottoneRegistrazione();

		// Associazione di bottone Login a relativo ascoltatore
		bRegistrazione.addActionListener(ascoltatoreBtRegistrazione);

		userF.requestFocusInWindow();

	}

	private class AscoltatoreBottoneRegistrazione implements ActionListener {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent arg0) {
			try {
				// CompratoreBean cb = new CompratoreBean();
				// cb.setNome(nomeF.getText());
				// cb.setCognome(cognomeF.getText());
				// cb.setUsername(userF.getText());
				// cb.setPassword(passwordF.getText());
				GestoreCompratore gc = GestoreCompratore.avviaControllerGestore();
				gc.serializzaCompratore(nomeF.getText(), cognomeF.getText(), userF.getText(), passwordF.getText(),
						null);
				// da implementare avvia serializzazione compratore
				// cb.registraCompratore();
				new Login();
				pannelloRegistrazione.setVisible(false);

			} catch (CredenzialiVuoteException e) {
				JOptionPane.showMessageDialog(null, " Tutti i campi devono essere riempiti", "Errore",
						JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}