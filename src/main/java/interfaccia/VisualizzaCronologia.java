package interfaccia;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bean.ProdottoBean;
import controller.GestoreCompratore;



public class VisualizzaCronologia extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<ProdottoBean> listaProdotti = GestoreCompratore.avviaControllerGestore().ottieniProdottiComprati();
	private static String[] header = { "nome", "categoria", "descrizione", "prezzo", "venditore" };// },"compra"};
	private DefaultTableModel dtm = new DefaultTableModel(null, header) {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Class<?> getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}
	};
	private JButton btnRecensisciProdotto;
	private JTable table = new JTable(dtm);
	private JScrollPane scrollPane = new JScrollPane(table);
	private JScrollBar vScroll = scrollPane.getVerticalScrollBar();
	private boolean isAutoScroll;
	public static JLabel testoPresentazione = new JLabel();
	public JPanel pannelloVisualizzaCronologia = new JPanel();
	public JPanel panelTitoloCompraProdotto = new JPanel();
	public JPanel panelTabellaProdotti = new JPanel();// = new
														// TabellaProdotti();
	public JScrollPane scrollPaneProdotti = new JScrollPane();
	public JLabel titoloCompraProdotto = new JLabel();
	// public List<Prodotto> listaProdottiBean =
	// GestoreCompratore.avviaControllerGestore().ottieniProdottiToBean();
	
	private String nomeProdotto;
	private String categoriaProdotto; 
	private String descrizioneProdotto; 
	private double prezzoProdotto;
	private String usernameVenditore;

	public VisualizzaCronologia() {

		// creo il pannello principale
		int border = 5;
		pannelloVisualizzaCronologia = new JPanel();
		pannelloVisualizzaCronologia.setSize(Inizio.Confine.getWidth(), Inizio.Confine.getHeight());
		Inizio.Confine.getContentPane().add(pannelloVisualizzaCronologia);
		pannelloVisualizzaCronologia.setLayout(null);
		// creo il pannello del titolo
		panelTitoloCompraProdotto.setLayout(null);
		panelTitoloCompraProdotto.setSize(Inizio.Confine.getWidth(), 45);
		panelTitoloCompraProdotto.setLocation(5, 5);
		// aggiungo il titolo al pannello del titolo
		panelTitoloCompraProdotto.add(titoloCompraProdotto);

		// setto le caratteristiche del titolo
		titoloCompraProdotto.setFont(new Font("Verdana", Font.BOLD, 20));
		titoloCompraProdotto.setLocation(border, border);
		titoloCompraProdotto.setSize(panelTitoloCompraProdotto.getWidth(), 35);
		titoloCompraProdotto.setHorizontalAlignment(JLabel.CENTER);
		titoloCompraProdotto.setVerticalAlignment(JLabel.CENTER);
		titoloCompraProdotto.setText("Seleziona un prodotto comprato e valuta il venditore associato");

		// inizio creazione tabella
		panelTabellaProdotti.setLayout(new BorderLayout());
		Dimension d = new Dimension(panelTabellaProdotti.getWidth() - 50, panelTabellaProdotti.getHeight() - 50);
		table.setPreferredScrollableViewportSize(d);
		// non permetter di editare i campi
		table.setDefaultEditor(Object.class, null);
		// riempi la tabella
		addRows(listaProdotti);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		vScroll.addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				isAutoScroll = !e.getValueIsAdjusting();
			}
		});
		panelTabellaProdotti.add(scrollPane, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		this.inviaDatiProdottoDaRecensire();
		btnRecensisciProdotto.setText("Recensisci venditore");
		panel.add(btnRecensisciProdotto);
		panelTabellaProdotti.add(panel, BorderLayout.SOUTH);
		// fine creazione tabella

		// setto layout e poszione del pannello della tabella
		panelTabellaProdotti.setSize(Inizio.Confine.getWidth() - 50, Inizio.Confine.getHeight() - 100);
		panelTabellaProdotti.setLocation(5, 50);
		panelTabellaProdotti.add(scrollPane);
		JButton btnIndietro=new JButton("<<<");
		btnIndietro.setSize(60,45);
		btnIndietro.setLocation(5,5);
		AscoltatoreBtnIndietro asc=new AscoltatoreBtnIndietro();
		btnIndietro.addActionListener(asc);
		pannelloVisualizzaCronologia.add(btnIndietro);
		pannelloVisualizzaCronologia.add(panelTitoloCompraProdotto);
		pannelloVisualizzaCronologia.add(panelTabellaProdotti);
		pannelloVisualizzaCronologia.setVisible(true);

	}

	private void addRows(List<ProdottoBean> lpb) {
		for (int i = 0; i < lpb.size(); i++) {
			dtm.addRow(new Object[] { String.valueOf(lpb.get(i).getNome()), String.valueOf(lpb.get(i).getCategorie()),
					String.valueOf(lpb.get(i).getDescrizione()), Double.valueOf(lpb.get(i).getPrezzo()), String.valueOf(lpb.get(i).getUsernameVenditore()) });
		}

	}
	private class AscoltatoreBtnIndietro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			pannelloVisualizzaCronologia.setVisible(false);
			new CompratoreLoggato();
			
		}
		
	}
	public void inviaDatiProdottoDaRecensire(){
	btnRecensisciProdotto = new JButton(new AbstractAction("Compra prodotto") {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			int rigaSelezionata = table.getSelectedRow();
			if (rigaSelezionata != -1) {
				nomeProdotto = (String) table.getValueAt(rigaSelezionata, 0);
				categoriaProdotto = (String) table.getValueAt(rigaSelezionata, 1);
				descrizioneProdotto = (String) table.getValueAt(rigaSelezionata, 2);
				prezzoProdotto = (double) table.getValueAt(rigaSelezionata, 3);
				usernameVenditore= (String) table.getValueAt(rigaSelezionata, 4);
				new Recensione(nomeProdotto,descrizioneProdotto,prezzoProdotto,categoriaProdotto,usernameVenditore);
				pannelloVisualizzaCronologia.setVisible(false);
			}
		}
	});
	}
}