package info.keloud.EV3MonitoringServer;

import lejos.remote.ev3.RMISampleProvider;
import lejos.remote.ev3.RemoteEV3;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ServerMain {
    public static void main(String[] args) throws RemoteException {
        RemoteEV3 ev3 = null;
        RMISampleProvider TouchSensor = null;
        RMISampleProvider UltraSonicSensor = null;
        RMISampleProvider ColorSensor = null;
        RMISampleProvider GyroSensor = null;

        try {
            //EV3に接続、引数にEV3のIPアドレス
            ev3 = new RemoteEV3("192.168.44.219");
            ev3.setDefault();
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            e.printStackTrace();
        }

        UltraSonicSensor = ev3.createSampleProvider("S2", "lejos.hardware.sensor.EV3UltrasonicSensor", "Distance");
        ColorSensor = ev3.createSampleProvider("S1", "lejos.hardware.sensor.EV3ColorSensor", "RGB");
        GyroSensor = ev3.createSampleProvider("S2", "lejos.hardware.sensor.EV3GyroSensor", "Angle");

        try {
            System.out.println("UltraSonicSensor");
            printData(UltraSonicSensor.fetchSample());
            System.out.println("ColorSensor");
            printData(ColorSensor.fetchSample());
            System.out.println("GyroSensor");
            printData(GyroSensor.fetchSample());
        } finally {
            TouchSensor.close();
            UltraSonicSensor.close();
            ColorSensor.close();
            GyroSensor.close();
        }
    }

    public static void printData(float[] sample) {
        for (float data : sample) {
            System.out.println(data);
        }
    }
}
