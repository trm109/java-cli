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
  
  @CommandLine.Parameters(index = "0", description = "The command to execute. Example: add-groups-to-program, add-course-and-assign-to-group, add-prereq-to-course")
}
