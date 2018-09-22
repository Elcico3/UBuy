package interfaccia;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.GestoreCompratore;
import eccezioni.CredenzialiException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Login {

	public static JLabel testoPresentazione = new JLabel();
	public JPanel pannelloLogin;
	public JPanel panelTitolo = new JPanel();
	public JPanel panelCredenzialiLogin = new JPanel();
	public JPanel panelCredenzialiPsw = new JPanel();
	public JPanel panelButtonLogin = new JPanel();

	public JButton bLogin;
	public JLabel loginLabel = new JLabel();
	public JTextField loginF = new JTextField();
	public JLabel passwordLabel = new JLabel();
	public JPasswordField passwordF = new JPasswordField();
	public JLabel titolo = new JLabel();

	public AscoltatoreLogin ascoltatoreBtLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Login window = new Login();
					// window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		int border = 5;

		pannelloLogin = new JPanel();

		pannelloLogin.setSize(Inizio.Confine.getWidth(), Inizio.Confine.getHeight());
		Inizio.Confine.add(pannelloLogin);
		pannelloLogin.setLayout(null);

		panelTitolo.setLayout(null);
		panelTitolo.setSize(Inizio.Confine.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Verdana", Font.BOLD, 20));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Inserire le proprie credenziali: Login e Password");

		pannelloLogin.add(panelTitolo);
		loginLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		loginLabel.setLocation(150, 50);
		loginLabel.setSize(panelTitolo.getWidth() / 2, 30);
		loginLabel.setText("Username: ");

		loginF = new JTextField("", 60);
		loginF.setFont(new Font("Verdana", 0, 15));
		loginF.setLocation(300, 50);
		loginF.setSize(panelTitolo.getWidth() / 2, 30);

		passwordLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		passwordLabel.setLocation(150, 50);
		passwordLabel.setSize(panelTitolo.getWidth() / 2, 30);
		passwordLabel.setText("Password: ");

		passwordF = new JPasswordField("", 60);
		passwordF.setLocation(300, 50);
		passwordF.setSize(panelTitolo.getWidth() / 2, 30);
		passwordF.setFont(new Font("Verdana", 0, 15));

		// Creazione bottone

		bLogin = new JButton("Login");
		bLogin.setLocation(300, 50);
		bLogin.setSize(panelTitolo.getWidth() / 4, 50);
		bLogin.setFont(new Font("Arial", 0, 20));

		bLogin.setBorderPainted(false);
		bLogin.setMargin(new Insets(0, 0, 0, 0));
		bLogin.setContentAreaFilled(false);
		// bLogin.setOpaque(true);
		bLogin.setText("Login");
		bLogin.setBounds(325, 40, 250, 50);

		panelCredenzialiLogin.setLayout(null);
		panelCredenzialiLogin.setSize(Inizio.Confine.getWidth(), Inizio.Confine.getHeight() / 5);
		panelCredenzialiLogin.setLocation(5, 70);
		panelCredenzialiLogin.add(loginLabel);
		panelCredenzialiLogin.add(loginF);

		panelCredenzialiPsw.setLayout(null);
		panelCredenzialiPsw.setSize(Inizio.Confine.getWidth(), Inizio.Confine.getHeight() / 5);
		panelCredenzialiPsw.setLocation(5, 200);
		panelCredenzialiPsw.add(passwordLabel);
		panelCredenzialiPsw.add(passwordF);

		panelButtonLogin.setLayout(null);
		panelButtonLogin.setSize(Inizio.Confine.getWidth(), Inizio.Confine.getHeight() / 5);
		panelButtonLogin.setLocation(5, 330);
		// panelButtonLogin.setBackground(new Color(190, 190, 190));
		panelButtonLogin.add(bLogin);

		pannelloLogin.add(panelCredenzialiLogin);
		pannelloLogin.add(panelCredenzialiPsw);
		pannelloLogin.add(panelButtonLogin);

		// Ascoltatore di bottone Login e relativa azione
		ascoltatoreBtLogin = new AscoltatoreLogin();

		// Associazione di bottone Login a relativo ascoltatore
		bLogin.addActionListener(ascoltatoreBtLogin);
		// passwordF.addActionListener(ascoltatoreEtAzioneLogin);

		loginF.requestFocusInWindow();

	}

	private class AscoltatoreLogin implements ActionListener {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent arg0) {
			GestoreCompratore gc = GestoreCompratore.avviaControllerGestore();
			try {
				gc.convalidaLogin(loginF.getText(), passwordF.getText());
				pannelloLogin.setVisible(false);
				new CompratoreLoggato();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CredenzialiException e) {
				JOptionPane.showMessageDialog(null, " Attenzione! Credenziali Errante ", "Errore",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

		}
	}
}
