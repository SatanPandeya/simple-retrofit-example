package com.dhabs.www.fakeretrofitapi.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhabs.www.fakeretrofitapi.R;
import com.dhabs.www.fakeretrofitapi.model.RetrofitPhotoModel;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolderClass> {
    private Context context;
    private List<RetrofitPhotoModel> retrofitPhotoModelList;

    PhotoAdapter(Context context, List<RetrofitPhotoModel> retrofitPhotoModelList) {
        this.context = context;
        this.retrofitPhotoModelList = retrofitPhotoModelList;
    }

    @NonNull
    @Override
    public MyViewHolderClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_photo, viewGroup, false);
        return new MyViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderClass myViewHolderClass, int i) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(retrofitPhotoModelList.get(i).getThumbnailUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(myViewHolderClass.image);
        myViewHolderClass.photoTitle.setText(retrofitPhotoModelList.get(i).getTitle());

    }

    @Override
    public int getItemCount() {
        return retrofitPhotoModelList.size();
    }

    class MyViewHolderClass extends RecyclerView.ViewHolder {
        private TextView photoTitle;
        private ImageView image;
        private Typeface typeface;

        MyViewHolderClass(@NonNull View itemView) {
            super(itemView);
            photoTitle = itemView.findViewById(R.id.imageTitleId);
            image = itemView.findViewById(R.id.imageViewId);
            typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/UbuntuMono-Regular.ttf");
            photoTitle.setTypeface(typeface);
        }
    }
}
