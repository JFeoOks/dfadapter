package jfeoks.newannot.pojo.update.provider.pojo;

import jfeoks.newannot.pojo.nested.DFParam;
import jfeoks.newannot.pojo.update.annotation.DFBidirectionalParamAdapter;
import jfeoks.newannot.pojo.update.annotation.ExcludeDFParam;
import jfeoks.newannot.pojo.update.annotation.IncludeAllDFParams;
import jfeoks.newannot.pojo.update.annotation.PropertyValue;

@IncludeAllDFParams
public class RequestDefaultTestPojo {

    @DFParam(name = "annotated_hello_field")
    @DFBidirectionalParamAdapter(
            readAdapterClass = MockReadAdapter.class,
            writeAdapterClass = MockWriterAdapter.class,
            propertyValues = {
                    @PropertyValue(name = "hello", spelExpression = "'Hello World!'")
            }
    )
    private String annotatedHelloStringField = "Holly field";

    @DFBidirectionalParamAdapter(
            readAdapterClass = MockReadAdapter.class,
            writeAdapterClass = MockWriterAdapter.class,
            propertyValues = {
                    @PropertyValue(name = "hello", spelExpression = "'Bye World!'")
            }
    )
    private String annotatedByeStringField = "Holly field";

    private String includedString = "Included field";

    @ExcludeDFParam
    private String fakeField;

    private String methodStringField = "Holly method";

    @DFParam(name = "annotated_overrided_string_method")
    @DFBidirectionalParamAdapter(
            readAdapterClass = MockReadAdapter.class,
            writeAdapterClass = MockWriterAdapter.class,
            propertyValues = {
                    @PropertyValue(name = "hello", spelExpression = "'Hello Overrided World!'")
            }
    )
    public String getMethodStringField() {
        return methodStringField;
    }

    @DFParam(name = "annotated_field_with_not_annotated_string_method")
    @DFBidirectionalParamAdapter(
            readAdapterClass = MockReadAdapter.class,
            writeAdapterClass = MockWriterAdapter.class,
            propertyValues = {
                    @PropertyValue(name = "hello", spelExpression = "'Hello World!'")
            }
    )
    private String annotatedFieldWithNotAnnotatedMethod = "Holly field with not annotated method!";

    public String getAnnotatedFieldWithNotAnnotatedMethod() {
        return annotatedFieldWithNotAnnotatedMethod;
    }

    public void setMethodStringField(String methodStringField) {
        this.methodStringField = methodStringField;
    }

    public String fakeMethod() {
        return null;
    }
}
