package org.gillianbc.messenger.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.gillianbc.messenger.database.DatabaseClass;
@XmlRootElement
public class Message {

	private long id;
	private String message;
	private Date created;
	private String author;
	private Map<Long, Comment> comments = new HashMap<>();
	private List<Link> links = new ArrayList<>();
	

	public Message() {
		//no arg constructor needed for JSON / XML conversion)
	}
	
	public Message(long id, String message, String author) {
		this(message,author);
		this.id = id;
	}
	
	public Message(String message, String author) {
		this.id = 0;
		this.message = message;
		this.created = Calendar.getInstance().getTime();
		this.author = author;
	}
	
	public void addLink(String url, String rel) {
		Link link = new Link();
		link.setLinkText(url.toString());
		link.setRel(rel);
		links.add(link);
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@XmlTransient  //ignore while converting XML/JSON
	public Map<Long, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	@Override
	public String toString() {
		return "\nMessage: " + message + 
				"\nAuthor: " + author +
				"\nId: " + id;
		
	}
}
