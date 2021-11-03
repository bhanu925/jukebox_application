package com.niit.impl;

import com.niit.dao.GenresDAO;
import com.niit.helper.MySqlConnection;
import com.niit.model.Genres;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenresDAOImpl implements GenresDAO {
    private Connection connection;

    public GenresDAOImpl() {
        connection = MySqlConnection.getConnection();
    }
    //addGenres
    @Override
    public boolean addGenres(Genres genres) {
        try
        {
            String query = "insert into genre(genreType) values(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
          //  preparedStatement.setInt(1,genres.getGenreId());
            preparedStatement.setString(1,genres.getGenreType());
           // preparedStatement.setString();

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
        catch (SQLException s)
        {
            s.printStackTrace();
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    //deleteGenres
    @Override
    public boolean deleteGenres(int gId)
    {
        try
        {
            String query="delete from genre where genreId = ?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,gId);
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
    //getGenresById
    @Override
    public Genres getGenresById(int genreID) {
        try
        {
            String query = "select * from genre where genreId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,genreID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                Genres genres = new Genres(resultSet.getInt("genreId"),
                        resultSet.getString("genereType"));
                return genres;
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
    //getAllGenres
    @Override
    public List<Genres> getAllGenres() {
        List<Genres> genresDisplay = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from genre");
            while (resultSet.next())
            {
                Genres genres = new Genres(resultSet.getInt("genreId"),
                        resultSet.getString("genreType"));
                genresDisplay.add(genres);
            }
            return genresDisplay;
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
       public void displayGenres(List<Genres> genresDisplay) {
           for(Genres genres : genresDisplay)
           {
               System.out.println(genres.toString());
           }
       }*/
    //displayGenres
    @Override
    public void displayGenres(List<Genres> genresDisplay) {
        genresDisplay.forEach(genres -> System.out.println(genres.toString()));
    }

}
