package br.com.pocomartins.pokemon;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Poço Martins on 4/28/2017.
 */

public class Pokemon implements Serializable{

    private String name;

    private String localizacao;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Pokemon(String name, String localizacao) {
        this.name = name;
        this.localizacao = localizacao;
    }

    public Pokemon(JSONObject jsonObject)  throws Exception{
        this.name = jsonObject.getString("name");
        this.localizacao = jsonObject.getString("resource_uri");
    }
}
