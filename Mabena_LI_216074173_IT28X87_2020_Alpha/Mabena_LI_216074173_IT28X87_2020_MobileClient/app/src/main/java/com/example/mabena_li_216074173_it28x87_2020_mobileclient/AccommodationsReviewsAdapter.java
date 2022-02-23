package com.example.mabena_li_216074173_it28x87_2020_mobileclient;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
public class AccommodationsReviewsAdapter extends ArrayAdapter<Review> {
    int intSelectedAccommodationID;
    Context context;
    List<Review> ReviewsList;
    public AccommodationsReviewsAdapter(Context context, List<Review> ReviewsList, int resource) {
        super(context, resource, ReviewsList);
        this.context = context;
        this.ReviewsList = ReviewsList;
        this.resource = resource;
    }
    int resource;
    public View getView(final int position, @Nullable View ConvertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        final Intent newIntent;

        View view = layoutInflater.inflate(resource, null, false);

        final TextView txtViewDesc = view.findViewById(R.id.txtReviewDescription);
        final TextView txtReviewRating = view.findViewById(R.id.txtReviewRating);

        Button btnBack = view.findViewById(R.id.btnBack);
        Review Review = ReviewsList.get(position);
        txtViewDesc.setText("Review Description: "+Review.getReview_description());
        txtReviewRating.setText("Rating is: "+ String.valueOf(Review.getReview_rating()+"/5"));

  btnBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
  });
        return view;
    }
}