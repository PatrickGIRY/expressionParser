package tools.expression.parser;

import java.util.Optional;

public interface ItemParser {

    Optional<Success> tryParse(String input);
}
