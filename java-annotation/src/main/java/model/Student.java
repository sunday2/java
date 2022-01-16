package model;

import annotation.LogComment;

/**
 * @program: java-annotation
 * @description: test
 * @author: xxx@gmail.com
 * @create: 2022-01-15 20:24
 **/

public class Student {

    @LogComment(name="stan", date = "2022-01-16")
    private Integer age;

    @LogComment(name="alex", date = "2022-01-15")
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}