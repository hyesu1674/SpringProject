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
	
	// 요청을 get방식으로 지정
	@RequestMapping(value="create", method=RequestMethod.GET)	
	public String boardFormGet(@ModelAttribute Criteria critera){
		// return 하면 view 페이지로 forwarding 하는것
		return "boardForm";		
	}
	
	// 요청을 post방식으로 지정
	@RequestMapping(value="create", method=RequestMethod.POST)	
	public String boardFormPost(Board board, RedirectAttributes rttr) throws Exception{
		service.regist(board);
		rttr.addFlashAttribute("result", "SUCCESS!!");
		return "redirect:listPage";
	}
	// get, post 방식을 지정하지 않으면 둘다받음
	
	@RequestMapping(value="result")
	public @ResponseBody String regresult(){
		return "result";
	}
	
	@RequestMapping(value="listAll", method=RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		List<Board> list = service.listAll();
		model.addAttribute("list", list);
		// view 페이지를 리턴해주지않으면 listAll.jsp 로 이동한다.
	}
	
	@RequestMapping(value="read", method=RequestMethod.GET)
	// RequestPram 안해도 자동으로 받지만 굳이 한다.
	// defaultValue는 문자열값만 저장된다.
	// @ModelAttribute를 해주면 model객체에 애트리뷰트가 추가된다. 
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
		System.out.println(criteria);	// 사용자로부터 받는 정보
		//List<Board> list = service.listPage(criteria);
		List<Board> list = service.listSearch(criteria);	// where절이 없으면 listPage와 같음
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
	public @ResponseBody Member getMember(){	// 뷰 페이지 이름이 아닌 데이터 자체를 넘기기 위해서는 @ResponseBody 어노테이션을 사용
		Member m = new Member();
		return m;
	}
}
