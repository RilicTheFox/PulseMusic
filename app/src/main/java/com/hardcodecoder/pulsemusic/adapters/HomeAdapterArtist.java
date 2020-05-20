package com.hardcodecoder.pulsemusic.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.hardcodecoder.pulsemusic.R;
import com.hardcodecoder.pulsemusic.interfaces.SimpleTransitionClickListener;
import com.hardcodecoder.pulsemusic.model.ArtistModel;

import java.util.List;

public class HomeAdapterArtist extends RecyclerView.Adapter<HomeAdapterArtist.AdapterSVH> {

    private List<ArtistModel> mList;
    private LayoutInflater mInflater;
    private SimpleTransitionClickListener mListener;

    public HomeAdapterArtist(List<ArtistModel> list, LayoutInflater inflater, SimpleTransitionClickListener listener) {
        this.mList = list;
        this.mInflater = inflater;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public AdapterSVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterSVH(mInflater.inflate(R.layout.rv_home_item_artist, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSVH holder, int position) {
        holder.updateData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        if (null != mList)
            return mList.size();
        return 0;
    }

    static class AdapterSVH extends RecyclerView.ViewHolder {

        private ImageView artistArt;
        private MaterialTextView artistName, trackCount;

        AdapterSVH(@NonNull View itemView, SimpleTransitionClickListener listener) {
            super(itemView);
            artistArt = itemView.findViewById(R.id.home_rv_artist_list_item_art);
            artistName = itemView.findViewById(R.id.home_rv_list_item_title);
            trackCount = itemView.findViewById(R.id.home_rv_list_item_text);
            itemView.setOnClickListener(v -> listener.onItemClick(artistArt, getAdapterPosition()));
        }

        void updateData(ArtistModel am) {
            artistArt.setTransitionName("shared_transition_artist_iv_" + getAdapterPosition());
            artistName.setText(am.getArtistName());
            trackCount.setText(String.format("%d " + itemView.getResources().getString(R.string.tracks_num ), am.getNumOfTracks()));
        }
    }
}
