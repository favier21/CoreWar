package jeuCoreWar.mars;
import java.util.LinkedList;


public class Ordonnanceur {

private String j1,j2;

private String joueurActuel;

private LinkedList<Integer> addresseInstructionA;

private LinkedList<Integer> addresseInstructionB;

private int addresseInstructionActuelle;

private boolean debut;

private boolean fin;

public Ordonnanceur(){ this("j1","j2");}
public Ordonnanceur(String j1, String j2){this(j1,j2,new LinkedList<Integer>(),new LinkedList<Integer>());}
public Ordonnanceur(String j1, String j2,LinkedList<Integer> instructionA,LinkedList<Integer> instructionB ){
  this.j1 = j1;
  this.j2 = j2;
  this.addresseInstructionA = instructionA;
  this.addresseInstructionB = instructionB;
  if(instructionA.size()!=0)
    this.addresseInstructionActuelle = instructionA.getFirst();
  this.joueurActuel = j1;
  this.debut = false;
  this.fin = false;
}

  //toString

@Override
public String toString(){
  return "j1:"+this.j1+"\nj2:"+this.j2+"\ninstruction actuelle:"+this.addresseInstructionActuelle+"\nJoueurActuel:"+this.joueurActuel;
}

  //getters

public int getInstructionActuelle(){
  return this.addresseInstructionActuelle;
}
public String getJoueur1(){
  return this.j1;
}
public String getJoueur2(){
  return this.j2;
}
public String getJoueurActuel(){
  return this.joueurActuel;
}
public LinkedList<Integer> getListeInstructionA(){
  return this.addresseInstructionA;
}
public LinkedList<Integer> getListeInstructionB(){
  return this.addresseInstructionB;
}
  //utile pour la comunication entre Mars et Menu
public boolean getDebut(){
  return this.debut;
}
public boolean getFin(){  
  return this.fin;
}

  //adders et setters

public void addInstruction(int nouvelleAdresse){
	if(this.j1 == this.joueurActuel)
		this.addInstructionA(nouvelleAdresse);
	else
		this.addInstructionB(nouvelleAdresse);
}
public void addInstructionA(int nouvelleAdresse){
  this.addresseInstructionA.addLast(nouvelleAdresse);
}

public void addInstructionB(int nouvelleAdresse){
  this.addresseInstructionB.addLast(nouvelleAdresse);
}

public void setInstructionActuelle(LinkedList<Integer> listeInstruction){
  this.addresseInstructionActuelle = listeInstruction.getFirst();
  
}
public void setDebut(boolean commence){
  this.debut = commence;
}
public void setFin(boolean estfini){ //utile pour la partie graphique
  this.fin = estfini;
}


  //Autres méthodes

public void changerTour(){
  if (this.joueurActuel == this.j1){
    this.joueurActuel = this.j2;
    this.setInstructionActuelle(this.addresseInstructionB);
    this.addresseInstructionA.removeFirst();
  }
  else{
    this.joueurActuel = this.j1;
    this.setInstructionActuelle(this.addresseInstructionA);
    this.addresseInstructionB.removeFirst();
  }
}
public int getWinner(){
  if(this.addresseInstructionB.isEmpty()){
    return 1;
  }
  if(this.addresseInstructionA.isEmpty()){
    return 2;
  }
  return -1;
}
}
