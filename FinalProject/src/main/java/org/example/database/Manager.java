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
    //    final_time VARCHAR(20) NOT NULL,
    //    CONSTRAINT FK_dept_course FOREIGN KEY (department_id) REFERENCES department(id)
    //    );
    try {
      Statement stmt = conn.createStatement();
      String sql = "CREATE TABLE IF NOT EXISTS course (id SERIAL PRIMARY KEY, name VARCHAR(50) NOT NULL, credit INT NOT NULL, department_id INT NOT NULL, term VARCHAR(20) NOT NULL, location VARCHAR(50) NOT NULL, final_time VARCHAR(20) NOT NULL, CONSTRAINT FK_dept_course FOREIGN KEY (department_id) REFERENCES department(id));";
      stmt.executeUpdate(sql);
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
      String sql = "CREATE TABLE IF NOT EXISTS class (id SERIAL PRIMARY KEY, time VARCHAR(20) NOT NULL, type VARCHAR(20) NOT NULL);";
      stmt.executeUpdate(sql);
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
    try{
      Statement stmt = conn.createStatement();
      String sql = "CREATE TABLE IF NOT EXISTS course_prereq (course_id INT NOT NULL, prereq_id INT NOT NULL, CONSTRAINT FK_course_prereq_course FOREIGN KEY (course_id) REFERENCES course(id), CONSTRAINT FK_course_prereq_prereq FOREIGN KEY (prereq_id) REFERENCES course(id), PRIMARY KEY (course_id, prereq_id));";
      stmt.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // #WORKING
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
  // #WORKING
  public static void AddCourse(String name, String credit, String departmentID, String term, String location, String finalTime) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("INSERT INTO course (name, credit, department_id, term, location, final_time) VALUES (?, ?, ?, ?, ?, ?)");
      ps.setString(1, name);
      ps.setInt(2, Integer.parseInt(credit));
      ps.setInt(3, Integer.parseInt(departmentID));
      ps.setString(4, term);
      ps.setString(5, location);
      ps.setObject(6, finalTime);
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("AddCourse");
  }
  // #WORKING
  public static void AddDepartment(String name) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("INSERT INTO department (name) VALUES (?)");
      ps.setString(1, name);
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("AddDepartment");
  }
  // #WORKING
  public static void AddGroup(String groupName, String credit) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("INSERT INTO grp (group_name, credit) VALUES (?, ?)");
      ps.setString(1, groupName);
      ps.setInt(2, Integer.parseInt(credit));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("AddGroup");
  }
  
  // #WORKING
  public static void AddClass(String time, String type) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("INSERT INTO class (time, type) VALUES (?, ?)");
      ps.setString(1, time);
      ps.setString(2, type);
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("AddClass");
  }
  // CREATE PROCEDURE LinkProgramAndGroup
  //    @programID INT,
  //    @groupID INT
  // AS
  // BEGIN
  //    INSERT INTO program_group (program_id, group_id)
  //    VALUES (@programID, @groupID);
  // END;
  // #UNTESTED
  public static void LinkProgramGroup(String programID, String groupID) {
    Manager.Connect();
    try{
      PreparedStatement ps = Manager.conn.prepareStatement("INSERT INTO program_group (program_id, group_id) VALUES (?, ?)");
      ps.setInt(1, Integer.parseInt(programID));
      ps.setInt(2, Integer.parseInt(groupID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("LinkProgramGroup");
  }

  // #UNTESTED
  public static void LinkGroupCourse(String groupID, String courseID) {
    Manager.Connect();
    try{
      PreparedStatement ps = Manager.conn.prepareStatement("INSERT INTO group_course (group_id, course_id) VALUES (?, ?)");
      ps.setInt(1, Integer.parseInt(groupID));
      ps.setInt(2, Integer.parseInt(courseID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("LinkGroupAndCourse");
  }

  // #UNTESTED
  public static void LinkCoursePrereq(String courseID, String prereqID) {
    Manager.Connect();
    try{
      PreparedStatement ps = Manager.conn.prepareStatement("INSERT INTO course_prereq (course_id, prereq_id) VALUES (?, ?)");
      ps.setInt(1, Integer.parseInt(courseID));
      ps.setInt(2, Integer.parseInt(prereqID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("LinkCourseAndPrerequisite");
  }

  // #UNTESTED
  public static void LinkDepartmentProgram(String departmentID, String programID) {
    Manager.Connect();
    try{
      PreparedStatement ps = Manager.conn.prepareStatement("INSERT INTO department_program (department_id, program_id) VALUES (?, ?)");
      ps.setInt(1, Integer.parseInt(departmentID));
      ps.setInt(2, Integer.parseInt(programID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("LinkDepartmentAndProgram");
  }

  // #UNTESTED
  public static void LinkCourseClass(String courseID, String classID) {
    Manager.Connect();
    try{
      PreparedStatement ps = Manager.conn.prepareStatement("INSERT INTO course_class (course_id, class_id) VALUES (?, ?)");
      ps.setInt(1, Integer.parseInt(courseID));
      ps.setInt(2, Integer.parseInt(classID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("LinkCourseAndClass");
  }

  // #UNTESTED
  public static void UpdateProgram(String programID, String name, String title, String type) {
    Manager.Connect();
    try{
      PreparedStatement ps = Manager.conn.prepareStatement("UPVARCHAR(20) program SET name = ?, title = ?, type = ? WHERE id = ?");
      ps.setString(1, name);
      ps.setString(2, title);
      ps.setString(3, type);
      ps.setInt(4, Integer.parseInt(programID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("UpdateProgram");
  }
  
  // #UNTESTED
  public static void UpdateCourse(String courseID, String name, String credit, String departmentID, String term, String location, String finalTime) {
    Manager.Connect();
    try{
      PreparedStatement ps = Manager.conn.prepareStatement("UPVARCHAR(20) course SET name = ?, credit = ?, department_id = ?, term = ?, location = ?, final_time = ? WHERE id = ?");
      ps.setString(1, name);
      ps.setInt(2, Integer.parseInt(credit));
      ps.setInt(3, Integer.parseInt(departmentID));
      ps.setString(4, term);
      ps.setString(5, location);
      ps.setObject(6, finalTime);
      ps.setInt(7, Integer.parseInt(courseID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("UpdateCourse");
  }

  // #UNTESTED
  public static void UpdateDepartment(String departmentID, String name) {
    Manager.Connect();
    try{
      PreparedStatement ps = Manager.conn.prepareStatement("UPVARCHAR(20) department SET name = ? WHERE id = ?");
      ps.setString(1, name);
      ps.setInt(2, Integer.parseInt(departmentID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("UpdateDepartment");
  }

  // #UNTESTED
  public static void UpdateGroup(String groupID, String groupName, String credit) {
    Manager.Connect();
    try{
      PreparedStatement ps = Manager.conn.prepareStatement("UPVARCHAR(20) grp SET group_name = ?, credit = ? WHERE id = ?");
      ps.setString(1, groupName);
      ps.setInt(2, Integer.parseInt(credit));
      ps.setString(3, groupID);
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("UpdateGroup");
  }

  // #UNTESTED
  public static void UpdateClass(String classID, String time, String type) {
    Manager.Connect();
    try{
      PreparedStatement ps = Manager.conn.prepareStatement("UPVARCHAR(20) class SET time = ?, type = ? WHERE id = ?");
      ps.setString(1, time);
      ps.setString(2, type);
      ps.setInt(3, Integer.parseInt(classID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("UpdateClass");
  }

  // #UNTESTED
  public static void DeleteProgram(String programID) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("DELETE FROM program WHERE id = ?");
      ps.setInt(1, Integer.parseInt(programID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("DeleteProgram");
  }

  // #UNTESTED
  public static void DeleteCourse(String courseID) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("DELETE FROM course WHERE id = ?");
      ps.setInt(1, Integer.parseInt(courseID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("DeleteCourse");
  }

  // #UNTESTED
  public static void DeleteDepartment(String departmentID) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("DELETE FROM department WHERE id = ?");
      ps.setInt(1, Integer.parseInt(departmentID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("DeleteDepartment");
  }

  // #UNTESTED
  public static void DeleteGroup(String groupID) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("DELETE FROM grp WHERE id = ?");
      ps.setInt(1, Integer.parseInt(groupID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("DeleteGroup");
  }

  // #UNTESTED
  public static void DeleteClass(String classID) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("DELETE FROM class WHERE id = ?");
      ps.setInt(1, Integer.parseInt(classID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("DeleteClass");
  }

  // #UNTESTED
  public static void SelectProgramByName(String programName) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("SELECT * FROM program WHERE name = ?");
      ps.setString(1, programName);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        System.out.println(rs.getString("id") + " " + rs.getString("name") + " " + rs.getString("title") + " " + rs.getString("type"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("SelectProgramByName");
  }

  // #UNTESTED
  public static void SelectCourseByName(String courseName) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("SELECT * FROM course WHERE name = ?");
      ps.setString(1, courseName);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        System.out.println(rs.getString("id") + " " + rs.getString("name") + " " + rs.getString("credit") + " " + rs.getString("department_id") + " " + rs.getString("term") + " " + rs.getString("location") + " " + rs.getString("final_time"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("SelectCourseByName");
  }

  // #UNTESTED
  public static void SelectDepartmentByName(String departmentName) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("SELECT * FROM department WHERE name = ?");
      ps.setString(1, departmentName);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        System.out.println(rs.getString("id") + " " + rs.getString("name"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("SelectDepartmentByName");
  }

  // #UNTESTED
  public static void SelectGroupByName(String groupName) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("SELECT * FROM grp WHERE group_name = ?");
      ps.setString(1, groupName);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        System.out.println(rs.getString("id") + " " + rs.getString("group_name") + " " + rs.getString("credit"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("SelectGroupByName");
  }

  // #UNTESTED
  public static void SelectClassByType(String classType) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("SELECT * FROM class WHERE type = ?");
      ps.setString(1, classType);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        System.out.println(rs.getString("id") + " " + rs.getString("time") + " " + rs.getString("type"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("SelectClassByType");
  }
  //
  //
  // Below are custom use-cases
  //
  //
  // #UNTESTED
  public static void SelectCoursesByDepartmentName(String departmentName) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("SELECT * FROM course WHERE department_id = (SELECT id FROM department WHERE name = ?)");
      ps.setString(1, departmentName);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        System.out.println(rs.getString("id") + " " + rs.getString("name") + " " + rs.getString("credit") + " " + rs.getString("department_id") + " " + rs.getString("term") + " " + rs.getString("location") + " " + rs.getString("final_time"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("SelectCoursesByDepartmentName");
  }

  // #UNTESTED
  public static void SelectClassesByCourseName(String courseName) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("SELECT * FROM class WHERE id = (SELECT class_id FROM course_class WHERE course_id = (SELECT id FROM course WHERE name = ?))");
      ps.setString(1, courseName);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        System.out.println(rs.getString("id") + " " + rs.getString("time") + " " + rs.getString("type"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("SelectClassesByCourseName");
  }
  
  // Adds groups to a program and returns total credit to get a degree
  // #UNTESTED
  public static void AddGroupsToProgramAndGetTotalCredits(String programID, String[] groupNames) {
    Manager.Connect();
    // create groups.
    for (String groupName : groupNames) {
      try {
        PreparedStatement ps = Manager.conn.prepareStatement("INSERT INTO program_group (program_id, group_id) VALUES (?, (SELECT id FROM grp WHERE group_name = ?))");
        ps.setInt(1, Integer.parseInt(programID));
        ps.setString(2, groupName);
        ps.executeUpdate();
        System.out.println("Assigned group to program");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("SELECT SUM(credit) FROM grp WHERE id IN (SELECT group_id FROM program_group WHERE program_id = ?)");
      ps.setInt(1, Integer.parseInt(programID));
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        System.out.println(rs.getString("SUM(credit)"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("AddGroupsToProgramAndGetTotalCredits");
  }
  // Adds a course, then assigns to a group.
  // #UNTESTED
  public static void AddCourseAndAssignToGroup(String courseName, String credit, String departmentID, String term, String location, String finalTime, String groupName) {
    Manager.Connect();
    try {
      PreparedStatement ps = Manager.conn.prepareStatement("INSERT INTO course (name, credit, department_id, term, location, final_time) VALUES (?, ?, ?, ?, ?, ?)");
      ps.setString(1, courseName);
      ps.setInt(2, Integer.parseInt(credit));
      ps.setInt(3, Integer.parseInt(departmentID));
      ps.setString(4, term);
      ps.setString(5, location);
      ps.setObject(6, finalTime);
      ps.executeUpdate();
      System.out.println("Added course");
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      // !! Not working.
      PreparedStatement ps = Manager.conn.prepareStatement("INSERT INTO group_course (group_id, course_id) VALUES ((SELECT id FROM grp WHERE group_name = ?), (SELECT id FROM course WHERE name = ?))");
      ps.setString(1, groupName);
      ps.setString(2, courseName);
      ps.executeUpdate();
      System.out.println("Assigned course to group");
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("AddCourseAndAssignToGroup");
  }
  // Adds multiple prequisites to a course
  // #UNTESTED
  public static void AddPrerequisitesToCourse(String courseID, String[] prerequisiteIDs) {
    Manager.Connect();
    for (String prerequisiteID : prerequisiteIDs) {
      try {
        PreparedStatement ps = Manager.conn.prepareStatement("INSERT INTO course_prereq (course_id, prereq_id) VALUES (?, ?)");
        ps.setInt(1, Integer.parseInt(courseID));
        ps.setInt(2, Integer.parseInt(prerequisiteID));
        ps.executeUpdate();
        System.out.println("Assigned prereq " + prerequisiteIDs + " to course " + courseID);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    System.out.println("AddPrerequisitesToCourse");
  }
//CREATE OR REPLACE FUNCTION GenerateClassesForSemester(
//    courseID INT,
//    semesterStart VARCHAR(20),
//    semesterEnd VARCHAR(20),
//    classDay VARCHAR(10), -- Day of the week (e.g., 'Monday', 'Tuesday', etc.)
//    classTime TIME,
//    classType VARCHAR(20)
//)
//RETURNS VOID AS $$
//DECLARE
//    currentDate VARCHAR(20) := semesterStart;
//    newClassID INT;
//BEGIN
//    WHILE currentDate <= semesterEnd LOOP
//        -- Check if the current day is the specified class day
//        IF EXTRACT(DOW FROM currentDate) + 1 = EXTRACT(ISODOW FROM CAST(classDay AS VARCHAR(20))) THEN
//            -- Adding 1 to EXTRACT(DOW FROM currentDate) because PostgreSQL returns Sunday as 0, while SQL Server returns it as 1.
//            INSERT INTO class (time, type)
//            VALUES (classTime, classType)
//            RETURNING id INTO newClassID;
//
//            -- Link the class to the course
//            INSERT INTO course_class (course_id, class_id)
//            VALUES (courseID, newClassID);
//        END IF;
//
//        -- Move to the next week
//        currentDate := currentDate + INTERVAL '1 week';
//    END LOOP;
//END;
//$$ LANGUAGE plpgsql;

  // #UNTESTED
  public static void GenerateClassesForSemester(String courseID, String semesterStart, String semesterEnd, String classDay, String classTime, String classType) {
    Manager.Connect();
    
    //try {
    //  // Create stored procedure
    //  Statement stmt = Manager.conn.createStatement("CREATE OR REPLACE FUNCTION GenerateClassesForSemester( courseID INT, semesterStart VARCHAR(20), semesterEnd DATE, classDay VARCHAR(10), -- Day of the week (e.g., 'Monday', 'Tuesday', etc.) classTime TIME, classType VARCHAR(20) ) RETURNS VOID AS $$ DECLARE currentDate DATE := semesterStart; newClassID INT; BEGIN WHILE currentDate <= semesterEnd LOOP -- Check if the current day is the specified class day IF EXTRACT(DOW FROM currentDate) + 1 = EXTRACT(ISODOW FROM CAST(classDay AS DATE)) THEN -- Adding 1 to EXTRACT(DOW FROM currentDate) because PostgreSQL returns Sunday as 0, while SQL Server returns it as 1. INSERT INTO class (time, type) VALUES (classTime, classType) RETURNING id INTO newClassID; -- Link the class to the course INSERT INTO course_class (course_id, class_id) VALUES (courseID, newClassID); END IF; -- Move to the next week currentDate := currentDate + INTERVAL '1 week'; END LOOP; END; $$ LANGUAGE plpgsql;");
    //  stmt.executeUpdate();
    //  System.out.println("Created stored procedure");
    //  // Call  stored procedure
    //  PreparedStatement ps = Manager.conn.prepareStatement("SELECT GenerateClassesForSemester(?, ?, ?, ?, ?, ?)");
    //  ps.setString(1, courseID);
    //  ps.setString(2, semesterStart);
    //  ps.setString(3, semesterEnd);
    //  ps.setString(4, classDay);
    //  ps.setString(5, classTime);
    //  ps.setString(6, classType);
    //  ResultSet rs = ps.executeQuery();
    //  while (rs.next()) {
    //    System.out.println(rs.getString("GenerateClassesForSemester"));
    //  }
    //  System.out.println("Called stored procedure");
    //} catch (Exception e) {
    //  e.printStackTrace();
    //}

    System.out.println("GenerateClassesForSemester");
  }
}

