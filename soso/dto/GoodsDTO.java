package dto;

public class GoodsDTO {
	private int prodnum;
	private String prodname;
	private int prodprice;
	private int prodamount;
	private boolean gudokOnly;
	
	public GoodsDTO() {
		// TODO Auto-generated constructor stub
	}
	public GoodsDTO(int prodnum, String prodname, int prodprice, int prodamount,boolean gudokOnly) {
		super();
		this.prodnum = prodnum;
		this.prodname = prodname;
		this.prodprice = prodprice;
		this.prodamount = prodamount;
		this.gudokOnly = gudokOnly;
	}

	public int getProdnum() {
		return prodnum;
	}

	public void setProdnum(int prodnum) {
		this.prodnum = prodnum;
	}

	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public int getProdprice() {
		return prodprice;
	}

	public void setProdprice(int prodprice) {
		this.prodprice = prodprice;
	}

	public int getProdamount() {
		return prodamount;
	}

	public void setProdamount(int prodamount) {
		this.prodamount = prodamount;
	}

	public boolean getGudokOnly() {
		return gudokOnly;
	}

	public void setGudokOnly(boolean gudokOnly) {
		this.gudokOnly = gudokOnly;
	}
	
	 public String toString() {
		      String gudok = "";
		      if(gudokOnly) {
		         gudok = "구독자만 구매 가능합니다";
		      }else {
		         gudok = "구매 가능한 상품입니다";
		      }
		      return prodname + " 가격 : " + prodprice + " 재고 : " + prodamount + " "+gudok;
		   }
}
