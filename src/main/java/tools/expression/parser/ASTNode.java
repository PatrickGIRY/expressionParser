package tools.expression.parser;


public sealed interface ASTNode {
    static Expression expression(NumericLiteral body) {
        return new Expression(body);
    }

    static NumericLiteral numericLiteral(int value) {
       return new NumericLiteral(value);
    }

    record Expression(NumericLiteral body) implements ASTNode {
    }

    record NumericLiteral(int value) implements ASTNode {
    }
}
