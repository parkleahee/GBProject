package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Bookdao;
import dao.Session;
import dao.Userdao;
import dto.BookDto;
import dto.Userdto;

public class Newbook {
	Bookdao bdao = new Bookdao();
	Userdao udao = new Userdao();
	Scanner sc = new Scanner(System.in);
	Userdto loginuser = (Userdto)Session.getdata("loginuser");
	
	public Newbook() {
		while(true) {
		System.out.println();
		System.out.println("==πμ±λ©λ΄π==");
		System.out.println("1. μ κ°λ³΄κΈ°");
		System.out.println("2. λ² μ€νΈ λμλ³΄κΈ°");
		System.out.println("3. λκ°κΈ°");
		int choice = sc.nextInt();
		System.out.println();
		if(choice==1) {
			bdao.newbook();
		}
	      else if(choice ==2 ) {
	    	  //μ± νμ΄ν + μ±
	          ArrayList<String> bestbook = bdao.bestbook();
	          //κ° μ±λΉ λ¦¬λ·° μ΅λ 5κ° κ°μ Έμ€κΈ° id_apple@ λμμ§ μμμ~~~ βββββ
	          ArrayList<ArrayList<String>> reviews = bdao.getreview(bestbook);

	          System.out.println("== νμμ΄ λ½μ best μ± ==");
	          for (int i = 0; i < bestbook.size(); i++) {
	        	 String [] bestbooksplit = bestbook.get(i).split("/");
	             System.out.println(i+1+"λ²μ§Έ μ± :γ"+bestbooksplit[0]+"γ "+bestbooksplit[1]);
	             for (int j = 0; j <  reviews.get(i).size(); j++) {
	            	 String [] review = reviews.get(i).get(j).split("/");
	            	 System.out.println("id_"+review[0]+" "+review[1]+" "+review[2]);					
				}
	             System.out.println();
	          }
	          
	          System.out.println("μμΈν λ³΄μ€ μ± λ²νΈλ₯Ό μλ ₯ νμΈμ");
	          System.out.println("0.λκ°κΈ°");
	          choice = sc.nextInt();
	          if(choice>=6||choice==0) {
	             continue;
	          }else {
	        	  String [] bestbook2 = bestbook.get(choice-1).split("/");
	          ArrayList<BookDto> book =bdao.search(bestbook2[0]); // bookdto type
	          System.out.println(book.get(0));
	          continue;
	          }
	       } else {break;}
	             
		
		}
		
	}
}
