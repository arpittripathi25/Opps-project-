// public class Student {
//     private String id;
//     private String name;
//     private String course;
//     private String photoPath;
    
//     public Student() {
//         this.id = generateId();
//     }
    
//     public Student(String name, String course, String photoPath) {
//         this.id = generateId();
//         this.name = name;
//         this.course = course;
//         this.photoPath = photoPath;
//     }
    
//     private String generateId() {
//         return "STU" + System.currentTimeMillis() % 100000;
//     }
    
//     // Getters
//     public String getId() {
//         return id;
//     }
    
//     public String getName() {
//         return name;
//     }
    
//     public String getCourse() {
//         return course;
//     }
    
//     public String getPhotoPath() {
//         return photoPath;
//     }
    
//     // Setters
//     public void setName(String name) {
//         this.name = name;
//     }
    
//     public void setCourse(String course) {
//         this.course = course;
//     }
    
//     public void setPhotoPath(String photoPath) {
//         this.photoPath = photoPath;
//     }
    
//     @Override
//     public String toString() {
//         return "ID: " + id + ", Name: " + name + ", Course: " + course;
//     }
// }
public class Student {

    private String id;
    private String name;
    private String course;
    private String rollNo;
    private String dob;
    private String phone;
    private String photoPath;

    public Student(String name, String course, String rollNo, String dob, String phone, String photoPath) {
        this.id = generateId();
        this.name = name;
        this.course = course;
        this.rollNo = rollNo;
        this.dob = dob;
        this.phone = phone;
        this.photoPath = photoPath;
    }

    private String generateId() {
        return "STU" + System.currentTimeMillis() % 100000;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public String getRollNo() { return rollNo; }
    public String getDob() { return dob; }
    public String getPhone() { return phone; }
    public String getPhotoPath() { return photoPath; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setCourse(String course) { this.course = course; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
    public void setDob(String dob) { this.dob = dob; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Name: " + name +
                " | Course: " + course +
                " | Roll: " + rollNo +
                " | DOB: " + dob +
                " | Phone: " + phone;
    }
}