# Actualisation des Compétences en Développement et Conception
***
## Getting Sarted
Cette section décrira comment reproduire la solution localement.
***
## Introduction
##### Contexte
Ce projet a été mené dans le cadre de de l'UV ACDC du semestre 1 en Filière Ingénièries Logcielle. Le but du projet est de permettre l'ajout de publication à un site web statique de façon dynamique. Le rendu final attendu est une interface facilitant la création de publication. Le projet est découpé en deux phases, dans la première une version on se concentre sur une API en proposant simplement une interface en ligne de commande. A l'issus de cette phase, nous échangeons le code au sein du groupe, afin de débuter la deuxième phase: à partir d'un code qui nous est inconnu, nous réalisons une interface graphique.
##### Technologie
Le projet est développé en Java, le site statique est directement hébergé sur Gitlab et utilise Gitlab pages, une fonctionnalité permettant de publier un site web statique directement depuis un répertoire gitlab. Le site web statique est produit grâce à l'outil jekyll.

##### Organisation et roadmap
Afin de suivre l'avancement du projet, il est attendu chaque semaine un fichier log permettatn de rendre compte de l'activité effectuée sur le projet. A ceci s'ajoute un tableau d'avancement permettant d'évaluer les fonctionnalités faites et celles encore à faire.

![Roadmap](https://github.com/Evrard-Nil/acdc/master/roadmapACDC.png "roadmap")

##OLD

Step 1: Acquire the different entries to make an article

Step 2: Generate a .md file of the post

Step 3: Using jeyll, turn the markdown files into html

Step 4: Using jekyll, launch a demo of the site with the new post

Step 5: Under user validation, push the changes on git

## Run this project
***

In order to run this code:

- Clone this repo

- Run code folder in any java IDE

Post will be created in _posttest/date-title.markdown


## API v1:
***
### Getters
***

- [X] string getTitle()
- [X] string getDate()
- [X] string getCategory()
- [X] string getContent()
- [X] string getAuthor()
- [X] string getPath()
***
### Setters
***
- [X] setTitle(string title)
- [X] setDate(string date)
- [X] setCategory(string category)
- [X] setContent(string content)
- [X] setAuthor(string auth)
- [X] setPath(string path)
***
### Other
***
- [X] writeFile(string path, string category, string date, string content, string auth, string title)
- [X] addCategery(string newCategory)
- [X] string[] getAllCateg()
- [X] launchDemo()
- [X] commitGit()
- [X] pushGit()

written w/ [Axel Coudray](github.com/acoudray1/)
