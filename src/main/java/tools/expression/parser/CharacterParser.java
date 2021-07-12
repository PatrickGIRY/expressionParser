package tools.expression.parser;

import java.util.Objects;
import java.util.OptionalInt;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface CharacterParser {
    static CharacterParser failure() {
        return (index, input) -> OptionalInt.empty();
    }

    static CharacterParser valueOf(int codePoint) {
        return (index, input) -> OptionalInt.of(codePoint);
    }

    static CharacterParser item() {
        return (index, input) -> {
            requireNonNull(input);
            final var inputLength = input.length();
            return inputLength > 0 ? OptionalInt.of(input.charAt(index)) : OptionalInt.empty();
        };
    }

    OptionalInt tryParse(int index, String input);
}
