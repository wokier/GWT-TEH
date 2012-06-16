package teh.gwt.rebind;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import teh.annotations.ToString;
import teh.annotations.ToStringEquals;
import teh.annotations.ToStringEqualsHashCode;
import teh.fields.TEHFields;
import teh.gwt.shared.GWTEHObject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.TreeLogger.Type;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.thirdparty.guava.common.base.Joiner;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

/**
 * GWTEHObject client TEHFields Generator
 * 
 * @author francois wauquier
 * 
 */
public class GWTEHObjectGenerator extends Generator {

    @Override
    public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {
	TypeOracle typeOracle = context.getTypeOracle();
	JClassType classType = typeOracle.findType(typeName);

	String packageName = classType.getPackage().getName();
	String simpleName = classType.getSimpleSourceName() + "_TEHFields";
	TreeLogger branchLogger = logger.branch(Type.INFO, "Generating " + simpleName + " ...");

	ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(packageName, simpleName);
	composer.setSuperclass(typeName);
	composer.addImplementedInterface(TEHFields.class.getName());
	composer.addImport(TEHFields.class.getName());
	composer.addImport(GWT.class.getName());
	composer.addImport(GWTEHObject.class.getName());
	composer.addImport(List.class.getName());
	composer.addImport(ArrayList.class.getName());
	composer.addImport(Arrays.class.getName());
	composer.addImport(Map.class.getName());
	composer.addImport(HashMap.class.getName());
	PrintWriter printWriter = context.tryCreate(logger, packageName, simpleName);
	if (printWriter == null) {
	    branchLogger.log(Type.INFO, "Already generated");
	    return packageName + "." + simpleName;
	}

	SourceWriter source = composer.createSourceWriter(context, printWriter);

	generateIsTEHActivated(classType, source, branchLogger);
	generateGetFieldValue(classType, source, branchLogger);
	generateExtractToStringFieldValues(classType, source, branchLogger);
	generateExtractEqualsFieldValues(classType, source, branchLogger);
	generateExtractHashCodeValues(classType, source, branchLogger);

	branchLogger.log(Type.INFO, "OK");
	source.commit(logger);

	return composer.getCreatedClassName();
    }

    private void generateIsTEHActivated(JClassType classType, SourceWriter source, TreeLogger branchLogger) {
	source.println("public boolean isTEHActivated(Object object) {");
	source.indent();
	source.println("return true;");
	source.outdent();
	source.println("}");
    }

    private void generateGetFieldValue(JClassType classType, SourceWriter source, TreeLogger logger) {
	source.println("private Object getFieldValue(Object object, String fieldName) {");
	source.indent();
	source.println("if (fieldName == null){");
	source.indent();
	source.println("return null;");
	source.outdent();
	source.println("}");
	source.println(classType.getName() + " casted = (" + classType.getName() + ") object;");
	JField[] fields = classType.getFields();
	for (int i = 0; i < fields.length; i++) {
	    String fieldName = fields[i].getName();
	    source.println("if (fieldName.equals (\"" + fieldName + "\")) {");
	    source.indent();
	    if (!fields[i].isPrivate()) {
		source.println("return casted." + fieldName + ";");
	    } else {
		try {
		    String getterMethodName = "get" + StringUtils.capitalize(fieldName);
		    classType.getMethod(getterMethodName, new JType[0]);
		    source.println("return casted." + getterMethodName + "();");
		} catch (NotFoundException e) {
		    logger.log(Type.WARN, "The field " + fieldName + " has @ToStringEqualsHashCode or @ToStringEquals or @ToString annotation, but was not visible.");
		}
	    }
	    source.outdent();
	    source.println("}");
	}
	source.println("return null;");
	source.outdent();
	source.println("}");
    }

    private void generateExtractToStringFieldValues(JClassType classType, SourceWriter source, TreeLogger logger) {
	logger.log(Type.INFO, "Generating ToString...");
	source.println("public List<TEHFieldValue> extractToStringFieldValues(Object object){");
	source.indent();
	List<String> toStringFieldNames = new ArrayList<String>();
	JField[] fields = classType.getFields();
	for (int i = 0; i < fields.length; i++) {
	    if (fields[i].isAnnotationPresent(ToString.class) || fields[i].isAnnotationPresent(ToStringEquals.class) || fields[i].isAnnotationPresent(ToStringEqualsHashCode.class)) {
		toStringFieldNames.add(fields[i].getName());
	    }
	}
	source.println("List<String> toStringFieldNames = Arrays.asList(\"" + Joiner.on("\",\"").join(toStringFieldNames) + "\");");
	source.println("List toStringFieldValues = new ArrayList();");
	source.println("for (String toStringFieldName : toStringFieldNames) {");
	source.indent();
	source.println("toStringFieldValues.add(new TEHFields.TEHFieldValue(toStringFieldName, getFieldValue(object, toStringFieldName)));");
	source.outdent();
	source.println("}");
	source.println("return toStringFieldValues;");
	source.outdent();
	source.println("}");
    }

    private void generateExtractEqualsFieldValues(JClassType classType, SourceWriter source, TreeLogger logger) {
	logger.log(Type.INFO, "Generating Equals...");
	source.println("public Map<String, TEHFieldValue> extractEqualsFieldValues(Object object) {");
	source.indent();
	List<String> equalsFieldNames = new ArrayList<String>();
	JField[] fields = classType.getFields();
	for (int i = 0; i < fields.length; i++) {
	    if (fields[i].isAnnotationPresent(ToStringEquals.class) || fields[i].isAnnotationPresent(ToStringEqualsHashCode.class)) {
		equalsFieldNames.add(fields[i].getName());
	    }
	}
	source.println("List<String> equalsFieldNames = Arrays.asList(\"" + Joiner.on("\",\"").join(equalsFieldNames) + "\");");
	source.println("Map equalsFieldValues = new HashMap();");
	source.println("for (String equalsFieldName : equalsFieldNames) {");
	source.indent();
	source.println("equalsFieldValues.put(equalsFieldName,new TEHFields.TEHFieldValue(equalsFieldName, getFieldValue(object, equalsFieldName)));");
	source.outdent();
	source.println("}");
	source.println("return equalsFieldValues;");
	source.outdent();
	source.println("}");
    }

    private void generateExtractHashCodeValues(JClassType classType, SourceWriter source, TreeLogger logger) {
	logger.log(Type.INFO, "Generating HashCode...");
	source.println("public List<Object> extractHashCodeValues(Object object){");
	source.indent();
	List<String> hashcodeFieldNames = new ArrayList<String>();
	JField[] fields = classType.getFields();
	for (int i = 0; i < fields.length; i++) {
	    if (fields[i].isAnnotationPresent(ToStringEqualsHashCode.class)) {
		hashcodeFieldNames.add(fields[i].getName());
	    }
	}
	source.println("List<String> hashcodeFieldNames = Arrays.asList(\"" + Joiner.on("\",\"").join(hashcodeFieldNames) + "\");");
	source.println("List hashcodeValues = new ArrayList();");
	source.println("for (String hashcodeFieldName : hashcodeFieldNames) {");
	source.indent();
	source.println("hashcodeValues.add(getFieldValue(object, hashcodeFieldName));");
	source.outdent();
	source.println("}");
	source.println("return hashcodeValues;");
	source.outdent();
	source.println("}");
    }

}
