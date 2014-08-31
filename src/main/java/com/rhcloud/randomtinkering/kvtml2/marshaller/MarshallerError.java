package com.rhcloud.randomtinkering.kvtml2.marshaller;

public class MarshallerError extends RuntimeException {

    MarshallerError(Exception e) {
        super(e);
    }

}
