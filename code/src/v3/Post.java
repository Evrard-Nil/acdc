package v3;
/**
 * 
 * @author Evrard-Nil Daillet
 * 
 * @version 3
 * 
 * This class is an implementation of a publication dynamically created and added to a static website.
 * 
 *
 */
public class Post {

	private String title;
	private String date;
	private String content;	
	private String author;
	private String category;
	private String layout="post";

	public Post() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	

}
