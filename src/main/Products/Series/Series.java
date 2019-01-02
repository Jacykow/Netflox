package main.Products.Series;

import com.google.gson.JsonObject;
import main.Products.Product;
import main.Products.Genre;

import java.time.Duration;
import java.util.ArrayList;

public class Series extends Product {

    private Genre genre;
    private ArrayList<String> actors;
    private ArrayList<Season> seasons;

    public Series(JsonObject json) throws NoSuchFieldException {
        super(json);
    }

    @Override
    public String getLongDescription() {
        return super.getLongDescription();
    }

    @Override
    public Duration getDuration() {
        duration = Duration.ZERO;
        for (Season season:
                seasons) {
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
