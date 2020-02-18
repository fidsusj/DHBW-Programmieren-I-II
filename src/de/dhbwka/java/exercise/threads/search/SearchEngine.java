package de.dhbwka.java.exercise.threads.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchEngine {

	public static void main(String[] args) {
		
		List<PageLoader> list = new LinkedList<>();
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		
		try(BufferedReader reader = new BufferedReader(new FileReader(new File("./files/searchEngine/searchEngine.txt")))){
			while (reader.ready()) {
				list.add(new PageLoader(reader.readLine()));
			}
		} catch (Exception e) {
			System.err.println("Something went wrong");
		}
		
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					for (int i = 0; i < list.size(); i++) {
						if(list.get(i).pageLoaded()){
							System.out.println(list.remove(i).getPageContent() + System.lineSeparator());
						}
					}
					if(list.size() == 0)
						break;
				}
			}
		});
		
		thread.start();
		
		for (PageLoader pageLoader : list) {
			executor.execute(pageLoader);
		}
		executor.shutdown();
		
	}
	
}