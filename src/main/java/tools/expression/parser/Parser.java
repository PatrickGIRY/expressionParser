package tools.expression.parser;

import java.util.Optional;

public interface Parser<T> {

    Optional<T> tryParse(int index, String input);
}
