package teh.gwt.shared;

import java.io.Serializable;

import teh.annotations.TEH;
import teh.fields.TEHFields;
import teh.utils.TEHObject;

import com.google.gwt.core.client.GWT;

/**
 * gwt-teh Object <br>
 * to extends to activate gwt-teh power
 * 
 * @author francois wauquier
 * 
 */
@TEH
public abstract class GWTEHObject extends TEHObject implements Serializable {

    @Override
    protected TEHFields getTEHFields() {
	if (GWT.isClient()) {
	    return createTEHFields();
	}
	return super.getTEHFields();
    }

    /**
     * Just implements this mehtod by using GWT.create(YourClass.class)<br>
     * No, it is not possible to do this automatically due to GWT Code
     * generation restrictions
     * 
     * @return
     */
    protected abstract TEHFields createTEHFields();

}
