package yjc.wdb.bbs;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionAdvice {
	
	@ExceptionHandler(Exception.class)
	private ModelAndView handleException(Exception ex){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error_common");
		modelAndView.addObject("exception", ex);
		
		return modelAndView;
	}
}
