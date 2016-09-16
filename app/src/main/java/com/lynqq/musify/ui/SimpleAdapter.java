package com.lynqq.musify.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lynqq.musify.R;
import com.lynqq.musify.beans.Folder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {

    private final Context mContext;
    private ArrayList<Folder> mFolders;
    //to avoid duplicate names
    private List<String> folderNames = new ArrayList<>();
    private Set<String> hs = new HashSet<>();

    public void add(Folder folder,int position) {
        position = position == -1 ? getItemCount()  : position;
        mFolders.add(position, folder);
        notifyItemInserted(position);
    }

    public void remove(int position){
        if (position < getItemCount()  ) {
            mFolders.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Folder getFolderId(int position){
        return mFolders.get(position);
    }


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final FrameLayout listRowItem;
        public final CheckBox checkBox;

        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.item_title);
            listRowItem = (FrameLayout) view.findViewById(R.id.list_row_item);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        }
    }

    public SimpleAdapter(Context context, ArrayList<Folder> folder) {
        mContext = context;

        if (folder != null) {
            mFolders = folder;
        }else{
            mFolders = new ArrayList<Folder>();
        }

       /* for (Folder folder : mFolders) {
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
        }*/

    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.folder_list_row, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, final int position) {


        Folder currentFolder = new Folder();
        currentFolder = mFolders.get(position);
        holder.title.setText(currentFolder.getFolderName());

        Log.v("Folder pos", String.valueOf(position));
        Log.v("Folder Name Adapter", currentFolder.getFolderName());
    //    holder.title.setText(mFolders.get(position));
        holder.listRowItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, mFolders.get(position).toString()+": Position ="+position,Toast.LENGTH_SHORT).show();
            }
        });

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //checked
                    holder.checkBox.setChecked(false);
                } else {
                    //not checked
                    holder.checkBox.setChecked(true);
                }
            }
        });

       /* holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // holder.checkBox.setChecked(!holder.checkBox.isChecked());
                holder.checkBox.setChecked(true);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mFolders.size();
    }
}