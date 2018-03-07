package br.com.cassio.marvelheroes.ui.characters;

import android.content.res.Resources;

import java.util.List;

import br.com.cassio.marvelheroes.network.CharactersService;
import br.com.cassio.marvelheroes.R;
import br.com.cassio.marvelheroes.model.Character;

/**
 * Created by Cassio on 06/03/2018.
 */

public class CharactersPresenter implements CharactersContracts.Presenter {
    private final CharactersContracts.View view;
    private final CharactersService service;

    public CharactersPresenter(CharactersContracts.View view) {
        this.view = view;
        this.service = new CharactersService(this);
    }

    public void fetchCharacters(int offset) {
        view.showProgressBar();
        getCharacters(offset);
    }

    public void getCharacters(int offset) {
        service.getCharacters(offset);
    }

    @Override
    public void onRetrieveSuccess(List<Character> characters) {
        view.hideProgressBar();
        view.renderCharacters(characters);
    }

    @Override
    public void onRetrieveError() {
        view.showError(Resources.getSystem().getString(R.string.message_error));
        view.hideProgressBar();
    }
}


