package tools.expression.parser.ast;

public record ASTNode<T>(ASTNodeType type, T value) {
}
