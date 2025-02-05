package com.comarch.szkolenia.rest.database.jdbc;

import com.comarch.szkolenia.rest.database.IUserRepository;
import com.comarch.szkolenia.rest.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class UserDAO implements IUserRepository {

    private final Connection connection;

    @Override
    public User persist(User user) {
        Optional<User> userBox = this.getById(user.getId());

        if(userBox.isPresent()) {
            try {
                String sql = "update user set login = ?, name = ?, surname = ?, age = ? where id = ?";
                PreparedStatement ps = this.connection.prepareStatement(sql);
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getName());
                ps.setString(3, user.getSurname());
                ps.setInt(4, user.getAge());
                ps.setLong(5, user.getId());

                ps.executeUpdate();

                return user;
            } catch (SQLException e) {
                System.out.println("nie dziala zapis usera !!");
                return null;
            }
        } else {
            try {
                String sql = "insert into user (login, name, surname, age) VALUES (?,?,?,?)";
                PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getName());
                ps.setString(3, user.getSurname());
                ps.setInt(4, user.getAge());

                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                user.setId(rs.getLong(1));
            } catch (SQLException e) {
                System.out.println("nie dziala zapis usera !!");
                return null;
            }

            return user;
        }
    }

    @Override
    public void delete(long id) {
        try {
            String sql = "delete from user where id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("nie dziala usuwanie usera na bazie !!");
        }
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try {
            String sql = "select * from user";
            PreparedStatement ps = this.connection.prepareStatement(sql);

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(new User(
                            rs.getLong("id"),
                            rs.getString("login"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getInt("age")));
                }
            }
        } catch (SQLException e) {
            System.out.println("nie dziala wyciaganie userow !!");
        }
        return result;
    }

    @Override
    public Optional<User> getById(long id) {
        try {
            String sql = "select * from user where id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setLong(1, id);

            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new User(
                            rs.getLong("id"),
                            rs.getString("login"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getInt("age")));
                }
            }
        } catch (SQLException e) {
            System.out.println("nie dziala wyciaganie usera po id !!!");
        }
        return Optional.empty();
    }

    @Override
    public List<User> getByAge(int age) {
        List<User> result = new ArrayList<>();
        try {
            String sql = "select * from user where age = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, age);

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(new User(
                            rs.getLong("id"),
                            rs.getString("login"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getInt("age")));
                }
            }
        } catch (SQLException e) {
            System.out.println("nie dziala wyciaganie userow !!");
        }
        return result;
    }
}
