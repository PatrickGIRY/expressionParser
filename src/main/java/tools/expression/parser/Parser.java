package tools.expression.parser;

import tools.expression.parser.ast.ASTNode;
import tools.expression.parser.production.NumericLiteral;

@FunctionalInterface
public interface Parser {
    static Parser create() {
        final var  expression = tools.expression.parser.production.Expression.create(
                NumericLiteral.create()
        );
        return expression::apply;
    }

   ASTNode.Expression parse(String text);
}
