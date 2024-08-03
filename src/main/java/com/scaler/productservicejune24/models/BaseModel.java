package com.scaler.productservicejune24.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel implements Serializable {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
    private Long id;
    private Date createdAt;
    private Date updatedAt;
}
