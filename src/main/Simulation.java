package main;

import main.Entities.Distributor;
import main.Entities.VOD;
import main.Entities.User;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class Simulation {

    public static final Duration DAY_DURATION = Duration.ofSeconds(60);

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



    private static Simulation instance;

    public static void start(){
        instance = new Simulation();
    }

    public static Simulation getInstance(){
        return instance;
    }

}
