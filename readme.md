# Actualisation des Compétences en Développement et Conception
***
## Getting Sarted
Cette section décrit comment reproduire la solution localement.
##### Prérequis
 - Jekyll
 - Java
 - Git et une clée SSH configurée

Cloner ce répertoire
Ouvrir le dossier dans Eclipse et indiquer dans le main le chemin du blog.
Génerer un jar et le lancer avec la commande:
`java -jar nomdujar.jar`
***
## Introduction
##### Contexte
Ce projet a été mené dans le cadre de de l'UV ACDC du semestre 1 en Filière Ingénièries Logcielle. Le but du projet est de permettre l'ajout de publication à un site web statique de façon dynamique. Le rendu final attendu est une interface facilitant la création de publication. Le projet est découpé en deux phases, dans la première une version on se concentre sur une API en proposant simplement une interface en ligne de commande. A l'issus de cette phase, nous échangeons le code au sein du groupe, afin de débuter la deuxième phase: à partir d'un code qui nous est inconnu, nous réalisons une interface graphique.
##### Technologie
Le projet est développé en Java, le site statique est directement hébergé sur Gitlab et utilise Gitlab pages, une fonctionnalité permettant de publier un site web statique directement depuis un répertoire gitlab. Le site web statique est produit grâce à l'outil jekyll.

##### Organisation et roadmap
Afin de suivre l'avancement du projet, il est attendu chaque semaine un fichier log permettatn de rendre compte de l'activité effectuée sur le projet. A ceci s'ajoute un tableau d'avancement permettant d'évaluer les fonctionnalités faites et celles encore à faire.

![Roadmap](https://github.com/Evrard-Nil/acdc/blob/master/roadmapACDC.png "roadmap")
##Choix d'implémentation
![UML](https://github.com/Evrard-Nil/acdc/blob/master/uml.png "UML")
## Logs
##### Log du 04/10 au 10/10:
- Lecture et appropriation du sujet
- Réunion avec les autres participants du projet
- Accord sur le travail demandé
- Reflexion sur le sujet
- Création d’un diagramme UML simple
- Réunion avec le professeur référent à venir le jeudi 11/10
- Documentation sur l’outil Jekyll et le langage markdown
##### Log du 10/10 au 17/10:
- Tests manuels d’ajout de pages jekyll
- 1ère implantation de saisie par commande des fichiers markdown et écriture de ces
derniers
##### Log du 17/10 au 24/10:
- Implantation de la compilation jekyll par java
- Implantation du lancement d’une demo jekyll par java
- Réorganisation du code (V2)
- Ebauche de fonction gitCommit()
- 
##### Log du 24/10 au 31/10:
- Fin de la fonction gitCommit-)
- Fonction gitPush()
- Organisation du code

##### Log du 31/10 au 07/11
- Refactoring du code

##### Log du 07/11 au 14/11
- pas avancé le projet

##### Log du 14/11 au 21/11
- Réorganisation du code 
- Tests sur windows: soucis d'arrêt du programme

