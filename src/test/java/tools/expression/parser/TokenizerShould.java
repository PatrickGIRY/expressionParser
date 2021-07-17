package tools.expression.parser;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TokenizerShould {

    private static final String INPUT_WITH_STRING_TO_YOKENIZE = "string to yokenize";
    private static final String NO_MORE_TOKEN_INTO_InPUT = "";
    private static final String NUMBER_INPUT = "42";

    @Test
    void create_with_input_text_and_initialier_cursor_index_to_zero() {
        final var tokenizer = Tokenizer.createAndInit(INPUT_WITH_STRING_TO_YOKENIZE);

        assertAll(
                () -> assertThat(tokenizer).isNotNull(),
                () -> assertThat(tokenizer.input()).isEqualTo(INPUT_WITH_STRING_TO_YOKENIZE),
                () -> assertThat(tokenizer.cursorIndex()).isEqualTo(0)
        );
    }

    @Test
    void return_empty_when_input_has_no_more_token() {
        final var tokenizer = Tokenizer.createAndInit(NO_MORE_TOKEN_INTO_InPUT);

        final var nextToken = tokenizer.nextToken();

        assertThat(nextToken).isEmpty();
    }

    @Test
    void return_a_token_of_type_number_when_input_start_by_a_number() {
        final var tokenizer = Tokenizer.createAndInit(NUMBER_INPUT);

        final var nextToken = tokenizer.nextToken();

        assertThat(nextToken).hasValue(new Token(TokenType.NUMBER, "42"));
    }
}
