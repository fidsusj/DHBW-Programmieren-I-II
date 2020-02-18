package de.dhbwka.java.exam;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.*;


import de.dhbwka.java.exam.utils.FileUtils;
import de.dhbwka.java.exercise.uebungsklausuren.jBay.Auktion;

/**
 * 
 * @author D070497
 * 
 * Dies ist eine reine Testklasse zum Ausprobieren von Code
 *
 */

@SuppressWarnings("unused")
public class Test{
	
	private JFrame frame;
	
	public Test() {
		
		
		
	}

	public static void main(String[] args) {
        String regex = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)";
        String text = "https://cdn.hipwallpaper.com/i/13/36/UQsKYm.jpg";
        System.out.println(IsMatch(text,regex));
}

    private static boolean IsMatch(String s, String pattern) {
        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException e) {
        return false;
    }       

    }}