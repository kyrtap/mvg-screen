package de.kyrtap5.mvgscreen;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class DateHandler {
    /**
     * Get the current date with an optional minute offset
     *
     * @param minuteOffset the minute offset to be applied
     * @return the current date with offset
     */
    public static Date getDate(int minuteOffset) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, minuteOffset);
        return cal.getTime();
    }

    /**
     * Format the date to the given time format
     *
     * @param date   the date to be formatted
     * @param format the formatting type
     * @return the formatted time String
     */
    public static String formatDate(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * Compare two dates and return the difference in minutes
     *
     * @param date1 the later date
     * @param date2 the earlier date
     * @return the difference in minutes
     */
    public static int getDifference(Date date1, Date date2) {
        return (int) ((date1.getTime() / 60000) - (date2.getTime() / 60000));
    }
}
