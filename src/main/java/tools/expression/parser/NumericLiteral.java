package tools.expression.parser;

import java.util.function.Function;

public interface NumericLiteral extends Function<String, ASTNode<?>> {
    static NumericLiteral create() {
        return text -> new ASTNode<>(ProductionType.NUMERIC_LITERAL, Integer.valueOf(text));
    }
}
