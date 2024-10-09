# Energy Management System (EMS)

## Project Description
This project is an **Energy Management System (EMS)** designed to manage and simulate energy data exchange between various charging stations and energy sources. The system generates log files for each day and each piece of equipment, allowing users to easily manage, retrieve, and archive logs.

## Features
- **Log Management**: Create, move, delete, and archive log files.
- **Daily Log Generation**: Separate log files are generated for each charging station and energy source for each day.
- **Stream-based Data Exchange**: Simulates data exchange using byte and character streams.
- **User Interaction**: Allows users to perform various operations like log file retrieval based on equipment name or date.
- **Archiving System**: Log files can be archived into ZIP files.

## Role Distribution and Folder Structure
```
Group5_HA_IO_RE/src 
├── LogMetadata.java (Amir Hossein Pakdel - 7221789)
├── LogService.java (Yug Dharmeshkumar Dave - 7222111)
└── EnergyManagementSystem.java (Hadis Delbord - 7222043)
```

### Class Descriptions

- **LogMetadata.java**: Defines metadata for log files including the name, date, equipment, and energy source.
- **LogService.java**: Provides services for creating, deleting, moving, and archiving log files.
- **EnergyManagementSystem.java**: The main class that interacts with the user and utilizes the `LogService` for log management.

### Classes and Key Methods

#### 1. LogMetadata
This class defines the metadata for the logs:

**Attributes**:
  - `logFileName`: The name of the log file.
  - `date`: The date of the log.
  - `equipmentName`: The name of the equipment.
  - `energySource`: The source of energy.
  - `EachDayOfEachChargingStation`: Indicates the charging station.
  
**Methods**:
  - `createLogFileName()`: Generates the log file name using the metadata.
  - Getters for retrieving various metadata attributes.

#### 2. LogService
This class handles the creation, movement, deletion, and archiving of log files.

**Key Methods**:
  - `createLogFile()`: Creates a log file with metadata and content.
  - `deleteLogFile()`: Deletes a log file based on equipment name or date.
  - `moveLogFile()`: Moves a log file to a specified directory.
  - `archiveLogFile()`: Archives log files into a ZIP file.
  - `getLogFilesByEquipment()`: Retrieves log files for a specific equipment.
  - `getLogFilesByDate()`: Retrieves log files based on the date.

#### 3. EnergyManagementSystem
This is the main entry point of the program. It provides a menu-based interface to interact with the log management system.

### Functionality Demonstration

#### Example Commands
**Create Log**:
  - Input the charging station, equipment name, and energy source.
  - A log file is created with these details, including the current date.
  
**Move Log**:
  - Input the equipment name or date and the target directory.
  - The log file is moved to the specified directory.
  
**Delete Log**:
  - Input the equipment name or date.
  - The log file is deleted.
  
**Archive Log**:
  - Input the log file name.
  - The log file is archived into a ZIP file.
  
**List Logs by Equipment**:
  - Input the equipment name.
  - All log files related to the equipment are listed.
  
**List Logs by Date**:
  - Input the date.
  - All log files from that date are listed.

### Example Log File Format
```
Log created at: 2024-10-09
Equipment Name: E1
Energy Source: Solar
Date: 2024-10-09
=======================================
```
