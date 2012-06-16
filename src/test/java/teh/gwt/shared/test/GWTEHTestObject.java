package teh.gwt.shared.test;

import teh.annotations.TEH;
import teh.annotations.ToStringEquals;
import teh.annotations.ToStringEqualsHashCode;
import teh.fields.TEHFields;
import teh.gwt.shared.GWTEHObject;

import com.google.gwt.core.client.GWT;

@TEH
public class GWTEHTestObject extends GWTEHObject {
    @ToStringEqualsHashCode
    public String attribute;

    @ToStringEquals
    private String privateField;

    public GWTEHTestObject() {
	super();
    }

    public GWTEHTestObject(String attribute, String privateField) {
	super();
	this.attribute = attribute;
	this.privateField = privateField;
    }

    @Override
    protected TEHFields createTEHFields() {
	return GWT.create(GWTEHTestObject.class);
    }

    public String getPrivateField() {
	return privateField;
    }

    public void setPrivateField(String privateField) {
	this.privateField = privateField;
    }

}