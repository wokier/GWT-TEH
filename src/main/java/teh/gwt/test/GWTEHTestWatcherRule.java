package teh.gwt.test;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import teh.gwt.server.GWTEH;

/**
 * Rule to use for regular JUnit tests on the server side<br>
 * Prefered way
 */
public class GWTEHTestWatcherRule extends TestWatcher {

    @Override
    protected void starting(Description description) {
	GWTEH.initServerSide();
    }
}
