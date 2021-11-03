package com.niit.impl;

import com.niit.dao.CatalogDAO;
import com.niit.helper.MySqlConnection;
import com.niit.model.Catalog;
import com.niit.model.Podcast;
import com.niit.model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogDAOImpl implements CatalogDAO {

    private Connection connection;

    public CatalogDAOImpl()
    {
        connection = MySqlConnection.getConnection();
    }
    //addCatalog
    @Override
    public boolean addCatalog(Catalog catalog) {
        try
        {
            String query="insert into catalog(cId,cName,itemId,itemType) values (?,?,?,?)";
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1,catalog.getcId());
            preparedStatement.setString(2,catalog.getcName());
            preparedStatement.setInt(3,catalog.getItemId());
            preparedStatement.setString(4,catalog.getItemType());

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
    //deleteCatalog
    @Override
    public boolean deleteCatalog(int catalogId) {
        try
        {
            String query="delete from catalog where cId = ?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,catalogId);
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
    //searchCatalogByName
    @Override
    public Catalog searchCatalogByName(String catalogName) {
        try
        {
            String query = "select * from catalog where cName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,catalogName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Catalog catalog = new Catalog(resultSet.getInt("cId"),resultSet.getString("cName"),
                        resultSet.getInt("itemId"),resultSet.getString("itemType"),resultSet.getString("sName"),resultSet.getString("songPath"));
                return catalog;
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
    //searchPodcastByCelebrityName
    @Override
    public Podcast searchPodcastByCelebrityName(String celebrityName) {
        try
        {
            String query = "select * from podcasts inner join catalog on podcasts.pId = catalog.cId where podcasts.pCelebrityName= ? ";
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
    //getAllCatalog
    @Override
    public List<Catalog> getAllCatalog() {

        List<Catalog> catalogs = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from catalog");
            while (resultSet.next())
            {
                Catalog catalog = new Catalog(resultSet.getInt("cId"),
                        resultSet.getString("cName"),resultSet.getInt("itemId"),
                        resultSet.getString("itemType"),resultSet.getString("sName"),resultSet.getString("songPath"));
                catalogs.add(catalog);          //cId int primary key,cname varchar(30),itemid int,itemtype varchar(30),sname varchar(20),songpath varchar(250));
            }
            return catalogs;
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
       public void displayCatalog(List<Catalog> displayCatalogs) {

           for(Catalog catalog : displayCatalogs)
           {
               System.out.println(catalog.toString());
           }

       }*/
    //displayCatalog
    @Override
    public void displayCatalog(List<Catalog> displayCatalogs) {
        displayCatalogs.forEach(catalog -> System.out.println(catalog.toString()));
    }
    //getSongFromCatalog
    @Override
    public List<Song> getSongFromCatalog() {

        List<Song> catalogSong = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from song inner join catalog on song.sId = catalog.cId");
            while (resultSet.next())
            {
                Song song = new Song(resultSet.getInt("sId"),
                        resultSet.getString("sName"),resultSet.getString("sDuration"),
                        resultSet.getString("songPath"),resultSet.getInt("songAId"));
                catalogSong.add(song);
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

    /*  @Override
      public void displaySongFromCatalog(List<Song> displaySongFromCatalog) {
          for(Song song : displaySongFromCatalog)
          {
              System.out.println(song.toString());
          }
      }*/
    //displaySongFromCatalog
    @Override
    public void displaySongFromCatalog(List<Song> displaySongFromCatalog) {
        displaySongFromCatalog.forEach(song -> System.out.println(song.toString()));
    }
    //getPodcastFromCatalog
    @Override
    public List<Podcast> getPodcastFromCatalog() {
        List<Podcast> catalogPodcast = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from podcasts inner join catalog on podcasts.pId = catalog.cId");
            while (resultSet.next())
            {
                Podcast podcast=new Podcast(resultSet.getInt("podcastid"),resultSet.getString("name"),
                        resultSet.getString("celebrity"),resultSet.getString("duration"),resultSet.getString("genre") ,
                        resultSet.getString("date"),resultSet.getString("pPath"));
                catalogPodcast.add(podcast);
            }
            return catalogPodcast;
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
       public void displayPodcastFromCatalog(List<Podcast> displayPodcastFromCatalog)
       {
           for(Podcast podcast : displayPodcastFromCatalog)
           {
               System.out.println(podcast.toString());
           }
       }*/
    //displayPodcastFromCatalog
    @Override
    public void displayPodcastFromCatalog(List<Podcast> displayPodcastFromCatalog)
    {
        displayPodcastFromCatalog.forEach(podcast -> System.out.println(podcast.toString()));
    }
    //getSongCatalogByName
    @Override
    public Song getSongCatalogByName(String catalogName) {
        try
        {
            String query = "select * from song inner join catalog on song.sId = catalog.cId  where cName=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,catalogName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Song song = new Song(resultSet.getInt("sId"),
                        resultSet.getString("sName"),resultSet.getString("sDuration"),
                        resultSet.getString("songPath"),resultSet.getInt("songAId"));
                return song;
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
}
