package com.geekuniversity.framework.code8.school;

import com.geekuniversity.framework.code8.school.Klass;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Created by lx_068
 */
@Data
@AllArgsConstructor
public class School {
    private List<Klass> myClasses;

    @Override
    public String toString() {
        return "Klass::" + myClasses.toString();
    }
}
