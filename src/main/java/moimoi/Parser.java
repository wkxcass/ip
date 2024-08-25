package moimoi;

import moimoi.command.AddCommand;
import moimoi.command.Command;
import moimoi.command.CommandEnum;
import moimoi.command.DeleteCommand;
import moimoi.command.ExitCommand;
import moimoi.command.FilterCommand;
import moimoi.command.ListCommand;
import moimoi.command.MarkCommand;
import moimoi.command.UnmarkCommand;
import moimoi.exception.InvalidCommandException;
import moimoi.exception.MissingArgumentException;
import moimoi.exception.MoiMoiException;

/**
 * The <code>Parser</code> class is responsible for interpreting and processing user input.
 */
public class Parser {

    /**
     * Returns a command corresponding to the specified user input.
     *
     * @param input User input.
     * @return Command corresponding to the specified user input.
     * @throws MoiMoiException If user input corresponds to an invalid command, or a missing argument.
     */
    public static Command parse(String input) throws MoiMoiException {

        try {
            String[] commandArgs = input.split(" ", 2);
            CommandEnum commandEnum = CommandEnum.valueOf(commandArgs[0].toUpperCase());
            Command command;

            switch (commandEnum) {
            case TODO:
            case DEADLINE:
            case EVENT:
                command = new AddCommand(commandEnum, Parser.extractArgs(commandArgs));
                break;
            case DELETE:
                command = new DeleteCommand(Parser.extractArgs(commandArgs));
                break;
            case MARK:
                command = new MarkCommand(Parser.extractArgs(commandArgs));
                break;
            case UNMARK:
                command = new UnmarkCommand(Parser.extractArgs(commandArgs));
                break;
            case LIST:
                command = new ListCommand();
                break;
            case FILTER:
                command = new FilterCommand(Parser.extractArgs(commandArgs));
                break;
            case BYE:
                command = new ExitCommand();
                break;
            default:
                throw new InvalidCommandException();
            }

            return command;
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException();
        }

    }

    /**
     * Extracts and returns the argument portion of the input.
     *
     * @param commandArgs Input consisting of a command, and an argument portion.
     * @return Argument portion of the input.
     */
    private static String extractArgs(String[] commandArgs) {
        return commandArgs[1];
    }

}
