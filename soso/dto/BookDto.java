package dto;

public class BookDto {

	private int booknum;
	private String title;
	private String author;
	private String publisher;
	private String reveiw;
	private int genrecode;

	
	public BookDto() {
	}

	public BookDto(int booknum, String title, String author, String publisher, int genrecode) {
		this.booknum = booknum;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.genrecode = genrecode;
	}

	public int getBooknum() {
		return booknum;
	}

	public void setBooknum(int booknum) {
		this.booknum = booknum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getGenrecode() {
		return genrecode;
	}

	public void setGenrecode(int genrecode) {
		this.genrecode = genrecode;
	}

	public String getReveiw() {
		return reveiw;
	}

	public void setReveiw(String reveiw) {
		this.reveiw = reveiw;
	}
	
	//alt + 1+8+3 하면 "·"나옴 
	   @Override
	   public String toString() {
		  String[] genre = {"", "한국소설", "외국소설","시","인문","요리","만화","종교","예술","정치사회","역사문화"};
		  String genre2 = "";
		  for (int i = 1; i < genre.length; i++) {
			 if(i==genrecode) {
				 genre2 = genre[i];
			 }
		}
		  // [예술] 『서양미술사』 · 곰브리치 · 창간
		  
	      return "["+genre2 + "]『"+title +"』"+" · "+ author +" · "+ publisher;
	   }
	
}



