	package v3;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		//Initializations
		Post post = new Post();
		Tools toolManager = new Tools();
		Category categoryManager = Category.catSingleton;
		Scanner sc = new Scanner(System.in);
		toolManager.setPath("D:/Profiles/edaillet/dev/IMT/acdc/blog/myblog/");
		
		/**
		 * Interface
		 * Dans cette partie du programme, on effectue l'acquisition des différentes données de la publication
		 **/
		post.setAuthor(Tools.askUserFor(sc,"Auteur de la publication"));
		post.setTitle(Tools.askUserFor(sc, "Titre de la publication"));
		post.setDate(Tools.askUserFor(sc, "Date de la publication (Format YYYY-MM-DD)"));
		// post.setLayout("post");
		askForCategory(post, toolManager, sc, categoryManager);
		post.setContent(Tools.askUserFor(sc, "Contenu de la publication"));
		toolManager.toMarkdown(post);
		try {
			toolManager.launchWebsite(sc);
			toolManager.gitCommit("add of file: "+post.getDate()+"-"+post.getTitle().replace(' ','_')+".markdown to _testsite folder");
			toolManager.gitPush();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//	close scanner to avoid any leak
		sc.close();
		
	}

	private static void askForCategory(Post post, Tools toolManager, Scanner sc, Category categoryManager) {
		do {
			List<String> categories = categoryManager.getAllCategories();
			System.out.println("0. - Creer nouvelle categorie");
			System.out.println("1. - Supprimer une categorie");
			for (int i = 0; i < categoryManager.getAllCategories().size(); i++) {
				System.out.println(Integer.toString(i + 2) + ". - " + categories.get(i));
			}
			int choixCategorie = Integer.parseInt(sc.nextLine());
			if (choixCategorie == 0) {
				System.out.println("Indiquez le nom souhaitee de la nouvelle categorie \n");
				String newCat = sc.nextLine();
				if (categories.contains(newCat)) {
					System.out.println("Cette catégorie existe déjà");
				} else {
					categoryManager.addCateg(newCat);
				}
				post.setCategory(newCat);
			} else if (choixCategorie == 1) {
				System.out.println("Indiquez le nom de la categorie à supprimer");
				String catToRemove = sc.nextLine();
				if (categories.contains(catToRemove)) {
					categoryManager.removeCategory(catToRemove);
				} else {
					System.out.println("Cette catégorie n'existe pas");
				}
			} else {
				post.setCategory(categories.get(choixCategorie - 2));
			} 
		} while (post.getCategory()==null);
	}

}
