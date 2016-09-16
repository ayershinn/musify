package com.lynqq.musify.beans;

/**
 * Created by Keuahn on 8/3/2016.
 */
public class Song {

    private Long songId;
    private String mediaPath;
    private String songTitle;
    private String songArtist;
    private String songDuration;
    private Long folderId;
    private String folderName;

    public Long getSongId() {
        return songId;
    }
    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }
    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongPath() {
        return mediaPath;
    }
    public void setSongPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public String getSongArtist() {
        return songArtist;
    }
    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongDuration() {
        return songDuration;
    }
    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public Long getFolderId() {
        return folderId;
    }
    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public String getFolderName() {
        return folderName;
    }
    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
