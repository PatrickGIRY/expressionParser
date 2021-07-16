package tools.expression.parser.ast;

import java.util.Objects;

public final class ExpressionNode extends AbstractASTNode {
    private final NumericLiteralNode numericLiteralNode;

    public ExpressionNode(NumericLiteralNode numericLiteralNode) {
        this.numericLiteralNode = numericLiteralNode;
    }

    @Override
    public ASTNodeType type() {
        return ASTNodeType.EXPRESSION;
    }

    public NumericLiteralNode body() {
        return numericLiteralNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExpressionNode that = (ExpressionNode) o;
        return Objects.equals(numericLiteralNode, that.numericLiteralNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numericLiteralNode);
    }

    @Override
    public String toString() {
        return "ExpressionNode{" +
                "type=" + type() +
                ", numericLiteralNode=" + numericLiteralNode +
                '}';
    }
}
