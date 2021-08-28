/**
 * @author Philipp Michelfeit, 11E1FA
 * @version 1.0
 */
public class TaschenrechnerModel {

	// ************************************************************************
	// Instanzfelder
	// ************************************************************************
	// private TaschenrechnerView view;

	private double ergebnis;
	
	private String letztesKommando;
	
	private boolean start;

	private boolean punktGesetzt;

	private boolean minusGesetzt;

	// ************************************************************************
	// Konstruktor der Klasse TaschenrechnerModel
	// ************************************************************************
	public TaschenrechnerModel() {
		this.start = true;
		this.letztesKommando = "=";
		this.ergebnis = 0;
		this.punktGesetzt = false;
		this.minusGesetzt = false;
	}

	// ************************************************************************
	// Getter
	// ************************************************************************
	public boolean istStart() {
		return this.start;
	}

	public String getLetztesKommando() {
		return this.letztesKommando;
	}

	public double getErgebnis() {
		return this.ergebnis;
	}

	public boolean istPunktGesetzt() {
		return this.punktGesetzt;
	}

	public boolean istMinusGesetzt() {
		return this.minusGesetzt;
	}

	// ************************************************************************
	// Setter
	// ************************************************************************
	public boolean setStart(boolean start) {
		return this.start = start;
	}

	public String setLetztesKommando(String kommando) {
		return this.letztesKommando = kommando;
	}

	public double setErgebnis(double ergebnis) {
		return this.ergebnis = ergebnis;
	}

	public boolean setPunktGesetzt(boolean istPunktGesetzt) {
		return this.punktGesetzt = istPunktGesetzt;
	}

	public boolean setMinusGesetzt(boolean istMinusGesetzt) {
		return this.minusGesetzt = istMinusGesetzt;
	}

	// ************************************************************************
	// Methode, um das Ergebnis zu berechnen
	// ************************************************************************
	/**
	 * Fuehrt die anstehenden Berechnungen aus.
	 * 
	 * @param zahl
	 *            Die mit dem vorherigen Ergebnis zu verrechnende Zahl
	 */
	public void berechne(double zahl) {
		
		if (this.letztesKommando.equals("+")) {
			this.ergebnis = this.ergebnis + zahl;
		} else if (this.letztesKommando.equals("-")) {
			this.ergebnis = this.ergebnis - zahl;
		} else if (this.letztesKommando.equals("x")) {
			this.ergebnis = this.ergebnis * zahl;
		} else if (this.letztesKommando.equals("/")) {
			this.ergebnis = this.ergebnis / zahl;
		} else if (this.letztesKommando.equals("=")) {
			this.ergebnis = zahl;
		}
	}
}
