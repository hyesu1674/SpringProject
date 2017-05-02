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

	@Inject
	private SqlSession sqlSession;
	private static final String NAMESPACE = "yjc.wdb.ReplyMapper";
	
	@Override
	public List<BoardReply> list(int bno) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".list", bno);
	}

	@Override
	public void create(BoardReply reply) throws Exception {
		sqlSession.insert(NAMESPACE + ".create", reply);
	}

	@Override
	public void update(BoardReply reply) throws Exception {
		sqlSession.update(NAMESPACE + ".update", reply); 
	}

	@Override
	public void delete(int rno) throws Exception {
		sqlSession.delete(NAMESPACE + ".delete", rno);
	}

	@Override
	public List<BoardReply> listPage(int bno, Criteria criteria) throws Exception {
		// 보낼 데이터가 두개 이상이면 한 객체로 묶어서 넘겨야한다.
		// Map을 이용 (key,value)쌍으로 저장하는 객체
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bno", bno);
		paramMap.put("criteria", criteria);
		return sqlSession.selectList(NAMESPACE + ".listPage", paramMap);
	}

	@Override
	public int replyCount(int bno) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".replyCount", bno);
	}

}
