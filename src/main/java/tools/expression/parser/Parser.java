package tools.expression.parser;

import tools.expression.parser.ast.ExpressionNode;
import tools.expression.parser.production.Expression;
import tools.expression.parser.production.NumericLiteral;

@FunctionalInterface
public interface Parser {
    static Parser create() {
        final var  expression = Expression.create(
                NumericLiteral.create()
        );
        return expression::apply;
    }

   ExpressionNode parse(String text);
}
