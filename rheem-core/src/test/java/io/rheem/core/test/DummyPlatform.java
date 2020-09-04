package io.rheem.core.test;

import io.rheem.core.api.Configuration;
import io.rheem.core.optimizer.costs.LoadProfile;
import io.rheem.core.optimizer.costs.LoadProfileToTimeConverter;
import io.rheem.core.optimizer.costs.TimeEstimate;
import io.rheem.core.optimizer.costs.TimeToCostConverter;
import io.rheem.core.platform.Executor;
import io.rheem.core.platform.Platform;

/**
 * {@link Platform} implementation for test purposes.
 */
public class DummyPlatform extends Platform {

    private static DummyPlatform INSTANCE;

    private DummyPlatform() {
        super("Dummy Platform", "dummy");
    }

    public static DummyPlatform getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DummyPlatform();
        }
        return INSTANCE;
    }

    @Override
    public void configureDefaults(Configuration configuration) {
    }

    @Override
    public Executor.Factory getExecutorFactory() {
        throw new UnsupportedOperationException();
    }

    @Override
    public LoadProfileToTimeConverter createLoadProfileToTimeConverter(Configuration configuration) {
        return new LoadProfileToTimeConverter(null, null, null) {
            @Override
            public TimeEstimate convert(LoadProfile loadProfile) {
                return new TimeEstimate(
                        loadProfile.getCpuUsage().getLowerEstimate(),
                        loadProfile.getCpuUsage().getUpperEstimate(),
                        0.9d
                );
            }
        };
    }

    @Override
    public TimeToCostConverter createTimeToCostConverter(Configuration configuration) {
        return new TimeToCostConverter(0d, 1d);
    }
}
