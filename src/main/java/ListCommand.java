public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.showList(tasks);
    }

}
