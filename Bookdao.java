package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import dbsql.Select;
import dto.BookDto;
import dto.GoodsDTO;
import dto.Userdto;

public class Bookdao {
	PreparedStatement ps ;
	Connection conn;
	ResultSet rs;
	public Bookdao() {
		conn = DBCon.getConn();
	}
	
	//지용 내가 입력한 책 제목이 들어간 모든 책제목이 나오도록 하기 
	public ArrayList<BookDto> search(String title) {
		ArrayList<BookDto> bookar = new ArrayList<BookDto>();

		try {
			String sql = "select * from book where title like ('%" + title + "%')";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				BookDto book = new BookDto();
				book.setAuthor(rs.getString("author"));
				book.setBooknum(rs.getInt("booknum"));
				book.setPublisher(rs.getString("publisher"));
				book.setTitle(rs.getString("title"));
				book.setGenrecode(rs.getInt("genrecode"));
				bookar.add(book);
			}

			return bookar;

		} catch (SQLException e) {	}
		return null;
	}
//	public Object search(ArrayList<String[]> sqlar) {
//		ArrayList<BookDto> bookar = new ArrayList<BookDto>();
//		String type = "where";
//		try {
//			String sql = Select.selection(sqlar, type);			
//			ps = conn.prepareStatement(sql);
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				BookDto book = new BookDto();
//				book.setAuthor(rs.getString("author"));
//				book.setBooknum(rs.getInt("booknum"));
//				book.setPublisher(rs.getString("publisher"));
//				book.setTitle(rs.getString("title"));
//				book.setGenrecode(rs.getInt("genrecode"));
//				bookar.add(book);
//			}
//			
//			return bookar;
//			
//		} catch (SQLException e) {	}
//		return null;
//	}
		//지용 읽은 책 제목 가져오는 메소드
	public ArrayList<String> search(String userid, int usercode) {
		ArrayList<String> bookar = new ArrayList<String>();
				
		try {
			String sql = "select b.title from userbook ub "
					+ "join userinfo ui on ub.user_code= ui.user_code join book b on b.booknum=ub.booknum where ub.user_code= ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, usercode);

			rs = ps.executeQuery();
			while(rs.next()) {
				
					bookar.add(rs.getString("title"));	
				
			}
			return bookar;
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return null;
	}
	
	//건민
	//별점 순으로 책 5개 정도 출력
    public ArrayList<String> bestbook() {
        ArrayList<String> books = new ArrayList<String>();
        
        try {
         String sql = "select b.title, avg(r.bookstar) 'bs' from book b join review r on r.booknum = b.booknum group by b.title order by r.bookstar desc limit 5";

        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        
        while(rs.next()) {
           int s = rs.getInt("bs");
           String title = rs.getString("title");
           String st = "";
           for (int i = 0; i < s; i++) {
            st += "★";
              
         }
           String momo = title +"/"+ st;
           books.add(momo);
        }
        return books;
        } catch (SQLException e) {
           System.out.println("오타쟁이");
        }
        return null;   
     }
	//지용 신간 보기
	public BookDto[] newbook() {
		
		
		
		return null;
	}
	//지용 userbook에 id 등록하는거
	public boolean insert(String title, int usercode) {
		int booknum=0;
		try {
			String sql = "select booknum from book where title = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,title);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				booknum = rs.getInt("booknum");
				
			}
		} catch (SQLException e1) {	}
		
		try {
			String sql = "insert into userbook(booknum,user_code) values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, booknum);
			ps.setInt(2, usercode);
			
			return 1 == ps.executeUpdate();
			
		} catch (SQLException e) { }
		
		return false;
	}
	//래희
	//잔액확인, 굿즈금액만큼 유저 포인트에서 차감, 세션 및 db에서 유저 포인트 업데이트,굿즈갯수 -1
	public boolean getgoods(GoodsDTO i, Userdto user) {
	      String sql = "";
	      try {
	         if (user.getPoint() >= i.getProdprice()) {
	            int cnt = i.getProdamount();
	            if (cnt > 0) {
	               if (i.getGudokOnly()) {
	                  if (user.getGudok()) {
	                     user.setPoint(user.getPoint() - i.getProdprice());
	                     cnt--;
	                     i.setProdamount(cnt);
	                     sql = "update Goods set Goodsamount = ? where Goodsnum = ?";
	                     ps = conn.prepareStatement(sql);
	                     ps.setInt(1, cnt);
	                     ps.setInt(2, i.getProdnum());
	                     if(ps.executeUpdate() == 1) {
	                        sql = "update userinfo set user_point = ? where user_id = ?";
	                        ps = conn.prepareStatement(sql);
	                        ps.setInt(1, user.getPoint());
	                        ps.setString(2, user.getUserid());
	                        return ps.executeUpdate()==1;
	                     }
	                  
	                  } else {
	                     return false;
	                  }
	               } else {
	                  user.setPoint(user.getPoint() - i.getProdprice());
	                  cnt--;
	                  i.setProdamount(cnt);
	                  sql = "update Goods set Goodsamount = ? where Goodsnum = ?";
	                  ps = conn.prepareStatement(sql);
	                  ps.setInt(1, cnt);
	                  ps.setInt(2, i.getProdnum());
	                  if(ps.executeUpdate() == 1) {
	                     sql = "update userinfo set user_point = ? where user_id = ?";
	                     ps = conn.prepareStatement(sql);
	                     ps.setInt(1, user.getPoint());
	                     ps.setString(2, user.getUserid());
	                     return ps.executeUpdate()==1;
	                  }
	               }
	            }
	         }
	      } catch (SQLException e) {   }
	      return false;
	   }

	//재은
	public ArrayList<GoodsDTO> goods() {
		ArrayList<GoodsDTO> goodsar = new ArrayList<GoodsDTO>();

		try {
			String sql = "select * from goods";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
				while(rs.next()) {
					GoodsDTO gdto = new GoodsDTO();
					gdto.setProdnum(rs.getInt("Goodsnum"));
					gdto.setProdname( rs.getString("Goodsname"));
					gdto.setProdprice(rs.getInt("Goodsprice"));
					gdto.setProdamount(rs.getInt("Goodsamount"));	
					gdto.setGudokOnly(rs.getBoolean("gudokOnly"));
					goodsar.add(gdto);
				}
								
				return goodsar;
			
		} catch (SQLException e) { }
		
		return null;
		
	}
	
	//책 디비에 넣기
	//건민
//	   public boolean insertbook(String title, String author, String publish, int genre) {
//		      BookDto bookdto = new BookDto();
//		      
//		      try {
//		         String sql = "insert into book(title, author, publisher, genrecode)"
//		               + "values(?,?,?,?)";
//		         
//		         ps = conn.prepareStatement(sql);
//		         
//		         ps.setString(1, title);
//		         ps.setString(2, author);
//		         ps.setString(3, publish);
//		         ps.setInt(4, genre);
//		         
//		         return ps.executeUpdate() == 1;
//		      } catch (SQLException e) {
//		         e.printStackTrace();
//		      }
//		      
//		      return false;
//		   }
	
	
	//래희 리뷰 및 별점 넣기
		public boolean insertreview(int usercode, String title, String review, int bookstar) {
			//booknum받기
			if(bookstar>5) {bookstar=5;}
		      String star =""+bookstar;
		      int booknum = 0;
		      String sql ="";
		      try {
		         sql = "select * from book where title = ?";
		         ps = conn.prepareStatement(sql);
		         ps.setString(1, title);
		         rs = ps.executeQuery();
		         if(rs.next()) {
		         booknum = rs.getInt("booknum"); 
		         }
		      } catch (SQLException e1) {
		         // TODO Auto-generated catch block
		    	  System.out.println("2번");
		         e1.printStackTrace();
		      }
		            
		      try {
		         sql = "insert into review(booknum,user_code,bookstar,review) values (?,?,?,?)";
		         ps = conn.prepareStatement(sql);
		         ps.setInt(1, booknum);
		         ps.setInt(2, usercode);
		         ps.setString(3, star);
		         ps.setString(4, review);
		         
		         return 1==ps.executeUpdate();
		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }

		      return false;
		   }
		//userbook에 같은 책번호 등록하지 못하기 재은
		public boolean checkbook(String title, int usercode) {
		      int booknum = 0;
		      String sql ="";
		      try {
		         sql = "select * from book where title = ?";
		         ps = conn.prepareStatement(sql);
		         ps.setString(1, title);
		         rs = ps.executeQuery();
		         if(rs.next()) {
		         booknum = rs.getInt("booknum"); 
		         }
		      } catch (SQLException e1) {  }
		      try {
				sql = "select * from userbook where user_code = ? and booknum =?";
				  ps = conn.prepareStatement(sql);
				  ps.setInt(1, usercode);
				  ps.setInt(2, booknum);
				  rs = ps.executeQuery();
				  if(rs.next()) {
					  return true;
				  }
			} catch (SQLException e) {	}	      
		      		return false;
		}
		//재은
		public ArrayList<ArrayList<String>> getreview(ArrayList<String> bestbook) {
			//각 bestbook에 대한 리뷰 (최대5개)가 들어있는 리스트
			ArrayList<ArrayList<String>> reviews = new ArrayList<ArrayList<String>>();
			reviews.add(new ArrayList<String>());
			reviews.add(new ArrayList<String>());
			reviews.add(new ArrayList<String>());
			reviews.add(new ArrayList<String>());
			reviews.add(new ArrayList<String>());
			//booknums에 bestbook들의 booknum이 들어잇음
			ArrayList<Integer> booknums= new ArrayList<Integer>();
			//한개 책에 대한 리뷰를 담는 변수들
			String buserid ="";
			String breview = "";
			int bbookstar = 0;
			String star = "";
			 
		    String sql ="";
		      //bestbook갯수만큼 booknum가져오기
		      try {
		         for (int i = 0; i < bestbook.size(); i++) {
		        	 sql = "select * from book where title = ?";
		        	 ps = conn.prepareStatement(sql);
		        	 String [] bb = bestbook.get(i).split("/");
		        	 ps.setString(1, bb[0]);
		        	 rs = ps.executeQuery();
		        	 if (rs.next()) {
		        		 booknums.add(rs.getInt("booknum")); 
		        	 }
					
				}
		      } catch (SQLException e1) {  }
		      
		      //각 booknum마다 review 5개 가져오기
			try {
				 sql = "select user_id, review, bookstar from review r "
						+ "join userinfo ui on r.user_code = ui.user_code "
						+ "where booknum = ? order by bookstar limit 5";
				ps = conn.prepareStatement(sql);
				for (int i = 0; i < bestbook.size(); i++) {
					ps.setInt(1, (booknums.get(i)));
					rs = ps.executeQuery();
					while(rs.next()) {
						buserid=rs.getString("user_id");
						breview=rs.getString("review");
						bbookstar=rs.getInt("bookstar");
						for (int j = 0; j < bbookstar; j++) {
							star += "★";
						}
						for (int j = 0; j < 5-bbookstar; j++) {
							star += "☆";
						}
						reviews.get(i).add(buserid+"/"+breview+"/"+star);
						star="";
					}
				}
				
				return reviews;				
				
			} catch (SQLException e) {	}
			
			return null;
		}

}










