package cn.xyh.dao;

import cn.xyh.model.Order;
import cn.xyh.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OrderDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    //通过用户寻找全部订单
    public List<Order> findOrdersByUser(String userId) {
        String sql = "select * from orders where user_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Order>(Order.class), userId);
    }

    //通过id寻找订单
    public Order findOrderById(int id) {
        return null;
    }
}
