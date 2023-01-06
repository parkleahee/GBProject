package dbsql;

import java.util.ArrayList;

public class Insert {
	public static String insert(String table, ArrayList<String[]> sqlar) {
		String cal = "";
		String data = "";
		
		for (int i = 0; i < sqlar.get(0).length; i++) {
			if(i==sqlar.get(0).length-1) {
				cal += sqlar.get(0)[i] + ")";
				data += "\'"+ sqlar.get(1)[i] + "\')"; 
			}else {
				cal += sqlar.get(0)[i] + ","; 
				data += "\'"+ sqlar.get(1)[i] + "\',"; 
			}
		}
		String sql = "insert into "+table+"("+cal+" values ("+data;
		//System.out.println(sql);
		return sql;
	}
}

// insert into userinfo(userid,userpw,usergoal) values ('prh0305','1234','4');