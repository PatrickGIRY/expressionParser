package tools.expression.parser;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ParserShould {

    @Test
    void return_an_ast_from_a_text() {
        final var parser = Parser.create();
        final var text = "42";

        final ASTNode.Expression ast = parser.parse(text);

        assertAll(
                () -> assertThat(ast).isNotNull(),
                () -> assertThat(ast.body()).isNotNull(),
                () -> assertThat(ast.body().value()).isEqualTo(42)
        );

    }
}
