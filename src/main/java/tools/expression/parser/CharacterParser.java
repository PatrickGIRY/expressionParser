package tools.expression.parser;

import java.util.OptionalInt;

@FunctionalInterface
public interface CharacterParser {
    static CharacterParser failure() {
        return (index, input) -> OptionalInt.empty();
    }

    static CharacterParser valueOf(int codePoint) {
        return (index, input) -> OptionalInt.of(codePoint);
    }

    OptionalInt tryParse(int index, String input);
}
