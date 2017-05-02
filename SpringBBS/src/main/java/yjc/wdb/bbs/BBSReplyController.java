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
	
	// 페이징 처리
	@RequestMapping(value="/{bno}/{page}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("bno") int bno, @PathVariable("page") int page){
		ResponseEntity<Map<String, Object>> entity = null;

		try {
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			
			int replyCount = service.replyCount(bno);
			criteria.setTotalCount(replyCount);
			
			List<BoardReply> list = service.listPage(bno, criteria);
			Map<String, Object> map = new HashMap<>();
			map.put("list", list);
			map.put("criteria", criteria);
			
			entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 댓글 삭제
	@RequestMapping(value="{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("rno") Integer rno){
		ResponseEntity<String> entity = null;
		
		try {
			service.delete(rno);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 댓글 수정
	@RequestMapping(value="{rno}", method={RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("rno") Integer rno, @RequestBody BoardReply reply){
		ResponseEntity<String> entity = null;
		
		try {
			reply.setRno(rno);	// mapper에서 rno값을 받아야하므로 값을 넘겨준다
			service.update(reply);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// 댓글 조회
	@RequestMapping(value="/all/{bno}", method=RequestMethod.GET)
	public ResponseEntity<List<BoardReply>> list(@PathVariable("bno") Integer bno){
		ResponseEntity<List<BoardReply>> entity = null;
		
		try {
			entity = new ResponseEntity<>(service.list(bno), HttpStatus.OK);	// 제네릭은 JDK버전 1.7부터 생략가능
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 댓글 등록
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody BoardReply reply){	// json으로 받은 데이터를 자동으로 객체로 만듬
		
		ResponseEntity<String> entity = null;
		
		try {
			service.create(reply);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	
	//---------------------------------------- TEST --------------------------------------------//
	@RequestMapping("getErrorNot")
	public ResponseEntity<List<Member>> getErrorNot(){
		ResponseEntity<List<Member>> entity = null;
		
		List<Member> list = new ArrayList<Member>();
		list.add(new Member("A", 18));
		list.add(new Member("B", 24));
		
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
		// 1.7 이상의 컴파일러 사용시 HashMap<>() 으로 사용가능
		// 자바 버전 변경하는 방법
		// project 우클릭 후 properties > project Facets > JAVA 버전 변경
		Map<Integer, Member> map = new HashMap<>();
		map.put(1, new Member("A", 18));
		map.put(2, new Member("B", 24));
		map.put(3, new Member("C", 20));
		map.put(4, new Member("D", 19));
		map.put(5, new Member("E", 22));
		return map;
	}
	
	@RequestMapping(value="getMList")
	public List<Member> getMList(){
		ArrayList<Member> list = new ArrayList<Member>();
		list.add(new Member("A", 18));
		list.add(new Member("B", 24));
		list.add(new Member("C", 20));
		list.add(new Member("D", 19));
		list.add(new Member("E", 22));
		return list;
	}
}
