package dbsql;
import java.util.ArrayList;
import java.util.Iterator;

public class Update {
	public static boolean updatedata(ArrayList<String[]> sqlar, String table) {
		String sqlset = "";
		String sqlwhere ="";
		//알트 시프트 알
		
		for (int i = 0; i < sqlar.get(0).length; i++) {
			if(i==sqlar.get(0).length-1) {
			sqlset +=" "+ sqlar.get(0)[i]; //컬럼
			sqlset += " =\'"+sqlar.get(1)[i]+"\'"; //변경데이터
			}else {
				sqlset +=" "+ sqlar.get(0)[i]; //컬럼
				sqlset += " =\'"+sqlar.get(1)[i] + "\',"; //변경데이터
			}
		}
		
		for (int i = 0; i < sqlar.get(2).length; i++) {
			if(i==sqlar.get(2).length-1) {
			sqlwhere += " "+  sqlar.get(2)[i]; //웨어 컬럼
			sqlwhere += " =\'" + sqlar.get(3)[i]+"\'"; //웨어 데이터
			}else {
				sqlwhere += " "+  sqlar.get(2)[i]; //웨어 컬럼
				sqlwhere += " =\'" + sqlar.get(3)[i]+"\',"; //웨어 데이터
			}
			
		}
		String sql = "update " + table + " set" + sqlset + " where" + sqlwhere;
		System.out.println(sql);
		return false;
	}
}

// update userinfo set userpw = ? where userid = ? usercode = ?;