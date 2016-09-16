package com.lynqq.musify;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lynqq.musify.beans.Folder;
import com.lynqq.musify.beans.Song;

import java.util.ArrayList;

/**
 * Created by Keuahn on 8/12/2016.
 */
public class MusifyAppState extends Application {

    // Prefs
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    ArrayList<Folder> songFolders = new ArrayList<Folder>();
    ArrayList<Song> songs = new ArrayList<Song>();

    private Folder selectedFolder;
    private Song selectedSong;

    public void setContextForPreferences(Context activity) {
        // Setup preferences
        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        editor = preferences.edit();
    }

    public ArrayList<Folder> getSongFolders() {
        return songFolders;
    }

    public void setSongFolders(ArrayList<Folder> songFolders) {
        this.songFolders = songFolders;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }


    public Folder getSelectedFolder() {
        return selectedFolder;
    }

    public void setSelectedFolder(Folder selectedFolder) {
        this.selectedFolder = selectedFolder;
    }

    public Song getSelectedSong() {
        return selectedSong;
    }

    public void setSelectedSong(Song selectedSong) {
        this.selectedSong = selectedSong;
    }


}
