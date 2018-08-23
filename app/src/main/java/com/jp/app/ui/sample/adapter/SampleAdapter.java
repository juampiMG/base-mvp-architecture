package com.jp.app.ui.sample.adapter;

import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jp.app.R;
import com.jp.app.model.SampleView;
import com.jp.app.utils.ImageHelper;
import com.jp.app.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SampleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SampleView> mList;

    private SampleAdapterCallBack mListener;

    public interface SampleAdapterCallBack {
        void sampleClicked(int adapterPosition);
    }

    public SampleAdapter(List<SampleView> samples, SampleAdapterCallBack callBack) {
        mList = samples;
        mListener = callBack;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_component, parent, false);
        return new ItemViewHolder(v);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SampleView sample = mList.get(position);
        drawSample((ItemViewHolder) holder, sample);
    }

    private void drawSample(ItemViewHolder holder, SampleView sample) {
        if (!StringUtils.isBlank(String.valueOf(sample.getTitle()))) {
            holder.title.setText(String.valueOf(sample.getTitle()));
        }

        if (!StringUtils.isBlank(sample.getUrlLogo())) {
            holder.loadImage(sample.getUrlLogo());
        }
    }


    public void addSamples(List<SampleView> newsamples) {
        mList.addAll(newsamples);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_view)
        public CardView layout;

        @BindView(R.id.sample_title)
        public AppCompatTextView title;

        @BindView(R.id.sample_image)
        public AppCompatImageView imageView;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void loadImage(String imagePath) {
            Uri uri = Uri.parse(imagePath);
            ImageHelper.loadImage(layout.getContext(), uri, imageView, null);
        }

        @OnClick(R.id.card_view)
        void onClick() {
            if (mListener != null) {
                mListener.sampleClicked(getAdapterPosition());
            }
        }
    }

}
