package interfaccia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import controller.GestoreCompratore;
import eccezioni.CronologiaVuotaException;
import eccezioni.CredenzialiVuoteException;

public class Recensione extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pannelloRecensione;
	private JPanel pannelloTitoloRecensione = new JPanel();
	private JLabel titoloRecensione = new JLabel();
	private JPanel pannelloProdottoComprato = new JPanel();
	private JLabel labelNomeProdottoComprato = new JLabel();
	private JLabel labelCategoriaProdottoComprato = new JLabel();
	private JLabel labelPrezzoProdottoComprato = new JLabel();
	private JLabel labelDescrizioneProdottoComprato = new JLabel();
	private JPanel pannelloValutazione = new JPanel();
	private JLabel labelInizioValutazione = new JLabel();
	private JLabel labelInizioCommento = new JLabel();
	private JTextArea fieldCommento = new JTextArea();
	private JButton btnInviaRecensione = new JButton("Invia Recensione");
	private String usernameCompratore;
	private int voto;
	private String commento;
	private JComboBox<Integer> comboVoto;

	private class AscoltatoreBtnIndietro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			pannelloRecensione.setVisible(false);
			new CompratoreLoggato();

		}

	}

	public Recensione(String nomeProdotto, String descrizioneProdotto, double prezzoProdotto, String categoriaProdotto,
			String usernameVenditore) {
		pannelloRecensione = new JPanel();
		pannelloRecensione.setSize(Inizio.Confine.getWidth(), Inizio.Confine.getHeight());
		Inizio.Confine.getContentPane().add(pannelloRecensione);
		pannelloRecensione.setLayout(null);

		pannelloTitoloRecensione.setLayout(null);
		pannelloTitoloRecensione.setSize(Inizio.Confine.getWidth(), 45);
		pannelloTitoloRecensione.setLocation(5, 5);
		// panelTitolo.setBounds(0, 0, 800, 400);
		pannelloTitoloRecensione.add(titoloRecensione);

		titoloRecensione.setFont(new Font("Verdana", Font.BOLD, 20));
		titoloRecensione.setLocation(5, 5);
		titoloRecensione.setSize(pannelloTitoloRecensione.getWidth(), 35);
		titoloRecensione.setHorizontalAlignment(JLabel.CENTER);
		titoloRecensione.setVerticalAlignment(JLabel.CENTER);
		titoloRecensione.setText("Recensione per il Venditore: " + usernameVenditore);

		pannelloProdottoComprato.setLayout(null);
		pannelloProdottoComprato.setSize(Inizio.Confine.getWidth(), Inizio.Confine.getHeight() / 4);
		pannelloProdottoComprato.setLocation(5, 55);

		pannelloProdottoComprato.add(labelNomeProdottoComprato);
		labelNomeProdottoComprato.setFont(new Font("Verdana", Font.PLAIN, 20));
		labelNomeProdottoComprato.setLocation(5, 5);
		labelNomeProdottoComprato.setSize(pannelloProdottoComprato.getWidth(), 30);
		labelNomeProdottoComprato.setText("Nome prodotto: " + nomeProdotto);

		pannelloProdottoComprato.add(labelCategoriaProdottoComprato);
		labelCategoriaProdottoComprato.setFont(new Font("Verdana", Font.PLAIN, 20));
		labelCategoriaProdottoComprato.setLocation(5, 40);
		labelCategoriaProdottoComprato.setSize(pannelloProdottoComprato.getWidth(), 30);
		labelCategoriaProdottoComprato.setText("Categoria: " + categoriaProdotto);

		pannelloProdottoComprato.add(labelPrezzoProdottoComprato);
		labelPrezzoProdottoComprato.setFont(new Font("Verdana", Font.PLAIN, 20));
		labelPrezzoProdottoComprato.setLocation(5, 70);
		labelPrezzoProdottoComprato.setSize(pannelloProdottoComprato.getWidth(), 30);
		labelPrezzoProdottoComprato.setText("Prezzo: " + prezzoProdotto + " euro");

		pannelloProdottoComprato.add(labelDescrizioneProdottoComprato);
		labelDescrizioneProdottoComprato.setFont(new Font("Verdana", Font.PLAIN, 20));
		labelDescrizioneProdottoComprato.setLocation(5, 75);
		labelDescrizioneProdottoComprato.setSize(pannelloProdottoComprato.getWidth(), 75);
		labelDescrizioneProdottoComprato.setText("Descrizione: " + descrizioneProdotto);

		pannelloValutazione.setLayout(null);
		pannelloValutazione.setSize(Inizio.Confine.getWidth(), Inizio.Confine.getHeight() / 2);
		pannelloValutazione.setLocation(5, 150);
		// pannelloValutazione.setBackground(new Color(100));

		pannelloValutazione.add(labelInizioValutazione);
		labelInizioValutazione.setFont(new Font("Verdana", Font.PLAIN, 20));
		labelInizioValutazione.setLocation(5, 80);
		labelInizioValutazione.setSize(pannelloProdottoComprato.getWidth(), 30);
		labelInizioValutazione.setText("Come valuti il venditore? (1=pessimo 5=ottimo)");

		// inizio creazione JComboBox
		comboVoto = new JComboBox<Integer>();
		comboVoto.addItem(1);
		comboVoto.addItem(2);
		comboVoto.addItem(3);
		comboVoto.addItem(4);
		comboVoto.addItem(5);
		// fine creazione JComboBox
		pannelloValutazione.add(comboVoto);
		comboVoto.setSize(100, 30);
		comboVoto.setLocation(5, 150);

		pannelloValutazione.add(labelInizioCommento);
		labelInizioCommento.setFont(new Font("Verdana", Font.PLAIN, 20));
		labelInizioCommento.setLocation(5, 185);
		labelInizioCommento.setSize(pannelloProdottoComprato.getWidth() - 320, 30);
		labelInizioCommento.setText("Desideri aggiungere un commento alla valutazione?");
		// labelInizioCommento.setBackground(new Color(100));

		// inizio creazione radio button si/no
		JRadioButton btnSi = new JRadioButton("Si");
		JRadioButton btnNo = new JRadioButton("No");
		btnNo.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		group.add(btnSi);
		group.add(btnNo);
		btnSi.setSize(50, 30);
		btnNo.setSize(50, 30);
		btnSi.setLocation(pannelloProdottoComprato.getWidth() - 320 + 30, 185);
		btnNo.setLocation(pannelloProdottoComprato.getWidth() - 320 + 100, 185);
		pannelloValutazione.add(btnSi);
		pannelloValutazione.add(btnNo);
		// fine creazione radio button

		fieldCommento.setLocation(5, 215);
		fieldCommento.setSize(pannelloProdottoComprato.getWidth(), 60);
		fieldCommento.setFont(new Font("Verdana", Font.PLAIN, 20));
		fieldCommento.setLayout(null);
		fieldCommento.setText(" ");
		pannelloValutazione.add(fieldCommento);
		fieldCommento.setBackground(new Color(190, 190, 190));

		fieldCommento.setVisible(false);
		// ascoltatore per rendere visibile la JTextArea per il commento
		btnSi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				if (btnSi.isSelected()) {
					fieldCommento.setVisible(true);

				}
			}
		});
		// fine ascoltatore

		btnInviaRecensione.setSize(150, 45);
		btnInviaRecensione.setLayout(null);
		btnInviaRecensione.setLocation(pannelloRecensione.getWidth() / 2 - 75, 455);
		voto = (int) comboVoto.getSelectedItem();
		commento = fieldCommento.getText();

		inviaRecensione(nomeProdotto, descrizioneProdotto, prezzoProdotto, categoriaProdotto, usernameVenditore);
		JButton btnIndietro = new JButton("<<<");
		btnIndietro.setSize(60, 45);
		btnIndietro.setLocation(5, 5);
		AscoltatoreBtnIndietro asc = new AscoltatoreBtnIndietro();
		btnIndietro.addActionListener(asc);
		pannelloRecensione.add(btnIndietro);
		pannelloRecensione.add(pannelloTitoloRecensione);
		pannelloRecensione.add(pannelloProdottoComprato);
		pannelloRecensione.add(pannelloValutazione);
		pannelloRecensione.add(btnInviaRecensione);
	}

	public void inviaRecensione(String nomeProdotto, String descrizioneProdotto, double prezzoProdotto,
			String categoriaProdotto, String usernameVenditore) {
		btnInviaRecensione.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				GestoreCompratore gc = GestoreCompratore.avviaControllerGestore();
				usernameCompratore = gc.getAccountC().getUsername();
				voto = (int) comboVoto.getSelectedItem();
				commento = fieldCommento.getText();
				gc.confermaValutazione(voto, commento, usernameCompratore, usernameVenditore);
				try {
					gc.rimuoviProdottoDopoRecensione(nomeProdotto, descrizioneProdotto, prezzoProdotto,
							categoriaProdotto);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CronologiaVuotaException e) {
					JOptionPane.showMessageDialog(null, "non è presente alcun acquisto da recensire", "Errore",
							JOptionPane.ERROR_MESSAGE);
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CredenzialiVuoteException e) {
					JOptionPane.showMessageDialog(null, "Il salvataggio della sessione non è andato a buon fine",
							"Errore", JOptionPane.ERROR_MESSAGE);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "La tua recensione è andata a buon fine!", "Recensione Effettuata",
						JOptionPane.INFORMATION_MESSAGE);
				pannelloRecensione.setVisible(false);
				new CompratoreLoggato();
			}
		});

	}
}