package v1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws IOException {
		
		//Attributes of post
		String layout= "post";
		String title;
		String title2;
		String date;
		String categories="post";
		String texte;
		String author;
		String sep="---";
		String beforeTitle="## ";
		String authorDet="*";
		int i =10;
		
		//
		Scanner sc=new Scanner(System.in);
		Scanner scInt=new Scanner(System.in);
		
		System.out.println("Indiquer le titre de la publication");
		title = sc.nextLine();
		System.out.println("Indiquer la date de la publication (YYY-MM-DD):");
		date = sc.nextLine();
		System.out.println("Indiquer l'autheur de la publication");
		author = sc.nextLine();
		System.out.println("Quelle type de publication ? 0: Post 1: Job 2: nouvelle publication");
		while (i>2) i=scInt.nextInt();
		switch(i) {
		case 0: categories ="post";break;
		case 1: categories ="job";break;
		case 2: System.out.println("Not implemented yet"); break;
		}
		System.out.println("titre secondaire de la publication");
		title2 = sc.nextLine();
		System.out.println("texte de la publication");
		texte = sc.nextLine();
		
		
		sc.close();
		scInt.close();
		
		// Write file 
		String filename= "../_posttest/"+date+"-"+title+".markdown";
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		writer.append(sep);
		writer.append("\n");
		writer.append("layout:  "+ layout);
		writer.append("\n");
		writer.append("title:  "+ title);
		writer.append("\n");
		writer.append("date:  "+ date);
		writer.append("\n");
		writer.append("categories:  "+ categories);
		writer.append("\n");
		writer.append(sep);
		writer.append("\n\n");
		writer.append(beforeTitle+title2);
		writer.append("\n\n");
		writer.append(authorDet+author+authorDet);
		writer.append("\n\n");
		writer.append(texte);
		writer.close();
		
		//TODO: run - jekyll build : .md ---> hmtl
		
		//TODO: run - jekyll serve : launch a demo of the website
		
		//TODO: Accept demo or modify post
		
		//TODO: run - git pull / add* / commit / push 
	}

}
