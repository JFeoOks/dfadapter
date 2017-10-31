package jfeoks.newannot.pojo.update.provider.pojo;

import jfeoks.newannot.pojo.nested.DFParam;
import jfeoks.newannot.pojo.update.annotation.DFBidirectionalParamAdapter;
import jfeoks.newannot.pojo.update.annotation.PropertyValue;

public class ResponseAnnotatedTestPojo {

    @DFParam(name = "annotated_response_declared_only_field")
    @DFBidirectionalParamAdapter(
            readAdapterClass = MockReadAdapter.class,
            writeAdapterClass = MockWriterAdapter.class,
            propertyValues = {
                    @PropertyValue(name = "hello", spelExpression = "'Fake field!'")
            }
    )
    public String declaredOnlyField = "declaredOnlyField";

    public String fakeField = "fakeField";

    public String declaredOnlyMethod = "declaredOnlyMethod";

    @DFParam(name = "annotated_response_declared_only_method")
    @DFBidirectionalParamAdapter(
            readAdapterClass = MockReadAdapter.class,
            writeAdapterClass = MockWriterAdapter.class,
            propertyValues = {
                    @PropertyValue(name = "hello", spelExpression = "'Fake method!'")
            }
    )
    public String getDeclaredOnlyMethod() {
        return declaredOnlyMethod;
    }

    public void setDeclaredOnlyMethod(String declaredOnlyMethod) {
        this.declaredOnlyMethod = declaredOnlyMethod;
    }

    public String fakeGetter() {
        return null;
    }
}
