package main;

import main.Entities.VOD;
import main.Misc.FileData;
import main.Misc.IMDBConnection;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Simulation implements Serializable {

    private static Simulation instance;
    private static final Random random = new Random();

    public static final Duration DAY_DURATION = Duration.ofSeconds(2);

    private transient IMDBConnection imdbConnection;
    private VOD vod;
    private Instant startTime;
    private Duration simTime;

    private transient FileData fileData;

    private boolean running;

    public static Instant time(){
        if(!running()){
            if(getInstance()!=null){
                return getInstance().startTime.plus(getInstance().simTime);
            }
            return Instant.now();
        }
        else {
            return getInstance().getSimTime();
        }
    }

    public static boolean running(){
        if(getInstance() == null) return false;
        return getInstance().running;
    }

    public static void defaultInit(){
        instance = new Simulation();
        getInstance().getVod().instantiateDefaults();
        pause();
    }

    private static final String simSaveFile = "./src/data/sim_data.ser";

    public static void save(){
        pause();
        try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(simSaveFile));
            file.writeObject(getInstance());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load(){
        pause();
        try {
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(simSaveFile));
            instance = (Simulation) file.readObject();
            getInstance().afterSerializationRoutine();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void start(){
        getInstance().startTime = Instant.now();
        getInstance().setRunning(true);
    }

    public static void pause(){
        getInstance().simTime = Duration.between(getInstance().startTime, time());
        getInstance().setRunning(false);
    }

    private Simulation(){
        startTime = Instant.now();
        simTime = Duration.ZERO;
        setVod(new VOD());
        afterSerializationRoutine();
        /*
        new Thread(() -> {
            while (vod.getProducts().size() < 2){
                try {
                    Thread.sleep(3000);
                    System.out.println("DING!!!");
                    vod.addRandomProducts(1,imdbConnection,fileData);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        */
    }

    public void afterSerializationRoutine(){
        try {
            setFileData(new FileData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setImdbConnection(new IMDBConnection("a90bd77"));
        getVod().afterSerializationRoutine();
        System.out.println("Initialized!");
    }

    public Instant getSimTime(){
        return startTime.plus(Duration.between(startTime,Instant.now()).multipliedBy(Duration.ofDays(1).toNanos()/DAY_DURATION.toNanos())).plus(simTime);
    }



    public VOD getVod() {
        return vod;
    }

    public void setVod(VOD vod) {
        this.vod = vod;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }



    public static Random getRandom(){
        return random;
    }

    public static Simulation getInstance(){
        return instance;
    }

    public FileData getFileData() {
        return fileData;
    }

    public void setFileData(FileData fileData) {
        this.fileData = fileData;
    }

    public IMDBConnection getImdbConnection() {
        return imdbConnection;
    }

    public void setImdbConnection(IMDBConnection imdbConnection) {
        this.imdbConnection = imdbConnection;
    }
}
