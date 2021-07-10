package tools.expression.parser;

import java.util.Optional;

@FunctionalInterface
public interface Parser {
    static Parser failure() {
        return __ -> Optional.empty();
    }

    Optional<?> tryParse(String input);
}
