package tools.expression.parser;

import java.util.Optional;

@FunctionalInterface
public interface Parser<T> {
    static <T> Parser<T> failure() {
        return __ -> Optional.empty();
    }

    static Parser<IntSuccess> valueOf(int codePoint) {
        return input -> Optional.of(new IntSuccess(codePoint, new Remaining(0, input)));
    }

    static Parser<IntSuccess> item() {
        return failure();
    }

    Optional<T> tryParse(String input);
}
