package org.example.database;

import java.sql.*;
import java.util.Properties;

public class Manager {
  private static String connectionUrl = 
    "jdbc:postgresql://db:1433/database;" 
    + "databaseName=Database;"
    + "user=sa;" 
    + "password=yourStrong(!)Password;" 
    + "encrypt=true;"
    + "trustServerCertificate=true;" 
    + "loginTimeout=15;";
  public static Connection conn = null;
  
  //private static ResultSet resultset = null;

  public static void Connect(){
    try{
      Class.forName("org.postgresql.Driver");
      String url = "jdbc:postgresql://db:5432/database";

      Properties props = new Properties();

      props.setProperty("user", "user");
      props.setProperty("password", "password!");
      conn = DriverManager.getConnection(url, props);
      // print connection info
      System.out.println("Connected to the PostgreSQL server successfully.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    InitializeDatabase();
  }
  public static void InitializeDatabase(){
    // Create  tables if they don't exist

    // Create program table
    /*
       CREATE TABLE program (
       id SERIAL PRIMARY KEY,
       name VARCHAR(50) NOT NULL,
       title VARCHAR(50) NOT NULL,
       type VARCHAR(20) NOT NULL
       );
    */
    try {
      Statement stmt = conn.createStatement();
      String sql = "CREATE TABLE IF NOT EXISTS program (id SERIAL PRIMARY KEY, name VARCHAR(50) NOT NULL, title VARCHAR(50) NOT NULL, type VARCHAR(20) NOT NULL);";
      stmt.executeUpdate(sql);
      System.out.println("Created program table");
    } catch (Exception e) {
      e.printStackTrace();
    }
  
    // Create course table
    //CREATE TABLE course(
    //    id SERIAL PRIMARY KEY,
    //    name VARCHAR(50) NOT NULL,
    //    credit INT NOT NULL,
    //    department_id INT NOT NULL,
    //    term VARCHAR(20) NOT NULL,
    //    location VARCHAR(50) NOT NULL,
    //    final_time DATE NOT NULL,
    //    CONSTRAINT FK_dept_course FOREIGN KEY (department_id) REFERENCES department(id)
    //    );
    try {
      Statement stmt = conn.createStatement();
      String sql = "CREATE TABLE IF NOT EXISTS course (id SERIAL PRIMARY KEY, name VARCHAR(50) NOT NULL, credit INT NOT NULL, department_id INT NOT NULL, term VARCHAR(20) NOT NULL, location VARCHAR(50) NOT NULL, final_time DATE NOT NULL, CONSTRAINT FK_dept_course FOREIGN KEY (department_id) REFERENCES department(id));";
      stmt.executeUpdate(sql);
      System.out.println("Created course table");
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Create department table
    //CREATE TABLE department (
    //    id SERIAL PRIMARY KEY,
    //    name VARCHAR(50) NOT NULL
    //    ON DELETE CASCADE
    //    );
    try {
      Statement stmt = conn.createStatement();
      String sql = "CREATE TABLE IF NOT EXISTS department (id SERIAL PRIMARY KEY, name VARCHAR(50) NOT NULL);";
      stmt.executeUpdate(sql);
      System.out.println("Created department table");
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Create group table
    //CREATE TABLE group (
    //    id SERIAL PRIMARY KEY,
    //    group_name VARCHAR(50) NOT NULL,
    //    credit INT NOT NULL
    //    );
    try {
      Statement stmt = conn.createStatement();
      String sql = "CREATE TABLE IF NOT EXISTS grp (id SERIAL PRIMARY KEY, group_name VARCHAR(50) NOT NULL, credit INT NOT NULL);";
      stmt.executeUpdate(sql);
      System.out.println("Created group table");
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Create class table
    //CREATE TABLE class (
    //    id SERIAL PRIMARY KEY,
    //    time TIME NOT NULL,
    //    type VARCHAR(20) NOT NULL
    //    );
    try {
      Statement stmt = conn.createStatement();
      String sql = "CREATE TABLE IF NOT EXISTS class (id SERIAL PRIMARY KEY, time TIME NOT NULL, type VARCHAR(20) NOT NULL);";
      stmt.executeUpdate(sql);
      System.out.println("Created class table");
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Create program_group table
    //CREATE TABLE program_group (
    //    program_id INT NOT NULL,
    //    group_id INT NOT NULL,
    //    CONSTRAINT FK_program_group_program FOREIGN KEY (program_id) REFERENCES program(id),
    //    CONSTRAINT FK_program_group_group FOREIGN KEY (group_id) REFERENCES group(id),
    //    PRIMARY KEY (program_id, group_id)
    //    );
    try {
      Statement stmt = conn.createStatement();
      String sql = "CREATE TABLE IF NOT EXISTS program_group (program_id INT NOT NULL, group_id INT NOT NULL, CONSTRAINT FK_program_group_program FOREIGN KEY (program_id) REFERENCES program(id), CONSTRAINT FK_program_group_group FOREIGN KEY (group_id) REFERENCES grp(id), PRIMARY KEY (program_id, group_id));";
      stmt.executeUpdate(sql);
      System.out.println("Created program_group table");
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Create group_course table
    //CREATE TABLE group_course (
    //    group_id INT NOT NULL,
    //    course_id INT NOT NULL,
    //    CONSTRAINT FK_group_course_group FOREIGN KEY (group_id) REFERENCES group(id),
    //    CONSTRAINT FK_group_course_course FOREIGN KEY (course_id) REFERENCES course(id),
    //    PRIMARY KEY (group_id, course_id)
    //    );
    try {
      Statement stmt = conn.createStatement();
      String sql = "CREATE TABLE IF NOT EXISTS group_course (group_id INT NOT NULL, course_id INT NOT NULL, CONSTRAINT FK_group_course_group FOREIGN KEY (group_id) REFERENCES grp(id), CONSTRAINT FK_group_course_course FOREIGN KEY (course_id) REFERENCES course(id), PRIMARY KEY (group_id, course_id));";
      stmt.executeUpdate(sql);
      System.out.println("Created group_course table");
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Create course_prerequisite table
    //CREATE TABLE course_prereq (
    //    course_id INT NOT NULL,
    //    prereq_id INT NOT NULL,
    //    CONSTRAINT FK_course_prereq_course FOREIGN KEY (course_id) REFERENCES course(id),
    //    CONSTRAINT FK_course_prereq_prereq FOREIGN KEY (prereq_id) REFERENCES course(id),
    //    PRIMARY KEY (course_id, prereq_id)
    //    );
    
    // Create department_program table
    //CREATE TABLE department_program (
    //    department_id INT NOT NULL,
    //    program_id INT NOT NULL,
    //    CONSTRAINT FK_department_program_department FOREIGN KEY (department_id) REFERENCES department(id),
    //    CONSTRAINT FK_department_program_program FOREIGN KEY (program_id) REFERENCES program(id),
    //    PRIMARY KEY (department_id, program_id),
    //    ON DELETE CASCADE
    //    );
    try {
      Statement stmt = conn.createStatement();
      String sql = "CREATE TABLE IF NOT EXISTS department_program (department_id INT NOT NULL, program_id INT NOT NULL, CONSTRAINT FK_department_program_department FOREIGN KEY (department_id) REFERENCES department(id), CONSTRAINT FK_department_program_program FOREIGN KEY (program_id) REFERENCES program(id), PRIMARY KEY (department_id, program_id));";
      stmt.executeUpdate(sql);
      System.out.println("Created department_program table");
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Create course_class table
    //CREATE TABLE course_class (
    //    course_id INT NOT NULL,
    //    class_id INT NOT NULL,
    //    CONSTRAINT FK_course_class_course FOREIGN KEY (course_id) REFERENCES course(id),
    //    CONSTRAINT FK_course_class_class FOREIGN KEY (class_id) REFERENCES class(id),
    //    PRIMARY KEY (course_id, class_id),
    //    ON DELETE CASCADE
    //    );
    try {
      Statement stmt = conn.createStatement();
      String sql = "CREATE TABLE IF NOT EXISTS course_class (course_id INT NOT NULL, class_id INT NOT NULL, CONSTRAINT FK_course_class_course FOREIGN KEY (course_id) REFERENCES course(id), CONSTRAINT FK_course_class_class FOREIGN KEY (class_id) REFERENCES class(id), PRIMARY KEY (course_id, class_id));";
      stmt.executeUpdate(sql);
      System.out.println("Created course_class table");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static void AddProgram(String name, String title, String type) {
    Manager.Connect();
    // using jdbc, add program to database
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("INSERT INTO program (name, title, type) VALUES (?, ?, ?)");
      ps.setString(1, name);
      ps.setString(2, title);
      ps.setString(3, type);
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    // print success message
    System.out.println("AddProgram"); 
  }

  public static void AddCourse(String name, String credit, String departmentID, String term, String location, String finalTime) {
    Manager.Connect();
    System.out.println("AddCourse");
  }

  public static void AddDepartment(String name) {
    Manager.Connect();
    System.out.println("AddDepartment");
  }

  public static void AddGroup(String groupName, String credit) {
    Manager.Connect();
    System.out.println("AddGroup");
  }

  public static void AddClass(String time, String type) {
    Manager.Connect();
    System.out.println("AddClass");
  }

  public static void LinkProgramGroup(String programID, String groupID) {
    Manager.Connect();
    System.out.println("LinkProgramGroup");
  }

  public static void LinkGroupCourse(String groupID, String courseID) {
    Manager.Connect();
    System.out.println("LinkGroupAndCourse");
  }

  public static void LinkCoursePrereq(String courseID, String prereqID) {
    Manager.Connect();
    System.out.println("LinkCourseAndPrerequisite");
  }

  public static void LinkDepartmentProgram(String departmentID, String programID) {
    Manager.Connect();
    System.out.println("LinkDepartmentAndProgram");
  }

  public static void LinkCourseClass(String courseID, String classID) {
    Manager.Connect();
    System.out.println("LinkCourseAndClass");
  }

  public static void UpdateProgram(String programID, String name, String title, String type) {
    Manager.Connect();
    System.out.println("UpdateProgram");
  }

  public static void UpdateCourse(String courseID, String name, String credit, String departmentID, String term, String location, String finalTime) {
    Manager.Connect();
    System.out.println("UpdateCourse");
  }

  public static void UpdateDepartment(String departmentID, String name) {
    Manager.Connect();
    System.out.println("UpdateDepartment");
  }

  public static void UpdateGroup(String groupID, String groupName, String credit) {
    Manager.Connect();
    System.out.println("UpdateGroup");
  }

  public static void UpdateClass(String classID, String time, String type) {
    Manager.Connect();
    System.out.println("UpdateClass");
  }

  public static void DeleteProgram(String programID) {
    Manager.Connect();
    System.out.println("DeleteProgram");
  }

  public static void DeleteCourse(String courseID) {
    Manager.Connect();
    System.out.println("DeleteCourse");
  }

  public static void DeleteDepartment(String departmentID) {
    Manager.Connect();
    System.out.println("DeleteDepartment");
  }

  public static void DeleteGroup(String groupID) {
    Manager.Connect();
    System.out.println("DeleteGroup");
  }

  public static void DeleteClass(String classID) {
    Manager.Connect();
    System.out.println("DeleteClass");
  }

  public static void SelectProgramByName(String programName) {
    Manager.Connect();
    System.out.println("SelectProgramByName");
  }

  public static void SelectCourseByName(String courseName) {
    Manager.Connect();
    System.out.println("SelectCourseByName");
  }

  public static void SelectDepartmentByName(String departmentName) {
    Manager.Connect();
    System.out.println("SelectDepartmentByName");
  }

  public static void SelectGroupByName(String groupName) {
    Manager.Connect();
    System.out.println("SelectGroupByName");
  }

  public static void SelectClassByType(String classType) {
    Manager.Connect();
    System.out.println("SelectClassByType");
  }

  public static void SelectCoursesByDepartmentName(String departmentName) {
    Manager.Connect();
    System.out.println("SelectCoursesByDepartmentName");
  }

  public static void SelectClassesByCourseName(String courseName) {
    Manager.Connect();
    System.out.println("SelectClassesByCourseName");
  }

  public static void AddGroupsToProgramAndGetTotalCredits(int programID, String groupNames) {
    Manager.Connect();
    System.out.println("AddGroupsToProgramAndGetTotalCredits");
  }

  public static void AddCourseAndAssignToGroup(String courseName, int credit, int departmentID, String term, String location, String finalTime, String groupName) {
    Manager.Connect();
    System.out.println("AddCourseAndAssignToGroup");
  }

  public static void AddPrerequisitesToCourse(int courseID, int[] prerequisiteIDs) {
    Manager.Connect();
    System.out.println("AddPrerequisitesToCourse");
  }

  public static void GenerateClassesForSemester(int courseID, String semesterStart, String semesterEnd, String classDay, String classTime, String classType) {
    Manager.Connect();
    System.out.println("GenerateClassesForSemester");
  }
}

