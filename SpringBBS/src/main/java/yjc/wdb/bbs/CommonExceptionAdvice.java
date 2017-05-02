package yjc.wdb.bbs;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// 컨트롤러에서 발생한 Exception를 처리한다는 것을 명시
@ControllerAdvice
public class CommonExceptionAdvice {

	@ExceptionHandler(Exception.class)
	private ModelAndView handleException(Exception ex){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error_common"); 
		// web-inf - views - error_common view페이지를 넘겨줌
		modelAndView.addObject("exception", ex); // exception이 발생하면 그 exception을
												 //	view페이지로 넘겨줌.
		
		return modelAndView;
	}
}
