public abstract class Command {

    public abstract void checkArgumentLength() throws MissingDescriptionException,
            MissingArgumentException,
            ExtraArgumentException;
    public abstract void checkFlags() throws MissingFlagException;
    public abstract void checkArgument() throws NotIntegerException;

    public void VerifyAndParse() throws Exception {
        checkArgumentLength();
        checkFlags();
        checkArgument();
    }

}
