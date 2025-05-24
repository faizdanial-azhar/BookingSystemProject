package StudentAuthGUI;

import java.io.*;
import java.util.*;

public class StudentManager {
    private final String filename = "students.txt";

    public boolean registerStudent(Student student) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(student.toCSV());
            bw.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Student loginStudent(String matric, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                Student s = Student.fromCSV(line);
                if (s != null && s.getMatric().equals(matric) && s.getPassword().equals(password)) {
                    return s;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
