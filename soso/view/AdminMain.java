package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Bookdao;
import dao.Userdao;
import dto.BookDto;

public class AdminMain {
	Bookdao bdao = new Bookdao();
	Userdao udao = new Userdao();

	public AdminMain() {
		
		while(true) {
		System.out.println("0. 문의보기");
		System.out.println("1. 책 추가하기");
		System.out.println("2. 포인트 관리");
		System.out.println("3. 회원관리");
		System.out.println("4. 나가기");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();	
			if (choice == 4) {
				break;
			} else if (choice == 0) {
				ArrayList<String> contactar = udao.viewContact();
				System.out.println("========== 문의 =============");
				for (int i = 0; i < contactar.size(); i++) {
					System.out.println(contactar.get(i));
				}
				System.out.println("===========================");
			}

			else if (choice == 1) {

				sc.nextLine();
				System.out.println("추가할 책제목을 입력하세요");				
				String title = sc.nextLine();
				System.out.println("작가를 입력하세요");				
				String author = sc.nextLine();
				System.out.println("출판사를 입력하세요");				
				String publish = sc.nextLine();
				System.out.println("장르번호를 입력하세요");				
				int genre = sc.nextInt();
				ArrayList<String[]> sar = new ArrayList<String[]>();
				String[] setcal = {"title", "author", "publisher", "genrecode"};
//				title, author, publisher,genrecode,publication
				sar.add(setcal);
				String[] setnewdata = {title, author, publish, genre+""};
				sar.add(setnewdata);
				 if(udao.insertdata("book",sar)) {
//					 "insert into book(title, author, publisher, genrecode) values(?,?,?,?)";
					 System.out.println("입력성공!");
				 }else {
					 System.out.println("입력실패");
				 }
			//2. 포인트 관리
			}else if(choice == 2) {
				sc.nextLine();
				System.out.println("아이디를 입력하세요");
				String userid = sc.nextLine();
				System.out.println("충전할 포인트를 입력하세요");
				int userpoint = sc.nextInt();
				System.out.println("현재 회원의 포인트를 입력하세요");
				int nowpoint = sc.nextInt();
				if(udao.chargepoint(userpoint, userid, nowpoint)) {
					System.out.println("입력성공!");
				}
			//3. 회원관리
			}else {
				sc.nextLine();
				System.out.println("강퇴할 회원아이디를 입력하세요");
				String goodbyeuser = sc.nextLine();
				if(udao.goodbye(goodbyeuser)) {
					System.out.println("강퇴성공!");
				}
				
				
			}
			
			
		}
		
	}
	
}
