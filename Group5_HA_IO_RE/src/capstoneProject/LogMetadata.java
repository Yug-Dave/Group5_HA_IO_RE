package capstoneProject;

import java.time.LocalDate;

public class LogMetadata {
    private String logFileName;
    private LocalDate date;
    private String equipmentName;
    private String energySource;
    private String EachDayOfEachChargingStation;

public LogMetadata(String EachDayOfEachChargingStation, String equipmentName, String energySource) {
        this.date = LocalDate.now();
        this.EachDayOfEachChargingStation = EachDayOfEachChargingStation;
        this.equipmentName = equipmentName;
        this.energySource = energySource;
        this.logFileName = createLogFileName();
    }

    private String createLogFileName() {
        return String.format("%s_%s_%s_%s.log", EachDayOfEachChargingStation, equipmentName, energySource, date);
    }

    public String getLogFileName() {
        return logFileName;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

	public String getEnergySource() {
        return energySource;
    }   
    public String getChargingStation() {
    	return EachDayOfEachChargingStation;
    }

}




















