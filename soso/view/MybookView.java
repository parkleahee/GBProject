package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Bookdao;
import dao.Session;
import dao.Userdao;
import dto.Userdto;

public class MybookView {
	Bookdao bdao = new Bookdao();
	Userdao udao = new Userdao();
	Scanner sc = new Scanner(System.in);
	Userdto loginuser = (Userdto)Session.getdata("loginuser");
public MybookView() {
	while(true) {
	//메모 내용을 담고 있는 변수 memo
	ArrayList<String> memo = null;			
	ArrayList<String> booktitle = bdao.search(loginuser.getUserid(), loginuser.getUsercode());
	if(booktitle.size()>=1) {
	for (int i = 0; i < booktitle.size(); i++) {
		System.out.println(i + 1 + "번 " + booktitle.get(i));
	}
	System.out.println("자세히 보실 책번호를 입력하세요");
	System.out.println("0.나가기");
	int choice = sc.nextInt();
	if (choice == 0) {
		break;
	} else {
		System.out.println("1.리뷰 등록하기 2. 메모보기");
		int choice4 = sc.nextInt();
		if(choice4==1) {

			sc = new Scanner(System.in);
			System.out.println("리뷰를 입력하세요");
			String review = sc.nextLine();
			System.out.println("별점을 입력하세요 1~5");
			int bookstar = sc.nextInt();
			if(bdao.insertreview(loginuser.getUsercode(), booktitle.get( choice - 1), review, bookstar)) {
				System.out.println("리뷰 등록 성공!");
			}else {System.out.println("리뷰 등록 실패");}
			
		}else if(choice4 == 2) {
		memo = udao.memo(loginuser.getUsercode(), booktitle.get(choice - 1));
		// 유저 번호랑 책타이틀 비교해서 메모 가져오기
		if(memo.size()==0) {
			sc.nextLine();
			System.out.println("등록된 메모가 없습니다.");
			System.out.println("메모를 입력하시겠습니까?");
			System.out.println("0. 나가기 1. 메모등록하기");
			int choice3 = sc.nextInt();
			if(choice3==1) {
				sc.nextLine();
				System.out.println("등록하실 메모 내용을 입력해주세요.");
				String memo2 = sc.nextLine();
				if(udao.insertmemo(memo2, booktitle.get(choice - 1), loginuser.getUsercode())) {
					System.out.println("메모가 등록되었습니다!");
				}else {System.out.println("메모 등록 실패");}
			}else {break;}
			
		}else {
		System.out.println();
		System.out.println(loginuser.getUsername() + " 님의 <" + booktitle.get(choice - 1) + "> 메모");
		System.out.println(memo);
		System.out.println();
		System.out.println("메모를 추가로 입력하시겠습니까?");
		System.out.println("0. 나가기 1. 메모등록하기");
		int choice3 = sc.nextInt();
		if(choice3==1) {	
			sc.nextLine();
			System.out.println("등록하실 메모 내용을 입력해주세요.");
			String memo2 = sc.nextLine();
			if(udao.insertmemo(memo2, booktitle.get(choice - 1), loginuser.getUsercode())) {
				System.out.println("메모가 등록되었습니다!");
			}else {System.out.println("메모 등록 실패");}
		}else {break;}
		
		}
	
	
	//메모 보여줘야하나?
	System.out.println();
	memo = udao.memo(loginuser.getUsercode(), booktitle.get(choice - 1));
	System.out.println(loginuser.getUsername() + " 님의 <" + booktitle.get(choice - 1) + "> 메모");
	System.out.println(memo);
	System.out.println();
	System.out.println("메모를 수정 및 삭제하시겠습니까?");
	System.out.println("0.나가기 1.메모 수정  2.메모 삭제");
	int choice2 = sc.nextInt();
	sc.nextLine();
	//1. 메모수정
	if(choice2==1) {
		//메모가 1개면	
		if(memo.size()==1) {						
				System.out.println("수정할 내용을 입력하세요");
				String updatememo = sc.nextLine();
				if(udao.updatememo(memo.get(0),updatememo,booktitle.get(choice - 1),loginuser.getUsercode())) {
					System.out.println("수정 완료!");
				}else {
					System.out.println("수정 실패");
				}									
		//메모가 2개 이상이라면
		}else {
			System.out.println("몇 번째 메모를 수정하시겠습니까?");
			for (int i = 0; i < memo.size(); i++) {
				System.out.println(i+1+"번째 memo: "+memo.get(i));
			}
			int num = sc.nextInt();
			//여러메모 중 특정 메모를 수정
			sc.nextLine();
			System.out.println("수정할 내용을 입력하세요");
			String updatememo = sc.nextLine();
			if(udao.updatememo2(memo.get(num-1),updatememo,booktitle.get(choice - 1),loginuser.getUsercode())) {
				System.out.println("수정 완료!");
			}else {
				System.out.println("수정 실패");
			}
		}
	//2. 메모삭제
	}else if(choice2==2) {
		System.out.println();
		//메모가 한개라면 -> 그냥 삭제되었습니다 출력
		if(memo.size()==1) {
			if(udao.deletememo(memo.get(0),loginuser.getUsercode(),booktitle.get(choice - 1))) {
				System.out.println("메모가 삭제되었습니다!");
			}else {
				System.out.println("메모 삭제 실패");
			}						
		//메모가 여러개라면 메모 전체 삭제하시겠습니까? 메모 일부 삭제하시겠습니까?
		}else {
			System.out.println("1.메모 전체 삭제 2.메모 일부 삭제 ");
			int choice3 = sc.nextInt(); 
			if (choice3 == 1) {
				if(udao.deletememo(memo.get(0),loginuser.getUsercode(),booktitle.get(choice - 1))) {
					System.out.println("메모가 삭제되었습니다!");
				}else {
					System.out.println("메모 삭제 실패");
				}	
				
			}else if(choice3 == 2) {
				System.out.println("삭제할 메모 번호를 입력하세요");
				for (int i = 0; i < memo.size(); i++) {
					System.out.println(i+1+"번째 memo: "+memo.get(i));
				}
				int num = sc.nextInt();
				
				if(udao.deletememo2(memo.get(num-1),loginuser.getUsercode(),booktitle.get(choice - 1))) {
					System.out.println("메모가 삭제되었습니다!");
				}else {
					System.out.println("메모 삭제 실패");
				}	
			}else {
				break;
			}						
		}
			
	}else {
		break;
	}
	}else {System.out.println("등록된 책이 없습니다! 먼저 독서 기록을 입력해주세요"); break;}
	}
	
}
	}
}
}

