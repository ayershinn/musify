package com.lynqq.musify;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.lynqq.musify.beans.Folder;
import com.lynqq.musify.fragments.FoldersFragment;
import com.lynqq.musify.helpers.GetSongListHelper;
import com.lynqq.musify.util.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MusifyAppState appState;
    private final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private Toolbar toolbar;

    private  ArrayList<Folder> songFolders = new ArrayList<Folder>();
    private ArrayList<Folder> tempSongFolders = new ArrayList<Folder>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        // Setup application class
        appState = ((MusifyAppState) getApplicationContext());
        appState.setContextForPreferences(this);

        // test functions
        String mac_address_wlan =   Utils.getMACAddress("wlan0");
        String mac_address_eth0 = Utils.getMACAddress("eth0");
        String ip_address =  Utils.getIPAddress(true); // IPv4

        Log.v("ip address: ", ip_address);
        Log.v("mac address wlan: ", mac_address_wlan);
        Log.v("mac address eth0: ", mac_address_eth0);


        if (Build.VERSION.SDK_INT >= 23) {
            // Marshmallow+
            readFromStorageWithCheck();
            Log.v("done", " read");
        } else {
            // Pre-Marshmallow
          //  SongsManager j = new SongsManager(true, true);
           // j.getPlayList();
           // getSongList();
            GetSongListHelper getSong = new GetSongListHelper();
            getSong.GetSongListHelper(this);
            getSong.getSongList();
            showView();
        }

    }

   /* public void getSongList() {
        //retrieve song info
        ContentResolver musicResolver = getApplicationContext().getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
        //iterate over results if valid
        if(musicCursor!=null && musicCursor.moveToFirst()){
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            int albumId = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.ALBUM_ID);
            int data= musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int albumkey=musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_KEY);

            //add songs to list
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                long thisalbumId = musicCursor.getLong(albumId);
                String thisdata= musicCursor.getString(data);
                String AlbumKey = musicCursor.getString(albumkey);
                tempSongList.add(new SongTemp(thisId, thisTitle, thisArtist, thisalbumId, thisdata, AlbumKey));

                Log.v("Song Temp", String.valueOf(thisId) + ", " + thisTitle + ", " + thisArtist + ", "+ thisalbumId + ", " + thisdata + ", " + AlbumKey);

                File f = new File(thisdata);
                String folderPath = f.getParent();
                String folderName = folderPath.substring(folderPath.lastIndexOf("/")+1);

                folderNamesTemp.add(folderName);
                Log.v("Folder Name", folderName);
                Log.v("folderNamesTemp  Main", String.valueOf(folderNamesTemp.size()));
            }
            while (musicCursor.moveToNext());

            showView();
        }


    }*/

    public void showView(){
      /*  if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            FoldersFragment frag = new FoldersFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            frag.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, frag).commit();

        }*/
        super.onPostResume(); //put this to wait for the getSongList to finish its process before showing the view (avoid empty list on startup)

        Fragment frag;
        FragmentManager fm1 = MainActivity.this
                .getSupportFragmentManager();
        FragmentTransaction ft1 = fm1.beginTransaction();
        frag = new FoldersFragment();
        ft1.replace(R.id.fragment_container, frag);
        ft1.commit();
    }



   /* String internal_media_path = Environment.getExternalStorageDirectory().getPath() + "/";  //   /storage/emulated/0/

    //for external storage:
    String sdcard_media_path = Environment.getExternalStorageDirectory().getPath();
    String[] splitPath= sdcard_media_path .split("/");
    private String external_media_path = "/" + splitPath[1] + "/";   //  /storage/



    // private String MEDIA_PATH[];
    private List<String> MEDIA_PATH = new ArrayList<String>();
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    private String mp3Pattern = ".mp3";



    // Constructor
    public SongsManager() {

    }

    *//**
     * Function to read all mp3 files and store the details in
     * ArrayList
     * *//*
    public ArrayList<HashMap<String, String>> getPlayList(boolean internal_storage, boolean external_storage) {

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
        Log.d("split path", splitPath[0] + " " + splitPath[1] + " " + splitPath[2]);
        Log.d("sdcard_media_path: " , sdcard_media_path);
        Log.d("Storages: ", String.valueOf(internal_media_path + " : " + external_media_path));

        for (int i = 0; i < MEDIA_PATH.size(); i++){
            if (MEDIA_PATH.get(i) != null) {
                Log.v("Media Path: " + i, (MEDIA_PATH.get(i)));
                File home = new File(MEDIA_PATH.get(i));
                File[] listFiles = home.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (File file : listFiles) {
                        Log.v("File absolutePath: ",file.getAbsolutePath());
                        if (file.isDirectory()) {
                            scanDirectory(file);
                        } else {
                            addSongToList(file);
                            Log.v("song list", String.valueOf(file.toString() ));
                        }
                    }
                }
            }
            Log.v("playList size: " + i, String.valueOf(songsList.size()));
        }

        mScanningLayout.setVisibility(View.INVISIBLE);
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
            mScanningItems.setText(song.getPath().toString());
            Log.v("add to song list", songMap.toString());
          //  String folderName = song.getPath().substring(song.getPath().lastIndexOf("/")+1);

            File f = new File(song.getPath());
            String path = f.getParent();

            String folderName = path.substring(path.lastIndexOf("/")+1);
            Log.v("Folder ", folderName);
            songsList.add(songMap);
        }
    }*/



    private void readFromStorageWithCheck() {
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                showMessageOKCancel(null, "OK", "Cancel", "Allow Musify to access to your Storage to scan for music/audio from your storage.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this,  new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                                        REQUEST_CODE_ASK_PERMISSIONS);

                            }
                        });
                return;
            }

            //Show with "Never ask again"
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_ASK_PERMISSIONS);

         Toast.makeText(getApplicationContext(), "Deny with Never ask again: SHOWN", Toast.LENGTH_SHORT)
                    .show();

            return;
        }
     //   getPlayList(true, true);
    //   SongsManager j = new SongsManager(true, true);
     //   j.getPlayList();

   //   getSongList();

        GetSongListHelper getSong = new GetSongListHelper();
        getSong.GetSongListHelper(this);
        getSong.getSongList();
        showView();

    }

    private void showMessageOKCancel(String title, String positiveBtnMsg, String negativeBtnMsg, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(positiveBtnMsg, okListener)
                .setNegativeButton(negativeBtnMsg, null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.v ("request code", String.valueOf(requestCode));
        switch (requestCode) {
            case  REQUEST_CODE_ASK_PERMISSIONS:
            {
                Log.v ("SINGLE PERMISSION", "captureVideo");
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted

                 //   getPlayList(true, true);
                 //   SongsManager j = new SongsManager(true, true);
                  //  j.getPlayList();

                    // getSongList();

                    GetSongListHelper getSong = new GetSongListHelper();
                    getSong.GetSongListHelper(this);
                    getSong.getSongList();
                    showView();

                } else {
                    // Permission Denied

                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE );
                    if (!showRationale) {
                        // user denied flagging NEVER ASK AGAIN
                        // you can either enable some fall back,
                        // disable features of your app
                        // or open another dialog explaining
                        // again the permission and directing to
                        // the app setting
                        Toast.makeText(getApplicationContext(), "Denied with Never ask again: TRUE", Toast.LENGTH_SHORT)
                                .show();

                        showMessageOKCancel("Allow Musify to use your phone's storage?", "App Settings", "Not now",
                                "This lets Musify store and access information like music on your phone and its SD card.\n\n" +
                                        "To enable this, click App Settings below and activate Storage under the Permissions menu.",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent();
                                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                                        intent.setData(uri);
                                        startActivity(intent);

                                    }
                                });


                    } else {
                        Toast.makeText(getApplicationContext(), "READ_EXTERNAL_PERMISSION Denied", Toast.LENGTH_SHORT)
                                .show();
                        // this is a good place to explain the user
                        // why you need the permission and ask if he want
                        // to accept it (the rationale)
                    }

                }

            }break;

            default:
                Log.v ("DEFAULT", "captureVideo");
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }