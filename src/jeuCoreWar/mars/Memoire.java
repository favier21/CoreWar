package jeuCoreWar.mars;
import java.util.Arrays;
public class Memoire {

public int[][] tabMemoire;

//public Memoire(){this(100);}

public Memoire(int taille){
  this.tabMemoire = new int[taille][5];
  Arrays.fill(this.tabMemoire, new int[]{0,-1,-1,-1,-1});

}

//getter

public int[][] getTab(){
  return this.tabMemoire;
}

//setter

public void setTab(int addresse,int[] contenu){
  if(addresse != -1)
    this.tabMemoire[addresse%(this.tabMemoire.length)] = contenu;
}


public void resetTab(int[][] tab){
  this.tabMemoire = tab;
}
public void fill(int[] elt){
  Arrays.fill(this.tabMemoire, elt);
}


}
