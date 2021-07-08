package tools.expression.parser;

import tools.expression.domain.Value;

import java.util.Optional;

public interface ExpressionParser {
    static ExpressionParser create(ValueParser valueParser) {
        return valueParser::tryParse;
    }

    Optional<Value> tryParse(String expression);
}
