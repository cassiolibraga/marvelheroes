package br.com.cassio.marvelheroes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Cassio on 06/03/2018.
 */

public class Thumbnail implements Serializable {

    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("extension")
    @Expose
    private String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getImagePath() {
        return path + "." + extension;
    }

    @Override
    public String toString() {
        return "Thumbnail{" +
                "path='" + path + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}