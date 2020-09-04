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
public class DummyExternalReusableChannel extends Channel {

    public static final ChannelDescriptor DESCRIPTOR = new ChannelDescriptor(
            DummyExternalReusableChannel.class,
            true,
            true
    );

    public DummyExternalReusableChannel(ChannelDescriptor descriptor, OutputSlot<?> producerSlot) {
        super(descriptor, producerSlot);
        assert DESCRIPTOR == descriptor;
    }

    public DummyExternalReusableChannel(Channel original) {
        super(original);
    }

    @Override
    public DummyExternalReusableChannel copy() {
        return new DummyExternalReusableChannel(this);
    }

    @Override
    public ChannelInstance createInstance(Executor executor,
                                          OptimizationContext.OperatorContext producerOperatorContext,
                                          int producerOutputIndex) {
        throw new UnsupportedOperationException();
    }
}
