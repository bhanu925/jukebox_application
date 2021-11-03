package com.niit.model;

public class Podcast {
    private int pId;
    private String pName;
    private String pCelebrityName;
    private String duration;
    private String genre;
    private String pDate;
    private String pPath;

    public Podcast(int pId, String pName, String pCelebrityName, String duration, String genre, String pDate, String pPath) {
        this.pId = pId;
        this.pName = pName;
        this.pCelebrityName = pCelebrityName;
        this.duration = duration;
        this.genre = genre;
        this.pDate = pDate;
        this.pPath = pPath;
    }

    public Podcast(String pName, String pCelebrityName, String duration, String genre, String pDate, String pPath) {
        this.pName = pName;
        this.pCelebrityName = pCelebrityName;
        this.duration = duration;
        this.genre = genre;
        this.pDate = pDate;
        this.pPath = pPath;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }


    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpCelebrityName() {
        return pCelebrityName;
    }

    public void setpCelebrityName(String pCelebrityName) {
        this.pCelebrityName = pCelebrityName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getpDate() {
        return pDate;
    }

    public void setpDate(String pDate) {
        this.pDate = pDate;
    }

    public String getpPath() {
        return pPath;
    }

    public void setpPath(String pPath) {
        this.pPath = pPath;
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "pId=" + pId +
                ", pName='" + pName + '\'' +
                ", pCelebrityName='" + pCelebrityName + '\'' +
                ", duration='" + duration + '\'' +
                ", genre='" + genre + '\'' +
                ", pDate='" + pDate + '\'' +
                ", pPath='" + pPath + '\'' +
                '}';
    }
}

