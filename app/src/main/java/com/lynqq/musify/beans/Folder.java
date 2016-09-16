package com.lynqq.musify.beans;

import java.util.ArrayList;

/**
 * Created by Keuahn on 8/3/2016.
 */
public class Folder {


    private Long albumId;
    private String folderPath;
    private String folderName;
    private String folderStorage;
    public ArrayList<Song> songs;

    public Long getFolderId() {
        return albumId;
    }
    public void setFolderId(Long albumId) {
        this.albumId = albumId;
    }

    public String getFolderStorage() {
        return folderStorage;
    }
    public void setFolderStorage(String folderStorage) {
        this.folderStorage = folderStorage;
    }

    public String getFolderPath() {
        return folderPath;
    }
    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getFolderName() {
        return folderName;
    }
    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

}
