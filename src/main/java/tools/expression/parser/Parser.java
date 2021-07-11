package tools.expression.parser;

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
            return IntSuccess.create(codePoint, 0, input);
        };
    }

    static Parser<IntSuccess> item() {
        return input -> {
            requireNonNull(input);
            return input.isEmpty()
                    ? Optional.empty()
                    : IntSuccess.create(input.charAt(0), 1, input);
        };
    }

    Optional<T> tryParse(String input);
}
