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

/*
CREATE PROCEDURE UpdateProgram
   @programID INT,
   @name VARCHAR(50),
   @title VARCHAR(50),
   @type VARCHAR(20)
AS
BEGIN
   UPDATE program
   SET name = @name, title = @title, type = @type
   WHERE id = @programID;
END;

CREATE PROCEDURE UpdateCourse
   @courseID INT,
   @name VARCHAR(50),
   @credit INT,
   @departmentID INT,
   @term VARCHAR(20),
   @location VARCHAR(50),
   @finalTime DATETIME
AS
BEGIN
   UPDATE course
   SET name = @name, credit = @credit, department_id = @departmentID,
       term = @term, location = @location, final_time = @finalTime
   WHERE id = @courseID;
END;

CREATE PROCEDURE UpdateDepartment
   @departmentID INT,
   @name VARCHAR(50)
AS
BEGIN
   UPDATE department
   SET name = @name
   WHERE id = @departmentID;
END;

CREATE PROCEDURE UpdateGroup
   @groupID INT,
   @groupName VARCHAR(50),
   @credit INT
AS
BEGIN
   UPDATE [group]
   SET group_name = @groupName, credit = @credit
   WHERE id = @groupID;
END;

CREATE PROCEDURE UpdateClass
   @classID INT,
   @time TIME,
   @type VARCHAR(20)
AS
BEGIN
   UPDATE class
   SET time = @time, type = @type
   WHERE id = @classID;
END;
*/
@CommandLine.Command(name = "update", description = "Update a table entry")
public class UpdateCommand implements Callable<Integer> {
  // Define which table to update
  @CommandLine.Parameters(index = "0", description = "Table name. Example: program, course, department, group, class")
  private String table;

  // Define Options
  // Possible Options:
  // - programId
  // - name
  // - title
  // - type
  // - courseID
  // - credit
  // - departmentID
  // - term
  // - location
  // - finalTime
  // - groupID
  // - groupName
  // - classID
  // - time
  @CommandLine.Option(names = {"-p", "--programId"}, description = "Program ID")
  private String programId;

  @CommandLine.Option(names = {"-n", "--name"}, description = "Name")
  private String name;

  @CommandLine.Option(names = {"-t", "--title"}, description = "Title")
  private String title;
  
  @CommandLine.Option(names = {"-y", "--type"}, description = "Type")
  private String type;

  @CommandLine.Option(names = {"-c", "--courseID"}, description = "Course ID")
  private String courseId;
  
  @CommandLine.Option(names = {"-r", "--credit"}, description = "Credit")
  private String credit;

  @CommandLine.Option(names = {"-d", "--departmentID"}, description = "Department ID")
  private String departmentId;
  
  @CommandLine.Option(names = {"-m", "--term"}, description = "Term")
  private String term;

  @CommandLine.Option(names = {"-l", "--location"}, description = "Location")
  private String location;

  @CommandLine.Option(names = {"-f", "--finalTime"}, description = "Final Time")
  private String finalTime;

  @CommandLine.Option(names = {"-g", "--groupID"}, description = "Group ID")
  private String groupId;

  @CommandLine.Option(names = {"-o", "--groupName"}, description = "Group Name")
  private String groupName;

  @CommandLine.Option(names = {"-a", "--classID"}, description = "Class ID")
  private String classId;

  @CommandLine.Option(names = {"-i", "--time"}, description = "Time")
  private String time;

  @Override
  public Integer call() throws Exception {
    switch (table) {
      case "program":
        // Update program
        // Check Required Fields:
        // Program ID
        // Name
        // Title
        // Type
        if (programId == null || name == null || title == null || type == null) {
          System.out.println("Missing required fields");
          System.out.println("Required fields: programId, name, title, type");
          return 1;
        }
        Manager.UpdateProgram(programId, name, title, type);
        break;
      case "course":
        // Update course
        // Check Required Fields:
        // Course ID
        // Name
        // Credit
        // Department ID
        // Term
        // Location
        // Final Time
        if (courseId == null || name == null || credit == null || departmentId == null || term == null || location == null || finalTime == null) {
          System.out.println("Missing required fields");
          System.out.println("Required fields: courseId, name, credit, departmentId, term, location, finalTime");
          return 1;
        }
        Manager.UpdateCourse(courseId, name, credit, departmentId, term, location, finalTime);
        break;
      case "department":
        // Update department
        // Check Required Fields:
        // Department ID
        // Name
        if (departmentId == null || name == null) {
          System.out.println("Missing required fields");
          System.out.println("Required fields: departmentId, name");
          return 1;
        }
        Manager.UpdateDepartment(departmentId, name);
        break;
      case "group":
        // Update group
        // Check Required Fields:
        // Group ID
        // Group Name
        // Credit
        if (groupId == null || groupName == null || credit == null) {
          System.out.println("Missing required fields");
          System.out.println("Required fields: groupId, groupName, credit");
          return 1;
        }
        Manager.UpdateGroup(groupId, groupName, credit);
        break;
      case "class":
        // Update class
        // Check Required Fields:
        // Class ID
        // Time
        // Type
        if (classId == null || time == null || type == null) {
          System.out.println("Missing required fields");
          System.out.println("Required fields: classId, time, type");
          return 1;
        }
        Manager.UpdateClass(classId, time, type);
        break;
      default:
        System.out.println("Invalid table name");
        return 1;
    }
    return 0;
  }
}
