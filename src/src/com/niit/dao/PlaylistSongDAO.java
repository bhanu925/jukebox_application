package com.niit.dao;

import com.niit.model.Artist;
import com.niit.model.PlaylistSong;
import com.niit.model.Song;

import java.util.List;


public interface PlaylistSongDAO {

    boolean addSong(PlaylistSong playlistSong);
    boolean addsongsToPlaylist(PlaylistSong playlistSong);
    boolean deleteSongById(int sId);
    PlaylistSong searchBySongName(String songName);
    PlaylistSong getSongById(int songId);
    List<PlaylistSong> getAllSong();
    void displaySong(List<PlaylistSong> displaySongs);
    PlaylistSong getSongPathByName(String songName);


}
