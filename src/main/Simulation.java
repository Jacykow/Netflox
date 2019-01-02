package main;

import main.Entities.Distributor;
import main.Entities.VOD;
import main.Entities.User;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

public class Simulation {

    public static final Duration DAY_DURATION = Duration.ofSeconds(30);

    private static final Random random = new Random();

    private IMDBConnection imdbConnection;
    private VOD vod;
    private ArrayList<User> users;
    private ArrayList<Distributor> distributors;
    private Instant startTime;


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
        vod = new VOD();
        try {
            vod.addRandomProducts(2,imdbConnection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        startTime = Instant.now();
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

    private static Simulation instance;

    public static void start(){
        instance = new Simulation();
    }

    public static Simulation getInstance(){
        return instance;
    }

}
