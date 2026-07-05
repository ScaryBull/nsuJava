package oopj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.InputStream;
import java.util.Properties;


public class CommandFactory {
    private static final Logger logger = LogManager.getLogger(CommandFactory.class);
    private final Properties properties = new Properties();

    public CommandFactory() {
        try (InputStream is = getClass().getResourceAsStream("/commands.properties")) {
            if (is == null) {
                logger.error("File commands.properties not found!");
                return;
            }
            properties.load(is);
            logger.info("Command configuration loaded");
        } catch (Exception e) {
            logger.error("Error occurred while loading configuration", e);
        }
    }

    public Command createCommand(String name) throws Exception {
        String className = properties.getProperty(name);
        if (className == null) 
            return null;
        
        logger.debug("Creating object of class: {}", className);
        return (Command) Class.forName(className).getDeclaredConstructor().newInstance();
    }
}
