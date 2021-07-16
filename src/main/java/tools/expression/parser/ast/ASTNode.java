package tools.expression.parser.ast;

public sealed interface ASTNode<T> permits NumericLiteralNode {
    static NumericLiteralNode numericLiteral(int value ) {
       return new NumericLiteralNode(value);
    }

    ASTNodeType type();
    T value();
}
