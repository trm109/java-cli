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

@CommandLine.Command(name = "add")
public class AddCommand implements Callable<Integer> {
  // Define what to add.
  @CommandLine.Parameters(index = "0", description = "Table to add to. [Program, Course, Department, Group, Class]")
  private String table;
  
  // Define Options.
  // Possible Options:
  // --name, --title, --type, --credit, --department, --term, --location, --finalTime, --time
  // --name
  @CommandLine.Option(names = {"-n", "--name"}, description = "Name of the program, course, department, group, or class.")
  private String name;

  // --title
  @CommandLine.Option(names = {"-t", "--title"}, description = "Title of the program, course, department, group, or class.")
  private String title;

  // --type
  @CommandLine.Option(names = {"-y", "--type"}, description = "Type of the program, course, department, group, or class.")
  private String type;

  // --credit
  @CommandLine.Option(names = {"-c", "--credit"}, description = "Credit of the program, course, department, group, or class.")
  private String credit;

  // --department
  @CommandLine.Option(names = {"-d", "--departmentId"}, description = "Department ID of the program, course, department, group, or class.")
  private String department;
  
  // --term
  @CommandLine.Option(names = {"-r", "--term"}, description = "Term of the program, course, department, group, or class.")
  private String term;

  // --location
  @CommandLine.Option(names = {"-l", "--location"}, description = "Location of the program, course, department, group, or class.")
  private String location;

  // --finalTime
  @CommandLine.Option(names = {"-f", "--finalTime"}, description = "Final Time of the program, course, department, group, or class.")
  private String finalTime;
  
  // --time
  @CommandLine.Option(names = {"-i", "--time"}, description = "Time of the program, course, department, group, or class.")
  private String time;

  @Override
  public Integer call() throws Exception {
    // switch case on param
    switch (table) {
      case "Program":
        // Add Program
        // Check required fields
        if (name == null || title == null || type == null) {
          System.out.println("Missing required fields.");
          return 1;
        }
        Manager.AddProgram(name, title, type);
        break;
      case "Course":
        // Add Course
        // Check required fields (name, credit, department, term, location, finalTime)
        if (name == null || credit == null || department == null || term == null || location == null || finalTime == null) {
          System.out.println("Missing required fields.");
          return 1;
        }
        Manager.AddCourse(name, credit, department, term, location, finalTime);
        break;
      case "Department":
        // Add Department
        // Check required fields (name)
        if (name == null) {
          System.out.println("Missing required fields.");
          return 1;
        }
        Manager.AddDepartment(name);
        break;
      case "Group":
        // Add Group
        // Check required fields (name, credit)
        if (name == null || credit == null) {
          System.out.println("Missing required fields.");
          return 1;
        }
        Manager.AddGroup(name, credit);
        break;
      case "Class":
        // Add Class
        // Check required fields (time, type)
        if (time == null || type == null) {
          System.out.println("Missing required fields.");
          return 1;
        }
        Manager.AddClass(time, type);
        break;
      default:
        System.out.println("Invalid table name.");
        break;
    }
    return 0;
  }
}
