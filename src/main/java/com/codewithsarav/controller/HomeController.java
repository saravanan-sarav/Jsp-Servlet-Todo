package com.codewithsarav.controller;

import com.codewithsarav.Dao.TodoDao;
import com.codewithsarav.model.Todo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeController extends HttpServlet {
    private TodoDao todoDao;
    public HomeController() {
        todoDao = new TodoDao();
    }

    private RequestDispatcher requestDispatcher;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            todoDao.deleteTodo(Integer.parseInt(id));
        }
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestDispatcher = req.getRequestDispatcher("home.jsp");
        int userId = Integer.parseInt(req.getSession().getAttribute("userId").toString());

        String todo = req.getParameter("todo");
        if (todo != null){
            todoDao.addTodo(todo, userId);
        }
//        Listing already sent data
        List<Todo> todoList = todoDao.getAllList(userId);
        req.setAttribute("todoList",todoList);
        requestDispatcher.forward(req,resp);
    }
}
