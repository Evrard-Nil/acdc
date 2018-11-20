package v3;

class CommandManager implements Runnable {
	private java.io.InputStream inputStream;
	private java.util.function.Consumer<String> consumer;

	public CommandManager(java.io.InputStream inputStream, java.util.function.Consumer<String> consumer) {
		this.inputStream = inputStream;
		this.consumer = consumer;
	}

	@Override
	public void run() {
		new java.io.BufferedReader(new java.io.InputStreamReader(inputStream)).lines()
		.forEach(consumer);
	}
	
}