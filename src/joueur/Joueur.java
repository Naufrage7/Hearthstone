package joueur;

import java.util.ArrayList;

import carte.ICarte;
import exception.HearthstoneException;
import heros.Heros;

public class Joueur implements IJoueur {
	
	private String pseudo;
	private Heros heros;
	private int mana;
	private int stockMana;
	private ArrayList<ICarte> main;
	private ArrayList<ICarte> jeu;

	/**
	 * Instancie un Joueur
	 * 
	 * @param pseudo le pseudo du joueur
	 * @param heros le héros du joueur
	 */
	public Joueur(String pseudo, Heros heros) {
		this.setPseudo(pseudo);
		this.setHeros(heros);
		this.setMana(0);
		this.setStockMana(0);
		this.setMain(new ArrayList<ICarte>());
		this.setJeu(new ArrayList<ICarte>());
	}

	/**
	 * Attribue le pseudo au joueur
	 * 
	 * @param pseudo le pseudo du joueur
	 */
	private void setPseudo(String pseudo) {
		if ( pseudo == null )
			throw new IllegalArgumentException("Le pseudo du joueur ne peut pas être nul !");
		this.pseudo = pseudo;
	}
	
	/**
	 * Attribue le héros au joueur
	 * 
	 * @param heros le héros du joueur
	 */
	private void setHeros(Heros heros) {
		if ( heros == null )
			throw new IllegalArgumentException("Le héros du joueur ne peut pas être nul !");
		this.heros = heros;
	}
	
	/**
	 * Attribue une quantité de mana au héros
	 * 
	 * @param mana la quantité de mana
	 */
	private void setMana(int mana) {
		if ( mana < 0 )
			throw new IllegalArgumentException("Le mana ne peut pas être une valeur négative !");
		else if ( mana > IJoueur.MAX_MANA )
			throw new IllegalArgumentException("Le mana ne peut pas dépasser " + IJoueur.MAX_MANA + " !");
		this.mana = mana;
	}
	
	/**
	 * Attribue une quantité au stock de mana
	 * 
	 * @param stockMana la quantité du stock de mana
	 */
	private void setStockMana(int stockMana) {
		if ( stockMana < 0 )
			throw new IllegalArgumentException("Le stock de mana ne peut pas être une valeur négative !");
		else if ( stockMana > mana )
			throw new IllegalArgumentException("Le stock de mana ne peut pas être supérieur au mana !");
		this.stockMana = stockMana;
	}
	
	private void setMain(ArrayList<ICarte> main) {
		this.main = main;
	}
	
	private void setJeu(ArrayList<ICarte> jeu) {
		this.jeu = jeu;
	}
	
	@Override
	public Heros getHeros() {
		return this.heros;
	}

	@Override
	public String getPseudo() {
		return this.pseudo;
	}

	@Override
	public int getMana() {
		return this.mana;
	}

	@Override
	public int getStockMana() {
		return this.stockMana;
	}

	@Override
	public ArrayList<ICarte> getMain() {
		return this.main;
	}

	@Override
	public ArrayList<ICarte> getJeu() {
		return this.jeu;
	}

	@Override
	public ICarte getCarteEnJeu(String nomCarte) {
		ICarte carte = null;
		for (ICarte c : jeu) {
			if ( c.getNom().contains(nomCarte) ) {
				carte = c;
				break;
			}
		}
		
		return carte;
	}

	@Override
	public ICarte getCarteEnMain(String nomCarteMain) {
		ICarte carte = null;
		for (ICarte c : main) {
			if ( c.getNom().contains(nomCarteMain) ) {
				carte = c;
				break;
			}
		}
		
		return carte;
	}

	@Override
	public void prendreTour() throws HearthstoneException {
		if ( this.mana < IJoueur.MAX_MANA )
			this.setMana(this.mana + 1);
		this.stockMana = this.mana;
	}

	@Override
	public void finirTour() throws HearthstoneException {
	}

	@Override
	public void piocher() throws HearthstoneException {
	}

	@Override
	public void jouerCarte(ICarte carte) throws HearthstoneException {
		if ( carte.getCout() > this.stockMana )
			throw new HearthstoneException("Impossible de jouer une carte coutant plus de mana que le joueur n'en possède");
	}

	@Override
	public void jouerCarte(ICarte carte, Object cible) throws HearthstoneException {
		if ( carte.getCout() > this.stockMana )
			throw new HearthstoneException("Impossible de jouer une carte coutant plus de mana que le joueur n'en possède");
	}

	@Override
	public void utiliserCarte(ICarte carte, Object cible) throws HearthstoneException {
	}

	@Override
	public void utiliserPouvoir(Object cible) throws HearthstoneException {
	}

	@Override
	public void perdreCarte(ICarte carte) throws HearthstoneException {
	}
	
	public String toString() {
		return "Joueur [pseudo="+this.pseudo+", mana="+this.mana+", stockMana="+this.stockMana+", heros="+this.heros+", main="+this.main+", jeu="+this.jeu+"]";
	}

}
