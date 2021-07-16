package tools.expression.parser;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserShould {

    @Test
    void return_an_ast_from_a_text() {
        final var parser = Parser.create();
        final var text = "42";

        final var ast = parser.parse(text);

        assertThat(ast).isEqualTo(new ASTNode<>(ProductionType.NUMERIC_LITERAL, 42));
    }
}
