//$Source: /petSys/petSys/src/java/com/drategy/pets/domain/News.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/02/14 05:38:30 $

package com.drategy.pets.domain;

/**
 * 系统新闻
 * 
 * @author Jason Jiang
 * @author $Author: jackie.dong $
 * @$Revision: 1.3 $
 */

public class News {

	private String id;

	private String title;

	private String content;

	private String author;

	private String date;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
