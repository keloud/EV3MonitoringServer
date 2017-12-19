package info.keloud.EV3MonitoringServer;

public class ClientEmulator {
    public static void main(String[] args) {
        try {
            if (args[0].equals("true")) {
                ServerMain.main(null);
            }
        } catch (NullPointerException e) {
            e.getStackTrace();
        }

        new RandomSender();
    }
}
