package com.lynqq.musify.beans;

import java.util.List;

/**
 * Created by Keuahn on 8/3/2016.
 */
public class FoldersData {

    private static FoldersData instance;
    private List<Folder> folders;
    private List<Song> songs;

    public FoldersData(){}

    public static FoldersData getInstance() {
        if (instance == null) {
            instance = new FoldersData();
        }
        return instance;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
