package tools.expression.parser;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface Parser<T> {
    static <T> Parser<T> failure() {
        return __ -> Optional.empty();
    }

    static Parser<IntSuccess> valueOf(int codePoint) {
        return input -> {
            requireNonNull(input);
            return Optional.of(new IntSuccess(codePoint, new Remaining(0, input)));
        };
    }

    static Parser<IntSuccess> item() {
        return input -> input.isEmpty()
                ? Optional.empty()
                : Optional.of(new IntSuccess(input.charAt(0), new Remaining(1, input)));
    }

    Optional<T> tryParse(String input);
}
