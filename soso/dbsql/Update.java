package dbsql;
import java.util.ArrayList;
import java.util.Iterator;

public class Update {
	public static boolean updatedata(ArrayList<String[]> sqlar, String table) {
		String sqlset = "";
		String sqlwhere ="";
		//��Ʈ ����Ʈ ��
		
		for (int i = 0; i < sqlar.get(0).length; i++) {
			if(i==sqlar.get(0).length-1) {
			sqlset +=" "+ sqlar.get(0)[i]; //�÷�
			sqlset += " =\'"+sqlar.get(1)[i]+"\'"; //���浥����
			}else {
				sqlset +=" "+ sqlar.get(0)[i]; //�÷�
				sqlset += " =\'"+sqlar.get(1)[i] + "\',"; //���浥����
			}
		}
		
		for (int i = 0; i < sqlar.get(2).length; i++) {
			if(i==sqlar.get(2).length-1) {
			sqlwhere += " "+  sqlar.get(2)[i]; //���� �÷�
			sqlwhere += " =\'" + sqlar.get(3)[i]+"\'"; //���� ������
			}else {
				sqlwhere += " "+  sqlar.get(2)[i]; //���� �÷�
				sqlwhere += " =\'" + sqlar.get(3)[i]+"\',"; //���� ������
			}
			
		}
		String sql = "update " + table + " set" + sqlset + " where" + sqlwhere;
		System.out.println(sql);
		return false;
	}
}

// update userinfo set userpw = ? where userid = ? usercode = ?;