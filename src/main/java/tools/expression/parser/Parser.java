package tools.expression.parser;

@FunctionalInterface
public interface Parser {
    static Parser create() {
       return Parser::expression;
    }

    ASTNode.Expression parse(String text);

    private static ASTNode.Expression expression(String text) {
        return  ASTNode.expression(numericLiteral(text));
    }

    private static ASTNode.NumericLiteral numericLiteral(String text) {
        return ASTNode.numericLiteral(Integer.parseInt(text));
    }
}
