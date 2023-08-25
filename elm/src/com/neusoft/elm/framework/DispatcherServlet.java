package com.neusoft.elm.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ���ı��봦��
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		// ��ȡ�ͻ�������·��
		String path = request.getServletPath();
		
		// ��������·������controller��������뷽������������
		String className = path.substring(1, path.lastIndexOf("/"));
		String methodName = path.substring(path.lastIndexOf("/") + 1);
		
		PrintWriter out = null;
		
		try {
			// ͨ��Class.forName��ȡcontrollere�����Ϣ
			Class clazz = Class.forName("com.neusoft.elm.controller." + className);
			// ����controller����
			Object controller = clazz.newInstance();
			// ��ȡcontroller�ķ���
			Method method = clazz.getMethod(methodName,new Class[] {HttpServletRequest.class});
			// ����controller�еķ���
			Object result = method.invoke(controller, new Object[] {request});
			
			out = response.getWriter();
			ObjectMapper om = new ObjectMapper();
			out.print(om.writeValueAsString(result));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DispatcherServlet��Ϣ��Servlet����url·����" + path);
			System.out.println("DispatcherServlet��Ϣ��������" + className + "\t��������" + methodName);
		} finally {
			out.close();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}
