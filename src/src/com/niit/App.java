package com.niit;
import com.niit.dao.*;
import com.niit.impl.CatalogDAOImpl;
import com.niit.impl.*;
import com.niit.model.*;
import com.niit.userdefinedexception.AlbumNotFoundException;
import com.niit.userdefinedexception.SongNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        int choice =0,choice1=0;
        Scanner scanner = new Scanner(System.in);

        GenresDAO genresDAO= new GenresDAOImpl();
        ArtistDAO artistDAO = new ArtistDAOImpl();
        SongDAO songDAO = new SongDAOImpl();
        PlaylistDAO playlistDAO = new PlaylistDAOImpl();
        PodcastDAO podcastDAO=new PodcastDAOImpl();
        CatalogDAO catalogDAO= new CatalogDAOImpl();
        AlbumDAO albumDAO=new AlbumDAOImpl();
        PlaylistSongDAO playlistSongDAO= new PlaylistSongDAOImpl();
        RecordDAO recordDAO = new RecordDAOImpl();

        System.out.println("-------------------- Welcome to MyModernJukeBox ---------------------");
        try
        {
            do {
                //Main Menu
                System.out.println(" 1. Song \n 2. Podcast \n " +
                        "3. Genre \n 4. Artist\n 5. Playlist\n " +
                        /*6. Catalog+*/"7. Album \n 8. Exit");
                System.out.println("===========================================================");
                choice1 = scanner.nextInt();

                switch (choice1) {
                    case 1:
                        do {
                            //Song
                            System.out.println(" \n 1. Add song\n 2. Delete Song name \n 3. Search song details from song name" +
                                    "\n 4. Search Artist name from song name\n 5. Play Song \n 6. Display All Songs \n 7. Searchsongs by Artistname \n 8. Back");
                            choice = scanner.nextInt();

                            switch (choice) {
                                case 1:
                                    System.out.println("-------------------------- Song ----------------------------------");
                                    System.out.println("******************** Add New Song *****************************");
            //                      System.out.println("Enter song ID ::");
            //                      int songId = scanner.nextInt();
                                    System.out.println("Enter song Name ::");
                                    String songName = scanner.next();
                                   // scanner.next();
                                    System.out.println("Enter Duration ::");
                                    String songDuration = scanner.next();
                                  //  System.out.println("Enter Location of song ::");
                                    String songPath = "D:\\fullstack\\mymodernjukebox\\Resources\\"+songName+".wav";
                                    System.out.println("Enter Artist ID ::");
                                    int songArtistId = scanner.nextInt();

                                    Song song = new Song( songName, songDuration, songPath, songArtistId);
                                    boolean resultSong = songDAO.addSong(song);
                                    if (resultSong) {
                                        System.out.println("song Name = " + song.getsName() + " inserted successfully.");
                                    } else {
                                        System.out.println("song Name = " + song.getsName() + " is not inserted successfully.");
                                    }
                                    System.out.println();
                                    break;
                                case 2:
                                    System.out.println("******************** Delete Song Name *********************");
                                    System.out.println("Enter song id :: ");
                                    int songId = scanner.nextInt();
                                    Song song2 = songDAO.getSongById(songId);
                                    boolean resultSong2 = songDAO.deleteSongById(songId);
                                    if (resultSong2) {
                                        System.out.println(song2.getsId() + " is successfully Deleted");
                                    } else {
                                        System.out.println("Song Id is not Deleted.");
                                    }
                                    System.out.println();
                                    break;
                                case 3:
                                    System.out.println("******************** Search Song Details By Song Name *********************");
                                    System.out.println("Enter Song name :: ");
                                    songName = scanner.next();
                                    Song song1 = songDAO.searchBySongName(songName);

                                    if(song1==null)
                                    {
                                        throw new SongNotFoundException("No Songs found");
                                    }
                                    else {
                                        System.out.println("\n" + song1.toString());
                                    }

                                    break;


                                case 4:
                                    System.out.println("*********************** Search Artist By Song Name ***********************");
                                    System.out.println("Enter Song Name :: ");
                                    String songName1 = scanner.next();

                                    Artist findArtistName = songDAO.searchByArtistName(songName1);
                                    System.out.println(" Artist Name = " + findArtistName.getaName());
                                    break;
                                case 5:
                                    System.out.println("Enter Song Name ::");
                                    String songName2 = scanner.next();
                                    Song songPath1 = songDAO.getSongPathByName(songName2);
                                    System.out.println(songPath1.getsName());
                                    try {

                                        String filePath = songPath1.getSongPath();
                                        AudioPlayerDAO audioPlayer = new AudioPlayerDAOImpl(filePath);
                                        audioPlayer.play();
                                        scanner = new Scanner(System.in);

                                        while (true) {

                                            System.out.println("1. pause");
                                            System.out.println("2. resume");
                                            System.out.println("3. restart");
                                            System.out.println("4. stop");
                                            int c = scanner.nextInt();
                                            audioPlayer.gotoChoice(c);

                                            if (c == 4)
                                                break;
                                        }

                                    }
                                    catch (NoSuchFileException ex) {
                                        ex.printStackTrace();
                                    } catch (Exception ex) {
                                        System.out.println("Error with playing sound.");
                                        ex.printStackTrace();
                                    }
                                    break;
                                case 6:
                                    System.out.println("*************** Display All Song *******************");
                                    List<Song> displaySong = songDAO.getAllSong();
                                    songDAO.displaySong(displaySong);
                                    System.out.println();
                                    break;
                                case 7:
                                    System.out.println("*********************** Search Artist By Song Name ***********************");
                                    System.out.println("Enter Artist Name :: ");
                                    String ArtistName = scanner.next();

                                    Song findSongsName = songDAO.searchAllByArtistName(ArtistName);
                                    System.out.println(" Song Name = " + findSongsName.getsName());
                                    break;

                            }

                        } while (choice <=7);

                        System.out.println("--------------------------------------------------------------------------------------");
                        break;
                        //Podcast
                    case 2:
                        do {
                            System.out.println("------------------------------------- Podcast ----------------------------------------");
                            System.out.println(" 1. Add Podcast\n 2. Search by Podcast Name\n 3. Search by Podcast Celebrity Name " +
                                    "\n 4. Delete By PodcastName\n 5. Display All Podcast \n 6. Play Podcast Song  \n" /* 7. Add Song To Podcast  \n*/+" 8. Back");
                            choice = scanner.nextInt();
                            switch (choice) {
                                case 1:
                                    System.out.println("******************** Add New Podcast ******************");

                                  //  System.out.println("Enter Podcast ID :: ");
                                  //  int podcastId = scanner.nextInt();
                                    System.out.println("Enter Podcast Name:");
                                    String podcastName = scanner.next();

                                    System.out.println("Enter Celebrity Name:");
                                    String podcastCelebrityName = scanner.next();

                                    System.out.println("Enter Podcast Duration:");
                                    String duration=scanner.next();

                                    System.out.println("Enter podcast genre");
                                    String genre = scanner.next();

                                    System.out.println("Enter Podcast Date :");
                                    String podcastDate = scanner.next();
                                 //   System.out.println("Enter path Podcast :");
                                    String pPath="D:\\fullstack\\mymodernjukebox\\Resources\\podcast\\"+podcastName+".wav";


                                    Podcast podcast = new Podcast( podcastName, podcastCelebrityName,duration, genre, podcastDate,pPath);
                                    boolean resultPodcast = podcastDAO.addPodcast(podcast);
                                    if (resultPodcast) {
                                        System.out.println(podcast.getpName() + " is added successfully");
                                    } else {
                                        System.out.println(podcast.getpName() + " is not added successfully");
                                    }
                                    System.out.println();
                                    break;
                                case 2:
                                    System.out.println("******************** Search By Podcast Name **********************************************");
                                    System.out.println("Enter Podcast Name to search ::");
                                    podcastName = scanner.next();

                                    Podcast podcast1 = podcastDAO.searchPodcastByName(podcastName);
                                    System.out.println("\n" + podcast1.toString());
                                    break;

                                case 3:
                                    System.out.println("******************** Search by Podcast Celebrity Name *********************");
                                    System.out.println("Enter Podcast Celebrity Name ::");
                                    podcastCelebrityName = scanner.next();
                                    Podcast podcast2 = podcastDAO.searchPodcastByCelebrityName(podcastCelebrityName);
                                    System.out.println(podcast2.toString());
                                    System.out.println();
                                    break;

                                case 4:
                                    System.out.println("******************** Delete By PodcastName **********************************************");
                                    System.out.println("Enter PodcastName to be delete :: ");
                                    String podcastName1 = scanner.next();
                                    Podcast deletePodcast = podcastDAO.getPodcastByName(podcastName1);
                                    boolean resultDeletePodcast = podcastDAO.deletePodcastByName(podcastName1);
                                    if (resultDeletePodcast) {
                                        System.out.println(" Podcast " + deletePodcast.getpName() + " is deleted Successfully.");
                                    } else {
                                        System.out.println(" Podcast " + deletePodcast.getpName() + " is not deleted.");
                                    }
                                    System.out.println();
                                    break;
                                case 5:
                                    System.out.println("*************** Display All Podcast *******************");
                                    List<Podcast> displayPodcast = podcastDAO.getAllPodcast();
                                    podcastDAO.displayPodcast(displayPodcast);
                                    System.out.println();
                                    break;

                                case 6:
                                    System.out.println("Enter Podcast Name ::");
                                    String podcastname = scanner.next();
                                    Podcast podcast4 = podcastDAO.getPodcastByName(podcastname);
                                    System.out.println(podcast4.getpName());
                                    try {

                                        String filePath = podcast4.getpPath();
                                        AudioPlayerDAO audioPlayer = new AudioPlayerDAOImpl(filePath);
                                        audioPlayer.play();
                                        scanner = new Scanner(System.in);

                                        while (true) {

                                            System.out.println("1. pause");
                                            System.out.println("2. resume");
                                            System.out.println("3. restart");
                                            System.out.println("4. stop");
                                            int c = scanner.nextInt();
                                            audioPlayer.gotoChoice(c);

                                            if (c == 4)
                                                break;
                                        }

                                    }
                                    catch (NoSuchFileException ex) {
                                        ex.printStackTrace();
                                    } catch (Exception ex) {
                                        System.out.println("Error with playing sound.");
                                        ex.printStackTrace();
                                    }
                                    break;

//                                case 7:
//                                    System.out.println("******************** Add New Song *****************************");
//                                    System.out.println("Enter song ID ::");
//                                    int songId = scanner.nextInt();
//                                    System.out.println("Enter song Name ::");
//                                    String songName = scanner.next();
//                                    System.out.println("Enter Duration ::");
//                                    String songDuration = scanner.next();
//                                    System.out.println("Enter Location of song ::");
//                                    String songPath = scanner.next();
//                                    System.out.println("Enter Artist ID ::");
//                                    int songArtistId = scanner.nextInt();
//
//                                    Song song = new Song(songId, songName, songDuration, songPath, songArtistId);
//                                    boolean resultSong = songDAO.addSong(song);
//                                    if (resultSong) {
//                                        System.out.println("song Name = " + song.getsName() + " inserted successfully.");
//                                    } else {
//                                        System.out.println("song Name = " + song.getsName() + " is not inserted successfully.");
//                                    }
//                                    System.out.println();
//                                    break;

                            }

                        } while (choice < 8);
                        System.out.println("--------------------------------------------------------------------------------------");
                        break;
                        //Genre
                    case 3:
                        do {
                            System.out.println("----------------------------- Genre -------------------------------------------");
                            System.out.println(" 1. Add Genre \n 2. Delete Genre By Id \n 3.Display all genres \n 4. Back");
                            choice = scanner.nextInt();
                            switch (choice) {
                                case 1:
                                    System.out.println("******************** Add Genre *********************");
//                                    System.out.println("Enter genre ID:");
//                                    int genreId = scanner.nextInt();
                                    System.out.println("enter genre Name:");
                                    String genreName = scanner.next();

                                    Genres genres = new Genres(genreName);
                                    boolean result = genresDAO.addGenres(genres);
                                    if (result) {
                                        System.out.println(genreName + " added successfully to database");
                                    } else {
                                        System.out.println(genreName + " is not added successfully to database");
                                    }
                                    System.out.println();
                                    break;
                                case 2:
                                    System.out.println("******************** Delete Genre By Id  *********************");
                                    System.out.println("Enter genre ID :");
                                    int genreId = scanner.nextInt();

                                    Genres deleteGenres = genresDAO.getGenresById(genreId);
                                    boolean resultDeleteGenres = genresDAO.deleteGenres(genreId);
                                    System.out.println();
                                    if (resultDeleteGenres) {
                                        System.out.println(" Genre Id " + deleteGenres.getGenreId() + " is deleted Successfully.");
                                    } else {
                                        System.out.println(" Genre Id " + deleteGenres.getGenreId() + " is not deleted.");
                                    }
                                    System.out.println();
                                    break;
                                case 3:
                                    System.out.println("****************** Display All Genres *****************");
                                    List<Genres> displayGenres = genresDAO.getAllGenres();
                                    genresDAO.displayGenres(displayGenres);
                                    System.out.println();
                                    break;
                            }
                        } while (choice < 4);
                        System.out.println("--------------------------------------------------------------------------------------");
                        //Artist
                        break;
                    case 4:

                        do {
                            System.out.println(" 1. Add Artist \n 2. Delete Artist By Id \n 3. Display Artist \n 4. Back");
                            choice = scanner.nextInt();
                            switch (choice) {
                                case 1:
                                    System.out.println("******************** Add New Song *****************************");
                                    System.out.println("Enter Artist ID:");
                                    int artistId = scanner.nextInt();
                                    System.out.println("Enter Artist Name:");
                                    String artistName = scanner.next();
                                    System.out.println("Enter Artist Genre ID:");
                                    int artistGenreId = scanner.nextInt();

                                    Artist artist = new Artist(artistId, artistName, artistGenreId);
                                    boolean resultArtist = artistDAO.addArtist(artist);
                                    if (resultArtist) {
                                        System.out.println();
                                        System.out.println("Artist id = " + artist.getaId() + " inserted successfully.");
                                    } else {
                                        System.out.println("Artist id = " + artist.getaId() + " is not inserted.");
                                    }
                                    System.out.println();
                                    break;
                                case 2:
                                    System.out.println("******************** Delete Artist by Id **********************************************");
                                    System.out.println("Enter ArtistName to be delete");
                                    artistId = scanner.nextInt();
                                    Artist deleteArtist = artistDAO.getArtistById(artistId);
                                    boolean resultDeleteArtist = artistDAO.deleteArtist(artistId);
                                    if (resultDeleteArtist) {
                                        System.out.println();
                                        System.out.println(" Artist Id " + deleteArtist.getaId() + " is deleted Successfully.");
                                    } else {
                                        System.out.println(" Artist Id " + deleteArtist.getaId() + " is not deleted Successfully.");
                                    }
                                    System.out.println();
                                    break;
                                case 3:
                                    System.out.println("******************* Display All Artist *****************");
                                    List<Artist> allArtist = artistDAO.getAllArtist();
                                    artistDAO.displayArtist(allArtist);
                                    System.out.println();
                                    break;
                            }

                        } while (choice < 4);
                        System.out.println("--------------------------------------------------------------------------------------");
                        break;
                        //Playlist
                    case 5:
                        do {
                            System.out.println("------------------------------------------- Playlist --------------------------------");
                            System.out.println(" 1. Display All Playlist\n 2. Add Playlist \n 3. Delete Song from Any  Playlist  \n 4. Search Playlist By Name\n " +
                                    "5. Add songs  \n 6. play Song \n 7. Display All Songs In Playlist \n 8. Display All Podcast In the Playlist \n " +
                                    "9. Delete  Playlist \n 10. Add PodCasts to PlayList \n 11. Delete Podcast From Playlist \n 12. Back");
                            choice = scanner.nextInt();
                            switch (choice) {
                                case 1:
                                    System.out.println("******************** Display All Playlist ****************************");
                                    List<PlayList> allPlaylist = playlistDAO.getAllPlaylist();
                                    playlistDAO.displayPlayList(allPlaylist);
                                    System.out.println();
                                    break;
                                case 2:
                                    System.out.println("Enter Playlist Id ::");
                                    int playlistid = scanner.nextInt();
                                    System.out.println("Enter Playlist Name ::");
                                    String name = scanner.next();


                                    PlayList playList = new PlayList(playlistid,name);
                                    boolean resultPlaylist = playlistDAO.addPlaylist(playList);
                                    if (resultPlaylist) {
                                        System.out.println();
                                        System.out.println("playList id = " + playList.getPlaylistName() + " inserted successfully.");
                                    } else {
                                        System.out.println("playList id = " + playList.getPlaylistName() + " is not inserted successfully.");
                                    }

                                    System.out.println();
                                    break;

                                case 3:
                                    System.out.println("enter playlist ID  from which you want to delete the song ");
                                    playlistDAO.getAllPlaylist();
                                    playlistid = scanner.nextInt();
                                    System.out.println("Enter name of song you want to remove");
                                    String songName = scanner.next();
                                    recordDAO.deleteSongFromPlaylist(songName);
                                    break;

                                case 4:
                                    System.out.println("******************** Search Playlist By Name ***********************");
                                    System.out.println("Enter Playlist Name");
                                    name = scanner.next();

                                    PlayList playList1 = playlistDAO.searchPlaylistByName(name);
                                    System.out.println("\n" + playList1.toString());
                                    System.out.println();
                                    break;

                                case 5:
                                    System.out.println("******************** Add New Song *****************************");
                                  //  System.out.println("Enter Record ID ::");
                                  //  int recordId = scanner.nextInt();
                                    System.out.println("Enter sId, ::");
                                    int songId = scanner.nextInt();
                                    System.out.println("song sName ::");
                                    String sName = scanner.next();
                                    System.out.println("Enter sDuration ::");
                                    String duration = scanner.next();
                                    System.out.println("Enter songPath::");
                                    String songPath = "D:\\fullstack\\mymodernjukebox\\Resources\\"+sName+".wav";
                                    System.out.println("Enter songAID");
                                    int songAId= scanner.nextInt();
                                    PlaylistSong playlistSong =new PlaylistSong();

                                 //   Record record = new Record( song);
                                    boolean resultSong = playlistSongDAO.addSong(playlistSong);
                                    if (resultSong) {
                                        System.out.println("song Id = " + playlistSong.getsId() + " inserted successfully.");
                                    } else {
                                        System.out.println("song Id = " + playlistSong.getsId() + " is not inserted successfully.");
                                    }
                                    System.out.println();
                                    break;

                                case 6:
                                    System.out.println("Enter Playlist(s) name ::");
                                    String songName2 = scanner.next();


                                    PlaylistSong songPath1 = playlistSongDAO.getSongPathByName(songName2);
                                    System.out.println(songPath1.getsName());
                                    try {

                                        String filePath = songPath1.getSongPath();
                                        AudioPlayerDAO audioPlayer = new AudioPlayerDAOImpl(filePath);
                                        audioPlayer.play();
                                        scanner = new Scanner(System.in);

                                        while (true) {

                                            System.out.println("1. pause");
                                            System.out.println("2. resume");
                                            System.out.println("3. restart");
                                            System.out.println("4. stop");
                                            int c = scanner.nextInt();
                                            audioPlayer.gotoChoice(c);
                                            if (c == 4)
                                                break;
                                        }


                                    } catch (NoSuchFileException ex) {
                                        ex.printStackTrace();
                                    } catch (Exception ex) {
                                        System.out.println("Error with playing sound.");
                                        ex.printStackTrace();
                                    }

                                    break;

                                case 7:
                                    System.out.println("Enter name of playlist you want to view the content for");
                                    String playlistName1 = scanner.next();
                                    recordDAO.viewPlaylistContent(playlistName1);
                                    break;

                                case 8:
                                    System.out.println("Enter name of playlist you want to view the content for");
                                    String playlistName2 = scanner.next();
                                    recordDAO.viewPodcastContent(playlistName2);
                                    break;




                                case 9:
                                    System.out.println("******************** Delete Song from playlist *********************");
                                    System.out.println("Enter song id :: ");
                                    songId = scanner.nextInt();
                                    PlaylistSong song2 = playlistSongDAO.getSongById(songId);
                                    boolean resultSong2 = songDAO.deleteSongById(songId);
                                    if (resultSong2) {
                                        System.out.println(song2.getsId() + " is successfully Deleted");
                                    } else {
                                        System.out.println("Song Id is not Deleted.");
                                    }
                                    System.out.println();
                                    break;


                                case 10:
                                    System.out.println(" available Podcasts ");
                                    podcastDAO.getAllPodcast();
                                    System.out.println("Enter podcast Id");
                                    int podcastId1 = scanner.nextInt();
                                    System.out.println("Enter Playlist Id");
                                    int playlistId1 = scanner.nextInt();
                                    System.out.println("Enter record ID based on the record table shown");
                                    recordDAO.displayAllRecord(recordDAO.showAllRecord());
                                    int recordId1 = scanner.nextInt();
                                    int songId1 = 0;
                                    int albumId1 = 0;
                                    Record record1 = new Record(recordId1, songId1, podcastId1, playlistId1, albumId1);
                                    recordDAO.addPodcastToPlaylist(record1);
                                    System.out.println("Podcast added successfully");
                                    break;

                                case 11:
                                    System.out.println("Enter playlist ID from which you want to delete the podcast ");
                                    playlistDAO.getAllPlaylist();
                                    playlistid = scanner.nextInt();
                                    System.out.println("Enter name of podcast you want to remove");
                                    String podcast = scanner.next();
                                    recordDAO.deletePodcastFromPlaylist(podcast);
                                    break;
                                case 12:
                                    System.out.println("Enter song Id ::");
                                    int sId = scanner.nextInt();
                                    System.out.println("Enter sName ::");
                                    sName = scanner.next();
                                    System.out.println("Enter sDuration ::");
                                    String sDuration = scanner.next();
                                    System.out.println("Enter songPath ::");
                                    songPath = scanner.nextLine();
                                    System.out.println("Enter songAID ::");
                                    int songAID = scanner.nextInt();
                                    PlaylistSong playlistSong1=new PlaylistSong( sId ,sName,  sDuration,  songPath,  songAID);
                                    playlistSongDAO.addSong(playlistSong1);

                            }

                        } while (choice < 12);
                        System.out.println("--------------------------------------------------------------------------------------");
                        break;

                        //Catalog
                    case 6:
                        do {
                            System.out.println("------------------------------------- Catalog ------------------------------------------------");
                            System.out.println(" 1. Display All Catalog\n 2. Add Catalog\n 3. Delete Catalog\n 4. Search Catalog By Name \n 5. Display Song From Catalog \n" +
                                    " 6. Display Podcast From Catalog \n 7. Play song  \n 8. Add song To Catalog  \n 9. Back ");
                            choice = scanner.nextInt();
                            switch (choice) {
                                case 1:
                                    System.out.println("******************** Display All Catalog ***********************");
                                    List<Catalog> displayCatalog = catalogDAO.getAllCatalog();
                                    catalogDAO.displayCatalog(displayCatalog);
                                    System.out.println();
                                    break;
                                case 2:
                                    System.out.println("******************** Add Catalog **********************");
                                    System.out.println("Enter Catalog Id :: ");
                                    int catalogId = scanner.nextInt();
                                    System.out.println("Enter Catalog Name :: ");
                                    String catalogName = scanner.next();
                                    System.out.println("Enter Catalog Item Id  :: ");
                                    int catalogItem = scanner.nextInt();
                                    System.out.println("Enter Catalog Item Type  :: ");
                                    String catalogType = scanner.next();
                                    System.out.println("Enter song Name :: ");
                                    String sName=scanner.next();
                                    System.out.println("Enter Catalog Path :: ");
                                    String songPath=scanner.next();



                                    Catalog catalog = new Catalog(catalogId, catalogName, catalogItem, catalogType,sName,songPath);
                                    boolean addCatalog = catalogDAO.addCatalog(catalog);
                                    if (addCatalog) {
                                        System.out.println(" Catalog " + catalog.getcName() + " is added successfully");
                                    } else {
                                        System.out.println("Catalog is not added successfully or have duplicate values");
                                    }
                                    System.out.println();
                                    break;
                                case 3:
                                    System.out.println("******************** Delete Catalog ***********************");
                                    System.out.println("Enter Catalog ID ");
                                    catalogId = scanner.nextInt();
                                    boolean result = catalogDAO.deleteCatalog(catalogId);
                                    if (result) {
                                        System.out.println(" Catalog is deleted successfully");
                                    } else {
                                        System.out.println("Catalog is Not Deleted");
                                    }
                                    System.out.println();
                                    break;
                                case 4:
                                    System.out.println("****************** Search Catalog By Name *******************");
                                    System.out.println("Enter Catalog Name :: ");
                                    catalogName = scanner.next();
                                    Catalog catalog1 = catalogDAO.searchCatalogByName(catalogName);
                                    System.out.println("\n" + catalog1.toString());
                                    System.out.println();
                                    break;
                                case 5:
                                    System.out.println("*************** Display Song From Catalog *******************");
                                    List<Song> displaySong = catalogDAO.getSongFromCatalog();
                                    catalogDAO.displaySongFromCatalog(displaySong);
                                    System.out.println();
                                    break;
                                case 6:
                                    System.out.println("*************** Display Podcast From Catalog *******************");
                                    List<Podcast> displayPodcast = catalogDAO.getPodcastFromCatalog();
                                    catalogDAO.displayPodcastFromCatalog(displayPodcast);
                                    System.out.println();
                                    break;
                                case 7:
                                    System.out.println("**************** Play song from playlist ********************");
                                    System.out.println("Enter Song Name ::");
                                    String songName2 = scanner.next();
                                    Song songPath1 = songDAO.getSongPathByName(songName2);
                                    System.out.println(songPath1.getsName());
                                    try {

                                        String filePath = songPath1.getSongPath();
                                        AudioPlayerDAO audioPlayer = new AudioPlayerDAOImpl(filePath);
                                        audioPlayer.play();
                                        scanner = new Scanner(System.in);

                                        while (true) {

                                            System.out.println("1. pause");
                                            System.out.println("2. resume");
                                            System.out.println("3. restart");
                                            System.out.println("4. stop");
                                            int c = scanner.nextInt();
                                            audioPlayer.gotoChoice(c);

                                            if (c == 4)
                                                break;
                                        }

                                    }
                                    catch (NoSuchFileException ex) {
                                        ex.printStackTrace();
                                    } catch (Exception ex) {
                                        System.out.println("Error with playing sound.");
                                        ex.printStackTrace();
                                    }
                                    break;

                                case 8:
                                    System.out.println("******************** Add New Song *****************************");
                                    System.out.println("Enter song ID ::");
                                    int songId = scanner.nextInt();
                                    System.out.println("Enter song Name ::");
                                    String songName = scanner.next();
                                    System.out.println("Enter Duration ::");
                                    String songDuration = scanner.next();
                                    System.out.println("Enter Location of song ::");
                                    songPath = scanner.next();
                                    System.out.println("Enter Artist ID ::");
                                    int songArtistId = scanner.nextInt();

                                    Song song = new Song(songId, songName, songDuration, songPath, songArtistId);
                                    boolean resultSong = songDAO.addSong(song);
                                    if (resultSong) {
                                        System.out.println("song Name = " + song.getsName() + " inserted successfully.");
                                    } else {
                                        System.out.println("song Name = " + song.getsName() + " is not inserted successfully.");
                                    }
                                    System.out.println();
                                    break;
                            }

                        } while (choice < 9);
                        System.out.println("--------------------------------------------------------------------------------------");
                        break;
                        //Album
                    case 7:
                        do {
                            System.out.println("------------------------------------ Album ----------------------------------------");
                            System.out.println(" 1. Add Album\n 2. Search by Album Name \n 3. Search By Album Date \n " +
                                    "4. Delete By Album Id \n 5. Display Album \n 6. Back");
                            choice = scanner.nextInt();
                            switch (choice) {
                                case 1:
                                    System.out.println("****************** Add Album ********************");
                                    System.out.println("Enter Album Id :: ");
                                    int albumId = scanner.nextInt();
                                    System.out.println("Enter Album Name :: ");
                                    String albumName = scanner.next();
                                    System.out.println("Enter Album Date (DD-MM-YYYY) ::");
                                    String albumDate = scanner.next();
                                    System.out.println("Enter Song Id :: ");
                                    int songId = scanner.nextInt();

                                    Album album = new Album(albumId, albumName, albumDate, songId);

                                    boolean resultAlbum = albumDAO.addAlbum(album);
                                    if (resultAlbum) {
                                        System.out.println(album.getAlbumName() + " is added Successfully");
                                    } else {
                                        System.out.println(album.getAlbumName() + " is not added Successfully or have duplicate values");
                                    }
                                    System.out.println();
                                    break;
                                //search by album name;
                                case 2:
                                    System.out.println("******************** Search By Album Date ********************");
                                    System.out.println("Enter AlbumName ::");
                                    albumName = scanner.next();
                                    Album album3 = albumDAO.searchByAlbumName(albumName);
                                    System.out.println("\n" + album3.toString());
                                    System.out.println();
                                    break;
                                case 3:
                                    System.out.println("******************** Search By Album Date ********************");
                                    System.out.println("Enter Album date (DD-MM-YYYY) ::");
                                    albumDate = scanner.next();
                                    Album album1 = albumDAO.searchByAlbumDate(albumDate);
                                    System.out.println("\n" + album1.toString());
                                    System.out.println();
                                    break;

                                case 4:
                                    System.out.println("******************** Delete Album By Id **********************");
                                    System.out.println("Enter Album id :: ");
                                    albumId = scanner.nextInt();

                                    Album album2 = albumDAO.getByAlbumId(albumId);
                                    boolean result = albumDAO.deleteByAlbumId(albumId);
                                    if (result) {
                                        System.out.println(album2.getAlbumName() + " is deleted.");
                                    } else {
                                        System.out.println("Album is Not Deleted");
                                    }
                                    System.out.println();
                                    break;
                                case 5:
                                    System.out.println("****************** Display Album *****************");
                                    List<Album> displayAlbum = albumDAO.getAllAlbum();
                                    if (displayAlbum.size() == 0) {
                                        throw new AlbumNotFoundException("No Album present in the album List");
                                    }
                                    else
                                    {
                                        albumDAO.displayAlbum(displayAlbum);
                                        System.out.println();
                                    }


                            }

                        } while (choice <=5);
                        break;
                }
                System.out.println("--------------------------------------------------------------------------------------");
            } while (choice1 < 8);


        }
        catch (AlbumNotFoundException exception)
        {
            System.out.println(exception.getMessage());
        }
        catch (InputMismatchException exception)
        {
            System.out.println(exception.toString());
        }
        catch (Exception exception)
        {
            System.out.println(exception.toString());
        }
    }

}