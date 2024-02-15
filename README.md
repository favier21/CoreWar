groupe 4a

Membres:

Thomas Jouin

Mikail Sari

Hugo Favier

Pour compiler le programe, laçez la ligne suivante:
	javac -d build -cp @sources.txt

Pour lancer l'algorithme gégétique, lancez la commande suivante:
	java -cp build jeuCoreWar.Main genetique

 Cette commende vous affiche le code d'un programme selectionné avec un algorithme génétique


Pour lancer une version graphique, lancez la commande suivante:
	java -cp build jeuCoreWar.Main visuel

 Cette commende montre programes très simples s'affronter. Cepandant remarquez qu'il ne peut y avoir de gagnant alor le programe s'arrete après un nombre de tour prédefini


Pour lancer une illustration graphique d'un algorithme génétiquement modifié, lancez la commande suivante:
	java -cp build jeuCoreWar.Main visuelGenetique

 Remarquez que les algorithmes séléctionnés génétiquement pn une tendance générale à devenir des programes détruisant tout sur la mémoire, y compris eux memes


Pour lancer une version console, lancez la commande suivante:
	java -cp build jeuCoreWar.Main console
