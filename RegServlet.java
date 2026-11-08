package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // 简单的非空检查
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "所有字段都不能为空！");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // 这里可以添加更复杂的验证逻辑，如邮箱格式验证
        if (!email.contains("@")) {
            request.setAttribute("error", "邮箱格式不正确！");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // 注册成功后，将用户信息作为属性传递给结果页面
        request.setAttribute("registeredUsername", username);
        request.setAttribute("registeredEmail", email);
        request.getRequestDispatcher("registration-success.jsp").forward(request, response);
    }
}