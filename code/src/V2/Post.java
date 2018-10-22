package V2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Evrard-Nil Daillet
 * 
 * @version 3
 * 
 * This class is an implementation of a publication dynamically created and added to a static website.
 * 
 *
 */
public class Post {

	private String title;
	private String date;
	private String content;	
	private String author;
	private String category;
	private String layout="post";
	private String path="/home/daillet/IMT/project"+File.separator+ "_testsite"+File.separator+ "_posts"+File.separator;

	public Post() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String toMarkdown() {
		return "---\n"
				+"layout: "+this.layout+ "\n"
				+"title: \""+this.title+"\"\n"
				+"categories: "+this.category+"\n"
				+"date: "+this.date+"\n"
				+"---\n\n"
				+"*"+this.author+"*\n\n"
				+this.content
				;
	}

	public void writeFile(String contentAdd) {
		// Write file 
		String filename= this.path + this.date+"-"+this.title.replace(' ','_')+".markdown";
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
	public List<String> getAllCat() {
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
	 		
	private static class StreamGobbler implements Runnable {
		private java.io.InputStream inputStream;
		private java.util.function.Consumer<String> consumer;

		public StreamGobbler(java.io.InputStream inputStream, java.util.function.Consumer<String> consumer) {
			this.inputStream = inputStream;
			this.consumer = consumer;
		}

		@Override
		public void run() {
			new java.io.BufferedReader(new java.io.InputStreamReader(inputStream)).lines()
			.forEach(consumer);
		}
	}

	public void demo() throws InterruptedException {
		final boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		try {
			ProcessBuilder builder = new ProcessBuilder();
			if (isWindows) {
				builder.command("cmd.exe", "/c", "bundle exec jekyll serve");
			} else {
				builder.command("sh", "-c", "bundle exec jekyll serve -o");
			}
			builder.directory(new File(System.getProperty("user.home")+"/IMT/project/_testsite/"));
			Process process = builder.start();
			StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
			Executors.newSingleThreadExecutor().submit(streamGobbler);
			TimeUnit.SECONDS.sleep(3);
			System.out.println("Press enter to stop demo");
			System.in.read();
			System.out.println("Demo killed");
			process.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gitCommit(String commit) throws InterruptedException {
		final boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		try {
			ProcessBuilder builder = new ProcessBuilder();
			if (isWindows) {
				builder.command("cmd.exe", "/c", "git status");
			} else {
				builder.command("sh", "-c", "git status ; git add . ; git commit -m \""+commit+"\"; git status");
			}
			builder.directory(new File(System.getProperty("user.home")+"/IMT/project/_testsite/"));
			Process process = builder.start();
			StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
			Executors.newSingleThreadExecutor().submit(streamGobbler);
			int exitCode = process.waitFor();
			assert exitCode == 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void gitPush() throws InterruptedException {
		final boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		Scanner sc = new Scanner(System.in);
		System.out.println("Username:");
		String username = sc.nextLine();
		System.out.println("Pwd:");
		String pwd = sc.nextLine();
		try {
			ProcessBuilder builder = new ProcessBuilder();
			if (isWindows) {
				builder.command("cmd.exe", "/c", "git status");
			} else {
				builder.command("sh", "-c", "git config --global user.name \""+username+"\";git config --global user.password \""+pwd+"\";git push");
			}
			builder.directory(new File(System.getProperty("user.home")+"/IMT/project/_testsite/"));
			Process process = builder.start();
			StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
			Executors.newSingleThreadExecutor().submit(streamGobbler);
			int exitCode = process.waitFor();
			assert exitCode == 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
