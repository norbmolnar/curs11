import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2 {

    public static void main(String[] args) {
        DailyPlanner planner = new DailyPlanner();

        // Adding activities
        planner.addActivity(Day.MONDAY, "Gym");
        planner.addActivity(Day.MONDAY, "Meeting with Bob");
        planner.addActivity(Day.TUESDAY, "Doctor's appointment");

        // Removing an activity
        planner.removeActivity(Day.MONDAY, "Gym");

        // Get activities for a specific day
        List<String> mondayActivities = planner.getActivities(Day.MONDAY);
        System.out.println("Monday's Activities: " + mondayActivities);

        // End planning and get the full schedule
        try {
            Map<Day, List<String>> fullSchedule = planner.endPlanning();
            System.out.println("Full Schedule: " + fullSchedule);
        } catch (NoActivitiesForDayException e) {
            e.printStackTrace();
        }
    }
}

enum Day {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY
}

class DaySchedule {
    private Day day;
    private List<String> activities;

    public DaySchedule(Day day) {
        this.day = day;
        this.activities = new ArrayList<>();
    }

    public Day getDay() {
        return day;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void addActivity(String activity) {
        activities.add(activity);
    }

    public void removeActivity(String activity) {
        activities.remove(activity);
    }
}

class NoActivityException extends RuntimeException {
    public NoActivityException(String message) {
        super(message);
    }
}

class NoActivitiesForDayException extends Exception {
    public NoActivitiesForDayException(String message) {
        super(message);
    }
}

class DailyPlanner {
    private List<DaySchedule> schedules;

    public DailyPlanner() {
        this.schedules = new ArrayList<>();
        for (Day day : Day.values()) {
            schedules.add(new DaySchedule(day));
        }
    }

    public void addActivity(Day day, String activity) {
        if (activity == null) {
            throw new NoActivityException("Activity cannot be null");
        }
        getDaySchedule(day).addActivity(activity);
    }

    public void removeActivity(Day day, String activity) {
        DaySchedule schedule = getDaySchedule(day);
        if (!schedule.getActivities().contains(activity)) {
            throw new NoActivityException("Activity does not exist");
        }
        schedule.removeActivity(activity);
    }

    public List<String> getActivities(Day day) {
        return getDaySchedule(day).getActivities();
    }

    public Map<Day, List<String>> endPlanning() throws NoActivitiesForDayException {
        Map<Day, List<String>> planMap = new HashMap<>();
        for (DaySchedule schedule : schedules) {
            if (schedule.getActivities().isEmpty()) {
                throw new NoActivitiesForDayException("No activities for " + schedule.getDay());
            }
            planMap.put(schedule.getDay(), schedule.getActivities());
        }
        return planMap;
    }

    private DaySchedule getDaySchedule(Day day) {
        for (DaySchedule schedule : schedules) {
            if (schedule.getDay() == day) {
                return schedule;
            }
        }
        throw new IllegalStateException("Day schedule not found"); // This should not happen
    }
}
