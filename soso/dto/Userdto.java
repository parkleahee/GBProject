package dto;

public class Userdto {

	private String userid;
    private int usercode;
    private String userpw;
    private String userphone;
    private String username;
    private String nick;
    private int joincnt;
    private int goal;
    private int point;
    private boolean gudok;
    
    
    public Userdto() {}    
	



	public Userdto(String userid, int usercode, String userpw, String userphone, String username, String nick,
			int joincnt, int goal, int point, boolean gudok) {
		super();
		this.userid = userid;
		this.usercode = usercode;
		this.userpw = userpw;
		this.userphone = userphone;
		this.username = username;
		this.nick = nick;
		this.joincnt = joincnt;
		this.goal = goal;
		this.point = point;
		this.gudok = gudok;
	}




	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getUsercode() {
		return usercode;
	}
	public void setUsercode(int usercode) {
		this.usercode = usercode;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}




	public int getJoincnt() {
		return joincnt;
	}




	public void setJoincnt(int joincnt) {
		this.joincnt = joincnt;
	}




	public int getGoal() {
		return goal;
	}



	public void setGoal(int goal) {
		this.goal = goal;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}


	public boolean getGudok() {
		return gudok;
	}


	public void setGudok(boolean gudok) {
		this.gudok = gudok;
	}



}
