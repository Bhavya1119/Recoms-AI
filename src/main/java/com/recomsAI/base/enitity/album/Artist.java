package com.recomsAI.base.enitity.album;

public class Artist {
    private final String name;
    private final String id;
    private final String url;

    public Artist(String name , String id, String url){
        this.name = name;
        this.id = id;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
