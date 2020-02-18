package de.dhbwka.java.exercise.threads.search;

public class PageLoader implements Runnable{

	private final String url;
	private StringBuffer content;
	private boolean finished;
	
	public PageLoader(String url) {
		this.url = url;
		this.content = new StringBuffer();
		this.finished = false;
	}

	public void run() {
		content.append(ReadURL.getStringContentFromUrl(url, "UTF-8"));
		finished = true;
	}
	
	public boolean pageLoaded() {
		return finished;
	}
	
	public String getPageContent() {
		if(finished)
			return content.toString();
		else
			return "";
	}
	
}