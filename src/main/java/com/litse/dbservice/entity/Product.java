package com.litse.dbservice.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "product")
@ToString
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "productId")
    private long id;
    @ApiModelProperty(notes = "productNames")
    @NotNull
    private String productName;
    @Max(45)
    private String productPrice;
    @Nullable
    private boolean status;

    public Product(String productName,String productPrice,boolean status) {

        this.productName = productName;
        this.productPrice = productPrice;
        this.status=status;
    }
}
