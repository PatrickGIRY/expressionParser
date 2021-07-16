package tools.expression.parser.ast;

import java.util.Objects;

public final class NumericLiteralNode extends AbstractASTNode {

    private final int value;

    NumericLiteralNode(int value) {
        this.value = value;
    }

    @Override
    public ASTNodeType type() {
        return ASTNodeType.NUMERIC_LITERAL;
    }
    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NumericLiteralNode that = (NumericLiteralNode) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    @Override
    public String toString() {
        return "NumericLiteralNode{" +
                "type=" + type() +
                ", value=" + value +
                '}';
    }
}
