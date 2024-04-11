package com.example.backend_admin.elastic.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Getter
@Document(indexName = "orders")
public class OrdersDocument {

    @Id
    private String id;

    @Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    @Field(type = FieldType.Integer)
    private int customerIdx;

    @Field(type = FieldType.Integer)
    private int productIdx;

    @Field(type = FieldType.Double)
    private double category;

    @Field(type = FieldType.Double)
    private double price;

    @Field(type = FieldType.Text)
    private String impUid;

    @Override
    public String toString() {
        return "OrdersDocument{" +
                "timestamp=" + timestamp +
                ", customerIdx='" + customerIdx + '\'' +
                ", productIdx='" + productIdx + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}