package v3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

/**
 * Classe reunissant les differents outils utilisés par les autres classes
 * 
 * @author edaillet
 *
 */
public class Tools {
	
	//singleton cmdManager
	private static Commands cmdManager = Commands.commandManager;
	// Lieu du dossier _post
	private String path="d:/Profiles/edaillet/dev/IMT/acdc/blog/myblog/_posts";
	
	/**
	 * getter
	 * 
	 * @return Lieu du dossier _post
	 */
	public String getPath() {
		return path;
	}
	/**
	 * setter
	 * 
	 * @param path Lieu du dossier _post
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * Méthode permettant de generer un string au format markdown
	 * 
	 * @param title
	 * @param layout
	 * @param category
	 * @param date
	 * @param author
	 * @param content
	 * @return
	 */
	public String toMarkdown(Post post) {
		return "---\n"
				+"layout: "+post.getLayout()+ "\n"
				+"title: \""+post.getTitle()+"\"\n"
				+"categories: "+post.getCategory()+"\n"
				+"date: "+post.getDate()+"\n"
				+"---\n\n"
				+"*"+post.getAuthor()+"*\n\n"
				+post.getContent()
				;
	}
	/**
	 * Methode d'ajout d'un string à la suite d'un fichier
	 * 
	 * @param contentAdd
	 * @param date
	 * @param title
	 */
	public void writeFile(String contentAdd, String date, String title) {

		String filename= this.path+"/_posts/" + date+"-"+title.replace(' ','_')+".markdown";
		PrintWriter writer;
		try {
			writer = new PrintWriter(filename);
			writer.print(contentAdd);
			writer.close();		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Permet de lancer une demo du ste jusqu'à sa fermeture par l'utilisateur
	 * 
	 *  [ERROR] le programme ne s'arrête pas sur windows.
	 * @throws InterruptedException
	 */
	public void launchWebsite(Scanner sc) throws InterruptedException {
		ExecutorService exe = cmdManager.runCommand("bundle exec jekyll serve -o", this.path);
		Tools.askUserFor(sc, "Press enter to kill demo");
		exe.shutdownNow();
	}
	
	/**
	 * Méthode permettant de commit les changements sur git
	 * 
	 * @param commit
	 * @throws InterruptedException
	 */
	public void gitCommit(String commit) throws InterruptedException {
		cmdManager.runCommand("git status ; git add . ; git commit -m \""+commit+"\"; git status", this.path);
	}
	
	/**
	 * Méthode permettant de push sur le répertoire distant.
	 * 
	 * @throws InterruptedException
	 */
	public void gitPush() throws InterruptedException {
		Scanner tempScanner = new Scanner(System.in);
		String username= Tools.askUserFor(tempScanner,"Username");
		String pwd = Tools.askUserFor(tempScanner,"Password");
		cmdManager.runCommand("git config --global user.name \""+username+"\";git config --global user.password \""+pwd+"\";git push", this.path);
		tempScanner.close();
	}
	
	/**
	 * Méthode permettant de demander à l'utilisateur une information
	 * but de refactoring du code
	 * @param sc
	 * @param info
	 * @return
	 */
	static public String askUserFor(Scanner sc, String info) {
		System.out.println(info+" :");
		return sc.nextLine();
	}
	
	


}
