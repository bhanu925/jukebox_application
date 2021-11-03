package com.niit.model;

public class Genres {
    private int genreId;
    private String genreType;

    public Genres(int genreId, String genreType) {
        this.genreId = genreId;
        this.genreType = genreType;
    }
    public Genres() {
        this.genreId = genreId;
        this.genreType = genreType;
    }

    public Genres(String genreType) {
        this.genreType = genreType;
    }


    public int getGenreId() {
        return genreId;
    }

    public String getGenreType() {
        return genreType;
    }

    public String toString() {
        return " GenreId = " + genreId +"\t"+ ", Genere Type = " + genreType+"\n";
    }
}
