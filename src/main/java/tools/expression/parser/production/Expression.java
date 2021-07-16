package tools.expression.parser.production;

import tools.expression.parser.ast.NumericLiteralNode;

import java.util.function.Function;

public interface Expression extends Function<String, NumericLiteralNode> {
    static Expression create(NumericLiteral numericLiteral) {
        return numericLiteral::apply;
    }
}
