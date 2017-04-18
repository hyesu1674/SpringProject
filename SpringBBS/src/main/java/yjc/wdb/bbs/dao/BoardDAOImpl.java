package yjc.wdb.bbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.bbs.bean.Board;
import yjc.wdb.bbs.bean.Criteria;

@Repository // DB관련 스프링 빈
public class BoardDAOImpl implements BoardDAO {
	
	private static final String namespace ="yjc.wdb.bbsMapper";
	
	@Inject // inject 어노테이션 : spring Bean의 객체를 자동으로 가져옴.
	private SqlSession sqlSession;
	// 내부적으로 root-context.xml에서 설정한 sqlSession을 불러줌
	// 그때 설정한 id값이 변수 이름


	@Override
	public void create(Board vo) throws Exception {
	
		sqlSession.insert(namespace+".create", vo);

	}

	@Override
	public Board read(int bno) throws Exception {
		
		return sqlSession.selectOne(namespace+".read", bno);
	}

	@Override
	public void update(Board vo) throws Exception {
		
		sqlSession.update(namespace+".update", vo);
	}

	@Override
	public void delete(int bno) throws Exception {
		
		sqlSession.delete(namespace+".delete", bno);
	}

	@Override
	public List<Board> listAll() throws Exception {
		
		return sqlSession.selectList(namespace+".listAll");
	}

	@Override
	public List<Board> listPage(Criteria criteria) throws Exception {
		return sqlSession.selectList(namespace+".listPage", criteria);
	}

	@Override
	public int getTotalCount() throws Exception {
		return sqlSession.selectOne(namespace+".totalCount");
	}

	@Override
	public List<Board> listSearch(Criteria criteria) throws Exception {
		return sqlSession.selectList(namespace+".listSearch", criteria);
	}

	@Override
	public int getSearchTotalcount(Criteria criteria) throws Exception {
		return sqlSession.selectOne(namespace+".searchTotalCount", criteria);
	}

}
