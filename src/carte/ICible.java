package carte;

import exception.MortException;

public interface ICible {
	
	/**
	 * Occasionne des dégats au héros
	 * 
	 * @param degats le nombre de dégats à occasionner
	 * @throws MortException
	 */
	public void recevoirDegats(int degats);
	
	public boolean peutRecevoirDegats();
	
	public int getVie();

	public int getAttaque();
	
}
