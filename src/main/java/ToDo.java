public class ToDo extends Task {

    public ToDo(String name) throws DukeException {
        super(name);
    }

    @Override
    public char taskType() {
        return 'T';
    }


}
