package tools.expression.parser.ast;

public sealed interface ASTNode permits AbstractASTNode {
    static ExpressionNode expression(NumericLiteralNode numericLiteralNode) {
        return new ExpressionNode(numericLiteralNode);
    }
    static NumericLiteralNode numericLiteral(int value) {
       return new NumericLiteralNode(value);
    }

    ASTNodeType type();
}
