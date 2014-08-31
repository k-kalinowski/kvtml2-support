package com.rhcloud.randomtinkering.kvtml2.model.entries;

import java.util.Map;
import lombok.Data;

@Data
public class Entry {

    private String id;
    Map<String, Translation> translations;

}
