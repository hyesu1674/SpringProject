package yjc.wdb.bbs.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.bbs.bean.Board;
import yjc.wdb.bbs.bean.Criteria;
import yjc.wdb.bbs.dao.BoardDAO;

@Service // 비지니스 로직
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;

	@Override
	public void regist(Board b) throws Exception {
		dao.create(b);
		
	}

	@Override
	public Board read(int bno) throws Exception {
		
		return dao.read(bno);
	}

	@Override
	public void modify(Board b) throws Exception {
		
		dao.update(b);
		
	}

	@Override
	public void remove(int bno) throws Exception {
		
		dao.delete(bno);
	}

	@Override
	public List<Board> listAll() throws Exception {
		
		return dao.listAll();
	}

	@Override
	public List<Board> listPage(Criteria criteria) throws Exception {
		
		return dao.listPage(criteria);
	}

	@Override
	public int getTotalCount() throws Exception {
		return dao.getTotalCount();
	}

	@Override
	public List<Board> listSearch(Criteria criteria) throws Exception {
		return dao.listSearch(criteria);
	}

	@Override
	public int getSearchTotalCount(Criteria criteria) throws Exception {
		return dao.getSearchTotalcount(criteria);
	}
	

}
