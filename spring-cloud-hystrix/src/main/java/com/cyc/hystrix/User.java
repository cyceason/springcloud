package com.cyc.hystrix;

import java.io.Serializable;

/**
 * Created by cyc_e on 2017/8/19.
 */
public class User implements Serializable {

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
