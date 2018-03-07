package br.com.cassio.marvelheroes.ui.characters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import br.com.cassio.marvelheroes.R;
import br.com.cassio.marvelheroes.model.Character;

/**
 * Created by Cassio on 06/03/2018.
 */

class CharactersAdapter extends RecyclerView.Adapter<CharactersViewHolder> {

    public interface ItemClickListener {
        void onItemClicked(Character comic);
    }

    private List<Character> mCharacters = new ArrayList<Character>();
    private final ItemClickListener itemClickListener;
    private final Context mContext;

    public void setCharacters(List<Character> characters) {
        this.mCharacters.addAll(characters);
        notifyDataSetChanged();
    }

    CharactersAdapter(Context context, ItemClickListener itemClickListener) {
        this.mCharacters = new ArrayList<>();
        this.mContext = context;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public CharactersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_list_content, parent, false);
        return new CharactersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CharactersViewHolder holder, int position) {
        final Character character = mCharacters.get(position);

        Glide.with(mContext)
                .load(character.getThumbnail().getImagePath())
                .fitCenter()
                .into(holder.mImageView);

        holder.mContentView.setText(character.getName());

        holder.itemView.setTag(character);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClicked(character);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }

}