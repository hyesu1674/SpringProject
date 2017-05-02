package yjc.wdb.bbs;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import yjc.wdb.bbs.bean.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="/test")
	public String hello(){
		return "hello";
	}
	
	/* test2라는 요청이 오면 test.jsp 페이지를 실행 시키도록
	 * 메소드를 작성해서 실행해 보시오.
	 */
	@RequestMapping(value="/test2")
	public String test2(){
		return "test2";
	}
	
	@RequestMapping(value="/hi")
	public void test(@RequestParam(value="myname", defaultValue="hs") String myname, int age, Model model){
		System.out.println("Name:"+myname+"~!");
		System.out.println("Age:"+age+"~!");
		model.addAttribute("name", myname);
		model.addAttribute("age", age);
	}
	
	@RequestMapping(value="/hi2")
	public String hi2(Member m, Model model){
		System.out.println("name:"+m.getName());
		System.out.println("age:"+m.getAge());
		System.out.println("Member:"+m); //멤버객체가 자동으로 만들어짐
		model.addAttribute("m", m);
		return "hi2";
	}
	
	@RequestMapping(value="/hi3")
	public String hi3(@ModelAttribute Member m){
		System.out.println("name:"+m.getName());
		System.out.println("age:"+m.getAge());
		System.out.println("Member:"+m); //멤버객체가 자동으로 만들어짐
		return "hi2";
	}
	
	@RequestMapping(value="/ajaxTest", method=RequestMethod.GET)
	public void ajaxTest(){
		
		
		
	}
}
