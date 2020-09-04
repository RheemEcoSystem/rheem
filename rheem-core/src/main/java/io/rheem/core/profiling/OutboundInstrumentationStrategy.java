package io.rheem.core.profiling;

import io.rheem.core.plan.executionplan.Channel;
import io.rheem.core.plan.executionplan.ExecutionStage;

/**
 * Instruments only outbound {@link Channel}s.
 */
public class OutboundInstrumentationStrategy implements InstrumentationStrategy {

    @Override
    public void applyTo(ExecutionStage stage) {
        stage.getOutboundChannels().forEach(Channel::markForInstrumentation);
    }
}
