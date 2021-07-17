package tools.expression.parser;


public sealed interface ASTNode {
    static Expression expression(NumericLiteral body) {
        return new Expression(body);
    }

    static NumericLiteral numericLiteral(int value) {
       return new NumericLiteral(value);
    }

    ASTNodeType type();

    record Expression(NumericLiteral body) implements ASTNode {

        @Override
        public ASTNodeType type() {
            return ASTNodeType.EXPRESSION;
        }
    }

    record NumericLiteral(int value) implements ASTNode {

        @Override
        public ASTNodeType type() {
            return ASTNodeType.NUMERIC_LITERAL;
        }
    }
}
