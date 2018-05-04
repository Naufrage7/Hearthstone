package capacite;

public class AttaqueDuHeros extends Capacite {
	private int degats;
	
	public AttaqueDuHeros(int degats) {
		super("Attaque du héros", "Cette capacité permet à la carte qui la possède, d'attaquer le héros adverse, en toute circonstance, y compris lorsque le héros est protégé par la provocation.");
		this.setDegats(degats);
	}
	
	private void setDegats(int degats) {
		this.degats = degats;
	}
}
