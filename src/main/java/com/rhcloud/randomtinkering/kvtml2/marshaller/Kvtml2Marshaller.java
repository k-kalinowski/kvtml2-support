package com.rhcloud.randomtinkering.kvtml2.marshaller;

import com.thoughtworks.xstream.XStream;
import java.io.InputStream;

public class Kvtml2Marshaller {

    private final XStream xstream;

    public Kvtml2Marshaller(XStream xstream) {
        this.xstream = xstream;
    }

    Kvtml2Marshaller() {
        this.xstream = XStreamConfigFactory.kvtmlUnmarshallingXstream();
    }

    public <T> T unmarshall(InputStream inputStream, Class<T> aClass) {
        try {
            Object value = xstream.fromXML(inputStream);
            return aClass.cast(value);
        } catch (Exception e) {
            throw new MarshallerError(e);
        }
    }
    
    public <T> T unmarshall(String xml, Class<T> aClass) {
        try {
            Object value = xstream.fromXML(xml);
            return aClass.cast(value);
        } catch (Exception e) {
            throw new MarshallerError(e);
        }
    }

    public String marshall(Object o) {
        try {
            return xstream.toXML(o);
        } catch (Exception e) {
            throw new MarshallerError(e);
        }
    }

}