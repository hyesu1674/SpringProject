package yjc.wdb.bbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.bbs.bean.Board;
import yjc.wdb.bbs.bean.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	//BoardMapper.xml�� �����ϱ����ؼ� namespace�� ��������� ����
	private static final String namespace = "yjc.wdb.bbsMapper";				
	
	//root-context.xml ������ SqlSession Spring Bean�� ������ sqlSession
	// ���������� ��ü�� �������� �ʰ� �����ӟp�� ��������(������ ����) >> ������̼�����(�ʼ�)
	@Inject
	private SqlSession sqlSession;	
	
	@Override
	public void create(Board vo) throws Exception {
		sqlSession.insert(namespace + ".create", vo);
	}

	@Override
	public Board read(int bno) throws Exception {
		return sqlSession.selectOne(namespace + ".read", bno);
	}

	@Override
	public void update(Board vo) throws Exception {
		sqlSession.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete(namespace + ".delete", bno);
	}

	@Override
	public List<Board> listAll() throws Exception {
		return sqlSession.selectList(namespace + ".listAll");
	}

	@Override
	public List<Board> listPage(Criteria criteria) throws Exception {
		return sqlSession.selectList(namespace + ".listPage", criteria);
	}

	@Override
	public int getTotalCount() throws Exception {
		return sqlSession.selectOne(namespace + ".totalCount");
	}

	@Override
	public List<Board> listSearch(Criteria criteria) throws Exception {
		return sqlSession.selectList(namespace + ".listSearch", criteria);
	}

	@Override
	public int getSearchTotalCount(Criteria criteria) throws Exception {
		return sqlSession.selectOne(namespace + ".searchTotalCount", criteria);
	}
	
}
