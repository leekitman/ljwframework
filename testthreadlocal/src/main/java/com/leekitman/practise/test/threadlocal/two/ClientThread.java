package com.leekitman.practise.test.threadlocal.two;

/**
 * @author LeeKITMAN
 * @see 2018/5/3 18:45
 */
public class ClientThread extends Thread {

    private ProductService productService;

    public ClientThread(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        productService.updateProductPrice(1,3000);
    }
}
