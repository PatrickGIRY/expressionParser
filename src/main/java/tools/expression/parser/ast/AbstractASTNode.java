package tools.expression.parser.ast;

import java.util.Objects;

abstract sealed class AbstractASTNode implements ASTNode permits ExpressionNode, NumericLiteralNode {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractASTNode that = (AbstractASTNode) o;
        return Objects.equals(type(), that.type());
    }

    @Override
    public int hashCode() {
        return Objects.hash(type());
    }

}
