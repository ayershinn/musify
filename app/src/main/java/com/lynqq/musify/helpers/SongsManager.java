package com.lynqq.musify.helpers;

import android.os.Environment;
import android.util.Log;

import com.lynqq.musify.beans.Folder;
import com.lynqq.musify.beans.FoldersData;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Keuahn on 8/2/2016.
 */
public class SongsManager {


       //for internal storage:
    String internal_media_path = Environment.getExternalStorageDirectory().getPath() + "/";

    //for external storage:
    String sdcard_media_path = Environment.getExternalStorageDirectory().getPath();
    String[] splitPath= sdcard_media_path .split("/");
    private String external_media_path = "/" + splitPath[1] + "/";

   // private String MEDIA_PATH[];
    private List<String> MEDIA_PATH = new ArrayList<String>();
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    private String mp3Pattern = ".mp3";

    private FoldersData foldersData;
    private List<Folder> folders = new ArrayList<Folder>();


    // Constructor
    public SongsManager(boolean internal_storage, boolean external_storage) {
        foldersData = FoldersData.getInstance();
        if ((internal_storage == true) && (external_storage == true)){
            MEDIA_PATH.add(internal_media_path);
            MEDIA_PATH.add(external_media_path);

        }else if ((internal_storage == true)&& (external_storage == false)){
            MEDIA_PATH.add(internal_media_path);
            MEDIA_PATH.add("");

        }else if ((internal_storage == false)&& (external_storage == true)){
            MEDIA_PATH.add(external_media_path);
            MEDIA_PATH.add("");
        }
        Log.v("storages: ", String.valueOf(internal_storage + " : " + external_storage));
    }

    /**
     * Function to read all mp3 files and store the details in
     * ArrayList
     * */
    public ArrayList<HashMap<String, String>> getPlayList() {
        folders.clear();
        songsList.clear();

        for (int i = 0; i < MEDIA_PATH.size(); i++){
            if (MEDIA_PATH.get(i) != null) {
                Log.v("Media Path: " + i, (MEDIA_PATH.get(i)));
                File home = new File(MEDIA_PATH.get(i));
                File[] listFiles = home.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (File file : listFiles) {
                        Log.v("File absolutePath: ",file.getAbsolutePath());
//                        MainActivity.mScanningItems.setText(file.getAbsolutePath().toString());
                        if (file.isDirectory()) {
                            scanDirectory(file);
                        } else {
                            addSongToList(file);
                            Log.v("song list", String.valueOf(file.toString()));

                        }
                    }
                }
            }
            Log.v("playList size: " + i, String.valueOf(songsList.size()));
        }
        //add to list
        foldersData.setFolders(folders);
 //       MainActivity.mScanningLayout.setVisibility(View.INVISIBLE);
        return songsList;

    }

    private void scanDirectory(File directory) {
        if (directory != null) {
            File[] listFiles = directory.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (file.isDirectory()) {
                        scanDirectory(file);
                    } else {
                        addSongToList(file);
                    }

                }
            }
        }
    }

    private void addSongToList(File song) {
        if (song.getName().endsWith(mp3Pattern)) {
            HashMap<String, String> songMap = new HashMap<String, String>();
            songMap.put("songTitle",
                    song.getName().substring(0, (song.getName().length() - 4)));
            songMap.put("songPath", song.getPath());

            // Adding each song to SongList
            Log.v("add to song list", songMap.toString());
            //  String folderName = song.getPath().substring(song.getPath().lastIndexOf("/")+1);

            //to get the folder name only
            File f = new File(song.getPath());
            String folderPath = f.getParent();
            String folderName = folderPath.substring(folderPath.lastIndexOf("/")+1);
            Log.v("folderPath ", folderPath);
            Log.v("Folder ", folderName);



            Folder folder = new Folder();
          //  folder.setFolderId(UUID.randomUUID().toString());
            folder.setFolderName(folderName);
            folder.setFolderPath(folderPath);

            folders.add(folder);
            songsList.add(songMap);
        }
    }
}
