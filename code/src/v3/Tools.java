package v3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Tools {
	
	private String path="/home/daillet/IMT/project"+File.separator+ "_testsite"+File.separator+ "_posts"+File.separator;
	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String toMarkdown(String title, String layout, String category, String date, String author, String content) {
		return "---\n"
				+"layout: "+layout+ "\n"
				+"title: \""+title+"\"\n"
				+"categories: "+category+"\n"
				+"date: "+date+"\n"
				+"---\n\n"
				+"*"+author+"*\n\n"
				+content
				;
	}

	public void writeFile(String contentAdd, String date, String title) {
		// Write file 
		String filename= this.path + date+"-"+title.replace(' ','_')+".markdown";
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
	 * Open and read a file, and return the lines in the file as a list
	 * of Strings.
	 * (Demonstrates Java FileReader, BufferedReader, and Java5.)
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
	 		


	public void demo() throws InterruptedException {
		this.runCommandUntilDestroy("bundle exec jekyll serve -o");
	}
	
	public void gitCommit(String commit) throws InterruptedException {
		this.runCommand("git status ; git add . ; git commit -m \""+commit+"\"; git status");
	}
	
	
	public void gitPush() throws InterruptedException {
		Scanner tempScanner = new Scanner(System.in);
		String username= this.askFor(tempScanner,"Username");
		String pwd = this.askFor(tempScanner,"Password");
		this.runCommand("git config --global user.name \""+username+"\";git config --global user.password \""+pwd+"\";git push");
		tempScanner.close();
	}
	
	public String askFor(Scanner sc, String info) {
		System.out.println(info+" :");
		return sc.nextLine();
	}
	
	
	public void runCommand(String cmd) throws InterruptedException {
		try {
			Process process = this.createProcess(cmd).start();
			CommandManager streamGcommobbler = new CommandManager(process.getInputStream(), System.out::println);
			Executors.newSingleThreadExecutor().submit(streamGcommobbler);
			int exitCode = process.waitFor();
			assert exitCode == 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void runCommandUntilDestroy(String cmd) {
		try {
			Process process = this.createProcess(cmd).start();
			CommandManager commandManager = new CommandManager(process.getInputStream(), System.out::println);
			Executors.newSingleThreadExecutor().submit(commandManager);
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Press enter to stop demo");
			System.in.read();
			System.out.println("Demo killed");
			process.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ProcessBuilder createProcess(String cmd) throws IOException {
		final boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		ProcessBuilder builder = new ProcessBuilder();
		if (isWindows) {
			builder.command("cmd.exe", "/c", cmd);
		} else {
			builder.command("sh", "-c", cmd);
		}
		builder.directory(new File(System.getProperty(path)));
		return builder;
	}

	public boolean remove(String lineToRemove) {
		try {
			File inputFile = new File("categories");
			File tempFile = new File("tempCategories");

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			String currentLine;

			while((currentLine = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.equals(lineToRemove)) continue;
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close(); 
			boolean successful = tempFile.renameTo(inputFile);
			return successful;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 		
	}
}
