package Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Martin on 05.02.2015.
 */
public class Toalett {

    @SerializedName("id")
    String id;
    @SerializedName("plassering")
    String plassering;
    @SerializedName("adresse")
    String adresse;
    @SerializedName("pris")
    String pris;
    @SerializedName("tid_hverdag")
    String tid_hverdag;
    @SerializedName("tid_lordag")
    String tid_lordag;
    @SerializedName("tid_sondag")
    String tid_sondag;
    @SerializedName("rullestol")
    String rullestol;
    @SerializedName("stellerom")
    String stellerom;
    @SerializedName("Pissior_only")
    String urinal;
    @SerializedName("latitude")
    String latitude;
    @SerializedName("longitude")
    String longitude;

    public void setId(String id) {
        this.id = id;
    }

    public void setPlassering(String plassering) {
        this.plassering = plassering;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setPris(String pris) {
            this.pris = pris;
    }

    public void setTid_hverdag(String tid_hverdag) {
        this.tid_hverdag = tid_hverdag;
    }

    public void setTid_lordag(String tid_lordag) {
        this.tid_lordag = tid_lordag;
    }

    public void setTid_sondag(String tid_sondag) {
        this.tid_sondag = tid_sondag;
    }

    public void setRullestol(String rullestol) {
        this.rullestol = rullestol;
    }

    public void setStellerom(String stellerom) {
        this.stellerom = stellerom;
    }

    public void setUrinal(String urinal) {
        this.urinal = urinal;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getId() {

        return id;
    }

    public String getPlassering() {
        return plassering;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getPris() {
        if(this.pris.equals("NULL")){
            return "Gratis";
        }
        else{
            return pris;
        }
    }

    public String getTid_hverdag() {
        return tid_hverdag;
    }

    public String getTid_lordag() {
        return tid_lordag;
    }

    public String getTid_sondag() {
        return tid_sondag;
    }

    public String getRullestol() {
        return rullestol;
    }

    public String getStellerom() {
        return stellerom;
    }

    public String getUrinal() {
        return urinal;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
