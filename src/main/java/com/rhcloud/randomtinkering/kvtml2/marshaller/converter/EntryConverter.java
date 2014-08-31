package com.rhcloud.randomtinkering.kvtml2.marshaller.converter;

import com.rhcloud.randomtinkering.kvtml2.model.entries.Entry;
import com.rhcloud.randomtinkering.kvtml2.model.entries.Translation;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class EntryConverter implements Converter {

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Entry entry = new Entry();
        Map<String, Translation> translations = new HashMap<>();
        String entryId = reader.getAttribute("id");
        entry.setId(entryId);
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            String nodeName = reader.getNodeName();
            if ("translation".equalsIgnoreCase(nodeName)) {
                String id = reader.getAttribute("id");
                Translation translation = (Translation) context.convertAnother(null, Translation.class);

                translations.put(id, translation);
            }
            reader.moveUp();
        }
        entry.setTranslations(translations);
        return entry;
    }

    @Override
    public boolean canConvert(Class type) {
        return Entry.class.isAssignableFrom(type);
    }

}
