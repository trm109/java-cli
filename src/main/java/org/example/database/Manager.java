// class that handles jdbc connection and queries
// CREATE PROCEDURE AddProgram
//    @name VARCHAR(50),
//    @title VARCHAR(50),
//    @type VARCHAR(20)
// AS
// BEGIN
//    INSERT INTO program (name, title, type)
//    VALUES (@name, @title, @type);
// END;
// 
// CREATE PROCEDURE AddCourse
//    @name VARCHAR(50),
//    @credit INT,
//    @departmentID INT,
//    @term VARCHAR(20),
//    @location VARCHAR(50),
//    @finalTime DATETIME
// AS
// BEGIN
//    INSERT INTO course (name, credit, department_id, term, location, final_time)
//    VALUES (@name, @credit, @departmentID, @term, @location, @finalTime);
// END;
// 
// CREATE PROCEDURE AddDepartment
//    @name VARCHAR(50)
// AS
// BEGIN
//    INSERT INTO department (name)
//    VALUES (@name);
// END;
// 
// CREATE PROCEDURE AddGroup
//    @groupName VARCHAR(50),
//    @credit INT
// AS
// BEGIN
//    INSERT INTO [group] (group_name, credit)
//    VALUES (@groupName, @credit);
// END;
// 
// CREATE PROCEDURE AddClass
//    @time TIME,
//    @type VARCHAR(20)
// AS
// BEGIN
//    INSERT INTO class (time, type)
//    VALUES (@time, @type);
// END;
// 
// CREATE PROCEDURE LinkProgramAndGroup
//    @programID INT,
//    @groupID INT
// AS
// BEGIN
//    INSERT INTO program_group (program_id, group_id)
//    VALUES (@programID, @groupID);
// END;
// 
// CREATE PROCEDURE LinkGroupAndCourse
//    @groupID INT,
//    @courseID INT
// AS
// BEGIN
//    INSERT INTO group_course (group_id, course_id)
//    VALUES (@groupID, @courseID);
// END;
// 
// CREATE PROCEDURE LinkCourseAndPrerequisite
//    @courseID INT,
//    @prereqID INT
// AS
// BEGIN
//    INSERT INTO course_prereq (course_id, prereq_id)
//    VALUES (@courseID, @prereqID);
// END;
// 
// CREATE PROCEDURE LinkDepartmentAndProgram
//    @departmentID INT,
//    @programID INT
// AS
// BEGIN
//    INSERT INTO department_program (department_id, program_id)
//    VALUES (@departmentID, @programID);
// END;
// 
// CREATE PROCEDURE LinkCourseAndClass
//    @courseID INT,
//    @classID INT
// AS
// BEGIN
//    INSERT INTO course_class (course_id, class_id)
//    VALUES (@courseID, @classID);
// END;
// 
// CREATE PROCEDURE UpdateProgram
//    @programID INT,
//    @name VARCHAR(50),
//    @title VARCHAR(50),
//    @type VARCHAR(20)
// AS
// BEGIN
//    UPDATE program
//    SET name = @name, title = @title, type = @type
//    WHERE id = @programID;
// END;
// 
// CREATE PROCEDURE UpdateCourse
//    @courseID INT,
//    @name VARCHAR(50),
//    @credit INT,
//    @departmentID INT,
//    @term VARCHAR(20),
//    @location VARCHAR(50),
//    @finalTime DATETIME
// AS
// BEGIN
//    UPDATE course
//    SET name = @name, credit = @credit, department_id = @departmentID,
//        term = @term, location = @location, final_time = @finalTime
//    WHERE id = @courseID;
// END;
// 
// CREATE PROCEDURE UpdateDepartment
//    @departmentID INT,
//    @name VARCHAR(50)
// AS
// BEGIN
//    UPDATE department
//    SET name = @name
//    WHERE id = @departmentID;
// END;
// 
// CREATE PROCEDURE UpdateGroup
//    @groupID INT,
//    @groupName VARCHAR(50),
//    @credit INT
// AS
// BEGIN
//    UPDATE [group]
//    SET group_name = @groupName, credit = @credit
//    WHERE id = @groupID;
// END;
// 
// CREATE PROCEDURE UpdateClass
//    @classID INT,
//    @time TIME,
//    @type VARCHAR(20)
// AS
// BEGIN
//    UPDATE class
//    SET time = @time, type = @type
//    WHERE id = @classID;
// END;
// 
// CREATE PROCEDURE DeleteProgram
//    @programID INT
// AS
// BEGIN
//    DELETE FROM program
//    WHERE id = @programID;
// END;
// 
// CREATE PROCEDURE DeleteCourse
//    @courseID INT
// AS
// BEGIN
//    DELETE FROM course
//    WHERE id = @courseID;
// END;
// 
// CREATE PROCEDURE DeleteDepartment
//    @departmentID INT
// AS
// BEGIN
//    DELETE FROM department
//    WHERE id = @departmentID;
// END;
// 
// CREATE PROCEDURE DeleteGroup
//    @groupID INT
// AS
// BEGIN
//    DELETE FROM [group]
//    WHERE id = @groupID;
// END;
// 
// CREATE PROCEDURE DeleteClass
//    @classID INT
// AS
// BEGIN
//    DELETE FROM class
//    WHERE id = @classID;
// END;
// 
// CREATE PROCEDURE SelectProgramByName
//    @programName VARCHAR(50)
// AS
// BEGIN
//    SELECT *
//    FROM program
//    WHERE name = @programName;
// END;
// 
// CREATE PROCEDURE SelectCourseByName
//    @courseName VARCHAR(50)
// AS
// BEGIN
//    SELECT *
//    FROM course
//    WHERE name = @courseName;
// END;
// 
// CREATE PROCEDURE SelectDepartmentByName
//    @departmentName VARCHAR(50)
// AS
// BEGIN
//    SELECT *
//    FROM department
//    WHERE name = @departmentName;
// END;
// CREATE PROCEDURE SelectGroupByName
//    @groupName VARCHAR(50)
// AS
// BEGIN
//    SELECT *
//    FROM [group]
//    WHERE group_name = @groupName;
// END;
// 
// CREATE PROCEDURE SelectClassByType
//    @classType VARCHAR(20)
// AS
// BEGIN
//    SELECT *
//    FROM class
//    WHERE type = @classType;
// END;
// — use cases start here – 
// CREATE PROCEDURE SelectCoursesByDepartmentName
//    @departmentName VARCHAR(50)
// AS
// BEGIN
//    SELECT c.*
//    FROM course c
//    JOIN department_program dp ON c.department_id = dp.department_id
//    JOIN department d ON dp.department_id = d.id
//    WHERE d.name = @departmentName;
// END;
// 
// CREATE PROCEDURE SelectClassesByCourseName
//    @courseName VARCHAR(50)
// AS
// BEGIN
//    SELECT cl.*
//    FROM class cl
//    JOIN course_class cc ON cl.id = cc.class_id
//    JOIN course c ON cc.course_id = c.id
//    WHERE c.name = @courseName;
// END;
// 
// CREATE PROCEDURE AddGroupsToProgramAndGetTotalCredits
//     @programID INT,
//     @groupNames NVARCHAR(MAX) -- Assuming group names are provided as a comma-separated string (e.g., 'GroupA,GroupB,GroupC')
// AS
// BEGIN
//     DECLARE @TotalCredits INT = 0;
// 
//     -- Temporary table to store group IDs
//     CREATE TABLE #TempGroupIDs (ID INT);
// 
//     -- Split the comma-separated string into rows
//     INSERT INTO #TempGroupIDs (ID)
//     SELECT value
//     FROM STRING_SPLIT(@groupNames, ',');
// 
//     -- Insert groups into the program and calculate total credits
//     INSERT INTO program_group (program_id, group_id)
//     SELECT @programID, ID
//     FROM #TempGroupIDs;
// 
//     -- Calculate total credits for the degree
//     SELECT @TotalCredits = SUM(credit)
//     FROM [group]
//     WHERE id IN (SELECT ID FROM #TempGroupIDs);
// 
//     -- Drop the temporary table
//     DROP TABLE #TempGroupIDs;
// 
//     -- Return the total credits
//     SELECT @TotalCredits AS TotalCredits;
// END;
// 
// CREATE PROCEDURE AddCourseAndAssignToGroup
//    @courseName VARCHAR(50),
//    @credit INT,
//    @departmentID INT,
//    @term VARCHAR(20),
//    @location VARCHAR(50),
//    @finalTime DATETIME,
//    @groupName VARCHAR(50)
// AS
// BEGIN
//    DECLARE @newCourseID INT;
// 
//    INSERT INTO course (name, credit, department_id, term, location, final_time)
//    VALUES (@courseName, @credit, @departmentID, @term, @location, @finalTime);
// 
//    SET @newCourseID = SCOPE_IDENTITY(); -- Get the ID of the newly inserted course
// 
//    INSERT INTO group_course (group_id, course_id)
//    SELECT g.id, @newCourseID
//    FROM [group] g
//    WHERE g.group_name = @groupName;
// END;
// 
// CREATE PROCEDURE AddPrerequisitesToCourse
//    @courseID INT,
//    @prerequisiteIDs INTArray 
// AS
// BEGIN
//    DECLARE @PrerequisiteID INT;
//    DECLARE PrerequisiteCursor CURSOR FOR
//    SELECT value FROM STRING_SPLIT(@prerequisiteIDs, ',');
// 
//    OPEN PrerequisiteCursor;
//    FETCH NEXT FROM PrerequisiteCursor INTO @PrerequisiteID;
// 
//    WHILE @@FETCH_STATUS = 0
//    BEGIN
//       -- Check if the prerequisite exists before adding the link
//       IF EXISTS (SELECT 1 FROM course WHERE id = @PrerequisiteID)
//       BEGIN
//          INSERT INTO course_prerequisite (course_id, prerequisite_id)
//          VALUES (@courseID, @PrerequisiteID);
//       END
//       ELSE
//       BEGIN
//          PRINT 'Prerequisite with ID ' + CAST(@PrerequisiteID AS VARCHAR(10)) + ' does not exist.';
//       END
// 
//       FETCH NEXT FROM PrerequisiteCursor INTO @PrerequisiteID;
//    END;
// 
//    CLOSE PrerequisiteCursor;
//    DEALLOCATE PrerequisiteCursor;
// END;
// 
// CREATE PROCEDURE GenerateClassesForSemester
//     @courseID INT,
//     @semesterStart DATE,
//     @semesterEnd DATE,
//     @classDay VARCHAR(10), -- Day of the week (e.g., 'Monday', 'Tuesday', etc.)
//     @classTime TIME,
//     @classType VARCHAR(20)
// AS
// BEGIN
//     DECLARE @currentDate DATE = @semesterStart;
// 
//     WHILE @currentDate <= @semesterEnd
//     BEGIN
//         -- Check if the current day is the specified class day
//         IF DATENAME(WEEKDAY, @currentDate) = @classDay
//         BEGIN
//             INSERT INTO class (time, type)
//             VALUES (@classTime, @classType);
// 
//             DECLARE @newClassID INT = SCOPE_IDENTITY(); -- Get the ID of the newly inserted class
// 
//             -- Link the class to the course
//             INSERT INTO course_class (course_id, class_id)
//             VALUES (@courseID, @newClassID);
//         END
// 
//         -- Move to the next week
//         SET @currentDate = DATEADD(WEEK, 1, @currentDate);
//     END;
// END;


package org.example.database;


public class Manager {
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

  public static void LinkProgramAndGroup(String programID, String groupID) {
    System.out.println("LinkProgramAndGroup");
  }

  public static void LinkGroupAndCourse(String groupID, String courseID) {
    System.out.println("LinkGroupAndCourse");
  }

  public static void LinkCourseAndPrerequisite(String courseID, String prereqID) {
    System.out.println("LinkCourseAndPrerequisite");
  }

  public static void LinkDepartmentAndProgram(String departmentID, String programID) {
    System.out.println("LinkDepartmentAndProgram");
  }

  public static void LinkCourseAndClass(String courseID, String classID) {
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

  public static void UpdateGroup(int groupID, String groupName, int credit) {
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

