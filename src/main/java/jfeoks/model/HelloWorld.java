package jfeoks.model;

import jfeoks.annotation.DFParamAdapter;
import jfeoks.annotation.PropertyValue;
import jfeoks.api.pojo.adapter.impl.HelloWorldDataFlowParameterAdapter;

public class HelloWorld {

    @DFParamAdapter(
            adapterClass = HelloWorldDataFlowParameterAdapter.class,
            propertyValues = {
                    @PropertyValue(name = "helloWorldProperty", spelExpression = "'Hello World'"),
                    @PropertyValue(name = "smile", spelExpression = "@smile")
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
