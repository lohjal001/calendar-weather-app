import java.time.LocalDate;
import java.time.DayOfWeek;

public class CustomDate {
    private LocalDate date;
    private String weekday;
    private String dayAndMonth;

    private String getOrdinalNumber(int num) {
        switch (num % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    private String makeFirstUpperCase(String str) {
        return str.substring(0, 1) + str.substring(1).toLowerCase();
    }

    public CustomDate(LocalDate date) {
        this.date = date;
        DayOfWeek currentDay = date.getDayOfWeek();
        weekday = makeFirstUpperCase(currentDay.name());
        dayAndMonth = String.format("%d%s of %s", date.getDayOfMonth(), getOrdinalNumber(date.getDayOfMonth()), makeFirstUpperCase(date.getMonth().name()));
    }

    public boolean isToday() {
        LocalDate currDate = LocalDate.now();
        return currDate.equals(date);
    }

    public LocalDate getDate() {
        return date;
    }

    public String getWeekday() {
        return weekday;
    }

    public String getDayAndMonth() {
        return dayAndMonth;
    }
}
