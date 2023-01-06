package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import dbsql.Insert;
import dto.BookDto;
import dto.Userdto;


public class Userdao{

	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public Userdao() {
		conn = DBCon.getConn();
	}
	
	//건민 수정안해도됨!, db 테이블 수정!
	public boolean checkid(String userid) {
//		중복아이디 검사
		String sql = "select * from userinfo where user_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			rs= ps.executeQuery();
//			System.out.println("check");
			return !rs.next();
		} catch (SQLException e) {
//			System.out.println("ex");
		}
		return false;
	}

	//재은_확인완료
//	public boolean join(String userid, String userpw, String username, int usergoal,String userphone) {
//		String [] ar = {userid, userpw, username, userphone};
//		try {
//		
//			String sql = "insert into userinfo(user_id, user_pw, user_name, user_phone,user_goal) values (?,?,?,?,?)";
//			ps = conn.prepareStatement(sql);
//			for (int i = 0; i < ar.length; i++) {
//				ps.setString(i+1, ar[i]);
//				
//			}
//			ps.setInt(5, usergoal);
//			return ps.executeUpdate()==1;
//			
//		} catch (SQLException e) {
//		}
//		
//		return false;
//	}
//	public boolean join(String userid, String userpw, String username, int usergoal,String userphone) {
//		String [] ar = {userid, userpw, username, userphone};
//		try {
//			ArrayList<String[]> sar = new ArrayList<String[]>();
//			String[] setcal = {"user_id", "user_pw", "user_name", "user_phone","user_goal"};
//			sar.add(setcal);
//			String[] setnewdata = {userid, userpw, username, userphone,usergoal+""};
//			sar.add(setnewdata);
//			String sql = Insert.insert("userinfo",sar);
//			//String sql = "insert into userinfo(user_id, user_pw, user_name, user_phone,user_goal) values (?,?,?,?,?)";
//			ps = conn.prepareStatement(sql);
////			for (int i = 0; i < ar.length; i++) {
////				ps.setString(i+1, ar[i]);
////				
////			}
////			ps.setInt(5, usergoal);
//			return ps.executeUpdate()==1;
//			
//		} catch (SQLException e) {
//		}
//		
//		return false;
//	}
	
//	public boolean join(ArrayList<String[]> sar) {
//		try {
//			String sql = Insert.insert("userinfo",sar);
//			ps = conn.prepareStatement(sql);
//			return ps.executeUpdate()==1;
//		} catch (SQLException e) {
//		}
//		return false;
//	}
	public boolean insertdata(String table,ArrayList<String[]> sar) {
		String sql = Insert.insert(table,sar);
		try {
			ps = conn.prepareStatement(sql);
			return ps.executeUpdate()==1;
		} catch (SQLException e) {
		}
		return false;
	}
	
	
	//동은
	//로그인 메소드
	public boolean login(String userid, String userpw) {
		try {
			String sql = "select * from userinfo where user_id = ? and user_pw = ?";
			ps =conn.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setString(2, userpw);
			rs=ps.executeQuery();
			if(rs.next()) {
				Userdto loginuser = new Userdto();
			loginuser.setUserid(rs.getString("user_id"));
			loginuser.setUsercode(rs.getInt("user_code"));
			loginuser.setUserpw(rs.getString("user_pw"));
			loginuser.setGoal(rs.getInt("user_goal"));
			loginuser.setUsername(rs.getString("user_name"));
			loginuser.setUserphone(rs.getString("user_phone"));
			loginuser.setJoincnt(rs.getInt("user_joincnt"));
			loginuser.setPoint(rs.getInt("user_point"));
			int check = rs.getInt("user_gudok");
			if(check==0) {
			loginuser.setGudok(false);	
			}else {loginuser.setGudok(true);}
			loginuser.setNick("메소드 구현");
			Session.setdata("loginuser", loginuser);
			return true;
			}
		} catch (SQLException e) {
		//	System.out.println("login ex");
		}
		return false;
	}

	//동은
	//문의하기 메소드
	public boolean contact(int usercode, String contact) {
//		insert into inquiry(inquirybody,user_code)values('등록해 주세요',1);
		try {
			String sql = "insert into contact(user_code,contactbody)values(?,?)";
			ps = conn.prepareStatement(sql);
//			ps.setString(1, userid);
			ps.setInt(1, usercode);
			ps.setString(2, contact);

			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
		}
		return false;
	}

	//동은
	public void logout() {
		
	}

	//재은_작동확인완료
	
	public ArrayList<String> memo(int usercode, String title) {
		ArrayList<String> memoar = new ArrayList<String>();
		
		try {

			String sql = "  select b.title,m.memo from memo m join book b on b.booknum = m.booknum "
					+ "where m.user_code=? and b.title=?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, usercode);
			ps.setString(2, title);
			
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
			
				memoar.add(rs.getString("memo"));
				
			}					
			return memoar;
			
		} catch (SQLException e) {
			System.out.println("id나 제목을 잘못 입력하셨습니다 다시 입력해주세요");
		}
		
		return null;
	}

	//동은 
	//목표권수 변경도 포함
	public boolean modifyinfo(String userid, int choice, String newData) {
		String[] cols = { "", "user_pw", "user_phone", "user_goal" };
		try {

			String sql = "update userinfo set " + cols[choice] + "=? where user_id=?";
			// update user set userpw = ? where userid = ?
			// update user set userphone = ? where userid = ?
			// update user set useraddr = ? where userid = ?

			ps = conn.prepareStatement(sql);

			ps.setString(1, newData);
			ps.setString(2, userid);

			if (ps.executeUpdate() == 1) {
				Userdto udto = new Userdto();
				udto = (Userdto)Session.getdata("loginuser");
				if(choice==1) {
				udto.setUserpw(newData);
				}else if(choice ==2) {
					udto.setUserphone(newData);
					
				}else if(choice ==3) {
					udto.setGoal(Integer.parseInt(newData));
				}
					return true;
				}
			
		} catch (SQLException e) {}
		return false;
	}


	//재은_작동확인완료
	public boolean insertmemo(String memo, String string, int usercode) {
		//
		int booknum = 0;
		try {
			String sql = "select booknum from book where title = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, string);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				booknum = rs.getInt("booknum");
			}
			
		} catch (SQLException e1) {	}
		
		
		try {
			
			String sql = "insert into memo(booknum,user_code,memo) values (?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, booknum);
			ps.setInt(2, usercode);
			ps.setString(3, memo);
			
			return 1==ps.executeUpdate();
			
		} catch (SQLException e) {
		}
		
		return false;
	}

	//건민
    public String goal(int goal, String userid) {
        // 몇권 읽으셨습니다. 목표까지 몇권 남았습니다.
        try {
           String sql = "select count(*) 'rb' from userbook ub join userinfo ui on ub.user_code = ui.user_code where ui.user_id = ?";

           ps = conn.prepareStatement(sql);

           ps.setString(1, userid);

           rs = ps.executeQuery();

           String result = "";
           int readbook = 0;
           if (rs.next()) {
              readbook = rs.getInt("rb");
           }
           
           if(goal>readbook) {
        	   result = userid + "님은 현재까지 " + readbook + "권 읽으셨습니다.\n목표까지 "
        		   + (goal - readbook) + "권 남았습니다.";
           }else if(goal == readbook) {
        	   result = userid + "님은 현재까지 " + readbook + "권 읽으셨습니다. \n축하합니다 목표를 달성하셨습니다~!";
           }else {
        	   result = userid + "님은 현재까지 " + readbook 
        			   + "권 읽으셨습니다. \n축하합니다!! 목표보다" + (readbook - goal)+"권 더 읽으셨습니다!";
        	   
           }
           return result;

        } catch (SQLException e) {
           System.out.println("오타쟁이");
        }

        return null;
     }

	//건민
	//유저가 달성한 퀘스트 전부 출력하기. 장르별, 출석별(처음본손님,단골손님,천생연분), 권수별.각 3개씩. 들어올때 칭호는 랜덤으로!
    public ArrayList<String> viewQuest(String userid) {
        // db에서 가져오는걸로
        ArrayList<String> result = new ArrayList<String>();
        Userdto loginuser = (Userdto) Session.getdata("loginuser");
        String[] level = { " 초보", " 중수", " 고수" };
        String[] gen = { "한국소설", "외국소설", "시", "인문", "요리", "만화", "종교", "예술", "정치사회", "역사문화"};

        try {
           String sql = "select user_id, genrecode, count(*) 'rb' ,(select count(*) from userbook ub join userinfo ui on ui.user_code = ub.user_code where ui.user_id = ?) 'totrb'from userbook ub join book b on ub.booknum = b.booknum join userinfo ui on ui.user_code = ub.user_code where user_id = ? group by genrecode";

           ps = conn.prepareStatement(sql);

           ps.setString(1, userid);
           ps.setString(2, userid);

           rs = ps.executeQuery();
           int getcnt=0;
           int totrb=0;
           while(rs.next()) {
           getcnt = rs.getInt("rb");
           int getgenre = rs.getInt("genrecode");
           totrb = rs.getInt("totrb");

           for (int i = 0; i < 10; i++) {
              if (getgenre == i + 1) {
                 if (getcnt >= 1) {
                    result.add(gen[i] + level[0]);
                    if (getcnt >= 3) {
                       result.add(gen[i] + level[1]);
                       if (getcnt >= 5) {
                          result.add(gen[i] + level[2]);
                       }
                    }
                 }
              }
           }
        }
           
           
           if (totrb >= 1) {
              result.add("오스트랄로 피테쿠스");
              if (totrb >= 3) {
                 result.add("호모 에렉투스");
                 if (totrb >= 5) {
                    result.add("호모 사피엔스");
                 }
              }

           }

           int userjoin = loginuser.getJoincnt();
           if (userjoin >= 1) {
              result.add("어색한 손님");
              if (userjoin >= 3) {
                 result.add("종종 오는 손님");
                 if (userjoin >= 5) {
                    result.add("단골손님");
                 }
              }
           }
           return result;

        } catch (Exception e) {
           e.printStackTrace();
        }

        return null;
     }
	//래희
	   public boolean chargepoint(int charge, String userid, int nowpoint) {
			Userdto loginuser = (Userdto)Session.getdata("loginuser");
			if(charge>0) {
				try {
					String sql = "update userinfo set user_point = ? where user_id = ?";
					int point = nowpoint + charge;
					ps=conn.prepareStatement(sql);
					ps.setInt(1, point);
					ps.setString(2, userid);
					if(ps.executeUpdate()==1) {
						loginuser.setPoint(point);
						return true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return false;
		}

	//래희
	public boolean gudok(String userid) {
		Userdto loginuser = (Userdto)Session.getdata("loginuser");
		if(!loginuser.getGudok()) {
		if(loginuser.getPoint()>999) {
			String sql ="update userinfo set user_gudok = '1', user_point = ? where user_id = ?";
			try {
				int point = loginuser.getPoint()-1000;
				ps=conn.prepareStatement(sql);
				ps.setInt(1, point);
				ps.setString(2, userid);
				if(ps.executeUpdate()==1) {
				loginuser.setGudok(true);
				loginuser.setPoint(point);
				return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					}
		}
		return false;
	}
	//동은
	public boolean goobyeGudok(String userid) {
		   Userdto loginuser = (Userdto)Session.getdata("loginuser");
		   String sql = "update userinfo set user_gudok = '0' where user_id = ?";
		         try {
		            ps=conn.prepareStatement(sql);
		            ps.setString(1, userid);
		            if(ps.executeUpdate()==1) {
		               loginuser.setGudok(false);
		               return true;
		            }
		         } catch (SQLException e) {
		            
		         }
		         return false;
		   }

	//래희
	//db에서 정보 다 삭제
	public boolean goodbye(String userid) {
		
		try {
			String sql = "delete from userinfo where user_id = ?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			if(ps.executeUpdate()>=1) {
				Session.setdata("loginuser", null);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	   public void joincntplus(String userid) {
		      // TODO Auto-generated method stub
		      try {
		         String sql = "update userinfo set user_joincnt =? where user_id =?";
		         ps = conn.prepareStatement(sql);
		         ps.setInt(1,(((Userdto)Session.getdata("loginuser")).getJoincnt()+1));
		         ps.setString(2, userid);
		         ps.executeUpdate();
		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }
		   }
	//동은
	//문의 테이블에서 문의들 다 갖고오기
	   public ArrayList<String> viewContact() {
	        ArrayList<String> vcar = new ArrayList<String>();

	        try {
	           String sql = "select  c.contactnum, c.user_code, u.user_id, c.contactbody from contact c join userinfo u on c.user_code= u.user_code";
	           ps = conn.prepareStatement(sql);
	           rs = ps.executeQuery();
	           while (rs.next()) {
	           int connum = rs.getInt("contactnum");
	            String usnum = rs.getString("user_id");
	            String moon =connum+". user_id : "+usnum+" -> "+rs.getString("contactbody");
	              vcar.add(moon);
	           }
	           return vcar;
	        } catch (SQLException e1) {   }

	        
	        return null;
	     }
	//재은
	//메모가 하나일 때 메모수정하기. 기존의 memo -> updatememo
	public boolean updatememo(String memo,String updatememo, String title, int usercode) {
		//booknum 가져오기
		int booknum = 0;
		try {
			String sql = "select booknum from book where title = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				booknum = rs.getInt("booknum");
			}
			
		} catch (SQLException e1) {	}

		try {
			String sql = "update memo set memo = ? where booknum = ? and user_code =?  and memo = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, updatememo);
			ps.setInt(2, booknum);
			ps.setInt(3, usercode);
			ps.setString(4, memo);
			
			return 1 == ps.executeUpdate();
		} catch (SQLException e) {	}
		
		
		return false;
	}
	//메모가 여러개일 때 특정 메모를 수정하기.
	//메모내용이랑 비교해서 삭제, 수정된 메모 추가 
	public boolean updatememo2(String memo, String updatememo, String title, int usercode) {
		//booknum가져오기
		int booknum = 0;
		try {
			String sql = "select booknum from book where title = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				booknum = rs.getInt("booknum");
			}
		} catch (SQLException e1) {	}
		
		//특정한 memonum가져오기
		int memonum=0;
		try {
			//정확하게 같은 메모는 등록못하게 해야하나..? 아니면 둘다 지워지게?
			String sql = "select memonum from memo where memo = ? and user_code = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, memo);
			ps.setInt(2, usercode);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				memonum = rs.getInt("memonum");
			}
			
		} catch (SQLException e1) {	}

		
		try {
			String sql = "update memo set memo = ? where booknum = ? and user_code =?  and memo = ? and memonum = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, updatememo);
			ps.setInt(2, booknum);
			ps.setInt(3, usercode);
			ps.setString(4, memo);
			ps.setInt(5, memonum);
			
			return 1 == ps.executeUpdate();
		} catch (SQLException e) { }
		
		return false;
	}
	//메모삭제 (메모가 1개일때)
	public boolean deletememo(String memo, int usercode, String title) {
		
		//booknum가져오기
		int booknum = 0;
		try {
			String sql = "select booknum from book where title = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				booknum = rs.getInt("booknum");
			}
			
		} catch (SQLException e1) {	}
		//메모삭제하기
		try {
			String sql = "delete from memo where booknum = ? and user_code =?  and memo = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, booknum);
			ps.setInt(2, usercode);
			ps.setString(3, memo);
			
			return 1 == ps.executeUpdate();
		} catch (SQLException e) {		}
		return false;
	}

	//메모삭제 (메모가 2개 이상일때)
	public boolean deletememo2(String memo, int usercode, String title) {
		//booknum가져오기
		int booknum = 0;
		try {
			String sql = "select booknum from book where title = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				booknum = rs.getInt("booknum");
			}
			
		} catch (SQLException e1) {	}
		//특정한 memonum가져오기
		int memonum=0;
		try {
			//정확하게 같은 메모는 등록못하게 해야하나..? 아니면 둘다 지워지게?
			String sql = "select memonum from memo where memo = ? and user_code = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, memo);
			ps.setInt(2, usercode);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				memonum = rs.getInt("memonum");
			}
			
		} catch (SQLException e1) {	}

		
		try {
			String sql = "delete from memo where booknum = ? and user_code =?  and memo = ? and memonum= ?";
			ps = conn.prepareStatement(sql); 
			ps.setInt(1, booknum);
			ps.setInt(2, usercode);
			ps.setString(3, memo);
			ps.setInt(4, memonum);
			
			return 1 == ps.executeUpdate();
		} catch (SQLException e) { }	
		return false;
	}

	public boolean setquest(String string, Userdto loginuser) {
		// TODO Auto-generated method stub
		String sql = "update userinfo set quest = ? where userid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, string);
			ps.setString(2, loginuser.getUserid());
			return ps.executeUpdate()==1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}



}














