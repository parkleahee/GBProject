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
		System.out.println("==📕책메뉴📙==");
		System.out.println("1. 신간보기");
		System.out.println("2. 베스트 도서보기");
		System.out.println("3. 나가기");
		int choice = sc.nextInt();
		System.out.println();
		if(choice==1) {
			bdao.newbook();
		}
	      else if(choice ==2 ) {
	    	  //책 타이틀 + 책
	          ArrayList<String> bestbook = bdao.bestbook();
	          //각 책당 리뷰 최대 5개 가져오기 id_apple@ 나쁘지 않았음~~~ ★★★★☆
	          ArrayList<ArrayList<String>> reviews = bdao.getreview(bestbook);

	          System.out.println("== 회원이 뽑은 best 책 ==");
	          for (int i = 0; i < bestbook.size(); i++) {
	        	 String [] bestbooksplit = bestbook.get(i).split("/");
	             System.out.println(i+1+"번째 책 :『"+bestbooksplit[0]+"』 "+bestbooksplit[1]);
	             for (int j = 0; j <  reviews.get(i).size(); j++) {
	            	 String [] review = reviews.get(i).get(j).split("/");
	            	 System.out.println("id_"+review[0]+" "+review[1]+" "+review[2]);					
				}
	             System.out.println();
	          }
	          
	          System.out.println("자세히 보실 책 번호를 입력 하세요");
	          System.out.println("0.나가기");
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
