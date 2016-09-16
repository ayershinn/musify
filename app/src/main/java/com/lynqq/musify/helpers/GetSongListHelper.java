package com.lynqq.musify.helpers;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.lynqq.musify.MusifyAppState;
import com.lynqq.musify.beans.Folder;
import com.lynqq.musify.beans.Song;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Keuahn on 8/16/2016.
 */
public class GetSongListHelper {

    private MusifyAppState appState;
    private Activity activity;
    private ArrayList<Folder> songFolders = new ArrayList<Folder>();
    private ArrayList<Song> songs = new ArrayList<Song>();


    public void GetSongListHelper(Activity a){
        this.activity = a;

        // Setup application class
        appState = ((MusifyAppState) activity.getApplicationContext());
        appState.setContextForPreferences(activity);
    }

    public void getSongList() {
        ArrayList<Folder> tempSongFolders = new ArrayList<Folder>();
        ArrayList<Song> tempSongs = new ArrayList<Song>();


        //retrieve song info
        ContentResolver musicResolver = activity.getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
        //iterate over results if valid
        if(musicCursor!=null && musicCursor.moveToFirst()){
            //get columns
            int titleColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST);
            int albumId = musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
            int data = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int albumkey = musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_KEY);
            int duration = musicCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            //add songs to list
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                long thisalbumId = musicCursor.getLong(albumId);
                String thisdata= musicCursor.getString(data);
                String AlbumKey = musicCursor.getString(albumkey);
                String thisDuration = musicCursor.getString(duration);
            //    tempSongList.add(new Song(thisId, thisTitle, thisArtist, thisalbumId, thisdata, AlbumKey));

                Log.v("Song Temp", String.valueOf(thisId) + ", " + thisTitle + ", " + thisArtist + ", "+ thisalbumId + ", " + thisdata + ", " + AlbumKey);

                File f = new File(thisdata);
                String folderPath = f.getParent();
                String folderName = folderPath.substring(folderPath.lastIndexOf("/")+1);

                Folder tempSongFolder = new Folder();
                Song tempSong = new Song();

                //set song data
                tempSong.setSongId(thisId);
                tempSong.setSongPath(thisdata);
                tempSong.setSongTitle(thisTitle);
                tempSong.setSongArtist(thisArtist);
                tempSong.setSongDuration(thisDuration);
                tempSong.setFolderId(thisalbumId);
                tempSong.setFolderName(folderName);
                tempSongs.add(tempSong);


                //set folder data
                tempSongFolder.setFolderId(thisalbumId);
                tempSongFolder.setFolderPath(folderPath);
                tempSongFolder.setFolderName(folderName);
                tempSongFolder.setSongs(songs);
                tempSongFolders.add(tempSongFolder);

             //   folderNamesTemp.add(folderName);

                Log.v("Folder Name", folderName);
                Log.v("tempSongFolder", tempSongFolder.getFolderName());

            } while (musicCursor.moveToNext());

            songs = tempSongs;
            songFolders = tempSongFolders;
            appState.setSongs(songs);
            appState.setSongFolders(songFolders);

            for(Folder f: appState.getSongFolders()){
                Log.v("hay foldername ", String.valueOf(f.getFolderId()));
            }
        }
    }
}
