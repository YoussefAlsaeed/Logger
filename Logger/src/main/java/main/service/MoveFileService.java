
package main.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MoveFileService {

    private static final String LOG_DIRECTORY = "logs";
    private static final String RETENTION_DIRECTORY = "logs/history";


/*
    @Scheduled(cron = "0 0 0 * * *")  // Runs every day at 12:00 AM
    public void moveLogFiles() {
    	
    	
        File logDirectory = new File(LOG_DIRECTORY);
        File[] logFiles = logDirectory.listFiles();

        if (logFiles != null) {
            for (File logFile : logFiles) {
                if (logFile.isFile()) {
                    String logFileName = logFile.getName();
                    String[] fileNameParts = logFileName.split("_");

                    if (fileNameParts.length >= 2) {
                        String destinationDirectory = RETENTION_DIRECTORY ;

                        try {
                            Path destinationPath = new File(destinationDirectory).toPath();
                            Files.createDirectories(destinationPath);

                            Path destinationFile = destinationPath.resolve(logFileName);
                            Files.move(logFile.toPath(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

                            System.out.println("Moved: " + logFileName + " to " + destinationFile);
                        } catch (Exception e) {
                            e.printStackTrace(); 
                        }
                    }
                }
            }
        }
    }
    */
}
