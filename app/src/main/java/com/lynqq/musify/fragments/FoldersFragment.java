package com.lynqq.musify.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lynqq.musify.MusifyAppState;
import com.lynqq.musify.R;
import com.lynqq.musify.beans.Folder;
import com.lynqq.musify.helpers.SongTemp;
import com.lynqq.musify.ui.DividerItemDecoration;
import com.lynqq.musify.ui.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Keuahn on 8/6/2016.
 */
public class FoldersFragment  extends Fragment {

  /*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_folders, container, false);
    }*/

    private MusifyAppState appState;

    //to avoid duplicate names
    private ArrayList<Folder> folderNames = new ArrayList<>();
    private List<String> folderNamesTemp = new ArrayList<>();
    private Set<String> hs = new HashSet<>();

    private View v;
    private ViewGroup rootView;
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView mRecyclerView;
    private SimpleAdapter mAdapter;
    private final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private boolean doneScanning;

    private ArrayList<SongTemp> tempSongList = new ArrayList<>();
    public FoldersFragment() {
    }


    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Setup application class
        appState = ((MusifyAppState) getActivity().getApplicationContext());
        appState.setContextForPreferences(getActivity());

        v = inflater.inflate(R.layout.fragment_folders, container, false);
        rootView = container;
        coordinatorLayout = (CoordinatorLayout) v.findViewById(R.id.coordinator);

        //Your RecyclerView
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL));


       /* if (Build.VERSION.SDK_INT >= 23) {
            // Marshmallow+
            readFromStorageWithCheck();

        } else {
            // Pre-Marshmallow
            getSongList();
          //  SongsManager j = new SongsManager(true, true);
          //  j.getPlayList();
          //  showView();
        }*/

        showView();

        return v;
    }

  /*  public void getSongList() {
        //retrieve song info
        ContentResolver musicResolver = getActivity().getContentResolver();
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
            }
            while (musicCursor.moveToNext());

            showView();
        }


    } */

    private void showView(){

        final ArrayList<Folder> tempFolderNames =  appState.getSongFolders();

        for(Folder f: tempFolderNames){

        }
        mAdapter = new SimpleAdapter(getActivity(), tempFolderNames);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.invalidate();
       /* mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // ...
                Toast.makeText(getActivity().getApplicationContext(), ": Position ="+ position, Toast.LENGTH_SHORT).show();


               *//* View rowView = mRecyclerView.getChildAt(position);
                if(rowView != null)
                {
                    // do whatever you want here
                //    ck = (CheckBox) v.findViewWithTag(tag);
                 //   ck.setChecked(true);
                    CheckBox checkBox = (CheckBox)rowView.findViewById(R.id.checkBox);
                    checkBox.setChecked(!checkBox.isChecked());
                }*//*

            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
                Toast.makeText(getActivity().getApplicationContext(), tempFolderNames.get(position).toString()+": Long Click Position ="+ position, Toast.LENGTH_SHORT).show();
            }
        }));*/

       /* for(int i = 0; i <folderNamesTemp1.size(); i++){
            folderNames.add(folderNamesTemp1.get(i));
        }


        // add elements to folderNames, including duplicates
        hs.addAll(folderNames);
        folderNames.clear();
        folderNames.addAll(hs);


        for (int i = 0; i < folderNames.size(); i++) {
            Log.v("folderNamesssss", folderNames.get(i));
            //   mFolderName.setText(mFolderName.getText() + "\n"  +folderNames.get(i));

            if(i==folderNames.size()-1){
                doneScanning = true;
                Log.v("foldername size i = " ,  String.valueOf(i));
                showRecyclerView();
            }
        }*/






        /*folders = foldersData.getFolders();
        Log.v("Folders Size", String.valueOf(folders.size()));


        for (Folder folder : folders) {
            Log.v("folderID", folder.getFolderId());
            Log.v("folderName", folder.getFolderName());
            folderNames.add(folder.getFolderName());
        }


        // add elements to folderNames, including duplicates
        hs.addAll(folderNames);
        folderNames.clear();
        folderNames.addAll(hs);

        for (int i = 0; i < folderNames.size(); i++) {
            Log.v("folderNamesssss", folderNames.get(i));
            //   mFolderName.setText(mFolderName.getText() + "\n"  +folderNames.get(i));
        }


        //Your RecyclerView
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL));

        //Your RecyclerView.Adapter
        mAdapter = new SimpleAdapter(getActivity(),folderNames);


        //This is the code to provide a sectioned list
        List<SimpleSectionedRecyclerViewAdapter.Section> sections =
                new ArrayList<SimpleSectionedRecyclerViewAdapter.Section>();

        //Sections
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0,"Section 1"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(2,"Section 2"));
        //    sections.add(new SimpleSectionedRecyclerViewAdapter.Section(12,"Section 3"));
        //     sections.add(new SimpleSectionedRecyclerViewAdapter.Section(14,"Section 4"));
        //     sections.add(new SimpleSectionedRecyclerViewAdapter.Section(20,"Section 5"));

        //Add your adapter to the sectionAdapter
        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
        SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new
                SimpleSectionedRecyclerViewAdapter(getActivity().getApplicationContext(),R.layout.section,R.id.section_text,mAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
//            mRecyclerView.setAdapter(mSectionedAdapter);
        mRecyclerView.setAdapter(mAdapter);
*/
    }

   /* private void showRecyclerView(){
        //Your RecyclerView.Adapter
        mAdapter = new SimpleAdapter(getActivity(),folderNames);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.invalidate();
    }
*/

    private void readFromStorageWithCheck() {
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                showMessageOKCancel(null, "OK", "Cancel", "Allow Musify to access to your Storage to scan for music/audio from your storage.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(getActivity(),  new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                                        REQUEST_CODE_ASK_PERMISSIONS);

                            }
                        });
                return;
            }

            //Show with "Never ask again"
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_ASK_PERMISSIONS);

            Toast.makeText(getActivity().getApplicationContext(), "Deny with Never ask again: SHOWN", Toast.LENGTH_SHORT)
                    .show();

            return;
        }
        //   getPlayList(true, true);
       // SongsManager j = new SongsManager(true, true);
       // j.getPlayList();
       // showView();

       // getSongList();

    }

    private void showMessageOKCancel(String title, String positiveBtnMsg, String negativeBtnMsg, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getActivity())
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

                  //  SongsManager j = new SongsManager(true, true);
                  //  j.getPlayList();
                  //  showView();

                //    getSongList();
                } else {
                    // Permission Denied

                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE );
                    if (!showRationale) {
                        // user denied flagging NEVER ASK AGAIN
                        // you can either enable some fall back,
                        // disable features of your app
                        // or open another dialog explaining
                        // again the permission and directing to
                        // the app setting
                        Toast.makeText(getActivity().getApplicationContext(), "Denied with Never ask again: TRUE", Toast.LENGTH_SHORT)
                                .show();

                        showMessageOKCancel("Allow Musify to use your phone's storage?", "App Settings", "Not now",
                                "This lets Musify store and access information like music on your phone and its SD card.\n\n" +
                                        "To enable this, click App Settings below and activate Storage under the Permissions menu.",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent();
                                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", getActivity().getApplicationContext().getPackageName(), null);
                                        intent.setData(uri);
                                        startActivity(intent);

                                    }
                                });


                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "READ_EXTERNAL_PERMISSION Denied", Toast.LENGTH_SHORT)
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

    @Override
    public void onDestroy() {
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(null);
        }
        super.onDestroy();
    }

}