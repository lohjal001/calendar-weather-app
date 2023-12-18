import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class CalendarApp {
    private JFrame frame;

    // Konstruktor utan argument för att skapa en ny JFrame
    public CalendarApp() {
        this.frame = new JFrame("Veckokalender");
    }

    // Metod som skapar och visar GUI
    public void createAndShowGUI() {
        // Layout för huvudramen
        frame.setLayout(new GridLayout(1, 7));
        frame.setPreferredSize(new Dimension(1100, 600));

        // Hämtar veckonumret direkt från Calendar.getInstance()
        int weekNumber = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);

        // Justerar datumet för att starta veckan från måndag
        int startDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int startMonth = Calendar.getInstance().get(Calendar.MONTH);
        int startYear = Calendar.getInstance().get(Calendar.YEAR);

        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        int daysToMonday = (Calendar.SATURDAY - dayOfWeek + 2) % 7;
        startDay -= daysToMonday;

        // Loopar över varje dag i veckan och skapa en CalendarPanel för varje dag
        for (int i = 0; i < 7; i++) {
            CalendarPanel calendarPanel = new CalendarPanel(startDay++, startMonth, startYear, weekNumber);
            frame.add(calendarPanel);
        }

        // Justerar storleken på ramen och gör den synlig
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Main-metoden där applikationen startar
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalendarApp calendarApp = new CalendarApp();
            calendarApp.createAndShowGUI();
        });
    }
}

