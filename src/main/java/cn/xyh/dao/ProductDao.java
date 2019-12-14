package cn.xyh.dao;

import cn.xyh.model.Product;
import cn.xyh.util.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 关于书的
 */
public class ProductDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    //根据分类和页数查询书籍
    public List<Product> findBooks(String category, int currentPage, int pageSize) {
        String sql = "select * from products where 1=1";
        List<Object> list = new ArrayList<>();
        if (category != null && !"".equals(category)) {
            sql += " and category = ?";
            list.add(category);
        }
        sql += " limit ?,?";
        list.add((currentPage-1)*pageSize);
        list.add(pageSize);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class), list.toArray());
    }

    //根据类别查个数
    public int productCount(String category) {
        String sql = "select count(*) from products where 1 = 1";
        int count = 0;
        if (category !=null && !"".equals(category)) {
            sql += " and category = ?";
            try {
                count = jdbcTemplate.queryForObject(sql, Integer.class, category);
            }catch (Exception e) {

            }
        }else {
            try {
                count = jdbcTemplate.queryForObject(sql, Integer.class);
            }catch (Exception e) {

            }
        }
        return count;
    }

    //根据id查书籍
    public Product findBookById(int id) {
        String sql = "select * from products where id = ?";
        Product product = null;
        try {
            product = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Product>(Product.class), id);
        }catch (Exception e) {

        }
        return product;
    }
}
