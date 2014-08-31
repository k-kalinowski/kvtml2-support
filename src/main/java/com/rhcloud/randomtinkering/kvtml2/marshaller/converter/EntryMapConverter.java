package com.rhcloud.randomtinkering.kvtml2.marshaller.converter;

import com.rhcloud.randomtinkering.kvtml2.model.entries.Entry;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.MapConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;
import java.util.Map;

public class EntryMapConverter extends MapConverter {

    public EntryMapConverter(Mapper mapper) {
        super(mapper);
    }

    @Override
    public boolean canConvert(Class type) {
        return super.canConvert(type);
    }

    @Override
    protected void putCurrentEntryIntoMap(HierarchicalStreamReader reader, UnmarshallingContext context,
            Map map, Map target) {
        String entryId = reader.getAttribute("id");
        Entry entry = (Entry) context.convertAnother(null, Entry.class);
        target.put(entryId, entry);

    }

}
