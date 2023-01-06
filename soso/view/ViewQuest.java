package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Session;
import dao.Userdao;
import dto.Userdto;

public class ViewQuest {
   Userdao udao = new Userdao();
   Userdto loginuser = (Userdto)Session.getdata("loginuser");
   Scanner sc = new Scanner(System.in);
   public ViewQuest() {
      System.out.println("가입 : 새싹 단계를 달성하셨습니다.");
      //몇권읽었는지가져와서 출력
      System.out.println(udao.goal(loginuser.getGoal(),loginuser.getUserid()));
         //달성한 퀘스트를 전부 보여주기   , 제일높은단계만 보여주는 걸로   
         ArrayList<String> userQuest = udao.viewQuest(loginuser.getUserid());
         for (int i = 0; i < userQuest.size(); i++) {
            System.out.println("1.  : " + userQuest.get(i));
      }
         System.out.println("칭호를 설정하시겠습니까?");
         int choice = sc.nextInt();
         udao.setquest(userQuest.get(choice-1),loginuser);
   }
   
   
}