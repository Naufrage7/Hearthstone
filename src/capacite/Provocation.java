package capacite;

public class Provocation extends Capacite {

	public Provocation(String nom, String description) {
		super(nom, description);
	}

	@Override
	public boolean necessiteCible() {
		return false;
	}

}
