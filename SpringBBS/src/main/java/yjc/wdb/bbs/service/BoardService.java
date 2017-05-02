package yjc.wdb.bbs.service;

import java.util.List;

import yjc.wdb.bbs.bean.Board;
import yjc.wdb.bbs.bean.Criteria;

public interface BoardService {
	public void regist(Board b) throws Exception;
	public Board read(int bno) throws Exception;
	public void modify(Board b) throws Exception;
	public void remove(int bno) throws Exception;
	public List<Board> listAll() throws Exception;
	public List<Board> listPage(Criteria criteria) throws Exception;	
	public int getTotalCount() throws Exception;
	public List<Board> listSearch(Criteria criteria) throws Exception;
	public int getSearchTotalCount(Criteria criteria) throws Exception;
}
