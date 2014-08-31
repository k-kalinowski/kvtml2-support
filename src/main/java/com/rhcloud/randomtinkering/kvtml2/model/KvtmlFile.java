package com.rhcloud.randomtinkering.kvtml2.model;

import com.rhcloud.randomtinkering.kvtml2.model.entries.Entry;
import com.rhcloud.randomtinkering.kvtml2.model.information.Information;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class KvtmlFile {

    private Information information;
    private Map<String, Entry> entries;
//    private Identifiers identifiers;
    private List<Lesson> lessons;
//    private List<WordType> wordTypes;
}
