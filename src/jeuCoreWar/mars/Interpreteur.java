package jeuCoreWar.mars;
import java.lang.Math;

// RedCode-88

public class Interpreteur {

	public Memoire memoire;
	public int[][] tabMemoire;
	private Ordonnanceur ordo;

	// Les 5 variables sont en public pour les tests, mais de base sont en private
	public int type;
	public int modA;
	public int a;
	public int modB;
	public int b;

	public int addresse;

	public Interpreteur(Memoire memoire, Ordonnanceur ordo) {
		this.memoire = memoire;
		this.tabMemoire = memoire.getTab();
		this.ordo = ordo;
		this.type = -1;
		this.modA = -1;
		this.a    = -1;
		this.modB = -1;
		this.b    = -1;
		this.addresse = -1;
	}
	public void deCode(int[] code){
		this.type = code[0];
		this.modA = code[1];
		this.a    = code[2];
		this.modB = code[3];
		this.b    = code[4];
	}
	public void updateCode(){
		this.addresse = this.ordo.getInstructionActuelle();
		if (this.addresse >= 0){
			int[] instruction = this.tabMemoire[this.borne(this.addresse)];
			this.deCode(instruction);
		}
		else
			this.type = 0;
	}
	
		//ces trois méthodes correspondent au 3 types d'addressages
	public int relative(int valeur){ // direct
		return this.borne(this.addresse+valeur);
	}
	
	public int borne(int valeur){ // immédiat #
		if (valeur >=0) {
			return valeur % (this.tabMemoire.length);
		} else {
			return this.tabMemoire.length - this.borne(Math.abs(valeur)) ;
		}
		
	}
		
	public int indirect(int valeur){ // indirect @
		return this.tabMemoire[this.relative(valeur)][4];
	}

	public int addressage(int valeur,int mod){
		if(mod == 0) // direct
			return this.borne(this.relative(valeur));
		if(mod == 1) // immédiat #
			return this.borne(valeur);
		else // indirect @
			return this.borne(this.indirect(valeur));
	}

	public int associeLeNomALaMethode() {
		switch ( this.type ) { // Switch veut un entier
			case 0:
				return this.dat();
			case 1:
				return this.mov();
			case 2:
				return this.add();
			case 3:
				return this.sub();
			case 4:
				return this.jmp();
			case 5:
				return this.jmz();
			case 6:
				return this.jmn();
			case 7:
				return this.cmp();
			case 8:
				return this.slt();
			case 9:
				return this.djn();
			case 10:
				return this.spl();
		}

		return -1;
	}

	// Les méthodes reste bien private, le temps des tests je les mets en public

	private int dat() {
		return -1; // Retourne une "erreur" qui sera gérer par Mars
	}

	public int mov() {	
		this.memoire.setTab(this.addressage(this.b,this.modB), this.tabMemoire[this.addressage(this.a,this.modA)]);
		return this.borne(this.addresse + 1); // Retourne l'instruction suivante pour Mars
	}

	private int add() {
		int[] instruction = this.tabMemoire[this.addressage(this.b,this.modB)];
		instruction[4] = this.borne(this.a+this.b);
		this.memoire.setTab(this.addressage(this.b,this.modB), instruction);

		return this.addresse + 1; // Retourne l'instruction suivante pour Mars
        }

        private int sub() {
        	int[] instruction = this.tabMemoire[this.addressage(this.b,this.modB)];
			instruction[4] = this.borne(this.a-this.b);
			this.memoire.setTab(this.addressage(this.b,this.modB), instruction);
        	
        	return this.addresse + 1; // Retourne l'instruction suivante pour Mars	
        }

        private int jmp() {
        	//return this.borne(this.a);
        	return this.addressage(this.a, this.modA);
        }


        private int jmz() {
        	if (this.b == 0) {
        		return this.addressage(this.a, this.modA);
                }
                return this.addresse + 1; // Retourne l'instruction suivante pour Mars
        }

        private int jmn() {
        	if (this.b != 0) {
        		return this.addressage(this.a, this.modA);
                }
                return this.addresse + 1; // Retourne l'instruction suivante pour Mars
        }

        private int cmp() {
        	/*
        	System.out.println("OKKKKKKKKK " + this.a);
        	int nbAdresseA = this.tabMemoire[this.addressage(this.a-1,this.modA)][2]; // Le nombre de départ
        	int nbAdresseB = this.tabMemoire[this.addressage(this.b-1,this.modB)][2]; // Le nombre de destination
        	System.out.println("LOUOUOUOUOUOUUO A : " + nbAdresseA  );
        	System.out.println("LOUOUOUOUOUOUUO B : " + nbAdresseB  );
        	*/
        	
        	int nbAdresseA = this.tabMemoire[this.borne(this.a-1)][2]; // Le nombre de départ
        	int nbAdresseB = this.tabMemoire[this.borne(this.b-1)][2]; // Le nombre de destination
        	
        	//System.out.println("nbAdresseA == " + nbAdresseA);
        	//System.out.println("nbAdresseB == " + nbAdresseB);
        	
        	if (nbAdresseA == nbAdresseB) {
			return this.addresse + 2; // La prochaine instruction est ignorée
                }
        	return this.addresse + 1; // Retourne l'instruction suivante pour Mars
        }

        private int slt() {
        	int nbAdresseA = this.tabMemoire[this.borne(this.a-1)][2]; // Le nombre de départ
        	int nbAdresseB = this.tabMemoire[this.borne(this.b-1)][2]; // Le nombre de destination
        	
        	//System.out.println("nbAdresseA == " + nbAdresseA);
        	//System.out.println("nbAdresseB == " + nbAdresseB);
        	
        	if (nbAdresseA < nbAdresseB) {
			return this.addresse + 2; // La prochaine instruction est ignorée
                }
        	return this.addresse + 1; // Retourne l'instruction suivante pour Mars
        }

        private int djn() {
        	this.b--;
        	if (this.b != 0) {
        		return this.addressage(this.a, this.modA);
                }
                return this.addresse + 1; // Retourne l'instruction suivante pour Mars
        }

        private int spl() {
        	this.ordo.addInstruction(this.a);
        
        	return this.addresse + 1; // Retourne l'instruction suivante pour Mars
        }


}
