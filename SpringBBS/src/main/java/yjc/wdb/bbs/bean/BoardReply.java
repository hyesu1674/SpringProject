package yjc.wdb.bbs.bean;

import java.sql.Date;

public class BoardReply {
/*
 * create table tbl_reply_1501280(
rno int primary key auto_increment,
bno int references tbl_board(bno),
replyText varchar(1000) not null,
replyer varchar(50) not null,
regdate TIMESTAMP not null default now(),
updateDate timestamp not null default now()
);
 * */
	private int rno;
	private int bno;
	private String replyText;
	private String replyer;
	private Date regdate;
	private Date updateDate;
	
	public String toString(){
		return "(rno : "+rno+", bno : "+ bno + ", replyTest : "+ replyText +")";
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
	public void setReplyText(String replyText) {
		this.replyText = replyText;
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
