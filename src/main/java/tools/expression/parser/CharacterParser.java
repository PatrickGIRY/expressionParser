package tools.expression.parser;

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
            requireNonNull(input);
            final var inputLength = input.length();
            return inputLength > 0 && index < inputLength ? OptionalInt.of(input.charAt(index)) : OptionalInt.empty();
        };
    }

    OptionalInt tryParse(int index, String input);

    default  CharacterParser flatMap(IntFunction<CharacterParser> mapper) {

        return (index, input) -> {
            final var result1 = tryParse(index, input);
            return result1.isPresent() ? mapper.apply(result1.getAsInt()).tryParse(index + 1, input) : result1;
        };
    }

    default CharacterParser satisfy(IntPredicate predicate) {
        return flatMap(c -> predicate.test(c) ? valueOf(c) : failure());
    }
}
