package br.com.pocomartins.pokemon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static br.com.pocomartins.pokemon.R.id.item_logo;
import static br.com.pocomartins.pokemon.R.layout.pokemon;

/**
 * Created by Poço Martins on 4/28/2017.
 */

public class PokemonAdapter extends ArrayAdapter<Pokemon> {

    public PokemonAdapter(Context context, ArrayList<Pokemon> listapokemon) {
        super(context, 0, listapokemon);

    }

    public static class PokemonHolder {

        TextView  nome;
        ImageView imagemPoke;

        public PokemonHolder (View view) {

            nome = (TextView) view.findViewById(R.id.item_name);
            imagemPoke = (ImageView) view.findViewById(item_logo);

        }


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(pokemon, parent, false);
        }

        Pokemon pokemon = getItem(position);

        PokemonHolder pokemonHolder;

        if(itemView.getTag() == null) {
            pokemonHolder = new PokemonHolder(itemView);
            itemView.setTag(pokemonHolder);
        } else {
            pokemonHolder = (PokemonHolder) itemView.getTag();
        }

        pokemonHolder.nome.setText(pokemon.getName().toUpperCase());
        new DownloadImageTask(pokemonHolder.imagemPoke).execute(pegaCodigo(pokemon.getLocalizacao()));

        return itemView;
    }

    public String pegaCodigo(String end) {
        return end.replace("api/v1/pokemon", "").replace("/", "");
    }


}
