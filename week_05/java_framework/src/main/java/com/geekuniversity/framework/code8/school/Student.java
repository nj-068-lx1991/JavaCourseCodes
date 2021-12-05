package com.geekuniversity.framework.code8.school;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by lx_068
 */
@Data
@AllArgsConstructor
public class Student {
    private int id;
    private String name;

    @Override
    public String toString() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("id", id);
        map.put("name", name);
        return "Student::" + map;
    }
}
