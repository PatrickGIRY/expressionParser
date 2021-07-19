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

    public Optional<Token> nextToken() {
        if (hasMoreToken()) {
            final var currentCharacter = input.charAt(cursorIndex);
            if (currentCharacter == '+'  || currentCharacter == '*') {
                cursorIndex++;
                return Optional.of(new Token(TokenType.OPERATOR, "" + currentCharacter));
            }
            if (Character.isDigit(currentCharacter)) {
                StringBuilder number = new StringBuilder();
                final var inputLength = input.length();
                while (cursorIndex < inputLength && Character.isDigit(input.charAt(cursorIndex))) {
                    number.append(input.charAt(cursorIndex++));
                }
                return Optional.of(new Token(TokenType.NUMBER, number.toString()));
            }
        }
        return Optional.empty();
    }

    private boolean hasMoreToken() {
        return this.cursorIndex < this.input.length();
    }
}
