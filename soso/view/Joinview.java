package view;


import java.util.ArrayList;
import java.util.Scanner;

import dao.*;


public class Joinview {
	Scanner sc = new Scanner(System.in);
	Userdao udao = new Userdao();
	public Joinview() {
		System.out.println("회원가입 페이지 입니다");
		System.out.println("-------------------");
		while(true){
		System.out.println("아이디를 입력하세요(이메일 형식입니다)");
		String userid = sc.next();
		if(userid.indexOf("@")==-1) {
			System.out.println("이메일 형식으로 입력해주세요");
			continue;}
		if(udao.checkid(userid)) {
			
			System.out.println("비밀번호를 입력하세요");
			String userpw = sc.next();
			System.out.println("이름을 입력하세요");
			String username = sc.next();
			System.out.println("전화번호를 입력하세요");
			String userphone = sc.next();
			userphone.replaceAll("-","");
			System.out.println("목표권수 입력");
			int usergoal = sc.nextInt();
			ArrayList<String[]> sar = new ArrayList<String[]>();
			String[] setcal = {"user_id", "user_pw", "user_name", "user_phone","user_goal"};
			sar.add(setcal);
			String[] setnewdata = {userid, userpw, username, userphone,usergoal+""};
			sar.add(setnewdata);
			
			if(udao.insertdata("userinfo",sar)) {
				System.out.println("회원가입 성공");
				break;
			}else {
			System.out.println("회원가입 실패 \n전화번호가 중복되었습니다 다시 시도해 주세요!");
			}
			
		}else {
			System.out.println(
			"중복된 아이디입니다");
			continue;
		}
	
	}
	}
	
}
