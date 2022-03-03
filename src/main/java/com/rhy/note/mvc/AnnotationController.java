package com.rhy.note.mvc;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Herion Lemon
 * @date: 2021年05月11日 13:21:00
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description:
 */
@Controller
@RequestMapping("/controller")
public class AnnotationController {

	@RequestMapping("/template")
	public String index(ModelMap map) {
		// 加入一个属性，用来在模板中读取
		map.addAttribute("msg", "Hello World!");
		// return模板文件的名称，对应src/main/resources/templates/index.html
		return "index";
	}
	@RequestMapping("/test3")
	@ResponseBody
	public String test3(@ModelAttribute("person") Pojo person, @ModelAttribute("cat") Pojo2 cat){
		return "test Response Ok!"+person+","+cat;
	}

//	@InitBinder("person")
//	public void initPerson(WebDataBinder binder){
//		binder.setFieldDefaultPrefix("person.");
//	}

//	@InitBinder("cat")
//	public void initCat(WebDataBinder binder){
//		binder.setFieldDefaultPrefix("cat.");
//	}
	@RequestMapping(value = {"/test1"})
	public String testRhy(HttpServletRequest httpServletRequest) {
		System.out.println("URL:"+httpServletRequest.getRequestURL());
		System.out.println("URI:"+httpServletRequest.getRequestURI());
		System.out.println("contextPath:"+httpServletRequest.getContextPath());
		System.out.println("serlvetPath:"+httpServletRequest.getServletPath());
		ServletContext servletContext = httpServletRequest.getServletContext();
		return "rhy";
	}


	@RequestMapping("/test2")
	@ResponseBody
	public Object returnJson() {
		Map<String,String> retMap = new HashMap<>();
		retMap.put("name","张三");
		return retMap;
	}


	@RequestMapping("/test4")
	@ResponseBody
	public Map<String,String> testRequestParam(@RequestParam("${annotation.name}") String name) { //( String name)(@RequestParam("name") String name)
		System.out.println("name="+name);
		Map<String,String> retMap = new HashMap<>();
		retMap.put("name",name);
		return retMap;
	}

	@RequestMapping("/test5")
	@ResponseBody
	public User testRequestParam(@RequestBody User user) { //(@RequestBody Map<String,Object> map)
		System.out.println("map="+user);
		return user;
	}



	@RequestMapping("/initbinder/user")
	public User getFormatData(User user) {
		System.out.println("user:"+user.toString());
		return user;
	}

	/**
	 * 作用于单个controller
	 * WebDataBinder 的作用
	 * @param webDataBinder
	 */
//	@InitBinder
//	public void initWebBinderDataFormatter(WebDataBinder webDataBinder) {
//		//作用一:加入类型转化器
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		CustomDateEditor dateEditor = new CustomDateEditor(df, true);
//
//		webDataBinder.registerCustomEditor(Date.class,dateEditor);
//	}
//
//	@ModelAttribute
//	public void populateModel(@RequestParam String abc, Model model) {
//		model.addAttribute("attributeName", abc);
//	}

	@RequestMapping(value = "/modelAttribute")
	public String helloWorld() {
		return "helloWorld";
	}
}
