package jfeoks.newannot.pojo.update;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;

import java.util.HashMap;
import java.util.Map;


public class ExpressionPropertySource {

    private ExpressionParser parser;

    private EvaluationContext evaluationContext;

    public ExpressionPropertySource(ExpressionParser parser, EvaluationContext evaluationContext) {
        this.parser = parser;
        this.evaluationContext = evaluationContext;
    }

    private Map<String, Object> properties = new HashMap<>();

    public void put(String key, String spelExpression) {
        Expression expression = parser.parseExpression(spelExpression);
        properties.put(key, expression.getValue(evaluationContext));
    }

    public <T> T get(String key, Class<T> type) {
        Object value = properties.get(key);
        return value == null ? null : type.cast(value);
    }

    public Object get(String key) {
        return get(key, Object.class);
    }

    public int size() {
        return properties.size();
    }

    public ExpressionParser getParser() {
        return parser;
    }

    public void setParser(ExpressionParser parser) {
        this.parser = parser;
    }

    public EvaluationContext getEvaluationContext() {
        return evaluationContext;
    }

    public void setEvaluationContext(EvaluationContext evaluationContext) {
        this.evaluationContext = evaluationContext;
    }
}
