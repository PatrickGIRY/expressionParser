package tools.expression.parser.ast;

import java.util.Objects;

public final class NumericLiteralNode implements ASTNode<Integer> {

    private final int value;

    NumericLiteralNode(int value) {
        this.value = value;
    }

    @Override
    public ASTNodeType type() {
        return ASTNodeType.NUMERIC_LITERAL;
    }

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumericLiteralNode that = (NumericLiteralNode) o;
        return value == that.value && Objects.equals(type(), that.type());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type());
    }

    @Override
    public String toString() {
        return "NumericLiteralNode{" +
                "type=" + type() +
                ", value=" + value +
                '}';
    }
}
