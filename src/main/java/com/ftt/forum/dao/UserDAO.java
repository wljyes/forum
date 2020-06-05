package com.ftt.forum.dao;

import com.ftt.forum.entity.User;

import java.sql.*;

public class UserDAO {

    public User selectById(int id) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/forum?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        String sql = "select * from user where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            User user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setPassword(rs.getString(3));
            return user;
        }
    }
}
