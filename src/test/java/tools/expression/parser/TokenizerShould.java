package tools.expression.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TokenizerShould {

    private static final String INPUT_WITH_STRING_TO_YOKENIZE = "string to yokenize";

    private Tokenizer tokenizer;

    @BeforeEach
    void setUp() {
        tokenizer = Tokenizer.createAndInit(INPUT_WITH_STRING_TO_YOKENIZE);
    }

    @Test
    void create_with_input_text_and_initialier_cursor_index_to_zero() {
        assertAll(
                () -> assertThat(tokenizer).isNotNull(),
                () -> assertThat(tokenizer.input()).isEqualTo(INPUT_WITH_STRING_TO_YOKENIZE),
                () -> assertThat(tokenizer.cursorIndex()).isEqualTo(0)
        );
    }
}
