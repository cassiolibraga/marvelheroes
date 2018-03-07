package br.com.cassio.marvelheroes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cassio on 06/03/2018.
 */

public class CharacterDataWrapper {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("attributionText")
    @Expose
    private String attributionText;
    @SerializedName("data")
    @Expose
    private CharacterDataContainer data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public CharacterDataContainer getData() {
        return data;
    }

    public void setData(CharacterDataContainer data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CharacterDataWrapper{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", attributionText='" + attributionText + '\'' +
                ", data=" + data +
                '}';
    }
}