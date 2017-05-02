package yjc.wdb.bbs.bean;

import java.sql.Date;

public class BoardReply {
	private int rno;
	private int bno;
	private String replyText;
	private String replyer;
	private Date regdate;
	private Date updateDate;
	
	public String toString(){
		return "rno:"+rno+", bno:"+bno+", replytext:"+replyText+", replyer:"+replyer+", regdate:"+regdate+", updatedate:"+updateDate;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getReplyText() {
		return replyText;
	}
	public void setReplyText(String replytext) {
		this.replyText = replytext;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
