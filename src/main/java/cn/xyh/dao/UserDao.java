package cn.xyh.dao;

import cn.xyh.model.User;
import cn.xyh.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * 关于用户的
 */
public class UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    //添加用户
    public int addUser(User user) {
        String sql = "insert into user values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getId(), user.getUsername(), user.getPassword(), user.getGender(),
                user.getEmail(), user.getTelephone(), user.getIntroduce(), user.getActiveCode(),
                user.getState(), user.getRole(), new Date());
    }

    //查找用户
    public User findUser(User user) {
        String sql = "select * from user where username = ? and PASSWORD = ?";
        User sqlUser = null;
        try {
            sqlUser = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),
                    user.getUsername(), user.getPassword());
        }catch (Exception e) {

        }
        return sqlUser;
    }

    //根据id查找用户
   public User findById(int id) {
        String sql = "select * from user where id = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        }catch (Exception e) {

        }
        return user;
   }

   //更新用户
   public int updateUser(int id, User user) {
        String sql = "update user set password = ?, gender = ?, telephone = ? where id = ?";
        return jdbcTemplate.update(sql, user.getPassword(), user.getGender(), user.getTelephone(), id);
   }

   //激活用户
   public int active(String activeCode) {
      String sql = "update user set state = 1 where activeCode = ?";
      return jdbcTemplate.update(sql, activeCode);
   }

   //通过激化码查找用户是否激活
   public User findByActiveCode(String activeCode) {
        String sql = "select * from user where activeCode = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), activeCode);
        }catch (Exception e) {

        }
        return user;
   }
}
