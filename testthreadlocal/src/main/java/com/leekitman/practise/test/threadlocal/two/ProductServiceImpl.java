package com.leekitman.practise.test.threadlocal.two;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LeeKITMAN
 * @see 2018/5/3 18:04
 */
public class ProductServiceImpl implements ProductService {

    private static final String UPDATE_PRODUCT_SQL = "update product set price = ? where id = ?";

    private static final String INSERT_LOG_SQL = "insert into log (created,description) values(?,?)";

    public void updateProductPrice(long productId, int price) {
        try {
            // 获取连接
            Connection conn = DBUtil.getConnection();
            conn.setAutoCommit(false);             // 关闭自动提交事务（开启事务）

            // 执行操作
            updateProduct(conn, UPDATE_PRODUCT_SQL, productId, price);      // 更新产品
            insertLog(conn, INSERT_LOG_SQL, "Create product.");             // 插入日志

            // 提交事务
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            DBUtil.closeConnection();
        }
    }

    private void insertLog(Connection conn, String insertLogSql, String logDescription) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(insertLogSql);
        pstmt.setString(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()));
        pstmt.setString(2, logDescription);
        int rows = pstmt.executeUpdate();
        if (rows != 0) {
            System.out.println("Insert log success!");
        }
    }

    private void updateProduct(Connection conn, String updateProductSql, long productId, int price) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(updateProductSql);
        pstmt.setInt(1, price);
        pstmt.setLong(2, productId);
        int rows = pstmt.executeUpdate();
        if (rows != 0) {
            System.out.println("Update product success!");
        }
    }

//    public static void main(String[] args) {
//        ProductServiceImpl productService = new ProductServiceImpl();
//        productService.updateProductPrice(1, 3000);
//    }

//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            ProductServiceImpl productService = new ProductServiceImpl();
//            ClientThread thread = new ClientThread(productService);
//            thread.start();
//        }
//    }

    public static void main(String[] args) {
        try {
            DatabaseMetaData meta = DBUtil.getConnection().getMetaData();
            int de = meta.getDefaultTransactionIsolation();
            System.out.println(de);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
