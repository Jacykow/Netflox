package main.Products.Series;

import main.Products.IWatchable;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;

public class Season implements IWatchable, Serializable {

    private ArrayList<Episode> episodes;

    @Override
    public Duration getDuration() {
        Duration d = Duration.ZERO;
        for (Episode episode:
             episodes) {
            d = d.plus(episode.getDuration());
        }
        return d;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }
}
