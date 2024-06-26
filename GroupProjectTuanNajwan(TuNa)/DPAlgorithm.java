package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DPAlgorithm {

    private static final int WORKING_HOURS_START = 8; // 8am
    private static final int WORKING_HOURS_END = 18;  // 6pm
    private static final int REQUIRED_FREE_HOURS = 2; // 2 hours time slos

    public DPAlgorithm() {
    }

    public void findContinuousFreeSlot(String day, List<Integer> lecturerAvailable, List<List<Integer>> studentAvailable) {
        boolean[] dp = new boolean[WORKING_HOURS_END]; 
     
        for (int i = WORKING_HOURS_START; i <= WORKING_HOURS_END - REQUIRED_FREE_HOURS; ++i) {
            boolean lecturerFree = isSlotAvailable(i, i + REQUIRED_FREE_HOURS, lecturerAvailable);
            boolean studentsFree = true;
            for (List<Integer> studentAvailability : studentAvailable) {
                if (!isSlotAvailable(i, i + REQUIRED_FREE_HOURS, studentAvailability)) {
                    studentsFree = false;
                    break;
                }
            }
            dp[i] = lecturerFree && studentsFree;
        }


        List<Integer> freeSlots = new ArrayList<>();
        for (int i = WORKING_HOURS_START; i <= WORKING_HOURS_END - REQUIRED_FREE_HOURS; ++i) {
            if (dp[i]) {
                freeSlots.add(i);
            }
        }

        printContinuousFreeSlots(day, freeSlots);
    }

    private boolean isSlotAvailable(int start, int end, List<Integer> availableTimes) {
        for (int i = start; i < end; ++i) {
            if (!availableTimes.contains(i)) {
                return false;
            }
        }
        return true;
    }

    private void printContinuousFreeSlots(String day, List<Integer> freeSlots) {
        System.out.println("Optimal 2-hour continuous time slots for replacement class on " + day + ":");
        for (int i = 0; i < freeSlots.size(); ++i) {
            System.out.println("Free from " + freeSlots.get(i) + ":00 to " + (freeSlots.get(i) + REQUIRED_FREE_HOURS) + ":00");
        }
    }

    public static void main(String[] args) {
    	DPAlgorithm scheduler = new DPAlgorithm();


        Map<String, List<Integer>> lecturerAvailable = new HashMap<>();
        Map<String, List<List<Integer>>> studentAvailable = new HashMap<>();

       

     // Monday availability
        lecturerAvailable.put("Monday", Arrays.asList(8, 9, 10, 11, 12, 13, 16, 17)); // Lecturer Dr Hafeez
        studentAvailable.put("Monday", Arrays.asList(
            Arrays.asList(11, 13, 16, 17), // Student 1 (Miza)
            Arrays.asList(8, 9, 11, 13, 17), // Student 2 (Jiki)
            Arrays.asList(11, 13, 16, 17), // Student 3 (Hanan)
            Arrays.asList(8, 9, 11, 13, 16, 17)  // Student 4 (Xue Rui) 
        ));

        // Tuesday availability
        lecturerAvailable.put("Tuesday", Arrays.asList(8, 9, 10, 12, 13, 14, 15, 16, 17)); // Lecturer Dr Hafeez
        studentAvailable.put("Tuesday", Arrays.asList(
            Arrays.asList(8, 9, 10, 12, 13, 14, 15, 16, 17), // Student 1 (Miza)
            Arrays.asList(12, 13), // Student 2 (Jiki)
            Arrays.asList(12, 13), // Student 3 (Hanan)
            Arrays.asList(12, 13, 16, 17)  // Student 4 (Xue Rui)
        ));

        // Wednesday availability
        lecturerAvailable.put("Wednesday", Arrays.asList(8, 9, 10, 11, 12, 13, 16, 17)); // Lecturer Dr Hafeez
        studentAvailable.put("Wednesday", Arrays.asList(
            Arrays.asList(8, 9, 13, 16, 17), // Student 1 (Miza)
            Arrays.asList(12, 13, 14, 15, 16, 17), // Student 2 (Jiki)
            Arrays.asList(8, 9, 10, 13, 14, 15, 16, 17), // Student 3 (Hanan)
            Arrays.asList(8, 9, 12, 13, 15, 16, 17)  // Student 4 (Xue Rui)
        ));

        // Thursday availability
        lecturerAvailable.put("Thursday", Arrays.asList(8, 9, 11, 12, 13, 14, 15, 16, 17)); // Lecturer Dr Hafeez
        studentAvailable.put("Thursday", Arrays.asList(
            Arrays.asList(12, 13, 14, 15, 16, 17), // Student 1 (Miza)
            Arrays.asList(11, 12, 13, 14, 15, 16, 17), /// Student 2 (Jiki)
            Arrays.asList(13, 14, 15, 16, 17), // Student 3 (Hanan)
            Arrays.asList(8, 12, 13, 14)  // Student 4 (Xue Rui)
        ));

        // Friday availability
        lecturerAvailable.put("Friday", Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16, 17)); // Lecturer Dr Hafeez
        studentAvailable.put("Friday", Arrays.asList(
            Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16, 17), // Student 1 (Miza)
            Arrays.asList(8, 12, 13, 14, 15, 16, 17), // Student 2 (Jiki)
            Arrays.asList(8, 12, 13, 14, 15, 16, 17), // Student 3 (Hanan)
            Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16, 17)  // Student 4 (Xue Rui)
        ));
      
        for (String day : lecturerAvailable.keySet()) {
            scheduler.findContinuousFreeSlot(day, lecturerAvailable.get(day), studentAvailable.get(day));
        }
    }
}
