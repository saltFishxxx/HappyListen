import cn.xyh.dao.ProductDao;
import cn.xyh.model.Product;
import org.junit.Test;

import java.util.List;

public class ProductDaoTest {
    private ProductDao productDao = new ProductDao();

    @Test
    public void testFindByBookId() {
        Product bookById = productDao.findBookById(2);
        System.out.println(bookById);
    }

    @Test
    public void testFindBooks() {
        List<Product> products =
                productDao.findBooks("计算机", 4, 4);
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Test
    public void testProductCount() {
        int productCount = productDao.productCount(null);
        System.out.println(productCount);
    }
}
