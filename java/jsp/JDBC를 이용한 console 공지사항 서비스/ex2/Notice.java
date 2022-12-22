package jdbc.ex2;

import java.util.Date;

public class Notice {
	private int id;
	private String title;
	private	String writeId;
	private String content;
	private Date regdate;
	private int hit;
	private String files;
	private int pub;
	
	public Notice() {}
	
	public Notice(int id, String title, String writeId, String content, Date regdate, int hit, String files, int pub) {
		this.id = id;
		this.title = title;
		this.writeId = writeId;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.files = files;
		this.pub = pub;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriteId() {
		return writeId;
	}
	public void setWriteId(String writeId) {
		this.writeId = writeId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public int getPub() {
		return pub;
	}
	public void setPub(int pub) {
		this.pub = pub;
	}
}