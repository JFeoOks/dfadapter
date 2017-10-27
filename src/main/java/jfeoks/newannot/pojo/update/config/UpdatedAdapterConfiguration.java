package jfeoks.newannot.pojo.update.config;

import jfeoks.newannot.pojo.update.ExpressionPropertySource;
import jfeoks.newannot.pojo.update.POJOProcessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@Configuration
@ComponentScan(basePackages = "jfeoks")
public class UpdatedAdapterConfiguration {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ExpressionPropertySource expressionPropertySourceBuilder(ExpressionParser parser, EvaluationContext evaluationContext) {
        return new ExpressionPropertySource(parser, evaluationContext);
    }

    @Bean
    public StandardEvaluationContext standardEvaluationContext(BeanFactory beanFactory) {
        StandardEvaluationContext evContext = new StandardEvaluationContext();
        evContext.setBeanResolver(new BeanFactoryResolver(beanFactory));

        return evContext;
    }

    @Bean
    public ExpressionParser expressionParser() {
        return new SpelExpressionParser();
    }

    @Bean
    public POJOProcessor pojoProcessor() {
        return new POJOProcessor();
    }
}
