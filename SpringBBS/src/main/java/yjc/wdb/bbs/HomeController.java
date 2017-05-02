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
//	public void hi(@RequestParam(value="myname", defaultValue="안넘어왔을때 기본값") String name, int age, Model model){
//		System.out.println("Name : " + name + "~");
//		System.out.println("Age : " + age + "~");
//		// Spring 프레임웤이 자동으로 Model 이라는 객체를 만들어서 넘겨준다.
//		// 객체에 값을 넣어줌
//		model.addAttribute("name", name);
//		model.addAttribute("age", age);
//	}
//	
//	@RequestMapping(value="/hi2")
//	public String hi2(Member m, Model model){
//		System.out.println("name : " + m.getName());
//		System.out.println("age : " + m.getAge());
//		System.out.println("Member : " + m);	//주소값만 찍힘
//		model.addAttribute("member", m);
//		
//		return "hi2";
//	}
	
	@RequestMapping(value="/hi3")
	public String hi3(@ModelAttribute Member m){
		System.out.println("name : " + m.getName());
		System.out.println("age : " + m.getAge());
		System.out.println("Member : " + m);	//주소값만 찍힘
		return "hi2";
	}
	
	
}
