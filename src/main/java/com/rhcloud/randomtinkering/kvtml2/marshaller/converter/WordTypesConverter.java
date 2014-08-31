package com.rhcloud.randomtinkering.kvtml2.marshaller.converter;

import com.rhcloud.randomtinkering.kvtml2.model.Lesson;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class WordTypesConverter implements Converter {

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        List<Lesson> lessons = new ArrayList<>();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            String nodeName = reader.getNodeName();
            if ("container".equalsIgnoreCase(nodeName)) {
                Lesson lesson = (Lesson) context.convertAnother(null, List.class, new SingleLessonConverter());
                lessons.add(lesson);
            }
            reader.moveUp();
        }
        return lessons;
    }

    @Override
    public boolean canConvert(Class type) {
        return List.class.isAssignableFrom(type);
    }

}
