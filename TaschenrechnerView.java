import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
// import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.UIManager.*;

/**
 * @author Philipp Michelfeit, 11E1FA
 * @version 1.0
 */
public class TaschenrechnerView extends JFrame implements ActionListener {

	// ************************************************************************
	// Instanzfelder und Swing-Komponenten
	// ************************************************************************
	private static final long serialVersionUID = 1;

	private final TaschenrechnerModel model;

	private JTextField tfErg;

	private JButton[] btNumber;

	private JButton[] btFunction;

	private GridBagConstraints gbc;

	private Container cP;

	// ************************************************************************
	// Konstruktor
	// ************************************************************************
	public TaschenrechnerView(TaschenrechnerModel model) {

		super("Taschenrechner");

		// Setzt das JFrame-Icon
		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(
				"/calculator-icon.png"));
		Image image = imageIcon.getImage();
		this.setIconImage(image);

		this.model = model;

		// Container-Objekt initialisieren
		this.cP = this.getContentPane();

		// Layoutmanager festsetzen
		this.cP.setLayout(new GridBagLayout());

		// Standard-LAF festlegen
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}

		// Neues Ein- / Ausgabetextfeld erzeugen
		this.tfErg = new JTextField();
		this.tfErg.setEditable(false);
		this.tfErg.setFocusable(false);

		// Erzeugen der Nummerntasten
		this.btNumber = new JButton[11];

		for (int i = 0; i < this.btNumber.length; i++) {
			if (i == this.btNumber.length - 1) {
				this.btNumber[i] = new JButton(".");
			} else {
				this.btNumber[i] = new JButton("" + i);
			}
		}

		// ActionListener fuer die Buttons hinzufuegen
		for (int i = 0; i < this.btNumber.length; i++) {
			this.btNumber[i].addActionListener(this);
		}

		// Erzeugen der Funktionstasten
		String[] functions = { "=", "+", "-", "x", "/", "C" };
		this.btFunction = new JButton[functions.length];

		for (int i = 0; i < btFunction.length; i++) {
			this.btFunction[i] = new JButton(functions[i]);
		}

		// ActionListener fuer die Funktionstasten hinzufuegen
		for (int i = 0; i < btFunction.length; i++) {
			this.btFunction[i].addActionListener(this);
		}

		this.gbc = new GridBagConstraints();
		this.gbc.fill = GridBagConstraints.BOTH;
		this.gbc.insets = new Insets(0, 0, 0, 0);
		this.gbc.weightx = 1;
		this.gbc.weighty = 0.5;

		// Einfuegen des Eingabefelds
		this.prepareGBC(0, 0, 3, 1);
		this.tfErg.setBackground(Color.lightGray);
		this.tfErg.setForeground(Color.black);
		this.cP.add(tfErg, gbc);

		// Einfuegen der Zahlentasten (1 bis 9)
		this.gbc.weighty = 1;
		for (int i = 1; i < 10; i++) {
			this.prepareGBC((i - 1) % 3, 3 - (i - 1) / 3, 1, 1);
			cP.add(btNumber[i], gbc);
		}

		// Einfuegen der Funktionstasten (+ bis / und =)
		for (int i = 0; i < btFunction.length - 1; i++) {
			this.prepareGBC(3, i, 1, 1);
			this.cP.add(btFunction[i], gbc);
		}

		// Einfuegen der Taste "0"
		prepareGBC(1, 4, 1, 1);
		this.cP.add(btNumber[0], gbc);

		// Einfuegen der Taste "."
		prepareGBC(0, 4, 1, 1);
		this.cP.add(btNumber[btNumber.length - 1], gbc);

		// Einfuegen der Taste "C"
		prepareGBC(2, 4, 1, 1);
		this.cP.add(btFunction[btFunction.length - 1], gbc);

		// Standard-Schliess-Operation festlegen
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// WindowListener hinzufuegen
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// Fenstergroesse aendern verbieten
		this.setResizable(false);

		// Fenster automatisch positionieren
		this.pack();

		// Minimal moegliche Fenstergroesse setzen
		this.setMinimumSize(new Dimension(300, 200));

		// Fenster in der Bildschirmmitte positionieren
		this.setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - this
						.getWidth()) / 2, (Toolkit.getDefaultToolkit()
						.getScreenSize().height - this.getHeight()) / 2);

		// Fenster sichtbar machen
		this.setVisible(true);
	}

	// ************************************************************************
	// Getter
	// ************************************************************************
	public TaschenrechnerModel getModel() {
		return this.model;
	}

	public JTextField getTfErg() {
		return this.tfErg;
	}

	// ************************************************************************
	// Setter
	// ************************************************************************

	// ************************************************************************
	// Methode, um ein GridBagConstraints-Objekt vorzubereiten
	// ************************************************************************
	private void prepareGBC(int x, int y, int width, int height) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
	}

	// ************************************************************************
	// ActionPerformed-Methode
	// ************************************************************************
	public void actionPerformed(ActionEvent event) {

		// Zahlen setzen
		for (int i = 0; i < btNumber.length - 1; i++) {

			if (event.getSource() == btNumber[i]) {
				String zahl = event.getActionCommand();

				if (this.model.istStart() == true) {
					this.tfErg.setText("");
					this.model.setStart(false);
				}
				this.tfErg.setText(tfErg.getText() + zahl);
			}
		}

		// Dezimalpunkt setzen
		if (event.getSource() == this.btNumber[btNumber.length - 1]) {
			String dezimalpunkt = event.getActionCommand();

			if (this.model.istStart() == true) {
				this.tfErg.setText("");
				this.model.setStart(false);
			} else {
				if (this.model.istPunktGesetzt() == false) {
					this.tfErg.setText(this.tfErg.getText() + dezimalpunkt);
					this.model.setPunktGesetzt(true);
				} else {
					this.tfErg.setText(this.tfErg.getText() + "");
				}
			}
		}

		// Cleartaste aktivieren
		if (event.getSource() == btFunction[btFunction.length - 1]) {
			this.anzeigeZuruecksetzen();
		}

		for (int i = 0; i < btFunction.length - 1; i++) {
			if (event.getSource() == btFunction[i]) {
				String kommando = event.getActionCommand();

				// Fuegt den Praefix "-" an den String an, wenn
				// es sich um den ersten Befehl handelt (negative Zahl)
				if (this.model.istStart() == true) {
					if (kommando.equals("-")) {
//						if (this.model.istMinusGesetzt() == false) {
							this.tfErg.setText(kommando);
//							this.model.setMinusGesetzt(true);
							this.model.setStart(false);
//						} else if (this.model.istMinusGesetzt() == true) {
//							this.tfErg.setText(this.tfErg.getText() + "");
//						}
					} else {
						this.getModel().setLetztesKommando(kommando);
					}
				} else {
					// Berechnung ausfuehren
					this.model
							.berechne(Double.parseDouble(this.tfErg.getText()));
					this.model.setLetztesKommando(kommando);
					this.tfErg
							.setText(String.valueOf(this.model.getErgebnis()));
					this.model.setStart(true);
					this.model.setPunktGesetzt(false);
					this.model.setMinusGesetzt(false);
				}
			}
		}
	}

	// ************************************************************************
	// Setzt bei Druecken der C-Taste die Werte im Model auf den Ursprung
	// zurueck
	// ************************************************************************
	private void anzeigeZuruecksetzen() {
		this.getTfErg().setText("");
		this.getModel().setLetztesKommando("=");
		this.getModel().setStart(true);
		this.getModel().setErgebnis(0);
		this.getModel().setPunktGesetzt(false);
		this.getModel().setMinusGesetzt(false);
	}
}