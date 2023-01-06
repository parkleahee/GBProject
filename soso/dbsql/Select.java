package dbsql;

import java.util.ArrayList;
import java.util.Iterator;

public class Select {
	static String cal = "";
	static String table = "";
	static String join = "";
	static String on = "";
	static String sqlwhere = "";

	public static void select(ArrayList<String[]> sqlar) {
		cal = "";
		table = "";
		join = "";
		on = "";
		sqlwhere = "";

		for (int i = 0; i < sqlar.get(0).length; i++) {
			cal += " " + sqlar.get(0)[i];
		}
		table = sqlar.get(1)[0];
		if (1 < sqlar.get(1).length) {
			for (int i = 1; i < sqlar.get(1).length; i++) {
				join += " join " + sqlar.get(1)[i];
			}
			for (int i = 0; i < sqlar.get(2).length; i++) {
				on += " on " + sqlar.get(2)[i];
			}
		}
		for (int i = 0; i < sqlar.get(3).length; i++) {
			if (i == sqlar.get(3).length - 1) {
				sqlwhere += " " + sqlar.get(3)[i]; // ���� �÷�
				sqlwhere += " =\'" + sqlar.get(4)[i] + "\'"; // ���� ������
			} else {
				sqlwhere += " " + sqlar.get(3)[i]; // ���� �÷�
				sqlwhere += " =\'" + sqlar.get(4)[i] + "\',"; // ���� ������
			}
		}

		return;
	}

	public static String selection(ArrayList<String[]> sqlar, String type) {
	//	Object obj = null;
		String sql = "";
		select(sqlar);
		String[] quStrings = { "select * from " + table, "select" + cal + " from " + table,
				"select * from " + table, "select" + cal + " from " + table + " where " + sqlwhere,
				"select" + cal + " from " + table,
				"select" + cal + " from " + table + " where " + sqlwhere,
				"select" + cal + " from " + table + join + on + " where " + sqlwhere };
		switch (type) {
		case "*":
			sql = quStrings[0];
			break;
		case "where":
			sql = quStrings[1];
			break;
		case "cal":
			sql = quStrings[2];
			break;
		case "calwhere":
			sql = quStrings[3];
			break;
		case "join":
			sql = quStrings[4];
			break;

		default:
			System.out.println("����");
		}
//		System.err.println(sql);

		return sql;
	}
}



//"select book.title 
//from userbook join userinfo 
//on userbook.user_code= userinfo.user_code 
//join book on book.booknum=userbook.booknum 
//where userbook.user_code= ?";