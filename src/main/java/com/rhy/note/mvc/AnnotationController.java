package com.rhy.note.mvc;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Herion Lemon
 * @date: 2021年05月11日 13:21:00
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description:
 */
@Controller
@RequestMapping("/controller")
public class AnnotationController {
	@RequestMapping("/annotation")
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response,String param){
		return "annotation";
	}

	@RequestMapping("/template")
	public String index(ModelMap map) {
		// 加入一个属性，用来在模板中读取
		map.addAttribute("msg", "Hello World!");
		// return模板文件的名称，对应src/main/resources/templates/index.html
		return "index";
	}
}
