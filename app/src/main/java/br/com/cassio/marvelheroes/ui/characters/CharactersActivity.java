package br.com.cassio.marvelheroes.ui.characters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import br.com.cassio.marvelheroes.R;
import br.com.cassio.marvelheroes.model.Character;
import br.com.cassio.marvelheroes.ui.character.CharacterDetailActivity;
import br.com.cassio.marvelheroes.ui.character.CharacterDetailFragment;
import br.com.cassio.marvelheroes.utils.EndlessRecyclerViewScrollListener;

public class CharactersActivity extends AppCompatActivity implements CharactersContracts.View, CharactersAdapter.ItemClickListener  {

    private boolean mTwoPane;
    private CharactersAdapter adapter;
    private CharactersPresenter presenter;
    private ProgressBar progressBar;
    private EndlessRecyclerViewScrollListener scrollListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new CharactersPresenter(this);

        configureLayout();

        if (findViewById(R.id.character_detail_container) != null) {
            mTwoPane = true;
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.character_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.fetchCharacters(page);
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        adapter = new CharactersAdapter(this,this);
        recyclerView.setAdapter(adapter);
        presenter.fetchCharacters(0);
    }

    private void configureLayout() {
        setContentView(R.layout.activity_character_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
    }

    @Override
    public void renderCharacters(List<Character> characters) {
        adapter.setCharacters(characters);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(Character comic) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putSerializable(CharacterDetailFragment.CHARACTER_ITEM, comic);
            CharacterDetailFragment fragment = new CharacterDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.character_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, CharacterDetailActivity.class);
            intent.putExtra(CharacterDetailFragment.CHARACTER_ITEM, comic);
            this.startActivity(intent);
        }
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
