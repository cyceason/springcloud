package com.cyc.provide;

import java.io.Serializable;

/**
 * Created by cyc_e on 2017/8/19.
 */
public class User implements Serializable {
    public User() {
        this.name = "陈奕丞";
        this.age = 26l;
    }

    private String name;
    private Long age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
