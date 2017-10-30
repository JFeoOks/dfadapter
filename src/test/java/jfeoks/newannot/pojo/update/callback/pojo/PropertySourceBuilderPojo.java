package jfeoks.newannot.pojo.update.callback.pojo;

import jfeoks.newannot.pojo.update.annotation.DFBidirectionalParamAdapter;
import jfeoks.newannot.pojo.update.annotation.PropertyValue;

public class PropertySourceBuilderPojo {

    @DFBidirectionalParamAdapter(
            readAdapterClass = MockReadAdapter.class,
            writeAdapterClass = MockWriterAdapter.class,
            propertyValues = {
                    @PropertyValue(name = "hello string", spelExpression = "'Hello World!'"),
                    @PropertyValue(name = "bye string", spelExpression = "'Bye World!'"),
                    @PropertyValue(name = "bean test", spelExpression = "@propertySourceBuilderPojo")
            }
    )
    private String testString = "test";

}
