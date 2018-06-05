package interactions;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import carte.ICarte;
import carte.ICible;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.Plateau;

public abstract class Interaction {
	private Interaction suivant = null;
	private int choix;
	
	public Interaction(int choix) {
		this.choix = choix;
	}
	
	public Interaction setSuivant(Interaction interaction) {
		if ( interaction == null )
			throw new IllegalArgumentException("L'intéraction suivante ne peut pas être nulle !");
		
		suivant = interaction;
		return suivant;
	}
	
	public ArrayList<String> getMessages() {
		ArrayList<String> messages = new ArrayList<String>();
		messages.add(getMessage());
		
		Interaction temporaire = suivant;
		while ( temporaire != null ) {
			messages.add(temporaire.getMessage());
			temporaire = temporaire.suivant;
		}
		
		return messages;
	}
	
	public void traiter(int choix) throws InteractionChoixNonValideException {
		if ( getChoix() == choix )
			traitementSpecialise();
		else if ( suivant != null )
			suivant.traiter(choix);
		else
			throw new InteractionChoixNonValideException("Le choix n'est pas dans le menu !");
	}
	
	public String getMessage() {
		return getChoix() + ". ";
	}
	
	public int getChoix() {
		return choix;
	}
	
	protected ICible getCible() {
		Scanner sc = new Scanner(System.in);
		IJoueur joueurCourant = Plateau.getInstance().getJoueurCourant();
		ICible cible = null;
		IJoueur joueurAdverse = null;
		
		try {
			joueurAdverse = Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant());
		} catch (HearthstoneException e) {
			e.printStackTrace();
		}
		
		System.out.println("Qui vises-tu ?");
		System.out.println(" - 1. Le héros adverse");
		System.out.println(" - 2. Un serviteur adverse");
		System.out.println(" - 3. Un serviteur allié");
		System.out.println(" - 4. Personne ...");

		boolean choixValide = false;
		String nomCarte = "";
		int choix = 0;
		
		while ( !choixValide ) {
			try {
				System.out.print("Ton choix : ");
				choix = sc.nextInt();
				choixValide = true;
			} catch ( InputMismatchException e ) {
				System.out.println("Un nombre, je veux un nombre !");
			}
		}
		
		sc.nextLine();
		
		Object carte = null;
		switch ( choix ) {
			case 1:
				cible = joueurAdverse.getHeros();
				break;
			case 2:
				System.out.print("Nom du serviteur : ");
				nomCarte = sc.nextLine();
				carte = joueurAdverse.getCarteEnJeu(nomCarte);
				if ( carte instanceof ICible )
					cible = (ICible) carte;
				break;
			case 3:
				System.out.print("Nom du serviteur : ");
				nomCarte = sc.nextLine();
				carte = joueurCourant.getCarteEnJeu(nomCarte);
				if ( carte instanceof ICible )
					cible = (ICible) carte;
				break;
		}
		
		return cible;
	}
	
	protected ICarte getCarteEnMain() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nom de la carte : ");
		String nomCarte = sc.nextLine();
		
		if ( nomCarte.length() == 0 )
			return null;

		IJoueur joueurCourant = Plateau.getInstance().getJoueurCourant();
		
		return joueurCourant.getCarteEnMain(nomCarte);
	}
	
	protected ICarte getCarteEnJeu() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nom de la carte : ");
		String nomCarte = sc.nextLine();
		
		if ( nomCarte.length() == 0 )
			return null;

		IJoueur joueurCourant = Plateau.getInstance().getJoueurCourant();
		
		return joueurCourant.getCarteEnJeu(nomCarte);
	}
	
	protected abstract void traitementSpecialise();
	
}
