package jfeoks.newannot.pojo.update;

import jfeoks.newannot.pojo.update.config.UpdatedAdapterConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(UpdatedAdapterConfiguration.class);

        //Something
    }
}
