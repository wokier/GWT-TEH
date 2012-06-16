package teh.gwt.client;

import teh.gwt.shared.test.GWTEHTestObject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

public class GWTEHClientGwtTest extends GWTTestCase {

    @Override
    public String getModuleName() {
	return "teh.TEH";
    }

    public void testGWT() throws Exception {
	assertTrue(GWT.isClient());
    }

    public void testToString() {
	GWTEHTestObject object = new GWTEHTestObject("a", "b");
	String toString = object.toString();
	assertTrue(toString, toString.contains(GWTEHTestObject.class.getName()));
	assertTrue(toString, toString.contains("attribute=a"));
	assertTrue(toString, toString.contains("privateField=b"));
    }

    public void testEquals() {
	assertTrue(new GWTEHTestObject("a", "b").equals(new GWTEHTestObject("a", "b")));
	assertFalse(new GWTEHTestObject("a", "b").equals(new GWTEHTestObject("a", "c")));
    }

    public void testAssertEquals() {
	assertEquals(new GWTEHTestObject("a", "b"), new GWTEHTestObject("a", "b"));
    }

    public void testHashCode() {
	assertTrue(new GWTEHTestObject("a", "b").hashCode() == new GWTEHTestObject("a", "b").hashCode());
	assertFalse(new GWTEHTestObject("a", "b").hashCode() == new GWTEHTestObject("b", "b").hashCode());
    }

}
