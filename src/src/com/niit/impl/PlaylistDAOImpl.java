package com.niit.impl;

import com.niit.dao.PlaylistDAO;
import com.niit.helper.MySqlConnection;
import com.niit.model.PlayList;
import com.niit.model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAOImpl implements PlaylistDAO {
    private Connection connection;



    public boolean addSong(Song song) {
        try
        {
            String query="insert into song(sId,sName,sDuration,songPath,songAID) values (?,?,?,?,?)";
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1,song.getsId());
            preparedStatement.setString(2,song.getsName());
            preparedStatement.setString(3,song.getsDuration());
            preparedStatement.setString(4,song.getSongPath());
            preparedStatement.setInt(5,song.getsongAID());

            int count = preparedStatement.executeUpdate();
            if(count>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            return false;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return false;
        }
    }

    public PlaylistDAOImpl()
    {
        connection = MySqlConnection.getConnection();
    }
    //addPlaylist
    @Override
    public boolean addPlaylist(PlayList playList) {
        try
        {
            String query="insert into playlist(playlistid,name) values (?,?)";
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1,playList.getPlaylistId());
            preparedStatement.setString(2,playList.getPlaylistName());


            int count = preparedStatement.executeUpdate();
            if(count>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            return false;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return false;
        }
    }
    //deletePlaylistByName
    @Override
    public boolean deletePlaylistByName(String playlistName) {
        try
        {
            String query="delete from playlist where name = ?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,playlistName);
            int count = preparedStatement.executeUpdate();
            if(count>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            return false;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return false;
        }
    }
    //searchPlaylistByName
    @Override
    public PlayList searchPlaylistByName(String playlistName)
    {
        try
        {
            String query = "select * from playlist where name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, playlistName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                PlayList playList = new PlayList(resultSet.getInt("playlistId"),resultSet.getString("name"));
                return playList;
            }
            else
            {
                return null;
            }
        }

        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            return null;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
    }
    //getPlaylistByName
    @Override
    public PlayList getPlaylistByName(String playlistName) {
        try
        {
            String query = "select * from playlist where name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,playlistName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                PlayList playList = new PlayList(resultSet.getInt("playlistid"),resultSet.getString("name"));
                return playList;
            }
            else
            {
                return null;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    //getAllPlaylist
    @Override
    public List<PlayList> getAllPlaylist() {

        List<PlayList> playlists = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from playlist");
            while (resultSet.next())
            {
                PlayList playlist = new PlayList(resultSet.getInt("playlistid"),resultSet.getString("name"));
                playlists.add(playlist);
            }
            return playlists;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    /* @Override
     public void displayPlayList(List<PlayList> playlists) {

         for(PlayList playlist : playlists)
         {
             System.out.println(playlist.toString());
         }

     }
    */
// displayPlayList
    @Override
    public void displayPlayList(List<PlayList> playlists) {

        playlists.forEach(playList -> System.out.println(playList.toString()));

    }


}
