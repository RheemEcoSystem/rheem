package io.rheem.core.test;

import io.rheem.core.optimizer.OptimizationContext;
import io.rheem.core.plan.executionplan.Channel;
import io.rheem.core.plan.rheemplan.OutputSlot;
import io.rheem.core.platform.ChannelDescriptor;
import io.rheem.core.platform.ChannelInstance;
import io.rheem.core.platform.Executor;

/**
 * Dummy {@link Channel}.
 */
public class DummyNonReusableChannel extends Channel {

    public static final ChannelDescriptor DESCRIPTOR = new ChannelDescriptor(
            DummyNonReusableChannel.class,
            false,
            false
    );

    public DummyNonReusableChannel(ChannelDescriptor descriptor, OutputSlot<?> producerSlot) {
        super(descriptor, producerSlot);
        assert DESCRIPTOR == descriptor;
    }

    public DummyNonReusableChannel(Channel original) {
        super(original);
    }

    @Override
    public DummyNonReusableChannel copy() {
        return new DummyNonReusableChannel(this);
    }

    @Override
    public ChannelInstance createInstance(Executor executor,
                                          OptimizationContext.OperatorContext producerOperatorContext,
                                          int producerOutputIndex) {
        throw new UnsupportedOperationException();
    }
}
