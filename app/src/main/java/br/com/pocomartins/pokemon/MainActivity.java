package br.com.pocomartins.pokemon;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    PokemonAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_pokemon);

        final ArrayList<Pokemon> lista = new ArrayList<>();

        adapter = new PokemonAdapter(getBaseContext(), lista);
        listView.setAdapter(adapter);

        new PokemonSync().execute();

    }


    public class PokemonSync extends AsyncTask<Object, Void, List<Pokemon>> {

        @Override
        protected List<Pokemon> doInBackground(Object... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("http://pokeapi.co/api/v1/pokedex/1/");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String linha;
                StringBuffer buffer = new StringBuffer();
                while ((linha = reader.readLine()) != null) {
                    buffer.append(linha);
                    buffer.append("\n");
                }

                List<Pokemon> pokemon = JsonUtils.fromJsonToList(buffer.toString());
/*
                int i = 0;
                for (Pokemon pokemon1 : pokemon)
                {
                    locaImagem = pegaCodigo(pokemon1.getLocalizacao());
                    url = new URL("http://pokeapi.co/" + locaImagem);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();
                    inputStream = urlConnection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    buffer = new StringBuffer();
                    while ((linha = reader.readLine()) != null) {
                        buffer.append(linha);
                        buffer.append("\n");
                    }
                    JsonUtils.compPokemon(pokemon1, buffer.toString());
                    i++;
                    if (i > 778) {
                         System.out.println("Loop :" + i++);
                    }

                }
*/
                return pokemon;

            } catch (Exception e) {
                e.printStackTrace();
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Pokemon> dados) {
            if (adapter != null) {
                adapter.clear();
            }
            adapter.addAll(dados);
            adapter.notifyDataSetChanged();

        }
    }
}
