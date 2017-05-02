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
	
	@RequestMapping(value="/test2")
	public String test(){
		return "test";
	}
	
	@RequestMapping(value="ajaxTest", method=RequestMethod.GET)
	public void ajaxTest(){
		
	}
	
//	@RequestMapping(value="/hi")
//	public void hi(@RequestParam(value="myname", defaultValue="�ȳѾ������ �⺻��") String name, int age, Model model){
//		System.out.println("Name : " + name + "~");
//		System.out.println("Age : " + age + "~");
//		// Spring �����ӟp�� �ڵ����� Model �̶�� ��ü�� ���� �Ѱ��ش�.
//		// ��ü�� ���� �־���
//		model.addAttribute("name", name);
//		model.addAttribute("age", age);
//	}
//	
//	@RequestMapping(value="/hi2")
//	public String hi2(Member m, Model model){
//		System.out.println("name : " + m.getName());
//		System.out.println("age : " + m.getAge());
//		System.out.println("Member : " + m);	//�ּҰ��� ����
//		model.addAttribute("member", m);
//		
//		return "hi2";
//	}
	
	@RequestMapping(value="/hi3")
	public String hi3(@ModelAttribute Member m){
		System.out.println("name : " + m.getName());
		System.out.println("age : " + m.getAge());
		System.out.println("Member : " + m);	//�ּҰ��� ����
		return "hi2";
	}
	
	
}
