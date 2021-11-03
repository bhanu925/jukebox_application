package com.niit.model;

public class PlayList {
    private int playlistid;
    private String name;



    public PlayList(int playlistid, String name) {
        this.playlistid = playlistid;
        this.name = name;
    }

    public PlayList() {
        this.playlistid = playlistid;
        this.name = name;

    }

    public int getPlaylistId() {
        return playlistid;
    }

    public String getPlaylistName() {
        return name;
    }


    public String toString() {
        return "     Playlist Id =>>>>> " + playlistid +"\t"+ "    , Playlist Name =>>>> " + name  +"\t";
    }
}
