package view;

import java.util.Scanner;

public class Index {
	public static void main(String[] args) {
		System.out.println("                πμμ μ±λ°©π");
		System.out.println("λΉμ λ§μ μμ μ±λ°©μ μ€μ κ±Έ νμν©λλ€");
		System.out.println("---------------------------");
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("0. μμ μ±λ°©μ΄λ?");
			System.out.println("1. νμκ°μ");
			System.out.println("2. λ‘κ·ΈμΈ");
			System.out.println("3. λκ°κΈ°");
		int choice	= sc.nextInt();
			if (choice == 3) {
				System.out.println("λ€μμ λ μ€μΈμ");
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
