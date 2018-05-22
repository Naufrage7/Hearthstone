package carte;

import exception.MortException;

public interface Cible {
	
	/**
	 * Occasionne des dégats au héros
	 * 
	 * @param degats le nombre de dégats à occasionner
	 * @throws MortException
	 */
	public void recevoirDegats(int degats);
	
}
