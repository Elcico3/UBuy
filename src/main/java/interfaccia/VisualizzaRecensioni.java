package interfaccia;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bean.ValutazioneBean;
import controller.GestoreCompratore;


public class VisualizzaRecensioni extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<ValutazioneBean> listaValutazioni = GestoreCompratore.avviaControllerGestore().ottieniValutazioniCompratoreCorrente();
	private static String[] header = { "venditore", "voto", "commento" };// },"compra"};
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
	
	private JTable table = new JTable(dtm);
	private JScrollPane scrollPane = new JScrollPane(table);
	private JScrollBar vScroll = scrollPane.getVerticalScrollBar();
	private boolean isAutoScroll;
	public static JLabel testoPresentazione = new JLabel();
	public JPanel pannelloVisualizzaCronologia = new JPanel();
	public JPanel panelTitoloCompraProdotto = new JPanel();
	public JPanel panelTabellaProdotti = new JPanel();
	public JScrollPane scrollPaneProdotti = new JScrollPane();
	public JLabel titoloCompraProdotto = new JLabel();
	public VisualizzaRecensioni() {

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
		titoloCompraProdotto.setText("Le tue recensioni");

		// inizio creazione tabella
		panelTabellaProdotti.setLayout(new BorderLayout());
		Dimension d = new Dimension(panelTabellaProdotti.getWidth() - 50, panelTabellaProdotti.getHeight() - 50);
		table.setPreferredScrollableViewportSize(d);
		// non permetter di editare i campi
		table.setDefaultEditor(Object.class, null);
		// riempi la tabella
		addRows(listaValutazioni);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		vScroll.addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				isAutoScroll = !e.getValueIsAdjusting();
			}
		});
		panelTabellaProdotti.add(scrollPane, BorderLayout.CENTER);
		JPanel panel = new JPanel();

		panelTabellaProdotti.add(panel, BorderLayout.SOUTH);
		// fine creazione tabella

		// setto layout e poszione del pannello della tabella
		panelTabellaProdotti.setSize(Inizio.Confine.getWidth() - 50, Inizio.Confine.getHeight() - 100);
		panelTabellaProdotti.setLocation(5, 50);
		panelTabellaProdotti.add(scrollPane);

		// aggiuungo al pannello principale il pannello del titotlo e della
		// tabella
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

	private void addRows(List<ValutazioneBean> lpb) {
		for (int i = 0; i < lpb.size(); i++) {
			dtm.addRow(new Object[] { String.valueOf(lpb.get(i).getUsernameVenditore()), Integer.valueOf(lpb.get(i).getVoto()),
					String.valueOf(lpb.get(i).getCommento())});
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

}