package com.codewithsarav.controller;

import com.codewithsarav.Dao.UserDao;
import com.codewithsarav.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthController extends HttpServlet {
    private UserDao userDao;
    private RequestDispatcher dispatcher;
    public AuthController() {
        userDao = new UserDao();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password =  req.getParameter("password");
        User user= userDao.loginValidation(username,password);
        if(user !=null){
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("userId",user.getId());
            System.out.println(httpSession.getAttribute("userId"));
            dispatcher = req.getRequestDispatcher("todo");
            dispatcher.forward(req,resp);
        }
        else {
            req.setAttribute("error", true);
            dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }




}
