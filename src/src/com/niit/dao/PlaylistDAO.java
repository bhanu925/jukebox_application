package com.niit.dao;

import com.niit.model.PlayList;
import com.niit.model.Song;

import java.util.List;


public interface PlaylistDAO {

    boolean addPlaylist(PlayList playList);
    boolean deletePlaylistByName(String playlistName);
    PlayList searchPlaylistByName(String playlistName);
    PlayList getPlaylistByName(String playlistName);
    List<PlayList> getAllPlaylist();
    void displayPlayList(List<PlayList> playlists);
    boolean addSong(Song song);
    /*List<Song> getSongFromPlayList();
    void displaySongFromPlayList(List<Song> displaySongFromCatalog);
    List<Podcast> getPodcastFromPlayList();
    void displayPodcastFromPlayList(List<Podcast> displayPodcastFromCatalog);*/
}
