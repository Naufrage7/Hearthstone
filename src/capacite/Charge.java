package capacite;

public class Charge extends Capacite {

	/**
	 * Instancie une classe charge
	 * 
	 * @param nom
	 *            de la carte
	 * @param description
	 *            de la carte
	 */
	public Charge(String nom, String description) {
		super(nom, description);
	}

	/**
	 * Fonction toString de Charge
	 */
	public String toString() {
		return super.toString() + " -> Charge []";
	}
}
