package main.Products.Series;

import com.google.gson.JsonObject;
import main.Products.Genre;
import main.Products.Product;
import main.Simulation;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class Series extends Product {

    private Genre genre;
    private ArrayList<String> actors;
    private ArrayList<Season> seasons;

    public Series(JsonObject json) {
        super(json);

        Instant premierDate = getPremierDate();
        Duration productionTime = Duration.ofDays(Simulation.getRandom().nextInt(70)+10);
        ArrayList<Season> seasons = new ArrayList<>();
        do{
            Season season = new Season();
            ArrayList<Episode> episodes = new ArrayList<>();
            do{
                Episode episode = new Episode();
                episode.setPremierDate(premierDate);
                episode.setDuration(super.getDuration().dividedBy(5)
                        .plus(Duration.ofMinutes(Simulation.getRandom().nextInt(11)-5)));
                episodes.add(episode);
                premierDate = premierDate.plus(productionTime);
            } while (Simulation.getRandom().nextFloat() < 0.8f);
            season.setEpisodes(episodes);
            seasons.add(season);
            setSeasons(seasons);
            premierDate = premierDate.plus(productionTime.multipliedBy(5));
        } while (Simulation.getRandom().nextFloat() < 0.8f);

        setGenre(Genre.None);
        String genreString = json.get("Genre").getAsString();
        for (Genre genre :
             Genre.values()) {
            if(genreString.contains(genre.toString())){
                setGenre(genre);
                break;
            }
        }
        setActors(new ArrayList<>(Arrays.asList(json.get("Actors").getAsString().split(","))));
    }

    @Override
    public String getLongDescription() {
        StringBuilder d = new StringBuilder();
        d.append("A series!");
        d.append("\n");
        d.append("Genre: ").append(getGenre().toString());
        d.append("\n");
        d.append("Actors: ");
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (String actor :
                getActors()) {
            stringJoiner.add(actor);
        }
        d.append(stringJoiner.toString());
        d.append("\n");
        return d + super.getLongDescription();
    }

    @Override
    public Duration getDuration() {
        Duration duration = Duration.ZERO;
        for (Season season:
                getSeasons()) {
            duration = duration.plus(season.getDuration());
        }
        return duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }
}
