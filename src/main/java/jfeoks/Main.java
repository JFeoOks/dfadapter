package jfeoks;

import jfeoks.api.pojo.adapter.DFAdapterProcessor;
import jfeoks.model.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws NoSuchFieldException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        DFAdapterProcessor dfAdapterProcessor = context.getBean("DFAdapterProcessor", DFAdapterProcessor.class);

        HelloWorld helloWorld = new HelloWorld();
        dfAdapterProcessor.process(helloWorld);
    }
}
