package org.example;

import org.example.picocli.PropertiesFileVersionProvider;
import picocli.CommandLine;
import org.example.database.Manager;

@CommandLine.Command(
  name = "app",
  mixinStandardHelpOptions = true,
  versionProvider = PropertiesFileVersionProvider.class,
  subcommands = {
    AddCommand.class,
    UpdateCommand.class,
    DeleteCommand.class,
    SelectCommand.class,
    LinkCommand.class,
    CustomCommand.class,
    ClearCommand.class,
    InitCommand.class,
  }
)
public class Main {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.out.println("");
        System.exit(exitCode);
    }
}
