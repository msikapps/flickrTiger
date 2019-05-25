package tigerspike.com.flickrgallery.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import tigerspike.com.flickrgallery.R;

public class PictureViewHolder extends RecyclerView.ViewHolder {

    ImageView picture;
    TextView publishedText;
    TextView pictureTitle;
    TextView publisherText;

    public PictureViewHolder(@NonNull View itemView) {
        super(itemView);
        picture = itemView.findViewById(R.id.image_preview);
        publishedText = itemView.findViewById(R.id.published_txt);
        publisherText = itemView.findViewById(R.id.publisher_txt);
        pictureTitle = itemView.findViewById(R.id.title_txt);
    }
}
