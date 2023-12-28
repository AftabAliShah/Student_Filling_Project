import java.io.*;
import java.util.*;

public class Main {
    public static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String ch = "";
        do {
        System.out.println("Welcome to Student Management System");
        System.out.println("\t1.Add a student.\n" +
                "\t2.Delete a Student.\n" +
                "\t3.Update a student.\n" +
                "\t4.Show All Students.\n" +
                "\t5.Show Students with a particular Age.\n" +
                "\t6.Show Students with a particular Standard.\n" +
                "\t7.Show Student with a particular RollNumber.\n" +
                "\t8.Show Student with Name.");

        final String FILE_NAME = "Student_Database.txt";

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.print("\tPlease input the choice : ");
        String choice = input.next();
        String content = "";
        BufferedReader br = null;
        String s = "";



            switch (choice) {
                case "1":
                    System.out.print("How many students want do you add : ");
                    Integer studentLength = input.nextInt();
                    for (int i = 0; i < studentLength; i++) {
                        System.out.print("Enter the student number : ");
                        String id = input.next();
                        System.out.print("Enter the student name : ");
                        String name = new Scanner(System.in).nextLine();
                        System.out.print("Enter the student age : ");
                        String age = input.next();
                        System.out.print("Enter the roll number : ");
                        String rollNumber = input.next();
                        System.out.print("Enter the student standard : ");
                        String standard = input.next();
                        studentList.add(new Student(id, name, age, rollNumber, standard));
                    }


                    boolean idExist = false;
                    boolean rollNumberExist = false;
                    try {
                        br = new BufferedReader(new FileReader(file));


                        while ((s = br.readLine()) != null) {
                            String data[] = s.split(":");

                            for (int i = 0; i < studentList.size(); i++) {
                                if (studentList.get(i).getId().equals(data[0])) {
                                    System.out.println("Id Can not be Duplicate");
                                    idExist = true;
                                }
                                if (studentList.get(i).getRollNumber().equals(data[3])) {
                                    System.out.println("Roll Number Can not be Duplicate");
                                    rollNumberExist = true;
                                }
                            }


                            content += s + "\n";
                        }
                        br.close();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    if (idExist || rollNumberExist) {
                        return;
                    }

                    for (Student st : studentList) {
                        content += st.getId() + ":" + st.getName() + ":" + st.getAge() + ":" + st.getRollNumber() + ":" + st.getStandard() + "\n";
                    }

                    try {
                        FileWriter fileWriter = new FileWriter(file);
                        fileWriter.write(content);
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                case "2":
                    System.out.println("Student Available in Database ");
                    try {
                        br = new BufferedReader(new FileReader(file));
                        while ((s = br.readLine()) != null) {
                            System.out.println(s);
                        }
                        br.close();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.out.print("Enter the student Id to delete Student : ");
                    String inputID = input.next();
                    try {
                        br = new BufferedReader(new FileReader(file));
                        while ((s = br.readLine()) != null) {
                            String data[] = s.split(":");
                            if (inputID.equals(data[0])) {
                                continue;
                            }
                            content += s + "\n";
                        }
                        br.close();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Student Successfully Deleted");

                    try {
                        FileWriter fileWriter = new FileWriter(file);
                        fileWriter.write(content);
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

//                deleteFromList(FILE_NAME);

                    break;
                case "3":
                    fileToUpdate(FILE_NAME);
                    break;
                case "4":
                    System.out.println("Available students are. ");
                    try {
                        br = new BufferedReader(new FileReader(file));
                        while ((s = br.readLine()) != null) {
                            System.out.println(s);
                        }
                        br.close();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "5":
                    displayStudentWithParticularAge(FILE_NAME);
                    break;
                case "6":
                    displayStudentsWithStandard(FILE_NAME);
                    break;
                case "7":
                    displayStudentsWithRollNumber(FILE_NAME);
                    break;
                case "8":
                    displayWithName(FILE_NAME);
                    break;
                default:
                    System.out.println("Invalid Choice");

            }
            System.out.print("Want to continue : Press(Y/N) : ");
            ch = input.next();
        } while (ch.equalsIgnoreCase("Y"));
    }

    public static void deleteFromList(String file) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter the Student Id to delete");
        String studentId = inp.next();

        BufferedReader br = null;
        String s = "";
        try {
            br = new BufferedReader(new FileReader(file));
            while ((s = br.readLine()) != null) {
                String data[] = s.split(":");
                studentList.add(new Student(data[0], data[1], data[2], data[3], data[4]));
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(studentId)) {
                studentList.remove(i);
            }
        }
        String content = "";
        for (Student st : studentList) {
            content += st.getId() + ":" + st.getName() + ":" + st.getAge() + ":" + st.getRollNumber() + ":" + st.getStandard() + "\n";
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileToUpdate(String file) {
        Scanner inp = new Scanner(System.in);
        BufferedReader br = null;
        String s = "";
        try {
            br = new BufferedReader(new FileReader(file));
            while ((s = br.readLine()) != null) {
                String data[] = s.split(":");
                studentList.add(new Student(data[0], data[1], data[2], data[3], data[4]));
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.print("Enter the Student Id\nDo you want to update : ");
        String studentId = inp.next();
        boolean notFound = true;
        for (int i = 0; i < studentList.size(); i++) {

            if (studentList.get(i).getId().equals(studentId)) {
                System.out.println("What Do you want to Update?");
                System.out.print("1.NAME\n2.AGE\n3.ROLL_NUMBER\n4.STANDARD\n5.NAME:AGE:ROLL_NUMBER:STANDARD\nPlease select the option : ");
                Student student = studentList.get(i);
                notFound = false;
                String swich = inp.next();
                switch (swich) {
                    case "1":
                        System.out.print("Enter the name : ");
                        String name = new Scanner(System.in).nextLine();
                        student.setName(name);
                        System.out.println("Name Updated!\n" + studentList.get(i));
                        break;
                    case "2":
                        System.out.print("Enter the student age : ");
                        String age = inp.next();
                        student.setAge(age);
                        System.out.println("Age Updated!\n" + studentList.get(i));
                        break;
                    case "3":
                        System.out.print("Enter the roll number : ");
                        String rollNumber = inp.next();
                        student.setRollNumber(rollNumber);
                        System.out.println("RollNumber Updated!\n" + studentList.get(i));
                        break;
                    case "4":
                        System.out.print("Enter the student standard : ");
                        String standard = inp.next();
                        student.setStandard(standard);
                        System.out.println("Standard Updated!\n" + studentList.get(i));
                        break;
                    case "5":
                        System.out.print("Enter the name : ");
                        name = new Scanner(System.in).nextLine();
                        studentList.remove(name);
                        student.setName(name);
                        System.out.print("Enter the student age : ");
                        age = inp.next();
                        student.setAge(age);
                        System.out.print("Enter the roll number : ");
                        rollNumber = inp.next();
                        student.setRollNumber(rollNumber);
                        System.out.print("Enter the student standard : ");
                        standard = inp.next();
                        student.setStandard(standard);
                        System.out.println("Information Updated!\n" + studentList.get(i));
                        break;
                }

            }
        }

        if (notFound) {
            System.out.println("Invalid ID");
        }


        String content = "";
        for (Student st : studentList) {
            content += st.getId() + ":" + st.getName() + ":" + st.getAge() + ":" + st.getRollNumber() + ":" + st.getStandard() + "\n";
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void displayStudentWithParticularAge(String file) {
        Scanner inp = new Scanner(System.in);
        BufferedReader br = null;
        String s = "";
        try {
            br = new BufferedReader(new FileReader(file));
            while ((s = br.readLine()) != null) {
                String data[] = s.split(":");
                studentList.add(new Student(data[0], data[1], data[2], data[3], data[4]));
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.print("Enter the Student age\nDo you want to Search : ");
        String age = inp.next();
        boolean notFound = true;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getAge().equals(age)) {
                System.out.println("Students with this age are.");
                System.out.println(studentList.get(i));
                notFound = false;
            }
        }
        if (notFound) {
            System.out.println("Not found with this information");
        }

        String content = "";
        for (Student st : studentList) {
            content += st.getId() + ":" + st.getName() + ":" + st.getAge() + ":" + st.getRollNumber() + ":" + st.getStandard() + "\n";
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void displayStudentsWithStandard(String file) {
        Scanner inp = new Scanner(System.in);
        BufferedReader br = null;
        String s = "";
        try {
            br = new BufferedReader(new FileReader(file));
            while ((s = br.readLine()) != null) {
                String data[] = s.split(":");
                studentList.add(new Student(data[0], data[1], data[2], data[3], data[4]));
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.print("Enter the Student Standard/Class,\nDo you want to Search : ");
        String standard = inp.next();
        boolean notFound = true;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStandard().equals(standard)) {
                System.out.println("Students studying in this standard are");
                System.out.println(studentList.get(i));
                notFound = false;
            }
        }
        if (notFound) {
            System.out.println("Not found with this information");
        }
        String content = "";
        for (Student st : studentList) {
            content += st.getId() + ":" + st.getName() + ":" + st.getAge() + ":" + st.getRollNumber() + ":" + st.getStandard() + "\n";
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void displayStudentsWithRollNumber(String file) {
        Scanner inp = new Scanner(System.in);
        BufferedReader br = null;
        String s = "";
        try {
            br = new BufferedReader(new FileReader(file));
            while ((s = br.readLine()) != null) {
                String data[] = s.split(":");
                studentList.add(new Student(data[0], data[1], data[2], data[3], data[4]));
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.print("Enter the Student roll number\nDo you want to Search : ");
        String rollNumber = inp.next();
        boolean notFound = true;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getRollNumber().equals(rollNumber)) {
                System.out.println("Students with this roll number are.");
                System.out.println(studentList.get(i));
                notFound = false;
            }
        }
        if (notFound) {
            System.out.println("Not found with this information");
        }
        String content = "";
        for (Student st : studentList) {
            content += st.getId() + ":" + st.getName() + ":" + st.getAge() + ":" + st.getRollNumber() + ":" + st.getStandard() + "\n";
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayWithName(String file) {
        Scanner inp = new Scanner(System.in);
        BufferedReader br = null;
        String s = "";
        try {
            br = new BufferedReader(new FileReader(file));
            while ((s = br.readLine()) != null) {
                String data[] = s.split(":");
                studentList.add(new Student(data[0], data[1], data[2], data[3], data[4]));
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.print("Enter the Student name\nDo you want to Search : ");
        String name = inp.next();
        boolean notFound = true;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getName().equalsIgnoreCase(name)) {
                System.out.println("Students with this name are.");
                System.out.println(studentList.get(i));
                notFound = false;
            }
        }
        if (notFound) {
            System.out.println("Not found with this information");
        }
        String content = "";
        for (Student st : studentList) {
            content += st.getId() + ":" + st.getName() + ":" + st.getAge() + ":" + st.getRollNumber() + ":" + st.getStandard() + "\n";
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

