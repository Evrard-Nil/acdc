package V2;

import java.util.List;
import java.util.Scanner;



public class Main {



	public static void main(String[] args) {



		// TODO Auto-generated method stub
		Post t = new Post();

		Scanner sc=new Scanner(System.in);
		Scanner scInt=new Scanner(System.in);

		System.out.println("Indiquer le titre de la publication\n");
		t.setTitle(sc.nextLine());
		System.out.println("Indiquer la date de la publication (YYY-MM-DD):\n");
		t.setDate(sc.nextLine());
		System.out.println("Indiquer l'autheur de la publication\n");
		t.setAuthor(sc.nextLine());
		System.out.println("Quelle type de publication ?\n");
		List<String> s = t.getAllCat();
		System.out.println("0. - Créer nouvelle categorie\n");
		for(int i=0;i<t.getAllCat().size(); i++) {
			System.out.println(Integer.toString(i+1)+". - "+s.get(i));
		}
		int r=scInt.nextInt();
		if(r == 0) {
			System.out.println("Indiquer le nom souhaité de la nouvelle catégorie \n");
			String newCat =sc.nextLine();
			t.addCateg(newCat);
			t.setCategory(newCat);
		}
		else {
			t.setCategory(s.get(r-1));
		}

		System.out.println("texte de la publication");
		t.setContent(sc.nextLine());


		System.out.println(t.toMarkdown());
		t.writeFile(t.toMarkdown());


		try {
			t.demo();
			t.gitCommit("add of file: "+t.getDate()+"-"+t.getTitle().replace(' ','_')+".markdown to _testsite folder");
			t.gitPush();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		sc.close();
		scInt.close();

	}


}
