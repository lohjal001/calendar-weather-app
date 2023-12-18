import java.io.*;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CustomCalendar {
    private LocalDate currentDate;
    private int weekOfYear;
    private int year;
    private ArrayList<Event> events;

    private void saveFile() {
        try {
            FileOutputStream fos = new FileOutputStream("calendar-data.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(events);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void readFile() {
        try {
            FileInputStream fis = new FileInputStream("calendar-data.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            events = (ArrayList<Event>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public CustomCalendar() {
        currentDate = LocalDate.now();
        weekOfYear = currentDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
        year = currentDate.getYear();
        events = new ArrayList<>();
        readFile();
    }

    public CustomDate getDayOfWeek(int dayIndex) {
        DayOfWeek currentDay = currentDate.getDayOfWeek();
        LocalDate newDate = currentDate;
        if (dayIndex > currentDay.getValue()) {
            newDate = currentDate.plusDays(dayIndex - currentDay.getValue());
        } else if (dayIndex < currentDay.getValue()) {
            newDate = currentDate.minusDays(currentDay.getValue() - dayIndex);
        }
        return new CustomDate(newDate);
    }

    public int getWeekOfYear() {
        return weekOfYear;
    }

    public int getYear() {
        return year;
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Event> getEventNamesByDate(LocalDate date) {
        return events.stream().filter(e -> e.getDate().equals(date)).collect(Collectors.toList());
    }

    public void addEvent(Event newEvent) {
        events.add(newEvent);
        saveFile();
    }

    public void removeEvent(Event oldEvent) {
        events.remove(oldEvent);
        saveFile();
    }

    public int nextWeek() {
        currentDate = currentDate.plusWeeks(1);
        weekOfYear = currentDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
        year = currentDate.getYear();
        return weekOfYear;
    }

    public int prevWeek() {
        currentDate = currentDate.minusWeeks(1);
        weekOfYear = currentDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
        year = currentDate.getYear();
        return weekOfYear;
    }
}
