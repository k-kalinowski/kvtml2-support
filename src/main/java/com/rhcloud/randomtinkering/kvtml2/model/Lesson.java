package com.rhcloud.randomtinkering.kvtml2.model;

import java.util.List;
import lombok.Data;

@Data
public class Lesson {

    private String name;
    
    List<String> entryIds;
}
