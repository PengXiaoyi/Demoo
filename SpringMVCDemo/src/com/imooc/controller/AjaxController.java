package com.imooc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.entity.Student;

@Controller
public class AjaxController {

	

	@RequestMapping(value = "/ajaxStr.action", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String method1(HttpServletRequest request) {
		// ���keyΪusername��value
		String username = request.getParameter("username");
		// �����õĲ���
		System.out.println(username);
		String reslut = "��¼�ɹ�:" + username;
		// ���ؽ��
		return reslut;
	}

	@RequestMapping(value = "/ajaxList.action",  produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public List<Student> returnList(HttpServletRequest request) {
		System.out.println("��ǰ̨��ȡ�Ĳ�����" + request.getParameter("username"));

		List<Student> students = new ArrayList<>();
		Student stu1 = new Student(1, "����");
		Student stu2 = new Student(2, "����");
		Student stu3 = new Student(3, "����");
		students.add(stu1);
		students.add(stu2);
		students.add(stu3);
		return students;

	}

	@RequestMapping(value = "/ajaxMap.action",  produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, Object> returnMap() {
		Map<String, Object> maps = new HashMap<>();
		
		maps.put("student", "����");
		maps.put("teacher", "����");

		return maps;
	}
	
	
	
	
	
	

}
