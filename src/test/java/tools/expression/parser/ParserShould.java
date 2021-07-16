package tools.expression.parser;

import org.junit.jupiter.api.Test;
import tools.expression.parser.ast.ASTNodeType;
import tools.expression.parser.ast.ExpressionNode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ParserShould {

    @Test
    void return_an_ast_from_a_text() {
        final var parser = Parser.create();
        final var text = "42";

        final ExpressionNode ast = parser.parse(text);

        assertAll(
                () -> assertThat(ast).isNotNull(),
                () -> assertThat(ast.type()).isEqualTo(ASTNodeType.EXPRESSION),
                () -> assertThat(ast.body()).isNotNull(),
                () -> assertThat(ast.body().type()).isEqualTo(ASTNodeType.NUMERIC_LITERAL),
                () -> assertThat(ast.body().value()).isEqualTo(42)
        );

    }
}
