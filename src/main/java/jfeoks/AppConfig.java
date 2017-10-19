package jfeoks;

import jfeoks.api.pojo.PropertySource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AppConfig {

    @Bean
    public ExpressionParser getExpressionParser() {
        return new SpelExpressionParser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Autowired
    public PropertySource getPropertySource(ExpressionParser parser, EvaluationContext evaluationContext) {
        return new PropertySource(parser, evaluationContext);
    }

    @Bean
    @Autowired
    public EvaluationContext getStandardEvaluationContext(BeanFactory beanFactory) {
        StandardEvaluationContext evContext = new StandardEvaluationContext();
        evContext.setBeanResolver(new BeanFactoryResolver(beanFactory));

        return evContext;
    }
}
