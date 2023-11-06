package com.codewithsarav.Dao;

import com.codewithsarav.DB.DbConnection;
import com.codewithsarav.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    static Connection conn;

    public UserDao() {
        conn = DbConnection.getConnection();
    }

    private static User loggedInUser =null;
    public static final String LOGIN_VALID_QUERY = "select id,username from auth where username=? and password=? and deleteflag=false;";

    public User loginValidation(String username, String password) {

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(LOGIN_VALID_QUERY);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                loggedInUser = new User();
                System.out.println("userId " + rs.getString("id"));
                System.out.println("UserName"+rs.getString("username"));
                loggedInUser.setId(Integer.parseInt(rs.getString("id")));
                loggedInUser.setUserName(rs.getString("username"));
                return loggedInUser;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loggedInUser;
    }
}
