package duke.common;

public class Constants {
    // The index of task list will not exceed 1000000000
    public static final int MAX_TASK_STRING_LENGTH = 9;
    // Datetime format example: 18/09/2022
    public static final String DATE_INPUT_PATTERN_1 = "dd/MM/yyyy";

    // Datetime format example: 2022-09-18
    public static final String DATE_INPUT_PATTERN_2 = "yyyy-MM-dd";

    // Datetime format example: Sep 18 2022
    public static final String DATE_INPUT_PATTERN_3 = "MMM dd yyyy";

    // Time format example: 1800
    public static final String TIME_INPUT_PATTERN_1 = "HHmm";

    // Time format example: 06:00 PM
    public static final String TIME_INPUT_PATTERN_2 = "hh:mm a";

    // Time format example: Sep 18 2022 06:00 PM
    public static final String TIME_OUTPUT_PATTERN = "MMM dd yyyy hh:mm a";
}
