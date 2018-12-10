package i.thesohankathait.atgfirstassignment.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import i.thesohankathait.atgfirstassignment.Model.MyModel;
import i.thesohankathait.atgfirstassignment.Model.Photo;
import i.thesohankathait.atgfirstassignment.R;
import i.thesohankathait.atgfirstassignment.ViewHolders.RetroPhotosViewHolder;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RetroPhotosViewHolder> {
    List<Photo> retroPhotosList;
    Context context;
    public MyRecyclerViewAdapter() {
    }

    public MyRecyclerViewAdapter(List<Photo> retroPhotosList, Context context) {
        this.retroPhotosList = retroPhotosList;
        this.context = context;
    }

    @NonNull
    @Override
    public RetroPhotosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.recycler_view_row,viewGroup,false);
        return new RetroPhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RetroPhotosViewHolder retroPhotosViewHolder, int i) {

        retroPhotosViewHolder.idTextView.setText(retroPhotosList.get(i).getId());
        Picasso.get().load(retroPhotosList.get(i).getUrlS()).into(retroPhotosViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return retroPhotosList.size();
    }
}
