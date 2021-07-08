package tools.expression.parser;

import tools.expression.domain.Value;

import java.util.Optional;

public interface ValueParser {
    static ValueParser create(ItemParser itemParser) {
        return input -> itemParser
                .tryParse(input)
                .map(Success::value)
                .map(Integer::parseInt)
                .map(Value::new);
    }

    Optional<Value> tryParse(String input);
}
