package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Bookdao;
import dao.Session;
import dto.GoodsDTO;
import dto.Userdto;


public class Goodsview {
	Bookdao bdao = new Bookdao();	
	Scanner sc = new Scanner(System.in);
	Userdto loginuser = (Userdto)Session.getdata("loginuser");
	public Goodsview() {
		System.out.println("굿즈샵에 오신것을 환영합니다~");
		System.out.println("현재 포인트는 "+ loginuser.getPoint()+"점 입니다!");
		
		//굿즈 정보 보여주기
		ArrayList<GoodsDTO> goods = bdao.goods();
		for (int i = 0; i < goods.size(); i++) {
			//toString 재정의 필요, num, name,price,amount?
			System.out.println( i+1 +"번째 굿즈 : "+goods.get(i).toString());
		}
		System.out.println("구매하실 굿즈 번호를 입력해주세요");
		int goodsnum = sc.nextInt();
		if(bdao.getgoods(goods.get(goodsnum-1),loginuser)) {
			System.out.println("구매 성공!");
		}else {
			if(goods.get(goodsnum-1).getGudokOnly()) {
				if(goods.get(goodsnum-1).getProdprice()>loginuser.getPoint()) {
					System.out.println("잔액이 부족합니다.");
				}else {
				System.out.println("구독자만 구매가능한 상품입니다.");
				}
			}else {
				System.out.println("잔액을 확인해주세요!");
		}	}
		
		
	}
}
