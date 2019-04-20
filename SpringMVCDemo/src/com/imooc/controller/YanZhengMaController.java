package com.imooc.controller;

import org.springframework.stereotype.Controller;
import java.awt.image.BufferedImage;  
import javax.imageio.ImageIO;  
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.servlet.ModelAndView;  
import com.google.code.kaptcha.Constants;  
import com.google.code.kaptcha.Producer;
@Controller
public class YanZhengMaController {
	
	
	@Autowired  
	private Producer captchaProducer;
	
	//�ύ������֤���߼�����
	@RequestMapping("/kaptcha.action")
	public String kaptchaSuccess(String checkCode,HttpServletRequest request){
		//��ǰ̨�����֤����е�����
		String username=checkCode;
		//��session�л����ȷ����֤���ֵ
		String str = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//�Ƚ��Ƿ���ͬ����ͬ��ô��������ͬ��ô��������ֻ�Ǽ򵥵�������಻��ͬ����ת��succees.jsp�У�
		System.out.println(username+"---------"+str);
		return "success";
	}
	
	
	
	
	
	
	
	 //������֤���handler
	 @RequestMapping("/kaptcha.jpg")  
	 //���requestMapping�е�ֵӦ�ú�web.xml��servlet�����ص�url��ͬ����jsp�б�����ʾ��֤��ͼƬ��src��ͬ
	    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {  
	  
	        response.setDateHeader("Expires", 0);  
	        // Set standard HTTP/1.1 no-cache headers.  
	        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
	        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
	        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
	        // Set standard HTTP/1.0 no-cache header.  
	        response.setHeader("Pragma", "no-cache");  
	        // return a jpeg  
	        response.setContentType("image/jpeg");  
	        // create the text for the image  
	        String capText = captchaProducer.createText();  
	        // store the text in the session ,����֤���ֵ����session��
	        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);  
	        // create the image with the text  
	        BufferedImage bi = captchaProducer.createImage(capText);  
	        ServletOutputStream out = response.getOutputStream();  
	        // write the data out  
	        ImageIO.write(bi, "jpg", out);  
	        try {  
	            out.flush();  
	        } finally {  
	            out.close();  
	        }  
	        return null;  
	    }  
	
	
	
	
	

	/**
	 * @return the captchaProducer
	 */
	public Producer getCaptchaProducer() {
		return captchaProducer;
	}

	/**
	 * @param captchaProducer the captchaProducer to set
	 */
	public void setCaptchaProducer(Producer captchaProducer) {
		this.captchaProducer = captchaProducer;
	}
	
	
	

}
