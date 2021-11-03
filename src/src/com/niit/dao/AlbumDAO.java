package com.niit.dao;

import com.niit.model.Album;

import java.util.List;

public interface AlbumDAO {

    boolean addAlbum(Album album);
    Album searchByAlbumDate(String albumDate);
    boolean deleteByAlbumId(int albumId);
    Album getByAlbumId(int albumId);
    List<Album> getAllAlbum();
    Album searchByAlbumName(String albumName);
    void displayAlbum(List<Album> albumDisplay);

}
