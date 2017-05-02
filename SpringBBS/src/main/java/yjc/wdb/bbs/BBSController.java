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

@Controller // 컨트롤러도 스프핑 빈
public class BBSController {

	@Inject
	private BoardService service; // Spring Bean 사용해야하기 때문에 inject 어노테이션을 설정

	/*	@RequestMapping(value="/create") // create라는 요청이 get 방식으로 오든 post 방식으로 오든 처리
	public String create(Board board) throws Exception{
		// 처리하고.. => dao.insert(board);

		service.regist(board);

		return "result";
	}*/

	//create - get 방식 - boardForm 나옴
	//submit 클릭 - /create - post 방식 - result 페이지 포워딩
	//(포워딩 해주면 안됨- 새로고침할 경우 같은 데이터가 계속 들어감(/create post방식) 
	//redirect 해야됨(result 페이지가 가장 최근이기 때문에 똑같은 데이터가  안들어감)
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String getBoardForm(@ModelAttribute Criteria criteria) {
		return "boardForm";
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(Board board, RedirectAttributes rttr) throws Exception {

		service.regist(board);
		//model.addAttribute("result", "SUCCESS");
		// 새로고침할때 success가 뜨지 않음.
		// 내부적으로 생성된 request 다 사라지고 없음.(메모리상에서 사라짐)
		// 그 전 request라서 생성된거이기 때문.

		rttr.addFlashAttribute("result", "SUCCESS"); // 새로고침할 때 나오지 않음, 한번만 전송함.

		return "redirect:listPage"; // 주소창에 /result로 넘어감.
	}

	@RequestMapping(value="/result")
	public @ResponseBody String regResult(){

		return "result";
	}

	@RequestMapping(value="/listAll", method=RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		List<Board> list = service.listAll();

		model.addAttribute("list", list);

	}
	
	@RequestMapping(value="member")
	public @ResponseBody Member getMember(){
		Member m = new Member();
		
		return m;
	}

	@RequestMapping(value="listPage", method=RequestMethod.GET)
	public String listPage(Criteria criteria, Model model) throws Exception{
		// page와 recordsPerPage를 받아 자동으로 객체에 넣어줌.
		// service에게 요청하면 List<Board> 객체 반환.
		// 그러면 그 객체를 model에 넣어 줌.
		// 그리고 뷰페이지에서 그 객체를 이용해서 글 목록 보여줌.
		
		System.out.println(criteria);
		List<Board> list = service.listSearch(criteria);
		model.addAttribute("list", list);
		model.addAttribute("criteria", criteria);
		
		// startPage, endPage, previous, next
		//int totalCount = service.getTotalCount();
		int totalCount = service.getSearchTotalCount(criteria);
		criteria.setTotalCount(totalCount);
		
		return "listPage";
		
	}
	
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public void read(@RequestParam(value="bno", defaultValue="-1") int bno, 
			@ModelAttribute Criteria criteria, Model model) throws Exception{
		// defaultValeu는 String 값

		Board board = service.read(bno);
		model.addAttribute("board", board);

	}

	@RequestMapping(value="/remove")
	public String remove(@RequestParam(value="bno", defaultValue="-1") int bno, Criteria criteria, 
			RedirectAttributes rttr) throws Exception{
		// @ModelAttribute : fowarding 할 때 정보를 넘겨줌.
	
		service.remove(bno);
		//return "redirect:listPage?"+criteria.getPage()+"&recordsPerPage"
		//+criteria.getRecordsPerPage();
		
		rttr.addAttribute("page", criteria.getPage());
		rttr.addAttribute("recordsPerPage", criteria.getRecordsPerPage());
		rttr.addFlashAttribute("delmsg", true); // 한번만 실행시켜줌
		rttr.addAttribute("searchType", criteria.getSearchType());
		rttr.addAttribute("keyword", criteria.getKeyword());
		return "redirect:listPage";

	}
	
	// Exception : 사용자 정의 가능, 다양한 종류가 있음.
	// Exception이라는 클래스가 있음. 상속 받음
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String modify(Board board, Criteria criteria, RedirectAttributes rttr) throws Exception{
		service.modify(board);
		
		rttr.addAttribute("bno", board.getBno());
		rttr.addAttribute("page", criteria.getPage());
		rttr.addAttribute("recordsPerPage", criteria.getRecordsPerPage());
		rttr.addAttribute("searchType", criteria.getSearchType());
		rttr.addAttribute("keyword", criteria.getKeyword());
		
		
		return "redirect:read";
	}
	
	
	@RequestMapping(value="listSearch", method=RequestMethod.GET)
	public String listSearch(@ModelAttribute Criteria criteria, Model model) throws Exception{
		
		List<Board> list = service.listSearch(criteria);
		model.addAttribute("list", list);
		int totalCount = service.getSearchTotalCount(criteria);
		criteria.setTotalCount(totalCount);
		return "listPage";
	}
	

}
