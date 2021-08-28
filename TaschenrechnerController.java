/**
 * @author Philipp Michelfeit, 11E1FA
 * @version 1.0
 */
public class TaschenrechnerController {

	// ************************************************************************
	// Instanzfelder
	// ************************************************************************
	private final TaschenrechnerModel model;

	private final TaschenrechnerView view;

	// ************************************************************************
	// Konstruktor
	// ************************************************************************
	public TaschenrechnerController() {
		
		// Objekte der Klasse View und Model instanzieren
		this.model = new TaschenrechnerModel();
		this.view = new TaschenrechnerView(this.model);
		
	}

	public TaschenrechnerView getView() {
		return view;
	}
}
