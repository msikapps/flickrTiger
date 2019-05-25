package tigerspike.com.flickrgallery.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import tigerspike.com.flickrgallery.R;
import tigerspike.com.flickrgallery.main.MainActivity;
import tigerspike.com.flickrgallery.models.Entry;
import tigerspike.com.flickrgallery.models.Link;
import tigerspike.com.flickrgallery.previewer.PreviewerActivity;
import tigerspike.com.flickrgallery.utils.Constant;

public class PictureAdapter extends RecyclerView.Adapter<PictureViewHolder> {

    private List<Entry> arrayList;
    private Context context;
    private MainActivity activity;


    public List<Entry> getArrayList() {
        return arrayList;
    }


    public PictureAdapter(Context context, MainActivity activity,
                          List<Entry> arrayList) {
        this.context = context;
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.picture_item, parent, false);
        return new PictureViewHolder(mainGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {
        Entry picture = arrayList.get(position);
        Link pictureLink = new Link();
        Intent previewIntent = new Intent(context, PreviewerActivity.class);
        if (picture.getLinks() != null) {
            for (Link link :
                    picture.getLinks()) {
                if (link.getRel().equals("enclosure"))
                    pictureLink = link;
            }
        }
        if (pictureLink != null) {
            Glide.with(context).load(pictureLink.getHref()).into(holder.picture);
            previewIntent.putExtra(Constant.PICTURE_URL, pictureLink.getHref());
        }
        holder.pictureTitle.setText(picture.getTitle());
        if (!picture.getPublishedOn().isEmpty()) {
            try {
                Date publishedOn = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.ENGLISH).parse(picture.getPublishedOn());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy '-' hh:mm");
                holder.publishedText.setText(simpleDateFormat.format(publishedOn));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        holder.publisherText.setText(picture.getAuthor().getName());
        holder.itemView.setOnClickListener(v -> {
            if (activity != null)
                previewIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(previewIntent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
