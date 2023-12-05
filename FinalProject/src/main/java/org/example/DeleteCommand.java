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

@CommandLine.Command(name = "delete", description = "Delete a table entry")
public class DeleteCommand implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "which table. Example: program, course, department, group, class")
    private String tableName;

    // ask for id
    @CommandLine.Option(names = {"-i", "--id"}, description = "id of the entry to delete")
    private String id;
    

    @Override
    public Integer call() throws Exception {
      if (tableName == null) {
        System.out.println("Please specify a table name");
        return 1;
      }
      if (id == null) {
        System.out.println("Please specify an id");
        return 1;
      }
      switch(tableName) {
        case "program":
          Manager.DeleteProgram(id);
          break;
        case "course":
          Manager.DeleteCourse(id);
          break;
        case "department":
          Manager.DeleteDepartment(id);
          break;
        case "group":
          Manager.DeleteGroup(id);
          break;
        case "class":
          Manager.DeleteClass(id);
          break;
        default:
          System.out.println("Invalid table name");
          break;
      }
      return 0; 
    }
}
