package tools.expression.parser;

public record ASTNode<T>(ProductionType numericLiteral, T value) {
}
