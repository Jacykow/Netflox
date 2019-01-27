package main;

import main.Entities.VOD;
import main.Misc.FileData;
import main.Misc.IMDBConnection;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Simulation {

    private static Simulation instance;
    private static final Random random = new Random();

    public static final Duration DAY_DURATION = Duration.ofSeconds(30);

    private IMDBConnection imdbConnection;
    private VOD vod;
    private Instant startTime;
    private FileData fileData;

    public static Instant time(){
        if(!running()){
            return Instant.now();
        }
        else {
            return getInstance().getSimTime();
        }
    }

    public static boolean running(){
        return !(getInstance() == null);
    }

    public static void stopAndSave(){

        instance = null;
    }

    public static void loadAndResume(){

    }



    private Simulation(){
        imdbConnection = new IMDBConnection("a90bd77");
        try {
            setFileData(new FileData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setVod(new VOD());
        startTime = Instant.now();
        System.out.println("Initialized!");
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

    public Instant getSimTime(){
        return startTime.plus(Duration.between(startTime,Instant.now()).multipliedBy(Duration.ofDays(1).toNanos()/DAY_DURATION.toNanos()));
    }



    public VOD getVod() {
        return vod;
    }

    public void setVod(VOD vod) {
        this.vod = vod;
    }



    public static Random getRandom(){
        return random;
    }

    public static void start(){
        instance = new Simulation();
        getInstance().getVod().instantiateDefaults();
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
