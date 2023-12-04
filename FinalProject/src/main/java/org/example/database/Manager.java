package org.example.database;

import java.sql.*;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class Manager {
  private static String connectionUrl = 
    "jdbc:sqlserver://127.0.0.1:1433;" 
    + "database=database;"
    + "user=sa;" 
    + "password=yourStrong(!)Password;" 
    + "encrypt=true;"
    + "trustServerCertificate=true;" 
    + "loginTimeout=15;";
  
  //private static ResultSet resultset = null;

  public static void Connect(){
      SQLServerDataSource ds = new SQLServerDataSource();
      ds.setURL(connectionUrl);
      // Connect to the database
    try (Connection connection = ds.getConnection()){
      System.out.println("Successfully connected to the database!");
    } catch (SQLException e){
      e.printStackTrace();
    }
  }

  public static void AddProgram(String name, String title, String type) {
    System.out.println("AddProgram"); 
  }

  public static void AddCourse(String name, String credit, String departmentID, String term, String location, String finalTime) {
    System.out.println("AddCourse");
  }

  public static void AddDepartment(String name) {
    System.out.println("AddDepartment");
  }

  public static void AddGroup(String groupName, String credit) {
    System.out.println("AddGroup");
  }

  public static void AddClass(String time, String type) {
    System.out.println("AddClass");
  }

  public static void LinkProgramGroup(String programID, String groupID) {
    System.out.println("LinkProgramGroup");
  }

  public static void LinkGroupCourse(String groupID, String courseID) {
    System.out.println("LinkGroupAndCourse");
  }

  public static void LinkCoursePrereq(String courseID, String prereqID) {
    System.out.println("LinkCourseAndPrerequisite");
  }

  public static void LinkDepartmentProgram(String departmentID, String programID) {
    System.out.println("LinkDepartmentAndProgram");
  }

  public static void LinkCourseClass(String courseID, String classID) {
    System.out.println("LinkCourseAndClass");
  }

  public static void UpdateProgram(String programID, String name, String title, String type) {
    System.out.println("UpdateProgram");
  }

  public static void UpdateCourse(String courseID, String name, String credit, String departmentID, String term, String location, String finalTime) {
    System.out.println("UpdateCourse");
  }

  public static void UpdateDepartment(int departmentID, String name) {
    System.out.println("UpdateDepartment");
  }

  public static void UpdateGroup(String groupID, String groupName, int credit) {
    System.out.println("UpdateGroup");
  }

  public static void UpdateClass(int classID, String time, String type) {
    System.out.println("UpdateClass");
  }

  public static void DeleteProgram(int programID) {
    System.out.println("DeleteProgram");
  }

  public static void DeleteCourse(int courseID) {
    System.out.println("DeleteCourse");
  }

  public static void DeleteDepartment(int departmentID) {
    System.out.println("DeleteDepartment");
  }

  public static void DeleteGroup(int groupID) {
    System.out.println("DeleteGroup");
  }

  public static void DeleteClass(int classID) {
    System.out.println("DeleteClass");
  }

  public static void SelectProgramByName(String programName) {
    System.out.println("SelectProgramByName");
  }

  public static void SelectCourseByName(String courseName) {
    System.out.println("SelectCourseByName");
  }

  public static void SelectDepartmentByName(String departmentName) {
    System.out.println("SelectDepartmentByName");
  }

  public static void SelectGroupByName(String groupName) {
    System.out.println("SelectGroupByName");
  }

  public static void SelectClassByType(String classType) {
    System.out.println("SelectClassByType");
  }

  public static void SelectCoursesByDepartmentName(String departmentName) {
    System.out.println("SelectCoursesByDepartmentName");
  }

  public static void SelectClassesByCourseName(String courseName) {
    System.out.println("SelectClassesByCourseName");
  }

  public static void AddGroupsToProgramAndGetTotalCredits(int programID, String groupNames) {
    System.out.println("AddGroupsToProgramAndGetTotalCredits");
  }

  public static void AddCourseAndAssignToGroup(String courseName, int credit, int departmentID, String term, String location, String finalTime, String groupName) {
    System.out.println("AddCourseAndAssignToGroup");
  }

  public static void AddPrerequisitesToCourse(int courseID, int[] prerequisiteIDs) {
    System.out.println("AddPrerequisitesToCourse");
  }

  public static void GenerateClassesForSemester(int courseID, String semesterStart, String semesterEnd, String classDay, String classTime, String classType) {
    System.out.println("GenerateClassesForSemester");
  }
}

