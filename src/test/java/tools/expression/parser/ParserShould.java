package tools.expression.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ParserShould {

    private Parser parser;

    @BeforeEach
    void setUp() {
        parser = Parser.create();
    }

    @Test
    void return_a_binary_expression_ast_from_a_text() {
        final var text = "35 + 46";

        final var expression = parser.parse(text);

        assertAll(
                () -> assertThat(expression).isNotNull(),
                () -> assertThat(expression).isInstanceOf(ASTNode.BinaryExpression.class),
                () -> assertThat(leftOf(expression)).isNotNull(),
                () -> assertThat(leftOf(expression)).isInstanceOf(ASTNode.NumericLiteral.class),
                () -> assertThat(valueOf(leftOf(expression))).isEqualTo(35),
                () -> assertThat(rightOf(expression)).isNotNull(),
                () -> assertThat(rightOf(expression)).isInstanceOf(ASTNode.NumericLiteral.class),
                () -> assertThat(valueOf(rightOf(expression))).isEqualTo(46)
        );
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "12 +; Unexpected end of input, expected: \"NUMBER\"",
            "12 ; Unexpected end of input, expected: \"OPERATOR\""})
    void throw_IllegalStateException_when_binary_expression_is_incompete(String incompleteInput, String errorMessage) {
        assertThatThrownBy(() -> parser.parse(incompleteInput))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(errorMessage);
    }

    static ASTNode.Expression leftOf(ASTNode.Expression expression) {
        return ((ASTNode.BinaryExpression) expression).left();
    }

    static ASTNode.Expression rightOf(ASTNode.Expression expression) {
        return ((ASTNode.BinaryExpression) expression).right();
    }

    static int valueOf(ASTNode.Expression expression) {
        return ((ASTNode.NumericLiteral) expression).value();
    }

    @Test
    void throw_IllegalStateException_when_it_is_the_end_of_input_and_expected_a_token_type() {
        final var parser = Parser.create();
        final var text = "";

        assertThatThrownBy(() -> parser.parse(text))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Unexpected end of input, expected: \"NUMBER\"");
    }

    /*@Test
    void throw_IllegalStateException_when_it_is_not_the_expected_token_type() {
        final var parser = Parser.create();
        final var text = "foo";

        assertThatThrownBy(() -> parser.parse(text))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Unexpected end of input, expected: \"NUMBER\"");
    }*/
}
