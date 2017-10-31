package jfeoks.newannot.pojo.update.provider.pojo;

import jfeoks.newannot.pojo.nested.DFParam;
import jfeoks.newannot.pojo.update.annotation.DFBidirectionalParamAdapter;
import jfeoks.newannot.pojo.update.annotation.PropertyValue;

public class RequestAnnotatedTestPojo {

    @DFParam(name = "annotated_hello_field")
    @DFBidirectionalParamAdapter(
            readAdapterClass = MockReadAdapter.class,
            writeAdapterClass = MockWriterAdapter.class,
            propertyValues = {
                    @PropertyValue(name = "hello", spelExpression = "'Hello World!'")
            }
    )
    private String annotatedHelloStringField = "Holly field";

    @DFParam(name = "annotated_bye_field")
    @DFBidirectionalParamAdapter(
            readAdapterClass = MockReadAdapter.class,
            writeAdapterClass = MockWriterAdapter.class,
            propertyValues = {
                    @PropertyValue(name = "hello", spelExpression = "'Bye World!'")
            }
    )
    private String annotatedByeStringField = "Holly field";

    private String fakeField;

    private String methodStringField = "Holly method";

    @DFParam(name = "annotated_string_method")
    @DFBidirectionalParamAdapter(
            readAdapterClass = MockReadAdapter.class,
            writeAdapterClass = MockWriterAdapter.class,
            propertyValues = {
                    @PropertyValue(name = "hello", spelExpression = "'Hello World!'")
            }
    )
    public String getMethodStringField() {
        return methodStringField;
    }

    public void setMethodStringField(String methodStringField) {
        this.methodStringField = methodStringField;
    }

    public String fakeMethod() {
        return null;
    }

}
