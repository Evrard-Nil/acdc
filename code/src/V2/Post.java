package V2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;


public class Post {
	
	private String title;
	private String date;
	private String content;
	private String author;
	private String category;
	private String layout="post";
	private String path=".."+File.separator+ "_testsite"+File.separator+ "_posts"+File.separator;
	
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
				+"layout:"+this.layout+ "\n"
				+"title:"+this.title+"\n"
				+"categories:"+this.category+"\n"
				+"date:"+this.date+"\n"
				+"---\n\n"
				+"*"+this.author+"*\n\n"
				+this.content
				;
	}
	
	public void writeFile(String contentAdd) {
		// Write file 
		String filename= date+"-"+this.title.replace(' ','_')+".markdown";
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(filename));
			writer.write(contentAdd);
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
	// TODO: VOID LAUNCH 		
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
    	    builder.command("cmd.exe", "/c", "bundle exec jekyll serve -o");
    	} else {
    	    builder.command("sh", "-c", "bundle exec jekyll serve -o");
    	}
    	builder.directory(new File(System.getProperty("user.home")+"/IMT/project/_testsite/"));
    	Process process = builder.start();
    	StreamGobbler streamGobbler = 
    	  new StreamGobbler(process.getInputStream(), System.out::println);
    	Executors.newSingleThreadExecutor().submit(streamGobbler);
    	int exitCode = process.waitFor();
    	assert exitCode == 0;
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
	}
	// TODO: PUSHGIT()
    public void git() throws InterruptedException {
    	final boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
    	try {
    		ProcessBuilder builder = new ProcessBuilder();
    	if (isWindows) {
    	    builder.command("cmd.exe", "/c", "bundle exec jekyll serve -o");
    	} else {
    	    builder.command("sh", "-c", "git pull", "git add .", "git commit", "git push", "evrard-nil", "Ansaerys123");
    	}
    	builder.directory(new File(System.getProperty("user.home")+"/IMT/project/_testsite/"));
    	Process process = builder.start();
    	StreamGobbler streamGobbler = 
    	  new StreamGobbler(process.getInputStream(), System.out::println);
    	Executors.newSingleThreadExecutor().submit(streamGobbler);
    	int exitCode = process.waitFor();
    	assert exitCode == 0;
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}	
	}
}
