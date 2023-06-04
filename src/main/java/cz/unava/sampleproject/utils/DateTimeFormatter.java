package cz.unava.sampleproject.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormatter {

    private static final String DATETIME_FORMAT = "dd.MM.yyyy HH:mm:ss";

    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);

    public static String formatDateTime(final Date date) {
        return sdf.format(date);
    }

}
