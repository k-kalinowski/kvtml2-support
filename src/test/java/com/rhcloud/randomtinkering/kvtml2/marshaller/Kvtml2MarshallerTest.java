package com.rhcloud.randomtinkering.kvtml2.marshaller;

import com.rhcloud.randomtinkering.kvtml2.model.KvtmlFile;
import com.rhcloud.randomtinkering.kvtml2.model.Lesson;
import com.rhcloud.randomtinkering.kvtml2.model.entries.Entry;
import com.rhcloud.randomtinkering.kvtml2.model.information.Information;
import java.io.FileNotFoundException;
import java.io.InputStream;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Kvtml2MarshallerTest {

    private Kvtml2Marshaller instance;

    @Before
    public void setUp() {
        instance = new Kvtml2Marshaller();
    }

    @Test
    public void testUnmarshall_shouldProperlyUnmarshallInformation() throws FileNotFoundException {
        //given
        KvtmlFile expectedKvtmlFile = new KvtmlFile();
        Information expectedInformation = expectedInformation();
        expectedKvtmlFile.setInformation(expectedInformation);
        final InputStream testFile = getClass().getResourceAsStream("/infoTest.kvtml");
        //when
        KvtmlFile result = instance.unmarshall(testFile, KvtmlFile.class);
        //then
        assertEquals("Objects should be the same", expectedKvtmlFile, result);
    }

    private Information expectedInformation() {
        Information expectedInformation = new Information();
        expectedInformation.setGenerator("Parley");
        expectedInformation.setTitle("basic vocabulary German-English");
        expectedInformation.setAuthor("Markus Büchele, Norbert Behrendt");
        expectedInformation.setLicense("GPL");
        expectedInformation.setDate("2014-08-18");
        return expectedInformation;
    }

    @Test
    public void testUnmarshall_shouldProperlyUnmarshallSingleEntry() throws FileNotFoundException {
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

        //when
        Entry result = instance.unmarshall(xmlToUnmarshall, Entry.class);
        //then
        assertNotNull("Entry should not be equal null", result);
        assertEquals("0", result.getId());
        assertTrue("Entry sould contain 2 translations", result.getTranslations().size() == 2);
        assertEquals("autumn", result.getTranslations().get("0").getText());
        assertEquals("der Herbst", result.getTranslations().get("1").getText());
    }

    @Test
    public void testUnmarshall_shouldProperlyUnmarshallEntries() throws FileNotFoundException {
        //given
        final InputStream testFile = getClass().getResourceAsStream("/entriesTest.kvtml");
        String kvtml
                = "<kvtml version=\"2.0\">\n"
                + "  <entries>\n"
                + "    <entry id=\"0\">\n"
                + "      <translation id=\"0\">\n"
                + "        <text>autumn</text>\n"
                + "      </translation>\n"
                + "      <translation id=\"1\">\n"
                + "        <text>der Herbst</text>\n"
                + "      </translation>\n"
                + "    </entry>\n"
                + "    <entry id=\"1\">\n"
                + "      <translation id=\"0\">\n"
                + "        <text>century</text>\n"
                + "      </translation>\n"
                + "      <translation id=\"1\">\n"
                + "        <text>das Jahrhundert</text>\n"
                + "      </translation>\n"
                + "    </entry>\n"
                + "    <entry id=\"2\">\n"
                + "      <translation id=\"0\">\n"
                + "        <text>day</text>\n"
                + "      </translation>\n"
                + "      <translation id=\"1\">\n"
                + "        <text>der Tag</text>\n"
                + "      </translation>\n"
                + "    </entry>\n"
                + "  </entries>\n"
                + "</kvtml>";

        //when
        KvtmlFile result = instance.unmarshall(kvtml, KvtmlFile.class);
        //then
        assertNotNull("Entry should not be equal null", result);
        assertTrue("Entry should contain 3 translations", result.getEntries().size() == 3);

        Entry entry = result.getEntries().get("0");

        assertEquals("0", entry.getId());
        assertEquals("autumn", entry.getTranslations().get("0").getText());
        assertEquals("der Herbst", entry.getTranslations().get("1").getText());

        entry = result.getEntries().get("1");
        assertEquals("1", entry.getId());
        assertEquals("century", entry.getTranslations().get("0").getText());
        assertEquals("das Jahrhundert", entry.getTranslations().get("1").getText());

        entry = result.getEntries().get("2");
        assertEquals("2", entry.getId());
        assertEquals("day", entry.getTranslations().get("0").getText());
        assertEquals("der Tag", entry.getTranslations().get("1").getText());
    }

    @Test
    public void testUnmarshall_shouldProperlyUnmarshallLessonsSection() throws FileNotFoundException {
        //given
        final InputStream testFile = getClass().getResourceAsStream("/lessonsTest.kvtml");
        //when
        KvtmlFile result = instance.unmarshall(testFile, KvtmlFile.class);
        //then
        assertEquals(47, result.getLessons().size());

        Lesson firstLesson = result.getLessons().get(0);
        assertEquals("time and space", firstLesson.getName());
        assertEquals(24, firstLesson.getEntryIds().size());
        assertEquals("0", firstLesson.getEntryIds().get(0));

        Lesson secondLesson = result.getLessons().get(1);
        assertEquals("survival training", secondLesson.getName());
        assertEquals(11, secondLesson.getEntryIds().size());
        assertEquals("24", secondLesson.getEntryIds().get(0));
    }
    
    @Test
    public void testUnmarshallFullFile() throws FileNotFoundException {
        //given
        final InputStream testFile = getClass().getResourceAsStream("/fullFile.kvtml");
        //when
        KvtmlFile result = instance.unmarshall(testFile, KvtmlFile.class);
        //then
        
        assertEquals(expectedInformation(), result.getInformation());
        
        assertEquals(47, result.getLessons().size());
        assertEquals(1332, result.getEntries().size());

        Lesson firstLesson = result.getLessons().get(0);
        assertEquals("time and space", firstLesson.getName());
        assertEquals(24, firstLesson.getEntryIds().size());
        assertEquals("0", firstLesson.getEntryIds().get(0));

        Lesson secondLesson = result.getLessons().get(1);
        assertEquals("survival training", secondLesson.getName());
        assertEquals(11, secondLesson.getEntryIds().size());
        assertEquals("24", secondLesson.getEntryIds().get(0));
        
        
        Entry entry = result.getEntries().get("4");
        assertEquals("4", entry.getId());
        assertEquals("hour", entry.getTranslations().get("0").getText());
        assertEquals("die Stunde", entry.getTranslations().get("1").getText());
        
        entry = result.getEntries().get("1331");
        assertEquals("1331", entry.getId());
        assertEquals("value", entry.getTranslations().get("0").getText());
        assertEquals("der Wert", entry.getTranslations().get("1").getText());
    }
}
