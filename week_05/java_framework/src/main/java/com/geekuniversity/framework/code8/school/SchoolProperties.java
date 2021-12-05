package com.geekuniversity.framework.code8.school;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;
import java.util.Map;

/**
 * @author Created by lx_068
 */
@Data
@ConfigurationProperties(prefix = "school")
public class SchoolProperties {
    private List<Integer> studentIds;
    private List<String> studentNames;
    private List<Integer> myClassIds;
    private List<String> myClassNames;
    private List<Map<String, Integer>> studentOfClass;
}
