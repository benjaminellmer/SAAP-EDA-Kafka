package at.sail.saap.ellmer;

public class Main {
    public static final String CUSTOMER_ID = "23474_Ellmer_B";
    public static final int[] EQUIPMENTS_PER_ED = {
        12, 3, 8, 4, 3, 1, 1, 1, 2, 2, 3, 9
    };

    public static void main(String[] args) {
        for(int i = 0; i < EQUIPMENTS_PER_ED.length; ++i) {
            EdgeDevice ed = new EdgeDevice(EQUIPMENTS_PER_ED[i], CUSTOMER_ID, i);
            ed.startSimulation();
        }
    }
}
