# ACDC project - FILA1
***

This repo contains the latest code of ACDC project.


The purpose of the project is to allow the dynamic addition of post on a site hosted on github.

Step 1: Acquire the different entries to make an article

Step 2: Generate a .md file of the post

*Step 3: Using jeyll, turn the markdown files into html*

*Step 4: Using jekyll, post a demo of the site with the new post*

*Step 5: Under user validation, push the changes on git*

## Run this project
***

In order to run this code:

> Clone this repo

> Run code folder in any java IDE

Post will be created in _posttest/date-title.markdown


## API v1:
***

- [ ] string getTitle()
- [ ] string getDate()
- [ ] string getCategory()
- [ ] string getContent()
- [ ] string getAuthor()
- [ ] string getPath()


- [ ] setTitle(string title)
- [ ] setDate(string date)
- [ ] setCategory(string category)
- [ ] setContent(string content)
- [ ] setAuthor(string auth)
- [ ] setPath(string path)


- [ ] writeFile(string path, string category, string date, string content, string auth, string title)
- [ ] addCategery(string newCategory)
- [ ] string[] getAllCateg()
- [ ] launchDemo()
- [ ] pushGit()

// Pour la mise en forme du markdown (optionnel ?)

- [ ] string urlMaker( string url, string text) // [link to Google!](http://google.com)
- [ ] string setBold( string txt)
- [ ] string setItalic(string txt)
- [ ] string setUnderline(string txt)
- [ ] string addPicture(string path)

written w/[Axel Coudray](github.com/acoudray1/)
