package com.example.android.roomwordssample;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WordListAdapter extends PagedListAdapter<Word,WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;

    WordListAdapter(Context context){
        super(DIFF_CALLBACK);
        mInflater = LayoutInflater.from(context);
    }

    //Or: constructor without Context as parameter as follows as I used in Ijome PagedListAdapter:
    /*
    WordListAdapter(){
        super(DIFF_CALLBACK);
    }
    */

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        //Or in case of a constructor without Context as parameter:
        //View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Word item = getItem(position);
        holder.wordItemView.setText(item.getWord());
        //Or:
        //holder.bindTo(item);//Not used in this case but a common way to bind values to the views
    }

    class WordViewHolder extends RecyclerView.ViewHolder{
        private final TextView wordItemView;
        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }

        //Not used in this case but a common way to bind values to the views
        void bindTo(Word word){
            wordItemView.setText(word.getWord());
        }

    }

    private static final DiffUtil.ItemCallback<Word> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Word>() {
                @Override
                public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
                    return oldItem.getWord().equals(newItem.getWord());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Word oldItem,
                                                  @NonNull Word newItem) {
                    return oldItem == newItem;
                }
            };

}