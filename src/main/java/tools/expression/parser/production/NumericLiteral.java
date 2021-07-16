package tools.expression.parser.production;

import tools.expression.parser.ast.ASTNode;
import tools.expression.parser.ast.ASTNodeType;
import tools.expression.parser.ast.NumericLiteralNode;

import java.util.function.Function;

public interface NumericLiteral extends Function<String, NumericLiteralNode> {
    static NumericLiteral create() {
        return text -> ASTNode.numericLiteral(Integer.parseInt(text));
    }
}
