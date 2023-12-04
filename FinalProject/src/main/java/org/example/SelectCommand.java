package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import picocli.CommandLine;

import org.example.database.Manager;

@CommandLine.Command(name = "select", description = "Select Entries from the Database")
public class SelectCommand implements Callable<Integer> {
  // Define what to select
  @CommandLine.Parameters(index = "0", description = "From which table to select. Example: program-by-name, course-by-name, dept-by-name, group-by-name, class-by-type, course-by-dept-name, class-by-course-name")
  private String table;


  // Define Options
  // -- name
  @CommandLine.Option(names = {"-n", "--name"}, description = "Name of the entry to select")
  private String name;
  // -- type
  @CommandLine.Option(names = {"-t", "--type"}, description = "Type of the entry to select")
  private String type;
  // -- department name
  @CommandLine.Option(names = {"-d", "--dept-name"}, description = "Department name of the entry to select")
  private String deptName;
  // -- course name
  @CommandLine.Option(names = {"-c", "--course-name"}, description = "Course name of the entry to select")
  private String courseName;

  @Override
  public Integer call() throws Exception{
    switch(table) {
      case "program-by-name":
        if (name == null) {
          System.out.println("Please specify a name");
          return 1;
        }
        Manager.SelectProgramByName(name);
        break;
      case "course-by-name":
        if (name == null) {
          System.out.println("Please specify a name");
          return 1;
        }
        Manager.SelectCourseByName(name);
        break;
      case "dept-by-name":
        if (name == null) {
          System.out.println("Please specify a name");
          return 1;
        }
        Manager.SelectDepartmentByName(name);
        break;
      case "group-by-name":
        if (name == null) {
          System.out.println("Please specify a name");
          return 1;
        }
        Manager.SelectGroupByName(name);
        break;
      case "class-by-type":
        if (type == null) {
          System.out.println("Please specify a type");
          return 1;
        }
        Manager.SelectClassByType(type);
        break;
      case "course-by-dept-name":
        if (deptName == null) {
          System.out.println("Please specify a department name");
          return 1;
        }
        Manager.SelectCoursesByDepartmentName(deptName);
        break;
      case "class-by-course-name":
        if (courseName == null) {
          System.out.println("Please specify a course name");
          return 1;
        }
        Manager.SelectClassesByCourseName(courseName);
        break;
      default:
        System.out.println("Please specify a valid table");
        return 1;
    }
    return 0;
  }
}

