package com.example.mabena_li_216074173_it28x87_2020_mobileclient;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AccommodationAdapter extends ArrayAdapter<Accommodation> {
    Context context;
    List<Accommodation> AccomodationList;
    String Id;
    String Address;
    String phone_number;

    public AccommodationAdapter(Context context, List<Accommodation> AccomodationList, int resource, String Id) {
        super(context, resource,AccomodationList);
        this.context = context;
        this.AccomodationList = AccomodationList;
        this.resource = resource;
        this.Id =Id;
    }

    int resource;
    public View getView(final int position, @Nullable View ConvertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final Intent newIntent;
        View view = layoutInflater.inflate(resource, null, false);
        final TextView txtViewName = view.findViewById(R.id.txtAccommodationName);
        Button btnChoose = view.findViewById(R.id.buttonSelect);
        Accommodation accommodation = AccomodationList.get(position);
        txtViewName.setText("Accommodation Name:"+ accommodation.getAccommodation_name());
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(v.getContext(), ChosenAccommodation.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Accommodation accommodation = AccomodationList.get(position);
                newIntent.putExtra("accommodation_id",AccomodationList.get(position).getAccommodation_id());
                newIntent.putExtra("seeker_id",Id);
                newIntent.putExtra("address_id", AccomodationList.get(position).getAccommodation_address_id());
                newIntent.putExtra("accommodation_name", AccomodationList.get(position).getAccommodation_name());
                v.getContext().startActivity(newIntent);
            }
        });
        return view;
    }
}

