package com.codewithsarav.Dao;

import com.codewithsarav.DB.DbConnection;
import com.codewithsarav.model.Todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {
    Connection conn;

    public TodoDao() {
        conn = DbConnection.getConnection();
    }

    private List<Todo> todoList = new ArrayList<>();
    private static final String ALL_ITEM_GET_QUERY = "select id,item from todo where userId=?;";
    private static final String INSERT_ITEM = "insert into todo(userId,item) values(?,?)";
    private final String DELETE_TODO = "DELETE todo WHERE id=?;";


    public List<Todo> getTodoList() {
        return todoList;
    }

    public List<Todo> getAllList(int userId) {
        try {
            PreparedStatement ps = conn.prepareStatement(ALL_ITEM_GET_QUERY);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("entered");
                Todo todo = new Todo();
                todo.setId(Integer.parseInt(rs.getString("id")));
                todo.setItem(rs.getString("item"));
                todoList.add(todo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return todoList;
    }

    public void addTodo(String todo, int i) {
        try {
            PreparedStatement ps = conn.prepareStatement(INSERT_ITEM);
            ps.setInt(1, i);
            ps.setString(2, todo);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTodo(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement(DELETE_TODO);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}