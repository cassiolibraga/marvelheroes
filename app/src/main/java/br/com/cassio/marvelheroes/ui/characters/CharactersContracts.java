package br.com.cassio.marvelheroes.ui.characters;

import java.util.List;

import br.com.cassio.marvelheroes.model.Character;

/**
 * Created by cassiolibraga on 3/7/18.
 */

public interface CharactersContracts {
     interface View {
        void hideProgressBar();
        void renderCharacters(List<Character> characters);
        void showError(String message);
        void showProgressBar();
    }

     interface Presenter {
        void onRetrieveSuccess(List<Character> characters);

        void onRetrieveError();
    }
}
