package tools.expression.parser;

import tools.expression.domain.Value;

import java.util.Optional;

public interface ValueParser {
    static ValueParser create(ItemParser itemParser) {
        return input -> Optional.of(Integer.parseInt(input)).map(Value::new);
    }

    Optional<Value> tryParse(String input);
}
