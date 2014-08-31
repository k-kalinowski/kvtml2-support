package com.rhcloud.randomtinkering.kvtml2.marshaller.converter;

import com.rhcloud.randomtinkering.kvtml2.model.Lesson;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class SingleLessonConverter implements Converter {

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        String lessonName = "";
        List<String> entryIds = new ArrayList<>();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            String nodeName = reader.getNodeName();
            if ("name".equalsIgnoreCase(nodeName)) {
                lessonName = reader.getValue();
            } else if ("entry".equalsIgnoreCase(nodeName)) {
                String id = reader.getAttribute("id");
                entryIds.add(id);
            }
            reader.moveUp();
        }
        Lesson resultLesson = new Lesson();
        resultLesson.setName(lessonName);
        resultLesson.setEntryIds(entryIds);
        return resultLesson;
    }

    @Override
    public boolean canConvert(Class type) {
        return true;
    }
}
