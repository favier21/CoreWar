package jeuCoreWar.genetique;
import java.util.Arrays;
import jeuCoreWar.mars.*;



public class EvaluationIndividu {
    //c'est la qu'on lance le jeu coreWar avec le code de chaque individu
    private Individu individu;

    private Mars moteur;

    public EvaluationIndividu(Individu individu){
      this.individu = individu;
      this.moteur = new Mars("individu","enemis",100,new Memoire(100),new Ordonnanceur());
    }

    public void initGrille(){
      this.moteur.clearTab();
      for (int[] ligneCode : this.individu.getCode()) {
        this.moteur.ajoutInit("individu",Arrays.copyOf(ligneCode,5));
    }
    }

    public int evaluer(){
      this.initGrille();
      int score_final = 0;
      score_final +=this.evaluerimp();
      this.initGrille();
      score_final +=this.evaluerdwarf();
      this.initGrille();
      score_final +=this.evaluerCHANG1();
      return score_final;
    }
    public int calculScore(int[] resultats){
      boolean victoire = resultats[0] == 1;  //si l'individu à gagné ou non
      boolean plusieurs_threads = resultats[1] >=2;  //si l'individu à effectué au moins un split ou non
      int vitesse = resultats[2]; // le nombre de tour que le programe à pris pour gagner

      int score = 0;
      if(victoire){
      score += 5+(500-vitesse);
      }
      if (plusieurs_threads) {
      score += 5;
      }
      return score;
    }

    public int evaluerimp(){ // test l'individu contre un warrior de type imp
      this.moteur.ajoutInit("enemis",new int[]{1,0,0,0,1});// mov 0 1
      int[] resultats = this.moteur.start();
      return this.calculScore(resultats);
    }
    public int evaluerdwarf(){// test contre un nain/dwarf
      this.moteur.ajoutInit("enemis",new int[]{2,1,4,0,3});
      this.moteur.ajoutInit("enemis",new int[]{1,0,2,2,2});
      this.moteur.ajoutInit("enemis",new int[]{4,0,-2,0,0});
      this.moteur.ajoutInit("enemis",new int[]{0,1,0,1,0});
      int[] resultats = this.moteur.start();
      return this.calculScore(resultats);
    }
    public int evaluerCHANG1(){// test contre de CHANG1
      this.moteur.ajoutInit("enemis",new int[]{4,0,4,0,0});
      this.moteur.ajoutInit("enemis",new int[]{1,0,2,0,-1});
      this.moteur.ajoutInit("enemis",new int[]{4,0,-1,0,0});
      this.moteur.ajoutInit("enemis",new int[]{0,0,9,0,9});
      this.moteur.ajoutInit("enemis",new int[]{10,0,-2,0,-2});
      this.moteur.ajoutInit("enemis",new int[]{10,0,4,0,4});
      this.moteur.ajoutInit("enemis",new int[]{2,1,-16,0,-3});
      this.moteur.ajoutInit("enemis",new int[]{1,0,-4,2,-4});
      this.moteur.ajoutInit("enemis",new int[]{4,0,-4,0,-4});
      this.moteur.ajoutInit("enemis",new int[]{10,0,2,0,2});
      this.moteur.ajoutInit("enemis",new int[]{4,0,-1,0,-1});
      this.moteur.ajoutInit("enemis",new int[]{1,0,0,0,1});
      int[] resultats = this.moteur.start();
      return this.calculScore(resultats);
    }

    public int evaluerCustom(Individu enemis){
      for (int[] ligneCode : this.individu.getCode()) {
        this.moteur.ajoutInit("enemis",Arrays.copyOf(ligneCode,5));
      }
      int[] resultats = this.moteur.start();
      return this.calculScore(resultats);
    }

}
