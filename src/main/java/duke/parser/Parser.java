package duke.parser;

import duke.exception.DukeException;

public interface Parser<T> {
    T parse(String userInput) throws DukeException;
}
