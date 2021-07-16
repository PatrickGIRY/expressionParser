package tools.expression.parser;

@FunctionalInterface
public interface Parser {
    static Parser create() {
        final var  expression = Expression.create(
                NumericLiteral.create()
        );
        return expression::apply;
    }

    ASTNode<?> parse(String text);
}
