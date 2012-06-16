package teh.gwt.test;

import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

import teh.gwt.server.GWTEH;

/**
 * Rule to use for regular JUnit tests on the server side<br>
 * TestWatchman is now deprectated in JUnit
 */
public class GWTEHTestWatchmanRule extends TestWatchman {

    @Override
    public void starting(FrameworkMethod method) {
	GWTEH.initServerSide();
    }
}
