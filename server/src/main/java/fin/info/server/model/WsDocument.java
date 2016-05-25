package fin.info.server.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.solr.client.solrj.beans.Field;
import org.hibernate.annotations.Type;

@SuppressWarnings("serial")
@Entity
@Table(name="wstable")
public class WsDocument implements Serializable {

	@Field("id")
	private String id;
	
	@Field("uri")
	private String uri;

	@Field("title")
	private String title;

	@Field("author")
	private String author;

	@Field("time")
	private Date time;

	@Field("description")
	private String description;
	
	private String content;
	
	private String images;
	
	private String view;
	
	@Field("firstimage")
	private String firstimage;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "uri", nullable = true)
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Column(name = "title", nullable = true)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "author", nullable = true)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "time1", nullable = true)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "description", columnDefinition="TEXT", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "content", columnDefinition="TEXT", nullable = true)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "images", columnDefinition="TEXT", nullable = true)
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	@Column(name = "view1", columnDefinition="TEXT", nullable = true)
	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getFirstimage() {
		return firstimage;
	}

	public void setFirstimage(String firstimage) {
		this.firstimage = firstimage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WsDocument other = (WsDocument) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WsDocument [id=" + id + ", uri=" + uri + ", title=" + title + ", author=" + author + ", time=" + time
				+ ", description=" + description + ", content=" + content + ", images=" + images + ", view=" + view
				+ ", firstimage=" + firstimage + "]";
	}
	
	
	


	
}
