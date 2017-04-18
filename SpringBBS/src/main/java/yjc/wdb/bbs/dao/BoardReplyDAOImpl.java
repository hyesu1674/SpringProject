package yjc.wdb.bbs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.bbs.bean.BoardReply;
import yjc.wdb.bbs.bean.Criteria;

@Repository
public class BoardReplyDAOImpl implements BoardReplyDAO {

	private static final String NAMESPACE ="yjc.wdb.BoardReplyMapper";

	@Inject
	private SqlSession sqlSession;


	@Override
	public List<BoardReply> list(int bno) throws Exception {

		return sqlSession.selectList(NAMESPACE+".list", bno);
	}

	@Override
	public void create(BoardReply reply) throws Exception {
		sqlSession.selectList(NAMESPACE+".create", reply);

	}

	@Override
	public void update(BoardReply reply) throws Exception {
		sqlSession.selectList(NAMESPACE+".update", reply);

	}

	@Override
	public void delete(int rno) throws Exception {
		sqlSession.selectList(NAMESPACE+".delete", rno);
	}

	@Override
	public List<BoardReply> listPage(int bno, Criteria criteria) throws Exception {
		
		// 넘겨줄 데이터가 두가지
		// 한 객체로 넘겨줘야함.
		
		// map : key , value로 이루어진 데이터
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("bno", bno);
		paraMap.put("criteria", criteria);
		
		return sqlSession.selectList(NAMESPACE+".listPage", paraMap);
	}

	@Override
	public int replyCount(int bno) throws Exception {
		return sqlSession.selectOne(NAMESPACE+".replyCount", bno);
	}

}
