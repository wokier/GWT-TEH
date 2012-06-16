package teh.fields;

/**
 * TEH fields Factory alternative implementation, allowing GWT compilation by
 * excluding teh.reflect package
 * 
 * @author francois wauquier
 * 
 */
public class TEHFieldsFactory {

    public static TEHFields instance = null;

    /**
     * singleton
     * 
     * @return
     */
    public static synchronized TEHFields get() {
	if (instance == null) {
	    throw new IllegalStateException("You did not called GWTEH.initServerSide");
	}
	return instance;
    }
}
