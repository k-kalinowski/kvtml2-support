package com.rhcloud.randomtinkering.kvtml2.marshaller;

import com.rhcloud.randomtinkering.kvtml2.marshaller.converter.EntryConverter;
import com.rhcloud.randomtinkering.kvtml2.model.entries.Entry;
import com.rhcloud.randomtinkering.kvtml2.model.entries.Translation;
import com.thoughtworks.xstream.XStream;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Test;

public class XStreamMapDeserializationTest {

    @Test
    public void testUnmarshall_shouldProperlyUnmarshallSingleEntry() {
        //given
        String xmlToUnmarshall
                = "<entry id=\"0\">\n"
                + "  <translation id=\"0\">\n"
                + "    <text>autumn</text>\n"
                + "  </translation>\n"
                + "  <translation id=\"1\">\n"
                + "    <text>der Herbst</text>\n"
                + "  </translation>\n"
                + "</entry>";

        XStream xstream = new XStream();
        xstream.alias("entry", Entry.class);
        xstream.registerConverter(new EntryConverter());
        //when
        Entry result = (Entry) xstream.fromXML(xmlToUnmarshall);
        //then
        assertNotNull("Entry should not be equal null", result);
        assertTrue("Should contain 2 translations", result.getTranslations().size() == 2);

        for (Map.Entry<String, Translation> entry : result.getTranslations().entrySet()) {
            assertNotNull(entry.getKey());
            Translation value = entry.getValue();
            assertNotNull(value.getText());

        }
    }
}
