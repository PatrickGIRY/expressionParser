package tools.expression.parser.production;

import tools.expression.parser.ast.ASTNode;
import tools.expression.parser.ast.ASTNodeType;

import java.util.function.Function;

public interface NumericLiteral extends Function<String, ASTNode<?>> {
    static NumericLiteral create() {
        return text -> ASTNode.numericLiteral(Integer.parseInt(text));
    }
}
