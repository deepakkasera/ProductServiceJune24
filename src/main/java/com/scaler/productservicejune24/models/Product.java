package com.scaler.productservicejune24.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel { //product
    private String title;
    private Double price;
    @ManyToOne
    private Category category;
}

/*

   1             1
Product ----- Category => M:1
   M             1

 */