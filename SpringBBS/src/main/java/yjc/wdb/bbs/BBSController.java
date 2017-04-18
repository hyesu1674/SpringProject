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

@Controller // ��Ʈ�ѷ��� ������ ��
public class BBSController {

	@Inject
	private BoardService service; // Spring Bean ����ؾ��ϱ� ������ inject ������̼��� ����

	/*	@RequestMapping(value="/create") // create��� ��û�� get ������� ���� post ������� ���� ó��
	public String create(Board board) throws Exception{
		// ó���ϰ�.. => dao.insert(board);

		service.regist(board);

		return "result";
	}*/

	//create - get ��� - boardForm ����
	//submit Ŭ�� - /create - post ��� - result ������ ������
	//(������ ���ָ� �ȵ�- ���ΰ�ħ�� ��� ���� �����Ͱ� ��� ��(/create post���) 
	//redirect �ؾߵ�(result �������� ���� �ֱ��̱� ������ �Ȱ��� �����Ͱ�  �ȵ�)
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String getBoardForm(@ModelAttribute Criteria criteria) {
		return "boardForm";
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(Board board, RedirectAttributes rttr) throws Exception {

		service.regist(board);
		//model.addAttribute("result", "SUCCESS");
		// ���ΰ�ħ�Ҷ� success�� ���� ����.
		// ���������� ������ request �� ������� ����.(�޸𸮻󿡼� �����)
		// �� �� request�� �����Ȱ��̱� ����.

		rttr.addFlashAttribute("result", "SUCCESS"); // ���ΰ�ħ�� �� ������ ����, �ѹ��� ������.

		return "redirect:listPage"; // �ּ�â�� /result�� �Ѿ.
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
		// page�� recordsPerPage�� �޾� �ڵ����� ��ü�� �־���.
		// service���� ��û�ϸ� List<Board> ��ü ��ȯ.
		// �׷��� �� ��ü�� model�� �־� ��.
		// �׸��� ������������ �� ��ü�� �̿��ؼ� �� ��� ������.
		
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
		// defaultValeu�� String ��

		Board board = service.read(bno);
		model.addAttribute("board", board);

	}

	@RequestMapping(value="/remove")
	public String remove(@RequestParam(value="bno", defaultValue="-1") int bno, Criteria criteria, 
			RedirectAttributes rttr) throws Exception{
		// @ModelAttribute : fowarding �� �� ������ �Ѱ���.
	
		service.remove(bno);
		//return "redirect:listPage?"+criteria.getPage()+"&recordsPerPage"
		//+criteria.getRecordsPerPage();
		
		rttr.addAttribute("page", criteria.getPage());
		rttr.addAttribute("recordsPerPage", criteria.getRecordsPerPage());
		rttr.addFlashAttribute("delmsg", true); // �ѹ��� ���������
		rttr.addAttribute("searchType", criteria.getSearchType());
		rttr.addAttribute("keyword", criteria.getKeyword());
		return "redirect:listPage";

	}
	
	// Exception : ����� ���� ����, �پ��� ������ ����.
	// Exception�̶�� Ŭ������ ����. ��� ����
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
