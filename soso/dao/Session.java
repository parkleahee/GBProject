package dao;

import java.util.HashMap;

public class Session {
	private final static HashMap<String, Object> datas = new HashMap<String, Object>();
	private static String[] quest;
	public static void setdata(String loginuser, Object obj) {
		datas.put(loginuser, obj);
	}
	
	public static Object getdata(String loginuser) {
		return datas.get(loginuser);
	}

	public static String[] getQuest() {
		return quest;
	}

	public static void setQuest(String[] quest) {
		Session.quest = quest;
	}
	
}
