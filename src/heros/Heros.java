package heros;

import capacite.Capacite;
import capacite.Provocation;
import carte.ICarte;
import carte.ICible;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.Plateau;

public class Heros implements ICible {

	private String nom;
	private int vie;
	private Capacite pouvoir;
	private boolean capaciteUtilisee;

	/**
	 * Construit un héros à partir de son nom et de sa vie
	 * 
	 * @param nom
	 *            le nom du héros
	 * @param vie
	 *            la vie du héros
	 */
	public Heros(String nom, int vie, Capacite pouvoir) {
		this.setNom(nom);
		this.setVie(vie);
		this.setPouvoir(pouvoir);
		this.setCapaciteUtilisee(false);
	}

	public void setCapaciteUtilisee(boolean capaciteUtilisee) {
		this.capaciteUtilisee = capaciteUtilisee;
	}
	
	public boolean getCapaciteUtilisee() {
		return capaciteUtilisee;
	}

	/**
	 * Attribue le nom au héros
	 * 
	 * @param nom
	 *            le nom du héros
	 */
	private void setNom(String nom) {
		if (nom == null)
			throw new IllegalArgumentException("Le nom du héros ne peut pas être nul !");
		this.nom = nom;
	}

	/**
	 * Attribue la vie au héros
	 * 
	 * @param vie
	 *            la vie du héros
	 */
	private void setVie(int vie) {
		if (vie < 0)
			this.vie = 0;
		else
			this.vie = vie;
	}

	/**
	 * Attribue le pouvoir au héros
	 * 
	 * @param pouvoir
	 *            le pouvoir du héros
	 */
	private void setPouvoir(Capacite pouvoir) {
		if (pouvoir == null)
			throw new IllegalArgumentException("Le pouvoir ne peut pas être nul !");

		this.pouvoir = pouvoir;
	}

	/**
	 * Retourne le nom du héros
	 * 
	 * @return le nom du héros
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Retourne la vie du héros
	 * 
	 * @return la vie du héros
	 */
	public int getVie() {
		return this.vie;
	}

	/**
	 * Retourne le pouvoir du héros
	 * 
	 * @return le pouvoir du héros
	 */
	public Capacite getPouvoir() {
		return this.pouvoir;
	}

	@Override
	public void recevoirDegats(int degats) {
		this.setVie(this.getVie() - degats);
	}

	@Override
	public boolean peutRecevoirDegats() {
		IJoueur joueur = Plateau.getInstance().getJoueurCourant();
		if (joueur.getHeros() != this) {
			try {
				joueur = Plateau.getInstance().getAdversaire(joueur);
			} catch (HearthstoneException e) {
				e.printStackTrace();
			}
		}

		boolean provocationSurLeTerrain = false;
		for (ICarte carte : joueur.getJeu()) {
			if (carte.getCapacite() instanceof Provocation) {
				provocationSurLeTerrain = true;
				break;
			}
		}

		return !provocationSurLeTerrain;
	}

	@Override
	public int getAttaque() {
		// TODO Auto-generated method stub
		return 0;
	}

}
