package com.niit.impl;

import com.niit.dao.PodcastDAO;
import com.niit.helper.MySqlConnection;
import com.niit.model.Podcast;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PodcastDAOImpl implements PodcastDAO {
    private Connection connection;

    public PodcastDAOImpl()
    {
        connection = MySqlConnection.getConnection();
    }
    //addPodcast
    @Override
    public boolean addPodcast(Podcast podcast) {
        try {
            String query="insert into podcasts(name,celebrity,duration,genre,date,pPath) values (?,?,?,?,?,?)";

          //  System.out.println(" insertion done");
            PreparedStatement preparedStatement=connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
           System.out.println(" insertion done for return key"+podcast.getpName()+podcast.getpCelebrityName()+podcast.getpDate());

            preparedStatement.setString(1,podcast.getpName());
            preparedStatement.setString(2,podcast.getpCelebrityName());
            preparedStatement.setString(3,podcast.getDuration());
            preparedStatement.setString(4,podcast.getGenre());
            preparedStatement.setString(5,podcast.getpDate());
            preparedStatement.setString(6,podcast.getpPath());
         //   System.out.println("checking");
            int count=preparedStatement.executeUpdate();
            if (count>0)
            {
               // System.out.println(" row effected");
                return true;
            }
            else
            {
              //  System.out.println("row not effected");
                return false;
            }
        }
        catch (SQLException s){
            System.out.println("i am in sqlexception");
            s.printStackTrace();
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("i am in exception");
            return false;
        }

    }
    // deletePodcastByName
    @Override
    public boolean deletePodcastByName(String podcastName) {
        try
        {
            String query="delete from podcasts where name = ?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,podcastName);
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


    //searchPodcastByName
    @Override
    public Podcast searchPodcastByName(String podcastName) {
        try
        {
            String query="select * from podcasts where name = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,podcastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Podcast podcast=new Podcast(resultSet.getInt("podcastid"),resultSet.getString("name"),
                        resultSet.getString("celebrity"),resultSet.getString("duration"),resultSet.getString("genre") ,
                        resultSet.getString("date"),resultSet.getString("pPath"));
                return podcast;
            }
            else {
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
    //searchPodcastByCelebrityName
    @Override
    public Podcast searchPodcastByCelebrityName(String celebrityName) {
        try
        {
            String query="select * from podcasts  where celebrity= ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,celebrityName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Podcast podcast=new Podcast(resultSet.getInt("podcastid"),resultSet.getString("name"),
                        resultSet.getString("celebrity"),resultSet.getString("duration"),resultSet.getString("genre") ,
                        resultSet.getString("date"),resultSet.getString("pPath"));

                return podcast;
            }
            else {
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
    //getPodcastByName
    @Override
    public Podcast getPodcastByName(String pName) {
        try
        {
            String query = "select * from podcasts where name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,pName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Podcast podcast=new Podcast(resultSet.getInt("podcastid"),resultSet.getString("name"),
                        resultSet.getString("celebrity"),resultSet.getString("duration"),resultSet.getString("genre") ,
                        resultSet.getString("date"),resultSet.getString("pPath"));
                return podcast;
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
    //getAllPodcast
    @Override
    public List<Podcast> getAllPodcast() {
        List<Podcast> listPodcast = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from podcasts");
            while (resultSet.next())
            {
                Podcast podcast=new Podcast(resultSet.getInt("podcastid"),resultSet.getString("name"),
                        resultSet.getString("Celebrity"),resultSet.getString("duration"),resultSet.getString("genre") ,
                        resultSet.getString("date"),resultSet.getString("pPath"));
                listPodcast.add(podcast);
            }
            return listPodcast;
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
    //displayPodcast
    @Override
    public void displayPodcast(List<Podcast> displayPodcast) {
        displayPodcast.forEach(podcast -> System.out.println(podcast.toString()));
    }
  /* @Override
     public void displayPodcast(List<Podcast> displayPodcast) {
        for(Podcast podcast : displayPodcast)
        {
            System.out.println(podcast.toString());
        }
    }*/
}
