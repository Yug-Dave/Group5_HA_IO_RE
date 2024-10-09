package capstoneProject;

import java.util.List;
import java.util.Scanner;

public class EnergyManagementSystem {
	public static void main(String[] args) {
		LogService logService = new LogService("logs");

		System.out.println("[Energy Management System]");

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Actions:");
			System.out.println("1. Create Log");
			System.out.println("2. Move Log");
			System.out.println("3. Delete Log");
			System.out.println("4. Archive Log");
			System.out.println("5. List Logs by Equipment");
			System.out.println("6. List Logs by Date");
			System.out.println("7. Exit");
			System.out.print("? ");

			int choice;
			try {
				choice = scanner.nextInt();
			} catch (Exception e) {
				System.err.println("[Error] Invalid input!");
				scanner.next(); // loop avoidance!
				continue;
			}

			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				System.out.print("Enter charging station: ");
				String chergingStation = scanner.nextLine();
				System.out.print("Enter equipment name: ");
				String equipmentName = scanner.nextLine();
				System.out.print("Enter energy source: ");
				String energySource = scanner.nextLine();
				LogMetadata metadata = new LogMetadata(chergingStation, equipmentName, energySource);
				logService.createLogFile(metadata, "Log created at: " + metadata.getDate());
				System.out.println("Log created: " + metadata.getLogFileName());
				break;

			case 2:
				System.out.print("Enter equipment name or date or log file name to move: ");
				String fileToMove = scanner.nextLine();
				System.out.print("Enter target directory name to move: ");
				String targetDirectory = scanner.nextLine();
				logService.moveLogFile(fileToMove, targetDirectory);
				System.out.println("Log file moved.");
				break;

			case 3:
				System.out.print("Enter equipment name or date or log file name to delete: ");
				String fileToDelete = scanner.nextLine();
				logService.deleteLogFile(fileToDelete);
				System.out.println("Log file deleted.");
				break;

			case 4:
				System.out.print("Enter log file name to archive: ");
				String fileToArchive = scanner.nextLine();
				logService.archiveLogFile(fileToArchive);
				System.out.println("Log file archived.");
				break;

			case 5:
				System.out.print("Enter equipment name to list logs: ");
				String equipNameToSearch = scanner.nextLine();
				List<String> logsByEquipment = logService.getLogFilesByEquipment(equipNameToSearch);
				if (logsByEquipment.isEmpty()) {
					System.out.println("No logs found for equipment: " + equipNameToSearch);
				} else {
					logsByEquipment.forEach(System.out::println);
				}
				break;

			case 6:
				System.out.print("Enter date (YYYY-MM-DD) to list logs: ");
				String dateToSearch = scanner.nextLine();
				List<String> logsByDate = logService.getLogFilesByDate(dateToSearch);
				if (logsByDate.isEmpty()) {
					System.out.println("No logs found for date: " + dateToSearch);
				} else {
					logsByDate.forEach(System.out::println);
				}
				break;

			case 7:
				System.out.println("Have a good day :)!");
				System.exit(0);
			}
		}
	}
}
