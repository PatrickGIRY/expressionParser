package tools.expression.parser.ast;

import java.util.Objects;

public sealed interface ASTNode {
    static Expression expression(NumericLiteral numericLiteral) {
        return new Expression(numericLiteral);
    }
    static NumericLiteral numericLiteral(int value) {
       return new NumericLiteral(value);
    }

    ASTNodeType type();

    final class Expression implements ASTNode {
        private final NumericLiteral numericLiteral;

        public Expression(NumericLiteral numericLiteral) {
            this.numericLiteral = numericLiteral;
        }

        @Override
        public ASTNodeType type() {
            return ASTNodeType.EXPRESSION;
        }

        public NumericLiteral body() {
            return numericLiteral;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            Expression that = (Expression) o;
            return type() == that.type() && Objects.equals(numericLiteral, that.numericLiteral);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type(), numericLiteral);
        }

        @Override
        public String toString() {
            return "ExpressionNode{" +
                    "type=" + type() +
                    ", numericLiteralNode=" + numericLiteral +
                    '}';
        }
    }

    final class NumericLiteral implements ASTNode {

        private final int value;

        NumericLiteral(int value) {
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
            NumericLiteral that = (NumericLiteral) o;
            return type() == that.type() && value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(type(), value);
        }

        @Override
        public String toString() {
            return "NumericLiteralNode{" +
                    "type=" + type() +
                    ", value=" + value +
                    '}';
        }
    }
}
