package cn.xyh.web.servlet;

import cn.xyh.model.PageBean;
import cn.xyh.model.Product;
import cn.xyh.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/product/*")
public class ProductServlet extends BaseServlet {
    private ProductService productService = new ProductService();

    //获得分页信息
    public void showProductByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageCount = 5;
        String category = request.getParameter("category");
        if (category != null && !"".equals(category)) {
            category = new String(category.getBytes("iso-8859-1"), "utf-8");
        }
        String currentPage = request.getParameter("currentPage");

        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        PageBean<Product> allProductByPage = productService.findAllProductByPage(category, currentPage, pageCount);
        request.setAttribute("products", allProductByPage);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/jsp/product_list.jsp").forward(request, response);
    }

    //获得id
    public void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        Product bookById = productService.findBookById(bid);
        request.setAttribute("book", bookById);
        request.getRequestDispatcher("/jsp/product_info.jsp").forward(request, response);
    }

    //购物车
    public void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> cart = (Map<String, Object>) request.getSession().getAttribute("cart");
        if (cart == null || cart.size() == 0) {
            Map<String, Object> newCart = new HashMap<>();
            request.getSession().setAttribute("cart", newCart);
        }
    }
}
