import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CalendarPanel extends JPanel {
    private JLabel dateLabel;
    private JTextArea activityText;
    private JPanel activitiesPanel; // Panel för att visa aktiviteter
    private ArrayList<JTextField> activityFields; // Lista för att lagra textfälten
    private ArrayList<String> activityList; // Lista för att lagra aktiviteterna

    public CalendarPanel(int startDay, int startMonth, int startYear, int weekNumber) {
        setLayout(new BorderLayout());

        dateLabel = new JLabel(String.format("%02d/%02d %s", startDay, startMonth + 1, getDayOfWeek(startDay, startMonth, startYear)));
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        add(dateLabel, BorderLayout.NORTH);

        add(new JLabel("Vecka " + weekNumber), BorderLayout.CENTER);

        activityText = new JTextArea(3, 15);
        add(new JScrollPane(activityText), BorderLayout.CENTER);

        activitiesPanel = new JPanel(); // Skapar en panel för aktiviteter
        activitiesPanel.setLayout(new BoxLayout(activitiesPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(activitiesPanel), BorderLayout.SOUTH);

        activityFields = new ArrayList<>(); // Skapar en lista för att lagra textfälten
        activityList = new ArrayList<>(); // Skapar en lista för att lagra aktiviteterna

        JButton addActivityButton = new JButton("Lägg till aktivitet");
        add(addActivityButton, BorderLayout.SOUTH);

        addActivityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addActivity();
                activityText.requestFocusInWindow();
            }
        });

        markToday(startDay, startMonth, startYear);
    }

    private void markToday(int day, int month, int year) {
        Calendar today = Calendar.getInstance();
        if (today.get(Calendar.YEAR) == year && today.get(Calendar.MONTH) == month && today.get(Calendar.DAY_OF_MONTH) == day) {
            setBorder(BorderFactory.createLineBorder(Color.BLUE));
            setBackground(Color.BLUE);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
            String todayDate = dateFormat.format(today.getTime());
            dateLabel.setText(todayDate + " " + getDayOfWeek(day, month, year));
        } else {
            setBorder(null);
            setBackground(null);
        }
    }

    private String getDayOfWeek(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        String dayOfWeek = new SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.getTime());
        return dayOfWeek.toUpperCase();
    }

    private void addActivity() {
        String activity = activityText.getText().trim();
        if (!activity.isEmpty()) {
            // Skapa ett nytt textfält för varje aktivitet
            JTextField activityField = new JTextField(activity);
            activityFields.add(activityField);
            activitiesPanel.add(activityField);

            // Lägg till aktiviteten i listan
            activityList.add(activity);

            // Återställ textområdet efter att ha lagt till aktivitet
            activityText.setText("");

            activitiesPanel.revalidate(); // Uppdaterar layouten
            activitiesPanel.repaint();    // Målar om panelen
        }
    }

    // Lägg till en metod för att hämta aktiviteterna
    public ArrayList<String> getActivityList() {
        return activityList;
    }
}
