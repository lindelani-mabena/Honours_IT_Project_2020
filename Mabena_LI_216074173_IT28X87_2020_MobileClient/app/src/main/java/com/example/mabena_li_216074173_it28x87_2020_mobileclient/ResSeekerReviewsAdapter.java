package com.example.mabena_li_216074173_it28x87_2020_mobileclient;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResSeekerReviewsAdapter extends ArrayAdapter<Review> {

    Context context;
    List<Review> ReviewsList;
    int ID;
    String Description;
    int Rating;
    public ResSeekerReviewsAdapter(Context context, List<Review> ReviewsList, int resource) {
        super(context, resource, ReviewsList);
        this.context = context;
        this.ReviewsList = ReviewsList;
        this.resource = resource;
    }

    int resource;


    public View getView(final int position, @Nullable View ConvertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        final View view = layoutInflater.inflate(resource, null, false);

        final TextView txtViewDesc = view.findViewById(R.id.txtReviewDescription);
        final TextView txtReviewRating = view.findViewById(R.id.txtReviewRating);

        Button btnEdit = view.findViewById(R.id.btnEdit);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnDelete = view.findViewById(R.id.btnDelete);


        Review Review = ReviewsList.get(position);

        txtViewDesc.setText(Review.getReview_description());
        ID = Review.getReview_id();
        Description = Review.getReview_description();
        Rating = Review.getReview_rating();
        txtReviewRating.setText(String.valueOf(Review.getReview_rating()));



        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getContext(),EditReview.class);
                Review Review = ReviewsList.get(position);
                newIntent.putExtra("review_id", Review.review_id);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                v.getContext().startActivity(newIntent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Review Review = ReviewsList.get(position);
                NetworkHandler networkHandler = NetworkClient.getNetworkClient().create(NetworkHandler.class);

                networkHandler.DeleteReview(Review.getReview_id()).enqueue(new Callback<com.example.mabena_li_216074173_it28x87_2020_mobileclient.Review>() {
                    @Override
                    public void onResponse(Call<com.example.mabena_li_216074173_it28x87_2020_mobileclient.Review> call, Response<com.example.mabena_li_216074173_it28x87_2020_mobileclient.Review> response) {
                        if(response.code()==200)
                        {
                            Toast.makeText(getContext(),"Review Deleted ", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<com.example.mabena_li_216074173_it28x87_2020_mobileclient.Review> call, Throwable t) {
                    }
                });
            }
        });
        return view;
    }
}
