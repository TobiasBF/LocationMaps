package Model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Martin on 05.02.2015.
 */
public class ToalettListe {

    @SerializedName("entries")
    private List<Toalett> toalettList;

    public List<Toalett> getToalettList(){
        return toalettList;
    }

    public void setToalettList(List<Toalett> toalettList) {
        this.toalettList = toalettList;
    }
}
