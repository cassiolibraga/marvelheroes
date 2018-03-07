package br.com.cassio.marvelheroes.ui.character;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.cassio.marvelheroes.R;
import br.com.cassio.marvelheroes.model.Character;

public class CharacterDetailFragment extends Fragment {

    public static final String CHARACTER_ITEM = "character_item";
    private Character mCharacter;

    public CharacterDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(CHARACTER_ITEM)) {
            mCharacter = (Character) getArguments().getSerializable(CHARACTER_ITEM);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mCharacter.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.character_detail, container, false);

        if (mCharacter != null) {
            String description = mCharacter.getDescription();
            if (description.isEmpty()) {
                description = getString(R.string.no_description);
            }
            ((TextView) rootView.findViewById(R.id.character_detail)).setText(description);
            final ImageView image = ((ImageView) getActivity().findViewById(R.id.profile_image));
            Glide.with(getContext())
                .load(mCharacter.getThumbnail().getImagePath())
                .into(image);
        }

        return rootView;
    }
}
