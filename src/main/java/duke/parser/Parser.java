package duke.parser;

import duke.exception.DukeException;

/**
 * <code>Parser</code> is the interface for the every type of parsers in the application.
 *
 * @param <T> Generic type
 */
public interface Parser<T> {
    T parse(String userInput) throws DukeException;
}