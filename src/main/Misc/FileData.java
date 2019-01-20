package main.Misc;

import main.Simulation;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FileData {
    private ArrayList<String> movies;
    private ArrayList<String> names;
    private ArrayList<String> surnames;

    public FileData() throws FileNotFoundException {
        names = new ArrayList<>();
        surnames = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader("./src/data/filmy.txt"));
        movies = reader.lines().collect(Collectors.toCollection(ArrayList::new));
        for(int x=0;x<movies.size();x++){
            movies.set(x,movies.get(x)
                    .replaceFirst(" \\(.*","")
                    .replaceFirst(".*\\t",""));
        }

        reader = new BufferedReader(new FileReader("./src/data/imiona.txt"));
        names = reader.lines().collect(Collectors.toCollection(ArrayList::new));

        reader = new BufferedReader(new FileReader("./src/data/nazwiska.txt"));
        surnames = reader.lines().collect(Collectors.toCollection(ArrayList::new));
    }

    public String GetRandomMovieTitle() {
        return movies.get(Simulation.getRandom().nextInt(movies.size()));
    }

    public String GetRandomName(){
        return names.get(Simulation.getRandom().nextInt(names.size())) + " " +
                surnames.get(Simulation.getRandom().nextInt(surnames.size()));
    }
}
