package com.niit.model;

public class Catalog {
    private int cId;
    private String cName;
    private int itemId;
    private String itemType;
    private String sName;
    private String songPath;
    //private int playlistId;

    public Catalog(int cId, String cName, int itemId, String itemType, String sName, String songPath) {
        this.cId = cId;
        this.cName = cName;
        this.itemId = itemId;
        this.itemType = itemType;
        this.sName=sName;
        this.songPath=songPath;
        //this.playlistId=playlistId;
    }
    public Catalog() {
        this.cId = cId;
        this.cName = cName;
        this.itemId = itemId;
        this.itemType = itemType;
        this.sName=sName;
        this.songPath=songPath;
        //this.playlistId=playlistId;
    }

    public int getcId() {
        return cId;
    }

    public String getcName() {
        return cName;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public String getsName(){  return  sName; }

    public String getSongPath() { return  songPath;}

  /*  public int getPlaylistId() {
        return playlistId;
    }*/

    public String toString() {

        return " Catalog Id = " + cId +"\t"+", Catalog Name = " + cName + "\t"+", Item Id = " + itemId+"\t"
                +", Item Type = "+itemType+"\t"+", Song Name = "+sName+"\t"+", Catalog Path = "+songPath+"\n";
    }
}
/*
System.out.println("Enter Playlist Id :: ");
        int playlistId = scanner.nextInt();
        ", Playlist Id = "+playlistId+*/
