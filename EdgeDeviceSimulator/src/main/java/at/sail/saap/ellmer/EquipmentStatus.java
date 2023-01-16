package at.sail.saap.ellmer;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Random;

public class EquipmentStatus {
    private Random rand = new Random();
    @Expose
    private boolean producing = true;
    @Expose
    private String equipmentId;
    @Expose
    private int jobPercentFinished = 0;

    public EquipmentStatus(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void increment() {
        if (producing) {
            randomlyStopProducing();
            addPercentIfProducing();
        } else {
            randomlyAwake();
            resetPercentIfFinishedAndProducing();
        }
    }

    private void addPercentIfProducing() {
        if (producing) {
            int progress = rand.nextInt(0, 5);
            if (jobPercentFinished + progress >= 100) {
                jobPercentFinished = 100;
                producing = false;
            } else {
                jobPercentFinished += progress;
            }
        }
    }

    private void resetPercentIfFinishedAndProducing() {
       if(jobPercentFinished == 100 && producing)  {
          jobPercentFinished = 0;
       }
    }

    private void randomlyAwake() {
        if (rand.nextInt(0, 5) > 4) {
            producing = true;
        }
    }

    private void randomlyStopProducing() {
        if (rand.nextInt(0, 20) < 1) {
            producing = false;
        }
    }

    public ProducerRecord<String, String> getProducerRecord() {
        Gson gson = GsonSingleton.getInstance();
        return new ProducerRecord<>("EquipmentStatus", equipmentId, gson.toJson(this));
    }

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
}
