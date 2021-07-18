package tools.expression.parser;


import java.util.Arrays;
import java.util.Objects;

public sealed interface ASTNode {
    static BinaryExpression binaryExpression(Expression left, Operator operator, Expression right) {
        return new BinaryExpression(left, operator, right);
    }

    static NumericLiteral numericLiteral(int value) {
       return new NumericLiteral(value);
    }

    static Operator operator(String sign) {
        return Arrays.stream(Operator.values()).filter(operator -> Objects.equals(operator.sign, sign))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unexpected sign '" + sign + "'"));
    }

    sealed interface Expression extends ASTNode { }

    record BinaryExpression(Expression left, Operator operator, Expression right) implements Expression {}

    record NumericLiteral(int value) implements Expression {}

    enum Operator {
        ADDITION("+"),
        MULTIPLICATION("*");

        public final String sign;

        Operator(String sign) {
            this.sign = sign;
        }
    }
}
