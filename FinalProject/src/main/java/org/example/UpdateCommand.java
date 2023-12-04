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
  // -p, --programId
  // -n, --name
  // -t, --type
  //
  @Override
  public Integer call() throws Exception {
    return 0;
  }
}
