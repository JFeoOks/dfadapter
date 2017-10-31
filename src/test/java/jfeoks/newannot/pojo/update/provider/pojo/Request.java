package jfeoks.newannot.pojo.update.provider.pojo;

import jfeoks.newannot.pojo.nested.DFParam;
import jfeoks.newannot.pojo.update.annotation.DFBidirectionalParamAdapter;

public class Request {

    @DFParam(name = "annotated_string_field")
    @DFBidirectionalParamAdapter(
            readAdapterClass = MockReadAdapter.class,
            writeAdapterClass = MockWriterAdapter.class
    )
    private String annotatedStringField = "Right Field";
}
