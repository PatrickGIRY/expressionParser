package tools.expression.parser;

import java.util.function.Function;

public interface Expression extends Function<String, ASTNode<?>> {
    static Expression create(NumericLiteral numericLiteral) {
        return numericLiteral::apply;
    }
}
