package com.fj.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Media {

    @Id
    @GeneratedValue
    private Long id;
    private Date gmtCreate = new Date();
    private Date gmtModify = new Date();

    private Date gmtOriginDate = new Date();
    private String type = "photo";
    private String storePath = "";
    private int size = 0;
}
