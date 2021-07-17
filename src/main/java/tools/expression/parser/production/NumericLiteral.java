package tools.expression.parser.production;

import tools.expression.parser.ast.ASTNode;

import java.util.function.Function;

public interface NumericLiteral extends Function<String, ASTNode.NumericLiteral> {
    static NumericLiteral create() {
        return text -> ASTNode.numericLiteral(Integer.parseInt(text));
    }
}
