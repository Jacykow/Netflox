package main;

import main.Entities.Distributor;
import main.Entities.User;
import main.Entities.VOD;
import main.Misc.FileData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

public class Simulation {

    private static Simulation instance;
    private static final Random random = new Random();

    public static final Duration DAY_DURATION = Duration.ofSeconds(30);

    private IMDBConnection imdbConnection;
    private VOD vod;
    private ArrayList<User> users;
    private ArrayList<Distributor> distributors;
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



    private Simulation(){
        imdbConnection = new IMDBConnection("a90bd77");
        try {
            setFileData(new FileData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setVod(new VOD());
        try {
            getVod().addRandomProducts(1,imdbConnection,fileData);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Distributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(ArrayList<Distributor> distributors) {
        this.distributors = distributors;
    }



    public static Random getRandom(){
        return random;
    }

    public static void start(){
        instance = new Simulation();
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
