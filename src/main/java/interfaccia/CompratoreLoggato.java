package interfaccia;

import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.GestoreCompratore;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CompratoreLoggato extends JPanel {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JPanel pannelloCompratoreLoggato = new JPanel();
	private JLabel titoloCompratoreLoggato = new JLabel();
	private JPanel panelTitolo = new JPanel();
	public JPanel panelButtons = new JPanel();
	public JButton bCompra = new JButton("Compra");
	public JButton bCronologia = new JButton("Cronoloia Acquisti");
	private AscoltatoreCompra ascoltatoreBtCompra;
	private AscoltatoreVisualizzaCronologia ascoltatoreBtCronologia;
	private ascoltatoreRecensioniCompratore ascoltatoreBtRecensioniCompratore;
	private JButton bRecensioniCompratore = new JButton("Visualizza Recensioni");

	public CompratoreLoggato() {

		// setto pannello principale
		pannelloCompratoreLoggato = new JPanel();
		pannelloCompratoreLoggato.setSize(Inizio.Confine.getWidth(), Inizio.Confine.getHeight());
		Inizio.Confine.add(pannelloCompratoreLoggato);
		pannelloCompratoreLoggato.setLayout(null);

		// setto pannello titolo titolo
		panelTitolo.setLayout(null);
		panelTitolo.setSize(Inizio.Confine.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titoloCompratoreLoggato);

		// setto titolo
		titoloCompratoreLoggato.setFont(new Font("Verdana", Font.BOLD, 20));
		titoloCompratoreLoggato.setLocation(5, 5);
		titoloCompratoreLoggato.setSize(panelTitolo.getWidth(), 35);
		titoloCompratoreLoggato.setHorizontalAlignment(JLabel.CENTER);
		titoloCompratoreLoggato.setVerticalAlignment(JLabel.CENTER);
		titoloCompratoreLoggato
				.setText("Benvenuto: " + GestoreCompratore.avviaControllerGestore().getAccountC().getNome());

		// setto pannello bottoni
		panelButtons.setLayout(null);
		panelButtons.setSize(pannelloCompratoreLoggato.getWidth(), pannelloCompratoreLoggato.getHeight());
		panelButtons.setLocation(5, 60);

		panelButtons.add(bCompra);
		bCompra.setBorderPainted(false);
		bCompra.setMargin(new Insets(0, 0, 0, 0));
		bCompra.setContentAreaFilled(false);
		bCompra.setOpaque(true);
		bCompra.setBounds(95, 100, 250, 50);
		ascoltatoreBtCompra = new AscoltatoreCompra();
		bCompra.addActionListener(ascoltatoreBtCompra);

		panelButtons.add(bCronologia);
		
		bCronologia.setBorderPainted(false);
		bCronologia.setMargin(new Insets(0, 0, 0, 0));
		bCronologia.setContentAreaFilled(false);
		bCronologia.setOpaque(true);
		bCronologia.setBounds(545, 100, 250, 50);
		ascoltatoreBtCronologia = new AscoltatoreVisualizzaCronologia();
		bCronologia.addActionListener(ascoltatoreBtCronologia);

		panelButtons.add(bRecensioniCompratore);
		bRecensioniCompratore.setBorderPainted(false);
		bRecensioniCompratore.setMargin(new Insets(0, 0, 0, 0));
		bRecensioniCompratore.setContentAreaFilled(false);
		bRecensioniCompratore.setOpaque(true);
		bRecensioniCompratore.setBounds(320, 100, 250, 50);
		ascoltatoreBtRecensioniCompratore = new ascoltatoreRecensioniCompratore();
		bRecensioniCompratore.addActionListener(ascoltatoreBtRecensioniCompratore);

		pannelloCompratoreLoggato.add(panelTitolo);
		pannelloCompratoreLoggato.add(panelButtons);
		pannelloCompratoreLoggato.setVisible(true);

	}

	private class AscoltatoreCompra implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			new CompraProdotto();
			pannelloCompratoreLoggato.setVisible(false);

		}
	}

	private class ascoltatoreRecensioniCompratore implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			new VisualizzaRecensioni();
			pannelloCompratoreLoggato.setVisible(false);

		}
	}

	private class AscoltatoreVisualizzaCronologia implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			new VisualizzaCronologia();
			pannelloCompratoreLoggato.setVisible(false);

		}
	}
}
