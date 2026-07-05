package oopj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;
import java.io.File;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Start processing");
        ExecutionContext context = new ExecutionContext();
        CommandFactory factory = new CommandFactory();
        try (Scanner scanner = (args.length > 0) ? new Scanner(new File(args[0])) : new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) 
                    continue;
                
                String[] parts = line.split("\\s+");
                String cmdName = parts[0];
                String[] cmdArgs = new String[parts.length - 1];
                System.arraycopy(parts, 1, cmdArgs, 0, cmdArgs.length);
                try {
                    Command command = factory.createCommand(cmdName);
                    if (command != null) {
                        command.execute(context, cmdArgs);
                        logger.info("Command {} executed successfully", cmdName);
                    } else {
                        logger.warn("Unknown command {}", cmdName);
                    }
                } catch (Exception e) {
                    logger.error("Error occurred while executing {}", cmdName, e.getMessage());
                }
            }
        } catch (Exception e) {
            logger.fatal("Critical error {}", e.getMessage());
        }

        logger.info("Processing completed");
    }
}
