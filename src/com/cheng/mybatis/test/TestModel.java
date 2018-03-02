package com.cheng.mybatis.test;

/**
 * *  test表对象
 * *  @author: dcl-Cheng
 * *  @github.io: https://dcl-cheng.github.io/
 **/
public class TestModel {

    private int id;
    private String name;

    public TestModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}