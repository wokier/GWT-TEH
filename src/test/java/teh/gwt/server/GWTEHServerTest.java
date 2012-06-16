package teh.gwt.server;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import junit.runner.Version;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import teh.gwt.shared.test.GWTEHTestObject;
import teh.gwt.test.GWTEHTestWatcherRule;

import com.google.gwt.core.client.GWT;

public class GWTEHServerTest {

    @Rule
    public TestRule gwteh = new GWTEHTestWatcherRule();

    @Test
    public void testJUnitVersion() throws Exception {
	assertEquals("4.10", Version.id());
    }

    @Test
    public void testGWT() throws Exception {
	assertFalse(GWT.isClient());
    }

    @Test
    public void testToStringUtils() throws Exception {
	GWTEHTestObject object = new GWTEHTestObject("a", "b");
	String toString = object.toString();
	assertTrue(toString, toString.contains(GWTEHTestObject.class.getName()));
	assertTrue(toString, toString.contains("attribute=a"));
	assertTrue(toString, toString.contains("privateField=b"));
    }

    @Test
    public void testEquals() {
	assertTrue(new GWTEHTestObject("a", "b").equals(new GWTEHTestObject("a", "b")));
	assertFalse(new GWTEHTestObject("a", "b").equals(new GWTEHTestObject("a", "c")));
    }

    @Test
    public void testAssertEquals() {
	assertEquals(new GWTEHTestObject("a", "b"), new GWTEHTestObject("a", "b"));
    }

    @Test
    public void testHashCode() {
	assertTrue(new GWTEHTestObject("a", "b").hashCode() == new GWTEHTestObject("a", "b").hashCode());
	assertFalse(new GWTEHTestObject("a", "b").hashCode() == new GWTEHTestObject("b", "b").hashCode());
    }
}
