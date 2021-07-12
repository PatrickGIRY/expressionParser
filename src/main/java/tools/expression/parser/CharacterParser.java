package tools.expression.parser;

import java.util.OptionalInt;

@FunctionalInterface
public interface CharacterParser {
    static CharacterParser failure() {
        return (index, input) -> OptionalInt.empty();
    }

    OptionalInt tryParse(int index, String input);
}
