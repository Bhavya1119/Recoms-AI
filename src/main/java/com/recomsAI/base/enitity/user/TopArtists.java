package com.recomsAI.base.enitity.user;

public class TopArtists {
    private ArtistInfo artistInfo;
    private String userName;
    private String userId;

    public TopArtists(ArtistInfo artistInfo){
        this.artistInfo = artistInfo;
    }
    public TopArtists(String userName, String userId, ArtistInfo artistInfo){
        this.userName = userName;
        this.userId = userId;
        this.artistInfo = artistInfo;
    }

    public ArtistInfo getArtistInfo() {
        return artistInfo;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TopArtists{" +
                "artistInfo=" + artistInfo +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
