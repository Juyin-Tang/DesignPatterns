import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    // Singleton instance
    private static Logger instance;

    // File handling
    private String fileName;
    private FileWriter fileWriter;

    // Private constructor
    private Logger() {
        this.fileName = "default_log.txt";
        try {
            fileWriter = new FileWriter(fileName, true);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Singleton getInstance method
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Write log message to file
    public void write(String message) {
        try {
            fileWriter.write(message + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Set file name dynamically
    public void setFileName(String newFileName) {
        // Close current file
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Update filename and open new file
        this.fileName = newFileName;
        try {
            fileWriter = new FileWriter(fileName, true);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Close the logger
    public void close() {
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}