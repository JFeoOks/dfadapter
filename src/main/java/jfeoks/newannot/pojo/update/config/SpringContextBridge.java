package jfeoks.newannot.pojo.update.config;

import jfeoks.newannot.pojo.update.ExpressionPropertySource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringContextBridge implements SpringContextBridgedServices, ApplicationListener<ContextRefreshedEvent> {

    private static volatile SpringContextBridge contextBridge;

    @Autowired
    private ExpressionPropertySource expressionPropertySource;

    @Autowired
    private StandardEvaluationContext evaluationContext;

    @Autowired
    private ExpressionParser parser;

    private ExpressionPropertySource expressionPropertySourceCustom;

    @Override
    public ExpressionPropertySource getExpressionPropertySource() {
        return new ExpressionPropertySource(parser, evaluationContext);
    }

    public static SpringContextBridgedServices services() {
        return contextBridge;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        SpringContextBridge.contextBridge = this;
    }
}
