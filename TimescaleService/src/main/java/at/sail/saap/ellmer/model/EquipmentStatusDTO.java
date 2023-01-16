package at.sail.saap.ellmer.model;

import at.sail.saap.ellmer.model.db.EquipmentStatus;

public class EquipmentStatusDTO {
    private boolean producing = true;
    private String equipmentId;
    private int jobPercentFinished = 0;

    @Override
    public String toString() {
        return "EquipmentStatus{" +
                "equipmentId=" + equipmentId +
                ", producing=" + producing +
                ", jobPercentFinished=" + jobPercentFinished +
                '}';
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

    public EquipmentStatus toEquipmentStatus() {
       EquipmentStatus status = new EquipmentStatus();
       status.setEquipmentId(equipmentId);
       status.setProducing(producing);
       status.setJobPercentFinished(jobPercentFinished);
       return status;
    }
}
