package br.com.pocomartins.pokemon;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Poço Martins on 4/28/2017.
 */

public class JsonUtils {

    public static List<Pokemon> fromJsonToList(String json) {
        List<Pokemon> list = new ArrayList<>();
        try {
            JSONObject jsonBase = new JSONObject(json);
            JSONArray results = jsonBase.getJSONArray("pokemon");

            for(int i = 0; i < results.length();i++) {
                JSONObject pokemonObject = results.getJSONObject(i);
                Pokemon pokemon = new Pokemon(pokemonObject);
                list.add(pokemon);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static void compPokemon (Pokemon pokemon, String json){

        try {
            JSONObject jsonBase = new JSONObject(json);
            String imagemPath = jsonBase.getJSONObject("sprites").getString("front_default");
            pokemon.setLocalizacao(imagemPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
