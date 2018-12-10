package i.thesohankathait.atgfirstassignment.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import i.thesohankathait.atgfirstassignment.R;

public class RetroPhotosViewHolder extends RecyclerView.ViewHolder {

   public ImageView imageView;
   public TextView idTextView;
    public RetroPhotosViewHolder(@NonNull View itemView) {
        super(itemView);
        idTextView=itemView.findViewById(R.id.idTextView);
        imageView=itemView.findViewById(R.id.imageView);
    }
}
