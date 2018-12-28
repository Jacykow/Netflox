package java;

import java.Entities.Distributor;
import java.Entities.User;
import java.Entities.VOD;
import java.time.Instant;
import java.util.ArrayList;

public class Simulation {

    private VOD vod;
    private ArrayList<User> users;
    private ArrayList<Distributor> distributors;



    private Simulation(){}

    private static Simulation instance;

    public static Simulation getInstance(){
        if(instance == null){
            instance = new Simulation();
        }
        return instance;
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
}
