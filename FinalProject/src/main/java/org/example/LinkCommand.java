package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

import picocli.CommandLine;

import org.example.database.Manager;

@CommandLine.Command(
  name = "link",
  description = "Link an entry from one table to another"
)
public class LinkCommand implements Callable<Integer>{
  // Define the type of link
  @CommandLine.Parameters(
    index = "0",
    description = "The type of link to create. Example; program-group, group-course, course-prereq, department-program, course-class"
  )
  private String linkType;

  // Define Options
  // Possible Options:
  // -p, --programId
  // -g, --groupId
  // -c, --courseId
  // -r, --prereqId
  // -d, --departmentId
  // -l, --classId
  @CommandLine.Option(
    names = {"-p", "--programId"},
    description = "The program id to link"
  )
  private String programId;

  @CommandLine.Option(
    names = {"-g", "--groupId"},
    description = "The group id to link"
  )
  private String groupId;

  @CommandLine.Option(
    names = {"-c", "--courseId"},
    description = "The course id to link"
  )
  private String courseId;

  @CommandLine.Option(
    names = {"-r", "--prereqId"},
    description = "The prereq id to link"
  )
  private String prereqId;

  @CommandLine.Option(
    names = {"-d", "--departmentId"},
    description = "The department id to link"
  )
  private String departmentId;

  @CommandLine.Option(
    names = {"-l", "--classId"},
    description = "The class id to link"
  )
  private String classId;


  @Override
  public Integer call() throws Exception {
    switch (linkType) {
      case "program-group":
        if (programId == null || groupId == null) {
          System.out.println("programId and groupId are required");
          return 1;
        }
        Manager.LinkProgramGroup(programId, groupId);
        break;
      case "group-course":
        if (groupId == null || courseId == null) {
          System.out.println("groupId and courseId are required");
          return 1;
        }
        Manager.LinkGroupCourse(groupId, courseId);
        break;
      case "course-prereq":
        if (courseId == null || prereqId == null) {
          System.out.println("courseId and prereqId are required");
          return 1;
        }
        Manager.LinkCoursePrereq(courseId, prereqId);
        break;
      case "department-program":
        if (departmentId == null || programId == null) {
          System.out.println("departmentId and programId are required");
          return 1;
        }
        Manager.LinkDepartmentProgram(departmentId, programId);
        break;
      case "course-class":
        if (courseId == null || classId == null) {
          System.out.println("courseId and classId are required");
          return 1;
        }
        Manager.LinkCourseClass(courseId, classId);
        break;
      default:
        System.out.println("Invalid link type");
        break;
    }
    return 0;
  }

}
