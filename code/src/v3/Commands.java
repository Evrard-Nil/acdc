package v3;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Cette classe propose une implementaion de l'interface Runnable, permettant ainsi de 
 * lancer des commandes bash, ou powershell le cas echeant, directement depuis java. 
 * @author edaillet
 * @
 */
class Commands {
	
	static Commands commandManager = new Commands();
	
	private Commands() {
		
	}
	/**
	 *  Cette approche semble préférable à celle d'utiliser Runtime.exec() 
	 *  car elle permet de modifier le dossier d'execution de la commande et 
	 *  rediriger la sortie de la commande.
	 * @param cmd
	 * @return 
	 * @throws InterruptedException
	 */
	public ExecutorService runCommand(String cmd, String path) throws InterruptedException {
		try {
			Process process = this.createProcess(cmd, path).start();
			StreamGobbler commandManager = new StreamGobbler(process.getInputStream(), System.out::println);
			ExecutorService exec = Executors.newFixedThreadPool(1);
			exec.submit(commandManager);
			return exec;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Méthode permettant de factoriser la creation de processBuilder.
	 * Permet de gerer l'OS de l'utilisateur
	 * 
	 * @param cmd commande a executer
	 * @param path ou executer la commande
	 * @return builder 
	 * @throws IOException
	 */
	public ProcessBuilder createProcess(String cmd, String path) throws IOException {
		final boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		ProcessBuilder builder = new ProcessBuilder();
		if (isWindows) {
			builder.command("cmd.exe", "/c", cmd);
		} else {
			builder.command("sh", "-c", cmd);
		}
		builder.directory(new File(path));
		return builder;
	}
	
	/**
	 * 
	 * 	Cette classe permet principalement de consommer la sortie produite par un process.
	 * 
	 * @author edaillet
	 *
	 */
	public static class StreamGobbler implements Runnable {
		
		private java.io.InputStream inputStream;
		private java.util.function.Consumer<String> consumer;
	
		/**
		 * Constructeur
		 * 
		 * @param inputStream 
		 * @param consumer
		 */
		public StreamGobbler(java.io.InputStream inputStream, java.util.function.Consumer<String> consumer) {
			this.inputStream = inputStream;
			this.consumer = consumer;
		}
	
		/**
		 * La sortie du process doit être "consommée" sinon notre process ne retournera pas correctement
		 */
		@Override
		public void run() {
			new java.io.BufferedReader(new java.io.InputStreamReader(inputStream)).lines()
			.forEach(consumer);
		}
	}

	
}