package dbsql;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Insertreal in = new Insertreal();
//		
//		in.setI(new Book());
//		in.insert();
//		
//		in.setI(new Join());
//		in.insert();

		String table = "userinfo";
		ArrayList<String[]> sar = new ArrayList<String[]>();
		String[] setcal = {"userpw","userphone"};
		String[] setnewdata = {"1234","01011223344"};
		String[] wherecal = {"userid","usercode"};
		String[] wheredata = {"prh0305","1"};
		sar.add(setcal);
		sar.add(setnewdata);
		sar.add(wherecal);
		sar.add(wheredata);
		
		Update.updatedata(sar, table);
		
		// insert into userinfo(userid,userpw,usergoal) values ('prh0305','1234','4');
		Insert.insert(table, sar);
		
		//"select book.title 
		//from userbook join userinfo 
		//on userbook.user_code= userinfo.user_code 
		//join book on book.booknum=userbook.booknum 
		//where userbook.user_code= ?";		
		ArrayList<String[]> sar2 = new ArrayList<String[]>();
		String table2 = "userbook";
		String[] cal = {"book.title"};
		String[] join = {table2,"userinfo","book"};
		String[] on = {"userbook.user_code= userinfo.user_code","book.booknum=userbook.booknum"};
		String[] wherecal2 = {"userbook.user_code"};
		String[] wheredata2 = {"1"};
		sar2.add(cal);
		sar2.add(join);
		sar2.add(on);
		sar2.add(wherecal2);
		sar2.add(wheredata2);
		Select.selection(sar2, "join");
		//cal,join(from),on,wherecal,wheredata
}
}