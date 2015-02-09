package Dao;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Model.Toalett;
import Model.ToalettListe;
import mt.locationmaps.R;


/**
 * Created by Martin on 05.02.2015.
 */
public class DoDao {

    ToalettListe toalettListe;

    public DoDao(Context context){

        try{
            GsonBuilder gsonBuilder = new GsonBuilder().serializeNulls();
            Gson gson = gsonBuilder.create();
            toalettListe = gson.fromJson(new InputStreamReader(context.getResources().openRawResource(R.raw.dokart)), ToalettListe.class);
        }catch(JsonSyntaxException | JsonIOException e){
            e.printStackTrace();
        }
    }

    public List<Toalett> getToalett(){
        List<Toalett> toaletts = new ArrayList<Toalett>();
        for(Toalett toalett : toalettListe.getToalettList()){
            toaletts.add(toalett);
        }
        return toaletts;
    }

}
