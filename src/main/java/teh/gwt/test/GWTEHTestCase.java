package teh.gwt.test;

import junit.framework.TestCase;
import teh.gwt.server.GWTEH;

/**
 * JUnit Test Case<br>
 * It is not recomended to use TestCase inheritance
 */
public abstract class GWTEHTestCase extends TestCase {

    @Override
    protected void setUp() throws Exception {
	super.setUp();
	GWTEH.initServerSide();
    }

}
