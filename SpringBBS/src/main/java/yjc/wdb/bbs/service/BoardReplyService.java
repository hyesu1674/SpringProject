package yjc.wdb.bbs.service;

import java.util.List;

import yjc.wdb.bbs.bean.BoardReply;
import yjc.wdb.bbs.bean.Criteria;

public interface BoardReplyService {
	public List<BoardReply> list(int bno) throws Exception;
	public void create(BoardReply reply) throws Exception;
	public void update(BoardReply reply) throws Exception;
	public void delete(int rno) throws Exception;
	public List<BoardReply> listPage(int bno, Criteria criteria) throws Exception;
	public int replyCount(int bno) throws Exception;
}
