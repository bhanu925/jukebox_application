package com.niit.impl;

import com.niit.dao.ArtistDAO;
import com.niit.helper.MySqlConnection;
import com.niit.model.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAOImpl implements ArtistDAO {
    private Connection connection;

    public ArtistDAOImpl() {
        connection = MySqlConnection.getConnection();
    }
    //  addArtist
    @Override
    public boolean addArtist(Artist artist) {
        try
        {
            String query = "insert into artist(aId,aName,genreId) values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,artist.getaId());
            preparedStatement.setString(2,artist.getaName());
            preparedStatement.setInt(3,artist.getArtistgId());

            int count=preparedStatement.executeUpdate();
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
    //deleteArtist
    @Override
    public boolean deleteArtist(int aId) {
        try
        {
            String query="delete from artist where aId = ?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,aId);
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
    //getArtistById
    @Override
    public Artist getArtistById(int artistId)
    {
        try
        {
            String query = "select * from artist where aId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,artistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Artist artist = new Artist(resultSet.getInt("aId"),resultSet.getString("aName")
                        ,resultSet.getInt("genreId"));
                return artist;
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
    //getAllArtist
    @Override
    public List<Artist> getAllArtist() {
        List<Artist> artistDisplay = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from artist");
            while (resultSet.next())
            {
                Artist artist = new Artist(resultSet.getInt("aId"),resultSet.getString("aName")
                        ,resultSet.getInt("genreId"));
                artistDisplay.add(artist);
            }
            return artistDisplay;
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

    /*   @Override
       public void displayArtist(List<Artist> artistDisplay) {
           for(Artist artist : artistDisplay)
           {
               System.out.println(artist.toString());
           }
       }*/
    //display Artist
    @Override
    public void displayArtist(List<Artist> artistDisplay) {
        artistDisplay.forEach(artist -> System.out.println(artist.toString()));
    }

}
