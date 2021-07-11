package tools.expression.parser;

import java.util.Optional;

public record IntSuccess(int value, Remaining remaining) {
    public static Optional<IntSuccess> create(int codePoint, int index, String input) {
        return Optional.of(new IntSuccess(codePoint, new Remaining(index, input)));
    }
}
