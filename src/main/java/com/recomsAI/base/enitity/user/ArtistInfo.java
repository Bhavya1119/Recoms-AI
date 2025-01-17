package com.recomsAI.base.enitity.user;

import java.util.List;

public class ArtistInfo {

    private String name ;
    private String id ;
    private List<String> genres ;
    private Long followers ;
    private Integer popularity ;
    private String href ;

    public ArtistInfo(String name , String id, List<String> genres, Integer popularity){
        this.name = name;
        this.id = id;
        this.genres = genres;
        this.popularity = popularity;
    }

    public ArtistInfo(String name , String id, List<String> genres, Long followers, Integer popularity, String href){
        this.name = name;
        this.id = id;
        this.genres = genres;
        this.followers = followers;
        this.popularity = popularity;
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public Long getFollowers() {
        return followers;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public String getHref() {
        return href;
    }

    @Override
    public String toString() {
        return "ArtistInfo{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", genres=" + genres +
                ", followers=" + followers +
                ", popularity=" + popularity +
                ", href='" + href + '\'' +
                '}';
    }
}
