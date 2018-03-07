package br.com.cassio.marvelheroes.ui.characters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.cassio.marvelheroes.R;

/**
 * Created by Cassio on 06/03/2018.
 */

class CharactersViewHolder extends RecyclerView.ViewHolder {
    final TextView mContentView;
    final ImageView mImageView;

    CharactersViewHolder(View view) {
        super(view);
        mImageView = (ImageView) view.findViewById(R.id.character_image);
        mContentView = (TextView) view.findViewById(R.id.character_name);
    }
}
