package com.rhy.note.mvc;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Herion Lemon
 * @date: 2021年05月11日 13:24:00
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description:
 */
@Component("/httpRequestHandlerController")
public class HttpRequestHandlerController implements HttpRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().log("进入了HttpRequestHandlerController处理器");
	}
}
