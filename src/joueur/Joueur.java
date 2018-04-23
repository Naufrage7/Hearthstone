package joueur;

import java.util.ArrayList;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import heros.Heros;

public class Joueur implements IJoueur {
	
	private String pseudo;
	private Heros heros;
	private int mana;
	private int stockMana;
	private ArrayList<ICarte> main;
	private ArrayList<ICarte> jeu;
	private ArrayList<ICarte> deck;

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
		this.initialiserDeck();
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
	
	/**
	 * Attribue la main du joueur
	 * 
	 * @param main la main du joueur
	 */
	private void setMain(ArrayList<ICarte> main) {
		if ( main == null ) 
			throw new IllegalArgumentException("La main du joueur ne peut pas être nulle !");
		
		this.main = main;
	}
	
	/**
	 * Attribue le jeu du joueur
	 * 
	 * @param jeu le jeu du joueur
	 */
	private void setJeu(ArrayList<ICarte> jeu) {
		if ( jeu == null ) 
			throw new IllegalArgumentException("Le jeu du joueur ne peut pas être nul !");
		
		this.jeu = jeu;
	}
	
	private void setDeck(ArrayList<ICarte> deck) {
		if ( main == null ) 
			throw new IllegalArgumentException("Le deck du joueur ne peut pas être nul !");
		
		this.deck = deck;
	}
	
	private ArrayList<ICarte> getDeck() {
		return this.deck;
	}
	
	private void initialiserDeck() {
		this.setDeck(new ArrayList<ICarte>());
		this.deck.add(new Serviteur(2, 1, "Chasse-marée murloc", 2, this));
		this.deck.add(new Serviteur(6, 6, "Champion de Hurlevent", 7, this));
		this.deck.add(new Serviteur(2, 2, "Chef de raid", 3, this));
		this.deck.add(new Serviteur(5, 4, "Garde de Baie-du-butin", 5, this));
		this.deck.add(new Serviteur(5, 2, "La missilière téméraire", 6, this));
		this.deck.add(new Serviteur(4, 4, "L'orgre-magi", 4, this));
		this.deck.add(new Serviteur(4, 7, "Archimage", 6, this));
		this.deck.add(new Serviteur(1, 1, "Gnôme lépreux", 1, this));
		this.deck.add(new Serviteur(2, 3, "Golem des moissons", 3, this));
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
		if ( this.deck.size() == 0 )
			throw new HearthstoneException("Le deck est vide malheureux !");
		
		ICarte carte = this.deck.remove(0);
		this.main.add(carte);
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
