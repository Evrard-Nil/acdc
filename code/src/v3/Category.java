package v3;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe implémentant la gestion des catégories, cces méthodes auraient pu faire partie de la classe Tools mais 
 * cette factorisation apporte une meilleure lisibilité du projet
 * 
 * @author edaillet
 *
 */
public class Category {
	
	public static Category catSingleton = new Category();
	
	private Category() {
		
	}
	/**
	 * Ouvre et lit un fichier 
	 * 
	 * @return Les lignes de ficher sous forme de liste.
	 */
	public List<String> getAllCategories() {

		List<String> records = new ArrayList<String>();
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("categories"));
			String line;
			while ((line = reader.readLine()) != null)
			{
				records.add(line);
			}
			reader.close();
			return records;
		}
		catch (Exception e)
		{
			System.err.format("Exception occurred trying to read '%s'.", "categories");
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Méthode d'ajout de catégorie
	 * 
	 * @param newCat
	 */
	public void addCateg(String newCat) {
		try
		{
			BufferedWriter w = new BufferedWriter(new FileWriter("categories", true));
			w.append(newCat+"\n");
			w.close();
		}
		catch (Exception e)
		{
			System.err.format("Exception occurred trying to write '%s'.", "categories");
			e.printStackTrace();
		}
	}
	/**
	 * Méthode de retrait de catégorie
	 * 
	 * @param lineToRemove
	 * @return bool: réussite de l'opération
	 */
	public boolean removeCategory(String lineToRemove) {
		try {
			File inputFile = new File("categories");
			File tempFile = new File("tempCategories");

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			String currentLine;

			while((currentLine = reader.readLine()) != null) {
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.equals(lineToRemove)) continue;
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close(); 
			boolean successful = tempFile.renameTo(inputFile);
			return successful;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} 		
	}

}
