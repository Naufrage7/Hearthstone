package joueur;

import java.util.ArrayList;

import application.Main;
import capacite.AttaqueCiblee;
import capacite.AttaqueDuHeros;
import capacite.AttaqueTotale;
import capacite.Charge;
import capacite.EffetPermanent;
import capacite.ImageMiroir;
import capacite.InvocationDeServiteurs;
import capacite.InvocationDesChiens;
import capacite.MarqueDuChasseur;
import capacite.Pioche;
import capacite.Provocation;
import carte.ICarte;
import carte.Serviteur;
import carte.Sort;
import exception.CibleInvalideException;
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
		this.deck.add(new Sort("Charge", 1, new Charge(), this));
		this.deck.add(new Sort("Attaque mentale", 2, new AttaqueDuHeros("Attaque mentale", 5), this));
		this.deck.add(new Serviteur(6, 6, "Champion de Hurlevent", 7, new EffetPermanent("Bonus de hurlevent", 1, 1),this));
		this.deck.add(new Serviteur(2, 2, "Chef de raid", 3, new EffetPermanent("Bonus du chef de raid", 1, 0), this));
		this.deck.add(new Serviteur(5, 4, "Garde de Baie-du-butin", 5, new Provocation("Provocation", "Force le joueur adverse à viser cette cible"), this));
		this.deck.add(new Serviteur(5, 2, "La missilière téméraire", 6, new Charge(), this));
		this.deck.add(new Serviteur(4, 4, "L'orgre-magi", 4, new Provocation("Provocation", "Force le joueur adverse à viser cette cible"), this));
		this.deck.add(new Serviteur(4, 7, "Archimage", 6, new Provocation("Provocation", "Force le joueur adverse à viser cette cible"), this));
		this.deck.add(new Serviteur(1, 1, "Gnôme lépreux", 1, new AttaqueCiblee("Attaque du lépreux", 2), this));
		this.deck.add(new Serviteur(2, 3, "Golem des moissons", 3, new InvocationDeServiteurs("Golémisation", "Invoque un \"Golem endomagé\" +2/+1 qui n'a aucune capacité", new Serviteur(2, 1, "Golem endomagé", 0, this)), this));
	
		if ( this.heros.getNom().equals("Jaina") ) {
			this.deck.add(new Sort("Choc de flamme", 7, new AttaqueTotale("Attaque massive", 4), this));
			this.deck.add(new Sort("Eclair de givre", 2, new AttaqueCiblee("Attaque du givre", 3), this));
			this.deck.add(new Sort("Intelligence des arcanes", 2, new Pioche("Pioche", "Pioche 2 cartes", 2), this));
			
			ImageMiroir imageMiroir = new ImageMiroir("Image miroir", "");
			imageMiroir.ajouterServiteur(new Serviteur(0, 2, "Serviteur de Jaina", 0, this));
			this.deck.add(new Sort("Image miroir", 7, imageMiroir, this));
			this.deck.add(new Sort("Explosion pyrotechnique", 10, new AttaqueCiblee("Explosion pyrotechnique", 10), this));
		} else if ( this.heros.getNom().equals("Rexxar") ) {
			this.deck.add(new Serviteur(3, 2, "Busard affamé", 5, new Pioche("Pioche", "Pioche 1 carte", 1), this));
			this.deck.add(new Sort("Marque du chasseur", 1, new MarqueDuChasseur("Marque du chasseur", "Réduit à 1 le nombre de points de vie"), this));
			this.deck.add(new Sort("Tir des arcanes", 1, new AttaqueCiblee("Tir des arcanes", 2), this));
			this.deck.add(new Sort("Lâchez les chiens", 3,  new InvocationDesChiens("", "", this), this));
			this.deck.add(new Sort("Ordre de tuer", 3, new AttaqueCiblee("Ordre de tuer", 3), this));
		}
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
		
		try {
			this.piocher();
		} catch ( HearthstoneException e ) {
			System.out.println(e.getMessage());
		}
		
		for ( ICarte carte : this.jeu ) {
			try {
				carte.executerEffetDebutTour(null);
			} catch ( CibleInvalideException e ) {
				Object cible = Main.demanderCible();
				carte.executerEffetDebutTour(cible);
			} catch ( HearthstoneException e ) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void finirTour() throws HearthstoneException {
		for ( ICarte carte : this.jeu ) {			
			try {
				carte.executerEffetFinTour(null);
			} catch ( CibleInvalideException e ) {
				Object cible = Main.demanderCible();
				carte.executerEffetFinTour(cible);
			} catch ( HearthstoneException e ) {
				e.printStackTrace();
			}
			
			if ( carte.disparait() ) {
				perdreCarte(carte);
				return;
			}
			
			if ( carte instanceof Serviteur ) {
				Serviteur serviteur = (Serviteur) carte;
				if ( !serviteur.peutAttaquer() )
					serviteur.setPeutAttaquer(true);
			}
		}
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
		jouerCarte(carte, null);
	}

	@Override
	public void jouerCarte(ICarte carte, Object cible) throws HearthstoneException {
		if ( carte.getCout() > this.stockMana )
			throw new HearthstoneException("Impossible de jouer une carte coutant plus de mana que le joueur n'en possède");
		
		carte.executerEffetDebutMiseEnJeu(cible);
		this.stockMana -= carte.getCout();
		this.main.remove(carte);
		this.jeu.add(carte);
		
		if ( carte instanceof Sort )
			this.perdreCarte(carte);
	}

	@Override
	public void utiliserCarte(ICarte carte, Object cible) throws HearthstoneException {
		if ( carte instanceof Serviteur ) {
			((Serviteur)carte).attaquer(cible);
		}
	}

	@Override
	public void utiliserPouvoir(Object cible) throws HearthstoneException {
		this.getHeros().getPouvoir().executerAction(cible);
	}

	@Override
	public void perdreCarte(ICarte carte) throws HearthstoneException {
		carte.executerEffetDisparition(null);
		jeu.remove(carte);
	}

}
