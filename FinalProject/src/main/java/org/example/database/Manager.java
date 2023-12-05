package org.example.database;

import java.sql.*;
import java.util.Properties;

public class Manager {
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
      //System.out.println("Connected to the PostgreSQL server successfully.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void ClearDatabase(){
    try{
      Manager.Connect();
      Statement stmt = conn.createStatement();
      String sql = "DROP TABLE IF EXISTS program, course, department, grp, class, program_group, group_course, course_prereq, department_program, course_class;";
      stmt.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static void InitializeDatabase(){
    // Create  tables if they don't exist
    try{
      Manager.Connect();
    } catch (Exception e) {
      e.printStackTrace();
    }
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
      String sql = "CREATE TABLE IF NOT EXISTS course (id SERIAL PRIMARY KEY, name VARCHAR(50) NOT NULL, credit INT NOT NULL, department_id INT NOT NULL, term VARCHAR(20) NOT NULL, location VARCHAR(50) NOT NULL, final_time VARCHAR(20) NOT NULL, CONSTRAINT FK_dept_course FOREIGN KEY (department_id) REFERENCES department(id) ON DELETE CASCADE);";
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
  // #WORKING
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

  // #WORKING
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

  // #WORKING
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

  // #WORKING
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

  // #WORKING
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

  // #WORKING
  public static void UpdateProgram(String programID, String name, String title, String type) {
    Manager.Connect();
    try{
      PreparedStatement ps = Manager.conn.prepareStatement("UPDATE program SET name = ?, title = ?, type = ? WHERE id = ?");
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
  
  // #WORKING
  public static void UpdateCourse(String courseID, String name, String credit, String departmentID, String term, String location, String finalTime) {
    Manager.Connect();
    try{
      PreparedStatement ps = Manager.conn.prepareStatement("UPDATE course SET name = ?, credit = ?, department_id = ?, term = ?, location = ?, final_time = ? WHERE id = ?");
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

  // #WORKING
  public static void UpdateDepartment(String departmentID, String name) {
    Manager.Connect();
    try{
      PreparedStatement ps = Manager.conn.prepareStatement("UPDATE department SET name = ? WHERE id = ?");
      ps.setString(1, name);
      ps.setInt(2, Integer.parseInt(departmentID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("UpdateDepartment");
  }

  // #WORKING
  public static void UpdateGroup(String groupID, String groupName, String credit) {
    Manager.Connect();
    try{
      PreparedStatement ps = Manager.conn.prepareStatement("UPDATE grp SET group_name = ?, credit = ? WHERE id = ?");
      ps.setString(1, groupName);
      ps.setInt(2, Integer.parseInt(credit));
      ps.setInt(3, Integer.parseInt(groupID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("UpdateGroup");
  }

  // #WORKING
  public static void UpdateClass(String classID, String time, String type) {
    Manager.Connect();
    try{
      PreparedStatement ps = Manager.conn.prepareStatement("UPDATE class SET time = ?, type = ? WHERE id = ?");
      ps.setString(1, time);
      ps.setString(2, type);
      ps.setInt(3, Integer.parseInt(classID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("UpdateClass");
  }

  // #WORKING
  public static void DeleteProgram(String programID) {
    Manager.Connect();
    try {
      // delete from program_group
      PreparedStatement psA = Manager.conn.prepareStatement("DELETE FROM program_group WHERE program_id = ?");
      psA.setInt(1, Integer.parseInt(programID));
      psA.executeUpdate();
      // delete from department_program
      PreparedStatement psB = Manager.conn.prepareStatement("DELETE FROM department_program WHERE program_id = ?");
      psB.setInt(1, Integer.parseInt(programID));
      psB.executeUpdate();
      // delete from program
      PreparedStatement ps = Manager.conn.prepareStatement("DELETE FROM program WHERE id = ?");
      ps.setInt(1, Integer.parseInt(programID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("DeleteProgram");
  }

  // #WORKING
  public static void DeleteCourse(String courseID) {
    Manager.Connect();
    try {
      // delete from course_class
      PreparedStatement psA = Manager.conn.prepareStatement("DELETE FROM course_class WHERE course_id = ?");
      psA.setInt(1, Integer.parseInt(courseID));
      psA.executeUpdate();
      // delete from course_prereq
      PreparedStatement psB = Manager.conn.prepareStatement("DELETE FROM course_prereq WHERE course_id = ?");
      psB.setInt(1, Integer.parseInt(courseID));
      psB.executeUpdate();
      PreparedStatement psC = Manager.conn.prepareStatement("DELETE FROM course_prereq WHERE prereq_id = ?");
      psC.setInt(1, Integer.parseInt(courseID));
      psC.executeUpdate();
      // delete from group_course
      PreparedStatement psD = Manager.conn.prepareStatement("DELETE FROM group_course WHERE course_id = ?");
      psD.setInt(1, Integer.parseInt(courseID));
      psD.executeUpdate();
      // delete from course
      PreparedStatement ps = Manager.conn.prepareStatement("DELETE FROM course WHERE id = ?");
      ps.setInt(1, Integer.parseInt(courseID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("DeleteCourse");
  }

  // #WORKING
  public static void DeleteDepartment(String departmentID) {
    Manager.Connect();
    try {
      // delete from department_program
      PreparedStatement psA = Manager.conn.prepareStatement("DELETE FROM department_program WHERE department_id = ?");
      psA.setInt(1, Integer.parseInt(departmentID));
      psA.executeUpdate();
      // delete from courses w/ department_id from course_prereq
      PreparedStatement psC = Manager.conn.prepareStatement("DELETE FROM course_prereq WHERE course_id IN (SELECT id FROM course WHERE department_id = ?)");
      psC.setInt(1, Integer.parseInt(departmentID));
      psC.executeUpdate();
      PreparedStatement psD = Manager.conn.prepareStatement("DELETE FROM course_prereq WHERE prereq_id IN (SELECT id FROM course WHERE department_id = ?)");
      psD.setInt(1, Integer.parseInt(departmentID));
      psD.executeUpdate();
      // delete from courses w/ department_id from group_course
      PreparedStatement psE = Manager.conn.prepareStatement("DELETE FROM group_course WHERE course_id IN (SELECT id FROM course WHERE department_id = ?)");
      psE.setInt(1, Integer.parseInt(departmentID));
      psE.executeUpdate();
      // delete from any course with department_id
      PreparedStatement psB = Manager.conn.prepareStatement("DELETE FROM course WHERE department_id = ?");
      psB.setInt(1, Integer.parseInt(departmentID));
      psB.executeUpdate();
      // delete from department
      PreparedStatement ps = Manager.conn.prepareStatement("DELETE FROM department WHERE id = ?");
      ps.setInt(1, Integer.parseInt(departmentID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("DeleteDepartment");
  }

  // #WORKING
  public static void DeleteGroup(String groupID) {
    Manager.Connect();
    try {
      // delete from program_group
      PreparedStatement psA = Manager.conn.prepareStatement("DELETE FROM program_group WHERE group_id = ?");
      psA.setInt(1, Integer.parseInt(groupID));
      psA.executeUpdate();
      // delete from group_course
      PreparedStatement psB = Manager.conn.prepareStatement("DELETE FROM group_course WHERE group_id = ?");
      psB.setInt(1, Integer.parseInt(groupID));
      psB.executeUpdate();
      // delete from grp
      PreparedStatement ps = Manager.conn.prepareStatement("DELETE FROM grp WHERE id = ?");
      ps.setInt(1, Integer.parseInt(groupID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("DeleteGroup");
  }

  // #WORKING
  public static void DeleteClass(String classID) {
    Manager.Connect();
    try {
      // delete from course_class
      PreparedStatement psA = Manager.conn.prepareStatement("DELETE FROM course_class WHERE class_id = ?");
      psA.setInt(1, Integer.parseInt(classID));
      psA.executeUpdate();
      // delete from class
      PreparedStatement ps = Manager.conn.prepareStatement("DELETE FROM class WHERE id = ?");
      ps.setInt(1, Integer.parseInt(classID));
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("DeleteClass");
  }

  // #WORKING
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

  // #WORKING
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

  // #WORKING
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

  // #WORKING
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

  // #WORKING
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
  // #WORKING
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

  // #WORKING
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
        PreparedStatement ps = Manager.conn.prepareStatement("INSERT INTO program_group (program_id, group_id) VALUES (?, (SELECT id FROM grp WHERE group_name = ? LIMIT 1))");
        ps.setInt(1, Integer.parseInt(programID));
        ps.setString(2, groupName);
        ps.executeUpdate();
        System.out.println("Assigned group to program");
        try {
          PreparedStatement psA = Manager.conn.prepareStatement("SELECT SUM(credit) as total_credits FROM grp WHERE id IN (SELECT group_id FROM program_group WHERE program_id = ? LIMIT 1)");
          psA.setInt(1, Integer.parseInt(programID));
          ResultSet rs = psA.executeQuery();
          while (rs.next()) {
            System.out.println(rs.getString("total_credits"));
          }
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("SQL query failed. programId may be invalid.");
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("SQL query failed. programId or groupName may be invalid.");
      }
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
      try {
        // !! Not working.
        PreparedStatement psA = Manager.conn.prepareStatement("INSERT INTO group_course (group_id, course_id) VALUES ((SELECT id FROM grp WHERE group_name = ? LIMIT 1), (SELECT id FROM course WHERE name = ? LIMIT 1))");
        psA.setString(1, groupName);
        psA.setString(2, courseName);
        psA.executeUpdate();
        System.out.println("Assigned course to group");
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("SQL query failed. courseName, credit, departmentID, term, location, finalTime, or groupName may be invalid.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("SQL query failed. courseName, credit, departmentID, term, location, finalTime, or groupName may be invalid.");
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
        System.out.println("Assigned prereq " + prerequisiteID + " to course " + courseID);
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("SQL query failed. courseID or prerequisiteIDs may be invalid.");
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
    try {
      // Create stored procedure
      Statement stmt = Manager.conn.createStatement();
      String sql = "CREATE OR REPLACE PROCEDURE GenerateClassesForSemester( IN courseID INT, IN semesterStart DATE, IN semesterEnd DATE, IN classDay VARCHAR(10), IN classTime VARCHAR(20), IN classType VARCHAR(20) ) AS $$ DECLARE currentDate DATE := semesterStart; newClassID INT; BEGIN WHILE currentDate <= semesterEnd LOOP IF EXTRACT(ISODOW FROM currentDate) = 1 AND classDay = 'Monday' THEN INSERT INTO class (time, type) VALUES (classTime, classType) RETURNING id INTO newClassID; INSERT INTO course_class (course_id, class_id) VALUES (courseID, newClassID); ELSIF EXTRACT(ISODOW FROM currentDate) = 2 AND classDay = 'Tuesday' THEN INSERT INTO class (time, type) VALUES (classTime, classType) RETURNING id INTO newClassID; INSERT INTO course_class (course_id, class_id) VALUES (courseID, newClassID); ELSIF EXTRACT(ISODOW FROM currentDate) = 3 AND classDay = 'Wednesday' THEN INSERT INTO class (time, type) VALUES (classTime, classType) RETURNING id INTO newClassID; INSERT INTO course_class (course_id, class_id) VALUES (courseID, newClassID); ELSIF EXTRACT(ISODOW FROM currentDate) = 4 AND classDay = 'Thursday' THEN INSERT INTO class (time, type) VALUES (classTime, classType) RETURNING id INTO newClassID; INSERT INTO course_class (course_id, class_id) VALUES (courseID, newClassID); ELSIF EXTRACT(ISODOW FROM currentDate) = 5 AND classDay = 'Friday' THEN INSERT INTO class (time, type) VALUES (classTime, classType) RETURNING id INTO newClassID; INSERT INTO course_class (course_id, class_id) VALUES (courseID, newClassID); ELSIF EXTRACT(ISODOW FROM currentDate) = 6 AND classDay = 'Saturday' THEN INSERT INTO class (time, type) VALUES (classTime, classType) RETURNING id INTO newClassID; INSERT INTO course_class (course_id, class_id) VALUES (courseID, newClassID); ELSIF EXTRACT(ISODOW FROM currentDate) = 7 AND classDay = 'Sunday' THEN INSERT INTO class (time, type) VALUES (classTime, classType) RETURNING id INTO newClassID; INSERT INTO course_class (course_id, class_id) VALUES (courseID, newClassID); END IF; currentDate := currentDate + INTERVAL '1 week'; END LOOP; END; $$ LANGUAGE plpgsql;";
      // CREATE OR REPLACE PROCEDURE GenerateClassesForSemester( IN courseID INT, IN semesterStart DATE, IN semesterEnd DATE, IN classDay VARCHAR(10), IN classTime VARCHAR(20), IN classType VARCHAR(20) ) AS $$ DECLARE currentDate DATE := semesterStart; newClassID INT; BEGIN WHILE currentDate <= semesterEnd LOOP IF EXTRACT(ISODOW FROM currentDate) = CASE classDay WHEN 'Monday' THEN 1 WHEN 'Tuesday' THEN 2 WHEN 'Wednesday' THEN 3 WHEN 'Thursday' THEN 4 WHEN 'Friday' THEN 5 WHEN 'Saturday' THEN 6 WHEN 'Sunday' THEN 7 END THEN INSERT INTO class (time, type) VALUES (classTime, classType) RETURNING id INTO newClassID; INSERT INTO course_class (course_id, class_id) VALUES (courseID, newClassID); END IF; currentDate := currentDate + INTERVAL '1 week'; END LOOP; END; $$ LANGUAGE plpgsql;
      stmt.executeUpdate(sql);
      System.out.println("Created stored procedure");
      // Call  stored procedure
      PreparedStatement ps = Manager.conn.prepareStatement("CALL GenerateClassesForSemester(?, ?, ?, ?, ?, ?)");
      ps.setInt(1, Integer.parseInt(courseID));
      //Date.valueOf(
      ps.setDate(2, Date.valueOf(semesterStart));
      ps.setDate(3, Date.valueOf(semesterEnd));
      ps.setString(4, classDay);
      ps.setString(5, classTime);
      ps.setString(6, classType);
      ps.executeUpdate();
      System.out.println("Called stored procedure");
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("GenerateClassesForSemester");
  }
}

