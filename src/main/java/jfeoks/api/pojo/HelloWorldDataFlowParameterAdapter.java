package jfeoks.api.pojo;

public class HelloWorldDataFlowParameterAdapter implements DataFlowParameterAdapter<String, String> {
    private static int count = 0;

    @Override
    public String convert(String source, PropertySource props) {
        String helloWorldProperty = props.get("helloWorldProperty", String.class);
        return source + ": " + helloWorldProperty +  " " + count++;
    }
}
