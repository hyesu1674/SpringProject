package yjc.wdb.bbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import yjc.wdb.bbs.bean.BoardReply;
import yjc.wdb.bbs.bean.Criteria;
import yjc.wdb.bbs.bean.Member;
import yjc.wdb.bbs.service.BoardReplyService;

@RestController
@RequestMapping("replies")
public class BBSReplyController {
	
	@Inject
	private BoardReplyService service;
	
	
	@RequestMapping(value="/{bno}/{page}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> llistPage(@PathVariable("bno") int bno, @PathVariable("page") int page){
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		try{
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			
			
			int replyCount = service.replyCount(bno);
			criteria.setTotalCount(replyCount);
			
			List<BoardReply> list = service.listPage(bno, criteria);
			
			// Map : Interface
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("criteria", criteria);
			map.put("list", list);
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);			
		}
	
	
		return entity;
	}
	
	
	@RequestMapping(value="/{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> removeReply(@PathVariable("rno") int rno){
		ResponseEntity<String> entity = null;
		
		try{
			
			service.delete(rno);
			entity =  new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	@RequestMapping(value="/{rno}", method={ RequestMethod.PUT, RequestMethod.PATCH })
	// put, patch ��� ó������.
	public ResponseEntity<String> updateReply(@PathVariable("rno") int rno, @RequestBody BoardReply reply){
		
		// PUT, PATCH, DELETE �־��~
		// Web Service �� �Ϲ����� ���� http ��û�� ���� html�� �������� �ش�.
		// http ��û�� ������ ������ html ������ �䱸�ϴ� ���� �ƴ϶� Ư�� url�� �ش��ϴ�
		// �����͸� ��� ���� ���� ���,
		// �׷��� ��û�� ó���ϴ� �����񽺸�
		// Restful Service 
		// Rest : Represtational State Transefer
		
		// �����͸� ���... POST
		// �����͸� ��ȸ ... GET
		// �����͸� ���� ... DELETE
		// �����͸� ���� ... PUT, PATCH
		
		ResponseEntity<String> entity = null;
		try{
			reply.setRno(rno);
			service.update(reply);
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	 
	}
	
	@RequestMapping(value="/all/{bno}", method=RequestMethod.GET)
	public ResponseEntity<List<BoardReply>> getReplyList(@PathVariable("bno") int bno){
		// @PathVariable url�� ���� �޾ƿ�. ��) http://localhost8080:/bbs/replies/all/ 345
		// 345 ���� �޾ƿ�
		ResponseEntity<List<BoardReply>> entity = null;
		
		try{
			List<BoardReply> list = service.list(bno);
			entity = new ResponseEntity<List<BoardReply>>(list, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}


	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody BoardReply reply){
		
		ResponseEntity<String> entity = null;
		
		try{
			service.create(reply);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	@RequestMapping("getErrorNot")
	public ResponseEntity<List<Member>> getErrorNot(){
		ResponseEntity<List<Member>> entity = null;
		List<Member> list = new ArrayList<Member>();
		list.add(new Member("hs", 22));
		list.add(new Member("hyesu",22));
		
		entity = new ResponseEntity<List<Member>>(list, HttpStatus.NOT_FOUND);
		
		return entity;
	}
	@RequestMapping("getErrorAuth")
	public ResponseEntity<Void> getErrorAuth(){
		ResponseEntity<Void> re = new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		
		return re;
	}

	@RequestMapping(value="getMap")
	public Map<Integer, Member> getMap(){
		Map<Integer, Member> map = new HashMap<Integer, Member>();
		map.put(1, new Member("hs",22));
		map.put(2, new Member("ge",22));
		map.put(3, new Member("ys",22));
		map.put(4, new Member("sh",22));
		map.put(5, new Member("gh",22));
		return map;
	}
	
	@RequestMapping(value="getMList")
	public List<Member> getMList(){
		ArrayList<Member> list = new ArrayList<Member>();
		
		list.add(new Member("hs", 22));
		list.add(new Member("ge", 23));
		list.add(new Member("ys", 22));
		list.add(new Member("sh", 25));
		list.add(new Member("gh", 22));
		list.add(new Member("sh", 22));
		return list;
	
	}
	
	

}
