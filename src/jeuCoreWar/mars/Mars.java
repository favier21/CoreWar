package jeuCoreWar.mars;


//prevoir un petit timer entre chaques modifs car l'interface graphique utiliste un système de rafraichissement toutes les n milisecondes


public class Mars {

private String j1,j2;

private int taille;

public Memoire memoire;

public Ordonnanceur ordonnanceur;

public Interpreteur interpreteur;
private int instructionActuelle;

private int derniereligne1;

private int derniereligne2;
public Mars(int taille,Memoire mem,Ordonnanceur ordo){this("j1","j2",taille,mem,ordo);}
public Mars(String j1, String j2,int taille,Memoire mem,Ordonnanceur ordo){
  this.j1 = j1;
  this.j2 = j2;
  this.taille = taille;

  this.memoire = mem;
  this.ordonnanceur = ordo;
  this.interpreteur = new Interpreteur(this.memoire,this.ordonnanceur);
  this.derniereligne1 = 0;
  this.derniereligne2 = this.taille/2;

  //this.addresseInstructionActuelle = ; le j1 commence case 0 et j2 commence commence taille/2

}
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

public int getTaille(){
  return this.taille;
}
public void clearTab(){
  this.memoire.fill(new int[]{0,0,0,0,0});
}
public int[][] getTab(){
  return this.memoire.getTab();
}
public int getDerniereLigne(String joueur){
  if (joueur == this.j1) {
      return this.derniereligne1;
  }
  return this.derniereligne2;
}
public void ligneSuivante(String joueur){
  if (joueur == this.j1) {
    this.derniereligne1+=1;
  }
  else {
    this.derniereligne2+=1;
  }

}
public void ajoutInit(String joueur,int[] code){
  int addresse = this.getDerniereLigne(joueur);
  this.memoire.setTab(addresse,code);
  this.ligneSuivante(joueur);
}
//renvoi une liste contenant:
//  -le gagnant en première position , en dexième,
//  -la survivabilité du j1(utile seulement pour l'algorithme genetique)
//  -la vitesse du code (le nombre de tour pris pour gagner)
public int[] start(){ 
  return this.start(false);
}
public int[] start(Boolean visible){ 
  return this.start(visible,500);
}
public int[] start(Boolean visible,int nbtour){
  return this.start(visible,nbtour,false);
}
public int[] start(Boolean visible,int nbtour,Boolean affichageConsle){
  this.ordonnanceur.addInstructionA(0);
  this.ordonnanceur.addInstructionB(this.taille/2);
  this.instructionActuelle = this.ordonnanceur.getInstructionActuelle();
  int i;
  for (i=0; i<nbtour; i++){
    
    this.interpreteur.updateCode();
    int addresse = this.interpreteur.associeLeNomALaMethode();
    if (addresse != -1) {
      this.ordonnanceur.addInstruction(addresse);
    }
    if (this.ordonnanceur.getWinner() != -1) {
      this.ordonnanceur.setFin(true);
      int[] result = new int[]{this.ordonnanceur.getWinner(),this.ordonnanceur.getListeInstructionA().size(),i};
      return result;
    }
		this.ordonnanceur.changerTour();
    if(visible){
      wait(10);
    }
    if(affichageConsle){
      String mem1 = "";
      for (int j=0; j<this.taille; j++) {
        mem1 += this.getTab()[j][0] + " ";
      }
      System.out.println(mem1);
    }
  }
  this.ordonnanceur.setFin(true);
  int[] result = new int[]{this.ordonnanceur.getWinner(),this.ordonnanceur.getListeInstructionA().size(),i};
  return result;
}
}
