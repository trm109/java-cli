package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

import picocli.CommandLine;

import org.example.database.Manager;

@CommandLine.Command(name = "clear", description = "Clears the database")
public class ClearCommand implements Callable<Integer> {
  @Override
  public Integer call() throws Exception {
    Manager.ClearDatabase();
    return 0;
  }
}
