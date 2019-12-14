package cn.xyh.service;

import cn.xyh.dao.ProductDao;
import cn.xyh.model.PageBean;
import cn.xyh.model.Product;

import java.util.List;

public class ProductService {
    private ProductDao productDao = new ProductDao();

    //通过类别和页码得到分页信息
    public PageBean<Product> findAllProductByPage(String category, String currentNumber, int pageCount ) {
        PageBean<Product> productPageBean = new PageBean<>();
        int productCount = productDao.productCount(category);
        int i = Integer.parseInt(currentNumber);

        productPageBean.setAllCounts(productCount);
        productPageBean.setCurrentPage(i);
        productPageBean.setPageSize(pageCount);
        productPageBean.setAllPages(productCount % pageCount == 0 ? productCount / pageCount : productCount / pageCount + 1);
        List<Product> books = productDao.findBooks(category, i, pageCount);
        productPageBean.setObject(books);
        return productPageBean;
    }

    //通过id查询图书
    public Product findBookById(String id) {
        return productDao.findBookById(Integer.parseInt(id));
    }
}
