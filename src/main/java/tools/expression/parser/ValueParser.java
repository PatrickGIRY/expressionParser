package tools.expression.parser;

import tools.expression.domain.Value;

import java.util.Optional;

public interface ValueParser {
    Optional<Value> tryParse(String input);
}
