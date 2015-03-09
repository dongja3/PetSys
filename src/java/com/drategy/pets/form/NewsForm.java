//$Source: /petSys/petSys/src/java/com/drategy/pets/form/NewsForm.java,v $
//LasterModified By:$Author: jackie.dong $
//$Date: 2006/01/11 09:06:29 $

package com.drategy.pets.form;

/**
 * 
 * @author Jackie Dong
 * @author $Author: jackie.dong $
 * @$Revision: 1.1 $
 */

public class NewsForm extends BaseForm {
	private static final long serialVersionUID = 1L;

	private String action = null;

	private String id = null;

	private String title = null;

	private String content = null;

	private String author = null;

	private String date = null;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
}
