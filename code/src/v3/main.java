	package v3;

import java.util.List;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		/*
		 * Méthode principale, ce fichier contient toute la logique propre à l'interface par ligne de commande.
		 */
		
		//Initializations
		Post post = new Post();
		Tools toolManager = new Tools();
		Scanner sc = new Scanner(System.in);
		
		/**
		 * Interface
		 * Dans cette partie du programme, on effectue l'acquisition des différentes données de la publication
		 **/
		post.setAuthor(toolManager.askFor(sc,"Auteur de la publication"));
		post.setTitle(toolManager.askFor(sc, "Titre de la publication"));
		post.setDate(toolManager.askFor(sc, "Date de la publication (Format YYYY-MM-DD)"));
		// post.setLayout("post");
		askForCategory(post, toolManager, sc);
		post.setContent(toolManager.askFor(sc, "Contenu de la publication"));
				
		//close scanner to avoid any leak
		sc.close();
	}

	private static void askForCategory(Post post, Tools toolManager, Scanner sc) {
		do {
			List<String> categories = toolManager.getAllCategories();
			System.out.println("0. - Creer nouvelle categorie");
			System.out.println("1. - Supprimer une categorie");
			for (int i = 0; i < toolManager.getAllCategories().size(); i++) {
				System.out.println(Integer.toString(i + 2) + ". - " + categories.get(i));
			}
			int choixCategorie = Integer.parseInt(sc.nextLine());
			if (choixCategorie == 0) {
				System.out.println("Indiquez le nom souhaitee de la nouvelle categorie \n");
				String newCat = sc.nextLine();
				if (categories.contains(newCat)) {
					System.out.println("Cette catégorie existe déjà");
				} else {
					toolManager.addCateg(newCat);
				}
				post.setCategory(newCat);
			} else if (choixCategorie == 1) {
				System.out.println("Indiquez le nom de la categorie à supprimer");
				String catToRemove = sc.nextLine();
				if (categories.contains(catToRemove)) {
					toolManager.remove(catToRemove);
				} else {
					System.out.println("Cette catégorie n'existe pas");
				}
			} else {
				post.setCategory(categories.get(choixCategorie - 2));
			} 
		} while (post.getCategory()==null);
	}

}
