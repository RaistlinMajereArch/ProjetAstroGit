package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import metier.Admin;
import metier.Compte;
import metier.CorpsCeleste;
import metier.Planete;
import metier.Position;
import metier.Etoile;
import metier.Satellite;
import metier.Utilisateur;
import DAO.DAOCompte;
import DAO.DAOPositions;
import DAO.DAOSystemeInit;
import DAO.DAOSysteme;

public class App {
	static Compte connected;
	static DAOCompte daoC = new DAOCompte();
	static DAOSystemeInit daoSI = new DAOSystemeInit();
	static DAOSysteme daoS = new DAOSysteme();
	static DAOPositions daoP = new DAOPositions();
	static List<CorpsCeleste> systeme=new ArrayList<>();

	public static int saisieInt(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextInt();

	}
	public static double saisieDouble(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextDouble();
	}
	public static String saisieString(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextLine();
	}


	public static void main(String[] args) {

		menuPrincipal();
		String miseAJour = saisieString("\nSouhaitezvous modifier votre systeme ?");


	}

	public static void menuPrincipal() {// genere le menu principal et propose les options

		System.out.println("\nCree ton systeme solaire!");
		System.out.println("1- Se connecter");
		System.out.println("2- Creation d'un compte utilisateur");
		System.out.println("3- Fermer l'appli");
		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
		case 1 : String login = saisieString("\nSaisir login"); String password = saisieString("Saisir password"); connected=DAOCompte.seConnecter(login, password);break;
		case 2 : String loginNewAccount = saisieString("\nSaisir login"); String passwordNewAccount = saisieString("Saisir password"); Utilisateur user = new Utilisateur(loginNewAccount,passwordNewAccount);daoC.insert(user);System.out.println("Compte cr��.");menuPrincipal();break;
		case 3 : System.exit(0);break;
		}		
		if(connected instanceof Utilisateur)
		{
			menuUtilisateur();
		}
		else {
			System.out.println("Identifiants invalides !");
			menuPrincipal();
		}
	}
	public static void menuUtilisateur() {// genere le menu de l'utilisateur et propose les options
		System.out.println("\nMenu");
		System.out.println("1- Creer un systeme");
		System.out.println("2- Charger un systeme");
		System.out.println("99- Se deconnecter");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : creerEtoile();break;
		case 2 : chargerSysteme();break;
		case 99 : connected=null;menuPrincipal();break;
		}
		menuUtilisateur();
	}

	public static void creerEtoile(){// cree une etoile au centre du systeme, immobile
		String nomEtoile = saisieString("\nSaisir le nom de l'etoile");
		Double masseEtoile=0d;
		Double diametreEtoile=0.0;
		boolean masseEtoileOk = false;
		boolean diametreEtoileOk = false;
		while (!masseEtoileOk) {
			masseEtoile = saisieDouble("Saisir la masse de l'etoile (en kg)");
			if (masseEtoile <= 0d) {
				System.out.println("La masse de l'etoile est incorrecte");
			} else {
				masseEtoileOk=true;		
			}
		}
		while (!diametreEtoileOk) {
			diametreEtoile = saisieDouble("Saisir le diametre de l'etoile (en km)");
			if (diametreEtoile <= 0d) {
				System.out.println("Le diametre de l'etoile est incorrect");
			} else {
				diametreEtoileOk=true;		
			}
		}
		Etoile e = new Etoile(masseEtoile, diametreEtoile, nomEtoile);
		systeme.add(e);
		daoSI.insert(e);
		boolean userHasFinished = false;
		boolean userIsCreating = true;
		while (userIsCreating) {
			String message = "";
			if (userHasFinished) {
				message = "Voulez-vous ajouter une nouvelle planete ? (y/n)";
			} else {
				message = "Ajouter une planete? (y/n)";
			}
			userHasFinished = true;
			String continuerOuiNon = saisieString(message);
			if (!continuerOuiNon.equalsIgnoreCase("y")) {
				userIsCreating = false;
			} else {
				creerPlanete(e);
			}
		}
	}

	public static void creerPlanete(Etoile e){ // cree une planete et ses eventuels satellites
		String nomPlanete=saisieString("Saisir le nom de la planete");
		boolean massePlaneteOk = true;
		Double massePlanete = e.getMasse();
		while (!massePlaneteOk) {
			massePlanete = saisieDouble("Saisir la masse de la planete (en kg)");
			if (massePlanete >= e.getMasse() || massePlanete <= 0d) {
				System.out.println("La masse de la planete est incorrecte");
			} else {
				massePlaneteOk=true;		
			}
		}
		boolean diametrePlaneteOk = true;
		Double diametrePlanete= 0d;
		while (!diametrePlaneteOk) {
			diametrePlanete= saisieDouble("Saisir le diametre de la planete (en km)");
			if (diametrePlanete <= 0d) {
				System.out.println("Le diametre de l'etoile est incorrect");
			} else {
				diametrePlaneteOk=true;		
			}
		}
		Double x0Planete=saisieDouble("Saisir la position x0 de la planete (en km par rapport � l'etoile)");
		Double y0Planete=saisieDouble("Saisir la position y0 de la planete (en km par rapport � l'etoile)");
		Double vitX0Planete=saisieDouble("Saisir la vitesse selon l'axe x de la planete (en km/s par rapport � l'etoile)");
		Double vitY0Planete=saisieDouble("Saisir la vitesse selon l'axe y de la planete (en km/s par rapport � l'etoile)");

		Planete p = new Planete(massePlanete,diametrePlanete,x0Planete,y0Planete,vitX0Planete,vitY0Planete,nomPlanete,1);
		systeme.add(p);
		daoSI.insert(p);

		boolean satelliteIsPicked = true;
		boolean satelliteIsCreated = false;;
		while (satelliteIsPicked) {
			String satelliteOuiNon = "";
			if (satelliteIsCreated) {
				satelliteOuiNon = saisieString("\nVoulez vous ajouter d'autres satellites a cette planete ? (y/n)");
			} else {
				satelliteOuiNon = saisieString("\nVoulez vous ajouter un/des satellites a cette planete ? (y/n)");
			}
			satelliteIsCreated = true;
			if (!satelliteOuiNon.equalsIgnoreCase("y")) {
				satelliteIsPicked = false;
			} else {

				boolean masseSatelliteOk = false;
				boolean diametreSatelliteOk = false;
				Double masseSatellite = 0d;
				Double diametreSatellite = 0d;
				int idPlaneteMere = p.getId();
				String nomSatellite = saisieString("\nSaisir le nom du satellite'");
				while (!masseSatelliteOk) {
					masseSatellite = saisieDouble("Saisir la masse du satellite (en kg)");
					if (masseSatellite >= p.getMasse() || massePlanete <= 0d) {
						System.out.println("La masse du satellite est incorrecte");
					} else {
						masseSatelliteOk=true;		
					}
				}
				while (!diametreSatelliteOk) {
					diametreSatellite = saisieDouble("Saisir le diametre du satellite (en km)");
					if (diametreSatellite <= 0d) {
						System.out.println("Le diametre du satellite est incorrect");
					} else {
						diametreSatelliteOk=true;		
					}
				}

				Double x0Satellite=saisieDouble("Saisir la position x0 du satellite (en km par rapport � l'etoile)");
				Double y0Satellite=saisieDouble("Saisir la position y0 du satellite (en km par rapport � l'etoile)");
				Double vitX0Satellite=saisieDouble("Saisir la vitesse selon l'axe x du satellite (en km/s par rapport � l'etoile)");
				Double vitY0Satellite=saisieDouble("Saisir la vitesse selon l'axe y du satellite (en km/s par rapport � l'etoile)");

				Satellite s = new Satellite(masseSatellite, diametreSatellite, x0Satellite, y0Satellite, vitX0Satellite, vitY0Satellite, nomSatellite, idPlaneteMere);
				systeme.add(s);
				daoSI.insert(s);		
			}
		}
	}

	public static void chargerSysteme(){
		daoS.findAll();
	}
	public static void avancerTimeStepSysteme() {// fait avancer l'ensemble du systeme d'un timestep
		for (int i=1;i<systeme.size();i++) {
			avancerTimeStepCorps(systeme.get(i));
		}
	}
	public static void avancerTimeStepCorps(CorpsCeleste c) {// fait avancer un corps celeste d'un timestep
		for (int i=1;i<systeme.size();i++) {
			List<double[]> forces = null;
			forces.add(c.calculForce(systeme.get(i)));
			double[] accelerations =c.calculAcceleration(forces);
			c.calculVitesse(accelerations);
			c.calculPosition();
		}
	}
	public static void simulation() {//lance et genere la simulation
		int timestep=saisieInt("Saisissez le nombre de timestep pour votre simulation (1 timestep=1jour) :");
		initSimu();
		for (int t=1;t<timestep;t++) {
			avancerTimeStepSysteme();
			for(int i=0;i<systeme.size();i++) {
				daoS.update(systeme.get(i));
			}
			for(int i=0;i<systeme.size();i++) {
				Position p=new Position(1,systeme.get(i).getId(),systeme.get(i).getX(),systeme.get(i).getY());
				daoP.insert(p);
			}
		}
	}
	public static void initSimu() {//initialise la simulation

		List<CorpsCeleste> systeme2=daoSI.findAll();
		for(int i=0;i<systeme2.size();i++) {
			daoS.insert(systeme2.get(i));
		}
		systeme2=daoS.findAll();
		for(int i=0;i<systeme2.size();i++) {
			Position p=new Position(1,systeme2.get(i).getId(),systeme2.get(i).getX(),systeme2.get(i).getY());
			daoP.insert(p);
		}
	}
	public static void retourT0() {//supprime les bdd de systeme et positions et r�initialise � t0
		daoS.deleteAll();
		daoP.deleteAll();
		initSimu();
	}
	public static void supprimerSimu() {//supprime les bdd de systeme, positions et systeme init
		daoS.deleteAll();
		daoSI.deleteAll();
		daoP.deleteAll();
	}
}		





