package com.hf.spring.springdata.web;

import java.beans.PropertyEditorSupport;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyControllerAdvice {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MyControllerAdvice.class);

	@ModelAttribute
	public void sysData(Model model) {
		// System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前把返回值放入Model");
		model.addAttribute("systoken", "HelloMaven");
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(text == null ? null : StringEscapeUtils
						.escapeHtml4(text.trim()));
			}

		});
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void processException(Exception e, HttpServletRequest request) {
		if (isAjax(request)) {
			LOGGER.error(request.getRequestURI() + " Ajax发生错误:"
					+ e.getLocalizedMessage());
		}
		LOGGER.error(request.getRequestURI() + " 发生错误:"
				+ e.getLocalizedMessage());
		// return "viewName"; // 返回一个逻辑视图名
	}

	private boolean isAjax(ServletRequest request) {
		boolean isAjax = false;
		if (request instanceof HttpServletRequest) {
			HttpServletRequest rq = (HttpServletRequest) request;
			String requestType = rq.getHeader("X-Requested-With");
			if (requestType != null && "XMLHttpRequest".equals(requestType)) {
				isAjax = true;
			}
		}
		return isAjax;
	}
}
