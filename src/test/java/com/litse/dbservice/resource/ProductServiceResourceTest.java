package com.litse.dbservice.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.litse.dbservice.entity.Product;
import com.litse.dbservice.repository.ProductRepository;
import com.litse.dbservice.resource.ProductServiceResource;;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductServiceResource.class)
public class ProductServiceResourceTest {
    public ProductServiceResourceTest() {
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    private Product product;
    private String requestUri = "/product";
    private String productJson;





    @MockBean
    ProductRepository productRepository;

    @Before
    public void setup() throws JsonProcessingException {
       product=new Product("Phone","45",true);
        productJson=mapper.writeValueAsString(product);
    }

//    @Test
//    public void findAllProducts() throws Exception {
//
//
//        when(productRepository.findAll()).thenReturn(Arrays.asList(product,product));
//
//        mockMvc.perform(get(requestUri))
//                .andExpect(content().json(Arrays.asList(productJson,productJson).toString()))
//                .andExpect(status().isOk());
//
//        verify(productRepository).findAll();
//
//    }

    @Test
    public void findProductById() throws Exception {
        when(productRepository.findByProductName("test")).thenReturn(product);

        mockMvc.perform(get(requestUri+"/{productName}","test"))
                .andExpect(content().json(productJson))
                .andExpect(status().isOk());

        verify(productRepository).findByProductName("test");
    }

    @Test
    public void deleteProduct() throws Exception {
        doNothing().when(productRepository).deleteById(1L);
        MvcResult mvcResult=mockMvc.perform(delete(requestUri+"/{id}",1))
                .andReturn();
         assertEquals("Deleted",mvcResult.getResponse().getContentAsString());
        verify(productRepository).deleteById(1L);
    }

    @Test
    public void createProduct() throws Exception {

        when(productRepository.save(Mockito.any())).thenReturn(product);

        mockMvc.perform(post(requestUri).contentType(MediaType.APPLICATION_JSON).content(productJson))
                 .andExpect(content().json(productJson))
                 .andExpect(status().isCreated());
        verify(productRepository).save(product);
    }

    @Test
    public void updateProduct() throws Exception {
        when(productRepository.save(any())).thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders.put(requestUri+"/{id}",0).contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsString(product)))
                .andExpect(status().isOk());
        verify(productRepository).save(product);
    }

    @Test
    public void updateStatus() throws Exception {
        Map<String,Boolean> map=new HashMap<>();
        map.put("status",true);
        product.setId(1);
        product.setStatus(map.get("status"));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders.patch(requestUri+"/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(map)))
                .andExpect(status().isOk());
        verify(productRepository).findById(1L);
        verify(productRepository).save(product);

    }

}