package view;

import java.util.Scanner;

import dao.Session;
import dao.Userdao;
import dto.Userdto;

public class Loginview {
	
	public Loginview() {
		Userdao udao = new Userdao();
		Scanner sc = new Scanner(System.in);
		System.out.println("아이디를 입력하세요");
		String userid = sc.next();
		System.out.println("비밀번호를 입력하세요");
		String userpw = sc.next();
		
		if(udao.login(userid, userpw)) {
			if(userid.equals("admin@naver.com")) {
				new AdminMain();
			}else {
				udao.joincntplus(((Userdto)Session.getdata("loginuser")).getUserid());
//				String[] nick = udao.viewQuest(userid);
//				if(nick.length==0) {
//					((Userdto)Session.getdata("loginuser")).setNick("새싹");
//				}else {
//				((Userdto)Session.getdata("loginuser")).setNick(nick[nick.length-1]);}
				new Main();				
			}
			} else {
				System.out.println("로그인 실패");
			}
		}
		
		
		
		
	}
	

