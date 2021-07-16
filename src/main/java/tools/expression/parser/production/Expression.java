package tools.expression.parser.production;

import tools.expression.parser.ast.ASTNode;

import java.util.function.Function;

public interface Expression extends Function<String, ASTNode<?>> {
    static Expression create(NumericLiteral numericLiteral) {
        return numericLiteral::apply;
    }
}
