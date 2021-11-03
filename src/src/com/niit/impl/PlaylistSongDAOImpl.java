package com.niit.impl;

import com.niit.dao.PlaylistSongDAO;
import com.niit.dao.SongDAO;
import com.niit.helper.MySqlConnection;
import com.niit.model.Artist;
import com.niit.model.PlaylistSong;
import com.niit.model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PlaylistSongDAOImpl implements PlaylistSongDAO {
    private Connection connection;

    public PlaylistSongDAOImpl()
    {
        connection = MySqlConnection.getConnection();
    }

    //addSong
    @Override
    public boolean addSong(PlaylistSong playlistSong) {
        try
        {
            String query="insert into playlistsong(sId,sName,sDuration,songPath,songAID) values (?,?,?,?,?)";
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1,playlistSong.getsId());
            preparedStatement.setString(2,playlistSong.getsName());
            preparedStatement.setString(3,playlistSong.getsDuration());
            preparedStatement.setString(4,playlistSong.getSongPath());
            preparedStatement.setInt(5,playlistSong.getsongAID());


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

    @Override
    public boolean addsongsToPlaylist(PlaylistSong playlistSong) {
        return false;
    }

    //deleteSongById
    @Override
    public boolean deleteSongById(int sId)
    {
        try
        {
            String query="delete from playlistsong where sId = ?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,sId);
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
    //searchBySongName
    @Override
    public PlaylistSong searchBySongName(String songName) {
        try
        {
            String query = "select * from playlistsong where sName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,songName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                PlaylistSong playlistSong = new PlaylistSong(resultSet.getInt("sId"),resultSet.getString("sName"),
                        resultSet.getString("sDuration"),resultSet.getString("songPath"),
                        resultSet.getInt("songAId"));
                return playlistSong;
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

    //getSongById
    @Override
    public PlaylistSong getSongById(int songId) {
        try
        {
            String query = "select * from playlistsong where sId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,songId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                PlaylistSong playlistSong = new PlaylistSong(resultSet.getInt("sId"),resultSet.getString("sName"),
                        resultSet.getString("sDuration"),resultSet.getString("songPath"),
                        resultSet.getInt("songAId"));
                return playlistSong;
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
    //getAllSong
    @Override
    public List<PlaylistSong> getAllSong() {
        List<PlaylistSong> catalogSong = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from playlistsong");
            while (resultSet.next())
            {
                PlaylistSong playlistSong = new PlaylistSong(resultSet.getInt("sId"),
                        resultSet.getString("sName"),resultSet.getString("sDuration"),
                        resultSet.getString("songPath"),resultSet.getInt("songAId"));
                catalogSong.add(playlistSong);
            }
            return catalogSong;
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

       //displaySong
    @Override
    public void displaySong(List<PlaylistSong> displaySongs) {
        displaySongs.forEach(playlistSong ->System.out.println(playlistSong.toString()));
    }
    //getSongPathByName
    @Override
    public PlaylistSong getSongPathByName(String songName) {
        try
        {
            String query = "select * from playlistsong where sName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,songName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                PlaylistSong playlistSong = new PlaylistSong(resultSet.getInt("sId"),resultSet.getString("sName"),
                        resultSet.getString("sDuration"),resultSet.getString("songPath"),
                        resultSet.getInt("songAId"));
                return playlistSong;
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


}

