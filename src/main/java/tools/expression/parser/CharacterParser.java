package tools.expression.parser;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;

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
            requireNonNull(input, "input required");
            final var inputLength = input.length();
            return inputLength > 0 && index < inputLength ? OptionalInt.of(input.charAt(index)) : OptionalInt.empty();
        };
    }

    static CharacterParser satisfy(IntPredicate predicate) {
        requireNonNull(predicate, "predicate required");
        return item().flatMap(c -> predicate.test(c) ? valueOf(c) : failure());
    }

    static CharacterParser character(int expectedCodePoint) {
        return satisfy(c -> c == expectedCodePoint);
    }

    OptionalInt tryParse(int index, String input);

    default CharacterParser flatMap(IntFunction<CharacterParser> mapper) {
        return (index, input) -> {
            final var result1 = tryParse(index, input);
            return result1.isPresent() ? mapper.apply(result1.getAsInt()).tryParse(index + 1, input) : result1;
        };
    }

    default <R> Parser<R> mapToObj(IntFunction<R> mapper) {
        requireNonNull(mapper, "mapper required");
        return (index, input) -> {
            final var result = tryParse(index, input);
            return result.isPresent() ? Optional.of(mapper.apply(result.getAsInt())) : Optional.empty();
        };
    }
}
