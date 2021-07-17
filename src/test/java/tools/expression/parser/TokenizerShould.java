package tools.expression.parser;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TokenizerShould {

    @Test
    void create_with_input_text_and_initialier_cursor_index_to_zero() {
        final var input = "string yo yokenize";
        final var tokenizer = Tokenizer.createAndInit(input);

        assertAll(
                () -> assertThat(tokenizer).isNotNull(),
                () -> assertThat(tokenizer.input()).isEqualTo(input),
                () -> assertThat(tokenizer.cursorIndex()).isEqualTo(0)
        );
    }
}
