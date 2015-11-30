package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CustomerBean;
import model.CustomerService;

public class LoginServlet extends HttpServlet {
	public CustomerService customerService = new CustomerService();
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//接收資料
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("aaaaaaaaaaa");
//驗證資料
		System.out.println("YAYA");
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		
		if(username==null || username.length()==0) {
			errors.put("username", "請輸入ID");
		}
		if(password==null || password.length()==0) {
			errors.put("password", "請輸入PWD");
		}
		if(errors!=null && !errors.isEmpty()) {
			request.getRequestDispatcher(
					"/secure/login.jsp").forward(request, response);
			return;
		}
		System.out.println("456");
//呼叫Model
		CustomerBean bean = customerService.login(username, password);
		
//根據Model執行結果，呼叫View
		if(bean==null) {
			errors.put("password", "登入失敗，請再試一次");
			request.getRequestDispatcher(
					"/secure/login.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", bean);

			String path = request.getContextPath();
			response.sendRedirect(path+"/index.jsp");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
