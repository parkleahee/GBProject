package view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import dao.Bookdao;
import dao.Session;
import dao.Userdao;
import dto.BookDto;
import dto.Userdto;

public class Mypage {
	Bookdao bdao = new Bookdao();
	Userdao udao = new Userdao();
	Scanner sc = new Scanner(System.in);
	public Mypage() {
	while(true) {
		Userdto loginuser = (Userdto)Session.getdata("loginuser");
		if(loginuser==null) {
			System.out.println("로그인 후 이용해주세요");
			break;
		}
		System.out.println();
		System.out.println("==👩‍🏫마이페이지👨‍🏫==");
		System.out.println("0. 나가기");
		System.out.println("1. 내 정보보기");
		System.out.println("2. 내 독서 기록 보기");
		System.out.println("3. 독서 기록 입력하기");
		System.out.println("4. 퀘스트 확인하기");
		System.out.println("5. 포인트");		
		System.out.println("6. 구독");
		System.out.println("7. 회원탈퇴");		
		int choice = sc.nextInt();
		if(choice==0) {
			break;
			//1.내 정보보기
		}else if(choice == 1) {
			System.out.println("==="+loginuser.getUsername()+"("+loginuser.getUserid()+")님의 회원정보===");
			System.out.println("아이디 : "+loginuser.getUserid());
			System.out.println("비밀번호 : "+loginuser.getUserpw());
			System.out.println("핸드폰번호 : "+loginuser.getUserphone());
			System.out.println("목표권수 : "+loginuser.getGoal());
			if(loginuser.getGudok()) {
				System.out.println("구독여부 : 구독 O ");
			}else {
				System.out.println("구독여부 : 구독 X ");				
			}
			
			System.out.println("===========================");
			System.out.println("1. 비밀번호 수정\n2. 핸드폰 번호 수정\n3. 목표권수 수정\n4. 뒤로가기\n5. 회원탈퇴");
			choice = sc.nextInt();
			if(choice==4) {
				continue;
			}
			sc.nextLine();
			System.out.println("수정할 내용을 적어주세요");
			String newData = sc.nextLine();
			if(udao.modifyinfo(loginuser.getUserid(), choice, newData)) {
				System.out.println("변경 성공");
			}else {
				System.out.println("변경 실패 다시 시도해 주세요");
			}	
			
		}
		//2. 내 독서 기록 보기
			else if (choice == 2) {
				new MybookView();
		} // 3. 독서 기록 입력하기, 본인이 읽은 책 등록하기 
		  //1. 읽었던 책에서 메모 등록하기 (메모만 등록하기)2. 신규 책 및 메모 등록하기
			else if (choice == 3) {
				BookDto bdto = new BookDto();
				sc.nextLine();
				System.out.println("책 제목을 입력하세요");
				String title = sc.nextLine();
				ArrayList<BookDto> book = bdao.search(title);
				
				if (book.size() != 0) {
					for (int i = 0; i < book.size(); i++) {
						System.out.println((i + 1) + "번 째: " + book.get(i));
					}

					System.out.println("등록하고 싶은 책번호를 입력하세요");
					int booknum = sc.nextInt();
					if(booknum>=book.size()) {
						continue;
					}
					if(bdao.checkbook(book.get(booknum - 1).getTitle(), loginuser.getUsercode())) {
						System.out.println("중복된 책은 등록할 수 없습니다");
					}else if (bdao.insert(book.get(booknum - 1).getTitle(), loginuser.getUsercode())) {
						System.out.println("등록성공!");
						
						System.out.println("리뷰 혹은 메모를 등록하시겠습니까?");
						System.out.println("1.리뷰등록 2.메모등록 3.나가기");
						int memonum = sc.nextInt();
						if (memonum == 1) {
							 sc = new Scanner(System.in);
							System.out.println("리뷰를 입력하세요");
//							String review = sc.nextLine();
							String h = sc.nextLine();
							System.out.println("별점을 입력하세요 1~5");
							int bookstar = sc.nextInt();
							if(bdao.insertreview(loginuser.getUsercode(), book.get(booknum - 1).getTitle(), h, bookstar)) {
								System.out.println("리뷰 등록 성공!");
							}else {System.out.println("리뷰 등록 실패");}
						} else if (memonum == 2) {
							sc.nextLine();
							System.out.println("등록하실 메모내용을 입력해주세요");
							String memo = sc.nextLine();
							if (udao.insertmemo(memo, book.get(booknum - 1).getTitle(), loginuser.getUsercode())) {
								System.out.println("등록성공!");
							}
						} else if (memonum == 3) {
							System.out.println("생각나면 다시 등록해주세요~");
							continue;
						} else {
							System.out.println("번호를 잘못 입력하셨습니다.");
						}
					} else {
						System.out.println("등록실패!");
					}
				
					} else {
					System.out.println("등록된 책이 없습니다. 문의하기를 이용해주세요!");
					}
				}				
				//4. 퀘스트 확인하기 , (달성칭호, 목표칭호, 목표권수등 보여주기)
				else if(choice==4) {
					new ViewQuest();
				}
				//5. 포인트 
				else if(choice==5) {
					System.out.println("현재 포인트는 "+ loginuser.getPoint()+"점 입니다.");
					System.out.println("1.충전 하기\n2.나가기");
					int choice2= sc.nextInt();
					if(choice2==1) {
						System.out.println("충전하실 포인트를 입력해주세요: ");
						int charge = sc.nextInt();	
						if(udao.chargepoint(charge,loginuser.getUserid(),loginuser.getPoint())) {
							System.out.println("충전 성공!");
						}else {
							System.out.println("충전 실패 ㅠㅠ 문의하기를 이용해주세요");
						}
						
					}else if (choice2==2) {
						continue;
					}
					
			}
			// 6. 구독
			else if (choice == 6) {
				if (loginuser.getGudok()) {
					System.out.println("이미 구독 중입니다 ~ 구독 취소 할까요 ? YES or NO");
					sc = new Scanner(System.in);
					String answer = sc.nextLine();
					if (answer.equals("YES")) {
						if (udao.goobyeGudok(loginuser.getUserid())) {
							System.out.println("구독 취소 성공 !");
						}
					} else if (answer.equals("NO")) {
						continue;
					}
				} else {
					System.out.println("정말 구독하시겠습니까? (구독비 1000포인트) YES or NO");
					sc = new Scanner(System.in);
					String answer = sc.nextLine();
					if (answer.equals("YES")) {
						if (udao.gudok(loginuser.getUserid())) {
							System.out.println("구독 성공!");
						} else {
							System.out.println("구독 실패! 잔액을 확인해보세요~");
						}
					} else if (answer.equals("NO")) {
						continue;
					} else {
						System.out.println("잘못입력하셨습니다..");
					}
				}

			}
			// 7.회원탈퇴
				else if(choice ==7) {
					System.out.println("정말 탈퇴하시겠습니까? 탈퇴를 정자로 입력하세요.");
					String xkfxhl= sc.next();
					if(xkfxhl.equals("탈퇴")) {
						if(udao.goodbye(loginuser.getUserid())) {
							System.out.println("회원탈퇴 성공!");
							break;
						}
					}else {
						System.out.println("잘생각하셨습니다~");
							continue;
					}
				}
		
			}

		
	}	
	}

