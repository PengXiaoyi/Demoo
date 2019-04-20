package com.imooc.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadAction {
	// �ļ��ϴ�����
	@RequestMapping("/fileUpload.action")
	public String fileUpload(MultipartFile file, HttpServletRequest request) throws Exception {
		if (!file.isEmpty()) {
			// ��ӡ�ļ�������
			// System.out.println("FileName:" + file.getOriginalFilename());
			// ȷ���ϴ��ļ���λ��
			String path = request.getServletContext().getRealPath("/fileupload");
			File newFile = new File(path, file.getOriginalFilename());
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			// �ϴ�
			file.transferTo(newFile);
		}
		// �ϴ��ɹ���ת����success.jsp
		return "success";
	}

}
