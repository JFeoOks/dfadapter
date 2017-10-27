package jfeoks.old.api.pojo;

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

    private Map<String, String> properties = new HashMap<>();

    public void put(String key, String spelExpression) {
        properties.put(key, spelExpression);
    }

    public <T> T get(String key, Class<T> type) {
        String expressionString = properties.get(key);
        return evaluate(expressionString, type);
    }

    public Object get(String key) {
        String expressionString = properties.get(key);
        return evaluate(expressionString, Object.class);
    }

    private <T> T evaluate(final String expressionString, Class<T> resultType) {
        Expression expression = parser.parseExpression(expressionString);
        return expression.getValue(evaluationContext, resultType);
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
