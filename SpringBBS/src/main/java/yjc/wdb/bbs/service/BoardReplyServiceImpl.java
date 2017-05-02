package yjc.wdb.bbs.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.bbs.bean.BoardReply;
import yjc.wdb.bbs.bean.Criteria;
import yjc.wdb.bbs.dao.BoardReplyDAO;

@Service
public class BoardReplyServiceImpl implements BoardReplyService {

	@Inject
	private BoardReplyDAO dao;
	
	@Override
	public List<BoardReply> list(int bno) throws Exception {
		return dao.list(bno);
	}

	@Override
	public void create(BoardReply reply) throws Exception {
		
		dao.create(reply);
	}

	@Override
	public void update(BoardReply reply) throws Exception {
		dao.update(reply);
	}

	@Override
	public void delete(int rno) throws Exception {
		dao.delete(rno);
	}

	@Override
	public List<BoardReply> listPage(int bno, Criteria criteria) throws Exception {
		// TODO Auto-generated method stub
		return dao.listPage(bno, criteria);
	}

	@Override
	public int replyCount(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.replyCount(bno);
	}

}
