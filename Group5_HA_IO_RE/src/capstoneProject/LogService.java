package capstoneProject;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LogService {
    private final String logDirectory;

    public LogService(String logDirectory) {
        this.logDirectory = logDirectory;
        createLogDirectory();
    }

    private void createLogDirectory() {
        try {
            Files.createDirectories(Paths.get(logDirectory));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createLogFile(LogMetadata metadata, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logDirectory + "/" + metadata.getLogFileName(), true))) {
        	writer.write(content);
             writer.newLine();
             writer.write("Equipment Name: " + metadata.getEquipmentName());
             writer.newLine();
             writer.write("Energy Source: " + metadata.getEnergySource());
             writer.newLine();
             writer.write("Date: " + metadata.getDate());
             writer.newLine();
             writer.write("=======================================");
             writer.newLine();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    public List<String> readLogFile(String logFileName) {
        List<String> logEntries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(logDirectory + "/" + logFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logEntries.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logEntries;
    }
    
    public void deleteLogFile(String logFileName) {
        try {
            Files.delete(Paths.get(logDirectory + "/" + logFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveLogFile(String logFileName, String targetDirectory) {
    	String newDirectory = logDirectory + "/" + targetDirectory;
        createLogDirectory(newDirectory);
        try {
            Files.move(Paths.get(logDirectory + "/" + logFileName), Paths.get(newDirectory + "/" + logFileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void archiveLogFile(String logFileName) {
        String archiveDirectory = logDirectory + "/archive";
        createLogDirectory(archiveDirectory);
        
        String fileName = logFileName;
        Path sourcePath = Paths.get(fileName);
        String zipFileName = archiveDirectory + File.separator + fileName + ".zip";

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileName))) {
            // Get all log files in the directory
            DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(archiveDirectory), "*.log");

            for (Path entry : stream) {
                // Add each log file to the ZIP file
                try (FileInputStream fileInputStream = new FileInputStream(entry.toFile())) {
                    ZipEntry zipEntry = new ZipEntry(entry.getFileName().toString());
                    zipOutputStream.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fileInputStream.read(buffer)) >= 0) {
                        zipOutputStream.write(buffer, 0, length);
                    }
                    zipOutputStream.closeEntry();
                } catch (IOException e) {
                    System.out.println("Failed to add file to ZIP: " + entry.getFileName());
                }
            }
            System.out.println("Log files archived successfully to: " + zipFileName);
        } catch (IOException e) {
            System.out.println("Failed to create ZIP file: " + e.getMessage());
        }
    }
    
    private void createLogDirectory(String directory) {
        try {
            Files.createDirectories(Paths.get(directory));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getLogFilesByEquipment(String equipmentName) {
        List<String> matchingLogs = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(logDirectory), "*" + equipmentName + "*.log")) {
            for (Path entry : stream) {
                
                matchingLogs.add(entry.getFileName().toString());
                
            	try (BufferedReader reader = new BufferedReader(new FileReader(logDirectory + "/" + entry.getFileName().toString()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                    	matchingLogs.add(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchingLogs;
    }

    public List<String> getLogFilesByDate(String date) {
        List<String> matchingLogs = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(logDirectory),"*_"+date+"*.log")) {
            for (Path entry : stream) {
                matchingLogs.add(entry.getFileName().toString());
                
            	try (BufferedReader reader = new BufferedReader(new FileReader(logDirectory + "/" + entry.getFileName().toString()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                    	matchingLogs.add(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchingLogs;
    }
}



