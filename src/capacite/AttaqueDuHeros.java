package capacite;

public class AttaqueDuHeros extends Capacite {
	private int degats;
	
	public AttaqueDuHeros(String nom, int degats) {
		super(nom, "Inflige " + degats + " de dégats au héros adverse");
		this.setDegats(degats);
	}
	
	private void setDegats(int degats) {
		this.degats = degats;
	}
}
