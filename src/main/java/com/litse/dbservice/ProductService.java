//package com.litse.dbservice;
//
//import com.litse.dbservice.entity.Product;
//import com.litse.dbservice.exception.ProductNotFound;
//import com.litse.dbservice.repository.ProductRepository;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//@Service
//public class ProductService {
//
//    private final ProductRepository productRepository;
//
//    public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    public  Product  productFinal(String productName) throws Exception {
//        Product product=productRepository.findByProductName(productName);
//        if(StringUtils.isEmpty(product)){
//            throw new ProductNotFound("product"+productName);
//        }
//        return productRepository.findByProductName(productName);
//    }
//
//}
