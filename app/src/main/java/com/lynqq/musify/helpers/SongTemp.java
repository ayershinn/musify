package com.lynqq.musify.helpers;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by swappnil on 19-06-2015.
 */
public class SongTemp  implements Parcelable {
    private long id;
    private String title;
    private String artist, data, albumkey, duration;
    private long alid;

    public SongTemp(long songID, String songTitle, String songArtist, long albumID, String thisdata, String AlbumKey, String songDuration) {
        id=songID;
        title=songTitle;
        artist=songArtist;
        alid=albumID;
        data=thisdata;
        albumkey=AlbumKey;
        duration=songDuration;

    }
    public SongTemp(){

    }
    public long getID(){return id;}
    public String getTitle(){return title;}
    public String getArtist(){return artist;}
    public long getAlbumID(){return alid;}
    public String getPath(){return data;}
    public String getAlbumKey(){return albumkey;}
    public String getSongDuration(){return duration;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(artist);
        dest.writeLong(alid);
        dest.writeLong(id);
        dest.writeString(data);
        dest.writeString(albumkey);
        dest.writeString(duration);
    }
    public static final Parcelable.Creator<SongTemp> CREATOR = new Parcelable.Creator<SongTemp>() {
        public SongTemp createFromParcel(Parcel in) {
            SongTemp song = new SongTemp();
            song.title = in.readString();
            song.artist = in.readString();
            song.alid = in.readLong();
            song.id = in.readLong();
            song.data= in.readString();
            song.albumkey=in.readString();
            song.duration=in.readString();
            return song;
        }

        public SongTemp[] newArray(int size) {
            return new SongTemp[size];
        }
    };
}