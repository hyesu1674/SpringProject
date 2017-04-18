package yjc.wdb.bbs.dao;

import java.util.List;

import yjc.wdb.bbs.bean.Board;
import yjc.wdb.bbs.bean.Criteria;

public interface BoardDAO {
	// CRUD : Create, Retrieve, Update, Delete
	public void create(Board vo) throws Exception;
	public Board read(int bno) throws Exception;
	public void update(Board vo) throws Exception;
	public void delete(int bno) throws Exception;
	public List<Board> listAll() throws Exception;
	public List<Board> listPage(Criteria criteria) throws Exception;
	public int getTotalCount() throws Exception;
	public List<Board> listSearch(Criteria criteria) throws Exception;
	public int getSearchTotalcount(Criteria criteria) throws Exception;
	
}
