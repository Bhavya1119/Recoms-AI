package com.recomsAI.base.enitity.album;

import java.util.List;

public class Album {

    private final String trackID;
    private final String title;
    private final Long duration;
    private final List<Artist> artists;

    public Album(String trackID, String title, Long duration, List<Artist> artists) {
        this.trackID = trackID;
        this.title = title;
        this.duration = duration;
        this.artists = artists;
    }

    public String getTrackID() {
        return trackID;
    }

    public String getTitle() {
        return title;
    }

    public Long getDuration() {
        return duration;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    @Override
    public String toString() {
        return "Album{" +
                "trackID='" + trackID + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", artists=" + artists +
                '}';
    }
}
