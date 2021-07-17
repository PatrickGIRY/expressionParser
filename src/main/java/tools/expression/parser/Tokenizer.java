package tools.expression.parser;

import java.util.Optional;

public class Tokenizer {
    private final String input;
    private int cursorIndex;

    public static Tokenizer createAndInit(String input) {
        return new Tokenizer(input, 0);
    }

    private Tokenizer(String input, int cursorIndex) {
        this.input = input;
        this.cursorIndex = cursorIndex;
    }

    public String input() {
        return this.input;
    }

    public int cursorIndex() {
        return this.cursorIndex;
    }

    public Optional<?> nextToken() {
        return Optional.empty();
    }
}
