package jeu;

public interface IPersonnage {
	
	/**
	 * Occasionne des dégats à la cible
	 * @param degats
	 */
	public void attaquer(IPersonnage personnage);
	
	/**
	 * Retourne les dégats que peut infliger le personnage
	 * @return
	 */
	public int getDegats();
	
	/**
	 * Retourne la vie du personnage
	 * @return
	 */
	public int getVie();
	
	/**
	 * Inflige des dégats au personnage
	 * @param degats
	 */
	public void recevoirDegats(int degats);

}
