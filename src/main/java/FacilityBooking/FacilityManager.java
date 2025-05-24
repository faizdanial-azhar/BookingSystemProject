package FacilityBooking;


import java.io.*;
import java.util.*;

public class FacilityManager {
    private final String filename = "facilities.txt";

    public List<Facility> loadFacilities() {
        List<Facility> facilities = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                Facility facility = Facility.fromCSV(line);
                if (facility != null) {
                    facilities.add(facility);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return facilities;
    }

    public void saveFacilities(List<Facility> facilities) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Facility facility : facilities) {
                bw.write(facility.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeDefaultFacilities() {
        File file = new File(filename);
        if (!file.exists()) {
            List<Facility> defaults = List.of(
                    new Facility(1, "Futsal Court", 10, 5.0f, "08:00", "22:00", true, "src/facilitybooking/images/futsal.jpg"),
                    new Facility(2, "Badminton Court", 4, 4.0f, "09:00", "21:00", true, "src/facilitybooking/images/badminton.jpeg")
            );
            saveFacilities(defaults);
        }
    }
}
