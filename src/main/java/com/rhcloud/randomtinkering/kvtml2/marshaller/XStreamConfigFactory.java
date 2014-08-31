package com.rhcloud.randomtinkering.kvtml2.marshaller;

import com.rhcloud.randomtinkering.kvtml2.marshaller.converter.EntryConverter;
import com.rhcloud.randomtinkering.kvtml2.marshaller.converter.EntryMapConverter;
import com.rhcloud.randomtinkering.kvtml2.marshaller.converter.LessonsConverter;
import com.rhcloud.randomtinkering.kvtml2.marshaller.converter.WordTypesConverter;
import com.rhcloud.randomtinkering.kvtml2.model.KvtmlFile;
import com.rhcloud.randomtinkering.kvtml2.model.Lesson;
import com.rhcloud.randomtinkering.kvtml2.model.entries.Entry;
import com.rhcloud.randomtinkering.kvtml2.model.information.Information;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import java.util.List;

class XStreamConfigFactory {

    public static XStream kvtmlUnmarshallingXstream() {
        XStream xstream = new SkippingUnmappedTagsXstream();
        xstream.alias("kvtml", KvtmlFile.class);
        xstream.alias("information", Information.class);

        xstream.alias("entry", Entry.class);
        xstream.alias("entries", EntriesMap.class);

        xstream.alias("lessons", List.class);
        xstream.alias("container", Lesson.class);

        xstream.registerConverter(new EntryConverter());

        EntryMapConverter entriesConverter = new EntryMapConverter(xstream.getMapper());
        xstream.registerLocalConverter(KvtmlFile.class, "entries", entriesConverter);
        xstream.registerLocalConverter(KvtmlFile.class, "lessons", new LessonsConverter());
        xstream.registerLocalConverter(KvtmlFile.class, "wordtypes", new WordTypesConverter());

        return xstream;

    }

    /**
     * It ensures that tags which haven't yet been added to xstream
     * configuration won't stop unmarshalling
     */
    private static class SkippingUnmappedTagsXstream extends XStream {

        @Override
        protected MapperWrapper wrapMapper(MapperWrapper next) {
            return new MapperWrapper(next) {
                @Override
                public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                    if (definedIn == Object.class) {
                        return false;
                    }
                    return super.shouldSerializeMember(definedIn, fieldName);
                }
            };
        }
    }
}
