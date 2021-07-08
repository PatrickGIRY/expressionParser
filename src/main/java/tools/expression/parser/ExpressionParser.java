package tools.expression.parser;

import tools.expression.domain.Value;

import java.util.Optional;

public interface ExpressionParser {
    static ExpressionParser create() {
        return input -> Optional.of(new Value(2));
    }

    Optional<Value> tryParse(String expression);
}
