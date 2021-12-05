package com.geekuniversity.framework.code8.school;


import lombok.Data;

import java.util.List;

/**
 * @author Created by lx_068
 */
@Data
public class Klass {
    private int id;
    private String name;
    private List<Student> students;

    public Klass(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public String toString() {
        return "MyClass::" + students.toString();
    }
}
