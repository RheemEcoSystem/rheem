package io.rheem.core.profiling;

import io.rheem.core.plan.executionplan.Channel;
import io.rheem.core.plan.executionplan.ExecutionPlan;
import io.rheem.core.plan.executionplan.ExecutionStage;

/**
 * Determines, which {@link Channel}s in an {@link ExecutionPlan} should be instrumented.
 */
public interface InstrumentationStrategy {

    /**
     * Mark {@link Channel}s within the {@code stage} that should be instrumented.
     *
     * @param stage that should be instrumented
     */
    void applyTo(ExecutionStage stage);
}
