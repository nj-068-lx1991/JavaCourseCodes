package com.lx.starter;

import com.geekuniversity.framework.code8.school.School;
import com.geekuniversity.framework.code8.school.SchoolAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SchoolAutoConfiguration.class)
public class SchoolTest {

    @Autowired
    School mySchool;

    @Test
    public void test() {
        System.out.println(mySchool.toString());
    }
}
