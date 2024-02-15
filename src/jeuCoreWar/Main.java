package jeuCoreWar;
import java.lang.Thread;
import java.util.Arrays;

import jeuCoreWar.genetique.*;
import jeuCoreWar.graphique.*;
import jeuCoreWar.mars.*;
public class Main {

	public static void wait(int ms)
{
    try
    {
        Thread.sleep(ms);
    }
    catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }
}
	public static void afficherType(int type){
	switch ( type ) { //affichage lisible du type décrit par un int dans le programme
		case 0:
			System.out.print("dat ");
			break;
		case 1:
			System.out.print("mov ");
			break;
		case 2:
			System.out.print("add ");
			break;
		case 3:
			System.out.print("sub ");
			break;
		case 4:
			System.out.print("jmp ");
			break;
		case 5:
			System.out.print("jmz ");
			break;
		case 6:
			System.out.print("jmn ");
			break;
		case 7:
			System.out.print("cmp ");
			break;
		case 8:
			System.out.print("slt ");
			break;
		case 9:
			System.out.print("djn ");
			break;
		case 10:
			System.out.print("spl ");
			break;

	default:
		break;
	}
}
	public static void afficherMod(int mod){
	switch (mod) { //affichage du mod décrit par un int
		case 0:
			System.out.print(" ");
			break;
		case 1:
			System.out.print("#");
			break;
		case 2:
			System.out.print("@");
			break;
		
		default:
			break;
	}
}
	public static void demonstrationGenetique(){
		System.out.println("debut de la sélection:");
		Individu elus = Selection.selectionTournoi(1000);
		System.out.println("séléction terminé!");
		System.out.println("le programe est en "+elus.getTaille()+" instructions");
		System.out.println("voici les instructions du programe sélectionné:");
		for (int[] ligneCode : elus.getCode()) {
			afficherType(ligneCode[0]);
			afficherMod(ligneCode[1]);
			System.out.print(ligneCode[2]+",");
			afficherMod(ligneCode[3]);
			System.out.println(ligneCode[4]+"");
		}
	}
	public static void demonstrationVisuelle(){
		Ordonnanceur ordo = new Ordonnanceur("joueur1","joueur2");
		Menu men = new Menu(ordo);
		while(!ordo.getDebut()){wait(40);}
		Memoire memoire = new Memoire(men.getTaille());
		Mars mars = new Mars("joueur1","joueur2",men.getTaille(),memoire,ordo);
		mars.ajoutInit("joueur1", new int[]{1,0,0,0,1});
		mars.ajoutInit("joueur2", new int[]{1,0,0,0,1});
		mars.start(true);
	}
	public static void demonstrationVisuelleGenetique(){
		System.out.println("debut de la sélection:");
		Individu elus = Selection.selectionTournoi(1000);
		System.out.println("séléction terminé!");
		System.out.println("Affichage graphique");
		Ordonnanceur ordo = new Ordonnanceur("joueur1","joueur2");
		Menu men = new Menu(ordo);
		int TAILLE = men.getTaille();
		Memoire memoire = new Memoire(TAILLE);
		Mars mars = new Mars("joueur1","joueur2",TAILLE,memoire,ordo);
		for (int[] ligneCode : elus.getCode()) {
			mars.ajoutInit("joueur1",Arrays.copyOf(ligneCode,5));
		}
		mars.ajoutInit("joueur2", new int[]{1,0,0,0,1});

		while(!ordo.getDebut()){wait(40);}
		mars.start(true,100);





	}
	public static void demonstrationConsole(){
		Ordonnanceur ordo = new Ordonnanceur("joueur1","joueur2");
		Memoire memoire = new Memoire(100);
		Mars mars = new Mars("joueur1","joueur2",100,memoire,ordo);
		mars.ajoutInit("joueur1", new int[]{1,0,0,0,1});
		mars.ajoutInit("joueur2", new int[]{1,0,0,0,1});
		mars.start(true,250,true);
	}
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("veuillez utiliser des arguments (voir le fichier README.txt)");
		}
		else{
			if(args[0].equals("genetique")){
				demonstrationGenetique();
			}
			else if(args[0].equals("visuel")){
				demonstrationVisuelle();
			}
			else if(args[0].equals("visuelGenetique")){
				demonstrationVisuelleGenetique();
			}
			else if(args[0].equals("console")){
				demonstrationConsole();
			}
			else{
				System.out.println("les arguments sont incorectes");
			}
		}
	}	


		/* Récapitulatif des fonctions :
			traduction() : ok
			associeLeNomALaMethode() : ok
			mov() : ok
			add() :
		
		
		
		
		int TAILLE = men.getTaille();

		Memoire memoire = new Memoire(TAILLE);
		//Mars M = new Mars("moi","PASmoi",TAILLE,memoire,ordo);

		////////////////////////////// Affichage mem1

		////////////////////////////// Affichage mem2 - test mov()
		Individu test = new Individu(new int[]{1,0,0,0,0});
		test.addCode(new int[]{1,0,0,0,0});
		test.addCode(new int[]{1,0,0,0,0});
		test.addCode(new int[]{4,0,-2,0,0});


		EvaluationIndividu eval = new EvaluationIndividu(test);
		eval.initGrille();
		System.out.println(eval.evaluerdwarf());


		System.out.println(
		"-----------------\n" +
		"type : " +  inter.type + "\n" +
		"modA : " + inter.modA  + "\n" +
		"a    : " + inter.a    + "\n" +
		"modB : " + inter.modB + "\n" +
		"b    : " + inter.b    + "\n" +
		"inst : " + ordo.getInstructionActuelle() +
		"\n-----------------");
		String mem3 = "";
		for (int i=0; i<TAILLE; i++) {
			mem3 += M.GetTable()[i][0] + " ";
		}

		System.out.println(mem3);
*/
		
}

