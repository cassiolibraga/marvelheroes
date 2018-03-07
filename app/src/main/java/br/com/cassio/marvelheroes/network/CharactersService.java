package br.com.cassio.marvelheroes.network;

import java.util.Date;

import br.com.cassio.marvelheroes.model.CharacterDataWrapper;
import br.com.cassio.marvelheroes.ui.characters.CharactersContracts;
import br.com.cassio.marvelheroes.utils.ApiParams;
import br.com.cassio.marvelheroes.utils.Utils;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Cassio on 07/03/2018.
 */

public class CharactersService {

    private final CharactersContracts.Presenter presenter;

    public CharactersService(CharactersContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    public void getCharacters(int page) {
        String timeStamp = new Date().getTime()+"";
        String hash = Utils.produceMD5(timeStamp+ApiParams.PRIVATE_KEY+ApiParams.PUBLIC_KEY);
        int offset = ApiParams.LIMIT * page;

        getAPI()
            .getCharacters(timeStamp,ApiParams.PUBLIC_KEY,hash,Integer.toString(ApiParams.LIMIT),Integer.toString(offset))
            .enqueue(new Callback<CharacterDataWrapper>() {
                @Override
                public void onResponse(Call<CharacterDataWrapper> call, Response<CharacterDataWrapper> response) {
                    if (response.isSuccessful()) {
                        CharacterDataWrapper result = response.body();
                        if(result != null) {
                            presenter.onRetrieveSuccess(result.getData().getCharacters());
                        }
                        else {
                            presenter.onRetrieveError();
                        }
                    }
                    else {
                        presenter.onRetrieveError();
                    }
                }

                @Override
                public void onFailure(Call<CharacterDataWrapper> call, Throwable t) {
                    presenter.onRetrieveError();
                }
            });
    }

    public interface MarvelAPI{
        @GET("/v1/public/characters")
        Call<CharacterDataWrapper> getCharacters(
                @Query("ts") String timestamp,
                @Query("apikey") String apiKey,
                @Query("hash") String hash,
                @Query("limit") String limit,
                @Query("offset") String offset
        );
    }

    public MarvelAPI getAPI(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiParams.ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(MarvelAPI.class);
    }
}
