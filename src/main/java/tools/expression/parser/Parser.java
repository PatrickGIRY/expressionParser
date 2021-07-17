package tools.expression.parser;

import java.util.Objects;
import java.util.Optional;

@FunctionalInterface
public interface Parser {
    static Parser create() {
       return text -> expression(TokenConsumer.create(Tokenizer.createAndInit(text)));
    }

    ASTNode.Expression parse(String text);

    private static ASTNode.Expression expression(TokenConsumer tokenConsumer) {
        return  ASTNode.expression(numericLiteral(tokenConsumer));
    }

    private static ASTNode.NumericLiteral numericLiteral(TokenConsumer tokenConsumer) {
        return ASTNode.numericLiteral(Integer.parseInt(tokenConsumer.eat(TokenType.NUMBER).value()));
    }

    @FunctionalInterface
    interface TokenConsumer {
        static  TokenConsumer create(Tokenizer tokenizer) {
            return tokenType -> {
                final var maybeToken = tokenizer.nextToken();
                final var token = maybeToken
                        .orElseThrow(() -> new IllegalStateException("Unexpected end of input, expected: \"" + tokenType + "\""));
                if (!Objects.equals(token.type(), tokenType)) {
                    throw new IllegalStateException("Unexpected token " + token.value() +  "expected: \"" + tokenType + "\"");
                }
                return token;
            };
        }

        Token eat(TokenType tokenType);
    }
}
