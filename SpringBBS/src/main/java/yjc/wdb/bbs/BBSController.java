package yjc.wdb.bbs;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import yjc.wdb.bbs.bean.Board;
import yjc.wdb.bbs.bean.Criteria;
import yjc.wdb.bbs.bean.Member;
import yjc.wdb.bbs.service.BoardService;

@Controller
public class BBSController {
	
	@Inject
	private BoardService service;
	
	// ��û�� get������� ����
	@RequestMapping(value="create", method=RequestMethod.GET)	
	public String boardFormGet(@ModelAttribute Criteria critera){
		// return �ϸ� view �������� forwarding �ϴ°�
		return "boardForm";		
	}
	
	// ��û�� post������� ����
	@RequestMapping(value="create", method=RequestMethod.POST)	
	public String boardFormPost(Board board, RedirectAttributes rttr) throws Exception{
		service.regist(board);
		rttr.addFlashAttribute("result", "SUCCESS!!");
		return "redirect:listPage";
	}
	// get, post ����� �������� ������ �Ѵٹ���
	
	@RequestMapping(value="result")
	public @ResponseBody String regresult(){
		return "result";
	}
	
	@RequestMapping(value="listAll", method=RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		List<Board> list = service.listAll();
		model.addAttribute("list", list);
		// view �������� ���������������� listAll.jsp �� �̵��Ѵ�.
	}
	
	@RequestMapping(value="read", method=RequestMethod.GET)
	// RequestPram ���ص� �ڵ����� ������ ���� �Ѵ�.
	// defaultValue�� ���ڿ����� ����ȴ�.
	// @ModelAttribute�� ���ָ� model��ü�� ��Ʈ����Ʈ�� �߰��ȴ�. 
	public void read(@RequestParam(value="bno", defaultValue="-1") int bno, @ModelAttribute("criteria") Criteria criteria, Model model) throws Exception{
		Board board = service.read(bno);
		model.addAttribute("board", board);
	}
	
	@RequestMapping(value="remove", method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, Criteria criteria, RedirectAttributes rttr) throws Exception {
		service.remove(bno);
		rttr.addAttribute("page", criteria.getPage());
		rttr.addAttribute("recordsPerPage", criteria.getRecordsPerPage());
		rttr.addFlashAttribute("msg", "SUCCESS");
		rttr.addAttribute("searchType", criteria.getSearchType());
		rttr.addAttribute("keyword", criteria.getKeyword());
		return "redirect:listPage";
	}
	
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String modify(int bno, Model model, Board board, Criteria criteria,RedirectAttributes rttr) throws Exception {
		service.modify(board);
		rttr.addAttribute("bno", board.getBno());
		rttr.addAttribute("page", criteria.getPage());
		rttr.addAttribute("recordsPerPage", criteria.getRecordsPerPage());
		rttr.addAttribute("searchType", criteria.getSearchType());
		rttr.addAttribute("keyword", criteria.getKeyword());
		return "redirect:read";
	}
	
	@RequestMapping(value="listPage", method=RequestMethod.GET)
	public String listPage(Criteria criteria, Model model) throws Exception{
		System.out.println(criteria);	// ����ڷκ��� �޴� ����
		//List<Board> list = service.listPage(criteria);
		List<Board> list = service.listSearch(criteria);	// where���� ������ listPage�� ����
		model.addAttribute("list", list);
		model.addAttribute("criteria", criteria);
		
		// startPage, endPage, previous, next
		//int totalCount = service.getTotalCount();
		int totalCount = service.getSearchTotalCount(criteria);
		criteria.setTotalCount(totalCount);
		
		return "listPage";
	}
	
	@RequestMapping(value="listSearch", method=RequestMethod.GET)
	public String listSearch(@ModelAttribute Criteria criteria, Model model) throws Exception{
		List<Board> list = service.listSearch(criteria);
		model.addAttribute("list", list);
		int totalCount = service.getSearchTotalCount(criteria);
		criteria.setTotalCount(totalCount);
		return "listPage";
	}
	
	@RequestMapping(value="member")
	public @ResponseBody Member getMember(){	// �� ������ �̸��� �ƴ� ������ ��ü�� �ѱ�� ���ؼ��� @ResponseBody ������̼��� ���
		Member m = new Member();
		return m;
	}
}
