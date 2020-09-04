package io.rheem.basic.operators.test;

import io.rheem.core.plan.rheemplan.UnarySink;
import io.rheem.core.types.DataSetType;

/**
 * Dummy sink for testing purposes.
 */
public class TestSink<T> extends UnarySink<T> {

    public TestSink(DataSetType<T> inputType) {
        super(inputType);
    }

}
