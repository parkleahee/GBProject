package view;

import java.util.Scanner;

public class Index {
	public static void main(String[] args) {
		System.out.println("                🍂소소 책방🍂");
		System.out.println("당신만의 작은 책방에 오신걸 환영합니다");
		System.out.println("---------------------------");
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("0. 소소 책방이란?");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 나가기");
		int choice	= sc.nextInt();
			if (choice == 3) {
				System.out.println("다음에 또 오세요");
				break;
			} else if (choice == 1) {
				new Joinview();

			} else if (choice == 2) {
				new Loginview();

			} else if(choice == 0) {
				new Sosoview();
			}
			
		}		
		
	}
}
