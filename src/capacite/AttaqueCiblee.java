package capacite;

public class AttaqueCiblee extends Capacite {
	private int degats;
	
	public AttaqueCiblee(String nom, int degats) {
		super(nom, "Inflige" + degats + " de dégats à la cible");
		this.setDegats(degats);
	}
	
	private void setDegats(int degats) {
		this.degats = degats;
	}
}
