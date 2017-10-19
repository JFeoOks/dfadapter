package jfeoks.model;

import jfeoks.annotation.DFParamAdapter;
import jfeoks.annotation.PropertyValue;
import jfeoks.api.pojo.HelloWorldDataFlowParameterAdapter;

public class HelloWorld {

    @DFParamAdapter(
            adapterClass = HelloWorldDataFlowParameterAdapter.class,
            propertyValues = {
                    @PropertyValue(name = "helloWorldProperty", spelExpression = "'Hello World'")
            }
    )
    public String helloField;

    public String getHelloField() {
        return helloField;
    }

    public void setHelloField(String helloField) {
        this.helloField = helloField;
    }
}
