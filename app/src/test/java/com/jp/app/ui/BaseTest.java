package com.jp.app.ui;

import com.jp.app.BuildConfig;
import com.jp.app.SampleApplication;
import com.jp.app.common.controller.BaseActivity;
import com.jp.app.common.view.BaseFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = SampleApplication.class, packageName = "com.jp.app")
public abstract class BaseTest<A extends BaseActivity, F extends BaseFragment> {

    @Rule
    public TestRule mInjectMocksRule = new TestRule() {
        @Override
        public Statement apply(Statement base, Description description) {
            MockitoAnnotations.initMocks(BaseTest.this);
            return base;
        }
    };

    @Rule
    public TestRule mImmediateSchedulersRule = new ImmediateSchedulersRule();


    private class ImmediateSchedulersRule implements TestRule {
        @Override
        public Statement apply(final Statement base, Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
                        @Override
                        public Scheduler apply(Scheduler scheduler) throws Exception {
                            return Schedulers.trampoline();
                        }
                    });
                    RxJavaPlugins.setComputationSchedulerHandler(new Function<Scheduler, Scheduler>() {
                        @Override
                        public Scheduler apply(Scheduler scheduler) throws Exception {
                            return Schedulers.trampoline();
                        }
                    });
                    RxJavaPlugins.setNewThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
                        @Override
                        public Scheduler apply(Scheduler scheduler) throws Exception {
                            return Schedulers.trampoline();
                        }
                    });

                    RxAndroidPlugins.setMainThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
                        @Override
                        public Scheduler apply(Scheduler scheduler) throws Exception {
                              return Schedulers.trampoline();
                        }
                    });

                    try {
                        base.evaluate();
                    }
                    finally {
                        RxJavaPlugins.reset();
                        RxAndroidPlugins.reset();
                    }
                }
            };
        }
    }

    @Test
    public abstract void controlViews ();

}
