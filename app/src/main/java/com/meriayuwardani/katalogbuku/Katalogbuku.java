package com.meriayuwardani.katalogbuku;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;


public class Katalogbuku{
    public static final String FIKSI="FIKSI";
    public static final String NONFIKSI="NONFIKSI";
    private String id;
    private Date tanggal;
    private String jenis;
    private String judul;
    private String pengarang;
    private String thnterbit;
    private String kodeisbn;

    public Katalogbuku() {
        this.id= UUID.randomUUID().toString();
        this.tanggal = new Date() ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getThnterbit() {
        return thnterbit;
    }

    public void setThnterbit(String thnterbit) {
        this.thnterbit = thnterbit;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getKodeisbn() {
        return kodeisbn;
    }

    public void setKodeisbn(String kodeisbn) {
        this.kodeisbn = kodeisbn;
    }


    public static Katalogbuku fromJSONObject(JSONObject obj) {
       Katalogbuku tr = new Katalogbuku();
        try {
            tr.setId(obj.getString("id"));
            tr.setTanggal(new Date(obj.getLong("tanggal")));
            tr.setJenis(obj.getString("jenis"));
            tr.setJudul(obj.getString("judul"));
            tr.setPengarang(obj.getString("pengarang"));
            tr.setThnterbit(obj.getString("thnterbit"));
            tr.setKodeisbn(obj.getString("kodeisbn"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tr;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("id",this.id);
            jsonObj.put("tanggal",this.tanggal.getTime());
            jsonObj.put("jenis",this.jenis);
            jsonObj.put("judul",this.judul);
            jsonObj.put("pengarang",this.pengarang);
            jsonObj.put("thnterbit",this.thnterbit);
            jsonObj.put("kodeisbn",this.kodeisbn);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}
