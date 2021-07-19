package tools.expression.parser;

import java.util.Objects;
import java.util.Optional;

@FunctionalInterface
public interface Parser {
    static Parser create() {
        return text -> expression(TokenConsumer.create(Tokenizer.createAndInit(text)))
                .orElseThrow(() -> new IllegalStateException("Expression: Unexpected expression production"));
    }

    ASTNode.Expression parse(String text);

    private static Optional<ASTNode.Expression> expression(TokenConsumer tokenConsumer) {
        final var maybeNumericLiteral = numericLiteral(tokenConsumer);
        return maybeNumericLiteral
                .flatMap(numericLiteral -> operator(tokenConsumer)
                        .flatMap(operator -> numericLiteral(tokenConsumer)
                                .<ASTNode.Expression>map(right -> ASTNode.binaryExpression(numericLiteral, operator, right))));
    }

    private static Optional<ASTNode.Operator> operator(TokenConsumer tokenConsumer) {
        return tokenConsumer.eat(TokenType.OPERATOR)
                .map(Token::value)
                .map(ASTNode::operator);
    }

    private static Optional<ASTNode.NumericLiteral> numericLiteral(TokenConsumer tokenConsumer) {
        return tokenConsumer.eat(TokenType.NUMBER)
                .map(Token::value)
                .map(Integer::parseInt)
                .map(ASTNode::numericLiteral);

    }

    @FunctionalInterface
    interface TokenConsumer {
        static TokenConsumer create(Tokenizer tokenizer) {
            return tokenType -> {
                final var maybeToken = tokenizer.nextToken();
                if (maybeToken.isPresent()) {
                    final var token = maybeToken.get();
                    if (Objects.equals(token.type(), tokenType)) {
                        return maybeToken;
                    } else {
                        throw new IllegalStateException("Unexpected token '" + token.value() + "' expected: \"" + tokenType + "\"");
                    }
                } else {
                    throw new IllegalStateException("Unexpected end of input, expected: \"" + tokenType + "\"");
                }
            };
        }

        Optional<Token> eat(TokenType tokenType);
    }
}
