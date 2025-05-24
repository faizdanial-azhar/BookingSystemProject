package StudentAuthGUI;

import java.io.*;
import java.util.*;

public class StudentManager {
    private final String filename = "students.txt";

    public boolean registerStudent(Student student) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            // Write in CSV format: matric,password,name,email,phone,faculty
            String csvLine = String.format("%s,%s,%s,%s,%s,%s", 
                student.getMatric(), 
                student.getPassword(), 
                student.getName(), 
                student.getEmail(), 
                student.getPhone(), 
                student.getFaculty());
            bw.write(csvLine);
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

    public boolean writeStudentInfoToFile(Student student, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("Student Information:");
            bw.newLine();
            bw.write("Matric No: " + student.getMatric());
            bw.newLine();
            bw.write("Name: " + student.getName());
            bw.newLine();
            bw.write("Email: " + student.getEmail());
            bw.newLine();
            bw.write("Phone: " + student.getPhone());
            bw.newLine();
            bw.write("Faculty: " + student.getFaculty());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
