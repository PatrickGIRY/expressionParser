package tools.expression.parser.production;

import tools.expression.parser.ast.ASTNode;
import tools.expression.parser.ast.ExpressionNode;

import java.util.function.Function;

public interface Expression extends Function<String, ExpressionNode> {
    static Expression create(NumericLiteral numericLiteral) {
        return text -> ASTNode.expression(numericLiteral.apply(text));
    }
}
