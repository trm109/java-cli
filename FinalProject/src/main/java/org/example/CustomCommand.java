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


@CommandLine.Command(name = "custom", description = "Custom commands.")
public class CustomCommand implements Callable<Integer> {
  
  @CommandLine.Parameters(index = "0", description = "The command to execute. Example: select-course-by-dept-name, select-class-by-course-name, add-groups-to-program, add-course-and-assign-to-group, add-prereq-to-course, generate-classes-for-semester")
  private String command;

  // Define options.
  // select-course-by-dept-name requires departmentName
  // select-class-by-course-name requires courseName
  // add-groups-to-program requires programId, groupNames[]
  // add-course-and-assign-to-group requires courseName, credit, departmentId, term, location, finalTime, groupName
  // add-prereq-to-course requires courseId, prereqCourseIds
  // generate-classes-for-semester requires courseId, semesterStart, semesterEnd, classDay, classTime, classType

  // --departmentName
  @CommandLine.Option(names = "--departmentName", description = "The department name.")
  private String departmentName;
  // --courseName
  @CommandLine.Option(names = "--courseName", description = "The course name.")
  private String courseName;
  // --programId
  @CommandLine.Option(names = "--programId", description = "The program id.")
  private String programId;
  // --groupNames
  @CommandLine.Option(names = "--groupNames", description = "The group names.")
  private String[] groupNames;
  // --credit
  @CommandLine.Option(names = "--credit", description = "The credit.")
  private String credit;
  // --departmentId
  @CommandLine.Option(names = "--departmentId", description = "The department id.")
  private String departmentId;
  // --term
  @CommandLine.Option(names = "--term", description = "The term.")
  private String term;
  // --location
  @CommandLine.Option(names = "--location", description = "The location.")
  private String location;
  // --finalTime
  @CommandLine.Option(names = "--finalTime", description = "The final time.")
  private String finalTime;
  // --groupName
  @CommandLine.Option(names = "--groupName", description = "The group name.")
  private String groupName;
  // --courseId
  @CommandLine.Option(names = "--courseId", description = "The course id.")
  private String courseId;
  // --prereqCourseIds
  @CommandLine.Option(names = "--prereqCourseIds", description = "The prereq course ids.")
  private String[] prereqCourseIds;
  // --semesterStart
  @CommandLine.Option(names = "--semesterStart", description = "The semester start.")
  private String semesterStart;
  // --semesterEnd
  @CommandLine.Option(names = "--semesterEnd", description = "The semester end.")
  private String semesterEnd;
  // --classDay
  @CommandLine.Option(names = "--classDay", description = "The class day.")
  private String classDay;
  // --classTime
  @CommandLine.Option(names = "--classTime", description = "The class time.")
  private String classTime;
  // --classType
  @CommandLine.Option(names = "--classType", description = "The class type.")
  private String classType;


  @Override
  public Integer call() throws Exception {
    switch(command) {
      case "select-course-by-dept-name":
        // select-course-by-dept-name requires departmentName
        if (departmentName == null) {
          System.out.println("Missing required option: --departmentName");
          return 1;
        }
        Manager.SelectCoursesByDepartmentName(departmentName);
        break;
      case "select-class-by-course-name":
        // select-class-by-course-name requires courseName
        if (courseName == null) {
          System.out.println("Missing required option: --courseName");
          return 1;
        }
        Manager.SelectClassesByCourseName(courseName);
        break;
      case "add-groups-to-program":
        // add-groups-to-program requires programId, groupNames[]
        if (programId == null) {
          System.out.println("Missing required option: --programId");
          return 1;
        }
        if (groupNames == null) {
          System.out.println("Missing required option: --groupNames");
          return 1;
        }
        Manager.AddGroupsToProgramAndGetTotalCredits(programId, groupNames);
        break;
      case "add-course-and-assign-to-group":
        // add-course-and-assign-to-group requires courseName, credit, departmentId, term, location, finalTime, groupName
        if (courseName == null) {
          System.out.println("Missing required option: --courseName");
          return 1;
        }
        if (credit == null) {
          System.out.println("Missing required option: --credit");
          return 1;
        }
        if (departmentId == null) {
          System.out.println("Missing required option: --departmentId");
          return 1;
        }
        if (term == null) {
          System.out.println("Missing required option: --term");
          return 1;
        }
        if (location == null) {
          System.out.println("Missing required option: --location");
          return 1;
        }
        if (finalTime == null) {
          System.out.println("Missing required option: --finalTime");
          return 1;
        }
        if (groupName == null) {
          System.out.println("Missing required option: --groupName");
          return 1;
        }
        Manager.AddCourseAndAssignToGroup(courseName, credit, departmentId, term, location, finalTime, groupName);
        break;
      case "add-prereq-to-course":
        // add-prereq-to-course requires courseId, prereqCourseIds
        if (courseId == null) {
          System.out.println("Missing required option: --courseId");
          return 1;
        }
        if (prereqCourseIds == null) {
          System.out.println("Missing required option: --prereqCourseIds");
          return 1;
        }
        Manager.AddPrerequisitesToCourse(courseId, prereqCourseIds);
        break;
      case "generate-classes-for-semester":
        // generate-classes-for-semester requires courseId, semesterStart, semesterEnd, classDay, classTime, classType
        if (courseId == null) {
          System.out.println("Missing required option: --courseId");
          return 1;
        }
        if (semesterStart == null) {
          System.out.println("Missing required option: --semesterStart");
          return 1;
        }
        if (semesterEnd == null) {
          System.out.println("Missing required option: --semesterEnd");
          return 1;
        }
        if (classDay == null) {
          System.out.println("Missing required option: --classDay");
          return 1;
        }
        if (classTime == null) {
          System.out.println("Missing required option: --classTime");
          return 1;
        }
        if (classType == null) {
          System.out.println("Missing required option: --classType");
          return 1;
        }
        Manager.GenerateClassesForSemester(courseId, semesterStart, semesterEnd, classDay, classTime, classType);
        break;
    }
    return 0;
  }
}
