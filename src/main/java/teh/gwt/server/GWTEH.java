package teh.gwt.server;

import teh.fields.TEHFieldsFactory;
import teh.reflect.TEHFieldsReflectionExtractor;

public class GWTEH {

    public static void initServerSide() {
	TEHFieldsFactory.instance = TEHFieldsReflectionExtractor.getInstance();
    }
}
