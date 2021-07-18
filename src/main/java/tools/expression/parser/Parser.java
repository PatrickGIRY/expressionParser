package tools.expression.parser;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface Parser {
    static Parser create() {
       return text -> expression(TokenConsumer.create(Tokenizer.createAndInit(text)));
    }

    ASTNode.Expression parse(String text);

    private static ASTNode.Expression expression(TokenConsumer tokenConsumer) {
        return tokenConsumer.eat(TokenType.NUMBER).<ASTNode.Expression>map(Parser::numericLiteral)
        .or(() -> tokenConsumer.eat(TokenType.OPERATOR).map(Parser.binaryExpression(tokenConsumer)))
                .orElseThrow(() -> new IllegalStateException("Expression: Unexpected expression production"));
    }

    private static Function<Token, ASTNode.BinaryExpression> binaryExpression(TokenConsumer tokenConsumer) {
        return token -> ASTNode.binaryExpression(expression(tokenConsumer), operator(token), expression(tokenConsumer));
    }

    static ASTNode.Operator operator(Token token) {
        return ASTNode.operator(token.value());
    }

    private static ASTNode.NumericLiteral numericLiteral(Token token) {
        return ASTNode.numericLiteral(Integer.parseInt(token.value()));
    }

    @FunctionalInterface
    interface TokenConsumer {
        static  TokenConsumer create(Tokenizer tokenizer) {
            return tokenType -> {
                final var maybeToken = tokenizer.nextToken();
                if (maybeToken.isPresent()) {
                    final var token = maybeToken.get();
                    if (Objects.equals(token.type(), tokenType)) {
                        return maybeToken;
                    } else {
                        throw new IllegalStateException("Unexpected token " + token.value() +  "expected: \"" + tokenType + "\"");
                    }
                } else {
                    throw new IllegalStateException("Unexpected end of input, expected: \"" + tokenType + "\"");
                }
            };
        }

        Optional<Token> eat(TokenType tokenType);
    }
}
