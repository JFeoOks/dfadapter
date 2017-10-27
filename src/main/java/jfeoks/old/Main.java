package jfeoks.old;

import jfeoks.old.api.pojo.adapter.DFAdapterProcessorOld;
import jfeoks.old.model.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws NoSuchFieldException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        DFAdapterProcessorOld dfAdapterProcessorOld = context.getBean("DFAdapterProcessorOld", DFAdapterProcessorOld.class);

        HelloWorld helloWorld = new HelloWorld();
        dfAdapterProcessorOld.process(helloWorld);
    }
}
