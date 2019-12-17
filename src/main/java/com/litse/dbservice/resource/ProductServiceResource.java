package com.litse.dbservice.resource;


import com.litse.dbservice.entity.Product;
import com.litse.dbservice.repository.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@Api(value = "HelloWorld ResourceS", description = "shows hello worldS")
public class ProductServiceResource {


    private final ProductRepository productRepository;

    public ProductServiceResource(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }


    @ApiOperation("ProductList")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "100 is the message"),
                    @ApiResponse(code = 200, message = "Successful Hello World")
            })
    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        return new ResponseEntity<>(productRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{productName}")
    @ApiOperation("Product")
    public ResponseEntity<Product> findProductById(@PathVariable("productName") String productName) {
        return new ResponseEntity<>(productRepository.findByProductName(productName), HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    @ApiOperation("Deleting Product")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return  ResponseEntity.ok("Deleted");
    }

    @PostMapping("/")
    public ResponseEntity createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@RequestBody Product product, @PathVariable("id") long id) {
        product.isStatus();
        productRepository.save(product);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateStatus(@RequestBody Map<String, Boolean> status, @PathVariable("id") Long id) {
       Optional<Product> product= productRepository.findById(id);
        Product product1 = null;
        if(product.isPresent()){
             product1=product.get();
        }
        product1.setStatus(status.get("status"));
        productRepository.save(product1);
        return new ResponseEntity(HttpStatus.OK);
    }

}
