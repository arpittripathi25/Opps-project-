import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private List<Student> students;
    
    public StudentService() {
        students = new ArrayList<>();
    }
    
    public void addStudent(Student student) {
        students.add(student);
    }
    
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
    
    public Student searchById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
    
    public Student searchByName(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }
    
    public boolean updateStudent(String id, String name, String course, String photoPath) {
        Student student = searchById(id);
        if (student != null) {
            student.setName(name);
            student.setCourse(course);
            student.setPhotoPath(photoPath);
            return true;
        }
        return false;
    }
    
    public boolean deleteStudent(String id) {
        Student student = searchById(id);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false;
    }
    
    public List<Student> searchStudentsByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(student);
            }
        }
        return result;
    }
    public List<Student> searchStudent(String query) {
        List<Student> result = new ArrayList<>();

        for (Student s : students) {
           if (
              s.getId().toLowerCase().contains(query.toLowerCase()) ||
              s.getName().toLowerCase().contains(query.toLowerCase()) ||
            //   s.getRoll().toLowerCase().contains(query.toLowerCase())
              s.getRollNo().toLowerCase().contains(query.toLowerCase())
          ) {
            result.add(s);
          }
    }

    return result;
}
    
    public int getStudentCount() {
        return students.size();
    }

    // public List<Student> searchByName(String name) {
    // List<Student> result = new ArrayList<>();

    // for (Student s : students) {
    //     if (s.getName().toLowerCase().contains(name.toLowerCase())) {
    //         result.add(s);
    //     }
    // }
    // return result;
    // }
}
