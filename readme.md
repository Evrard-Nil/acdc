# ACDC project - FILA1
***

This repo contains the latest code of ACDC project.


The purpose of the project is to allow the dynamic addition of post on a site hosted on github.

Step 1: Acquire the different entries to make an article

Step 2: Generate a .md file of the post

*Step 3: Using jeyll, turn the markdown files into html*

*Step 4: Using jekyll, launch a demo of the site with the new post*

*Step 5: Under user validation, push the changes on git*

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
