package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.*;
import dto.BookDto;
import dto.Userdto;
//회원정보에 목표권수 넣어주기
public class Main {

	public Main() {
		Bookdao bdao = new Bookdao();
		Userdao udao = new Userdao();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			Userdto loginuser = (Userdto)Session.getdata("loginuser");
			if(loginuser==null) {
				System.out.println("로그인 후 이용해주세요");
				break;
			}
		System.out.println();	
		System.out.println("==✨메인메뉴✨==");	
		System.out.println("0. 로그아웃");
		System.out.println("1. 도서보기");
		System.out.println("2. 도서검색");
		System.out.println("3. 마이페이지");
		System.out.println("4. 굿즈 구매하기");
		System.out.println("5. 문의하기");
		int choice = sc.nextInt();
		if(choice == 0) {
			System.out.println(loginuser.getUsername()+"님 다음에 또 오세요");
			Session.setdata("loginuser", null);
			break;
		}
		else if(choice ==1) {
			new Newbook();
		
		}
		else if(choice ==2) {
			sc.nextLine();
			System.out.println("검색하실 책 제목을 입력하세요");
			String title =  sc.nextLine();
			ArrayList<BookDto>book = bdao.search(title);
			if(book.size()>0) {
				for (int i = 0; i < book.size(); i++) {
					System.out.println(i+1+"번째 책: "+book.get(i));
				}
			}else {
				System.out.println("검색결과가 없습니다! 검색어의 철자가 정확한지 다시 한 번 확인해주세요.");
				System.out.println("지속적으로 검색결과가 나오지 않을시 문의하기를 이용해주세요");
			}
			
		}
		else if(choice ==3) {
			new Mypage();
			
		}else if(choice ==4) {	
			new Goodsview();
			
		}else if(choice ==5) {
			sc.nextLine();
			System.out.println("문의하실 내용을 입력해 주세요");
			String contact = sc.nextLine();
			if(udao.contact(loginuser.getUsercode(),contact)) {
				System.out.println("문의가 접수 되었습니다");
			}else {
				System.out.println("문의하기 실패!");
			}
				
		}
		}
	
	}
	
	
}
