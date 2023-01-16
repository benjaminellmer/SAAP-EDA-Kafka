package com.sail.saap.ellmer.model;

public class EquipmentStatusDTO {
    private boolean producing = true;
    private String equipmentId;
    private int jobPercentFinished = 0;

    @Override
    public String toString() {
        return "EquipmentStatus:\n" +
                "equipmentId: " + equipmentId + "\n" +
                "producing: " + producing + "\n" +
                "jobPercentFinished: " + jobPercentFinished + "%";
    }

    public boolean isProducing() {
        return producing;
    }

    public void setProducing(boolean producing) {
        this.producing = producing;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public int getJobPercentFinished() {
        return jobPercentFinished;
    }

    public void setJobPercentFinished(int jobPercentFinished) {
        this.jobPercentFinished = jobPercentFinished;
    }
}
