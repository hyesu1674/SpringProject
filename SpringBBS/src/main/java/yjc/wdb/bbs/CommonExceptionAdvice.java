package yjc.wdb.bbs;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// ��Ʈ�ѷ����� �߻��� Exception�� ó���Ѵٴ� ���� ���
@ControllerAdvice
public class CommonExceptionAdvice {

	@ExceptionHandler(Exception.class)
	private ModelAndView handleException(Exception ex){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error_common"); 
		// web-inf - views - error_common view�������� �Ѱ���
		modelAndView.addObject("exception", ex); // exception�� �߻��ϸ� �� exception��
												 //	view�������� �Ѱ���.
		
		return modelAndView;
	}
}
