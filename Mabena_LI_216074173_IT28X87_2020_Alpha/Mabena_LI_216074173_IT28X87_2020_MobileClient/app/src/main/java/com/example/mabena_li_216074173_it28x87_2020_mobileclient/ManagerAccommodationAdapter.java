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

public class ManagerAccommodationAdapter extends ArrayAdapter<Accommodation> {
    Context context;
    List<Accommodation> AccomodationList;
    String Id;

    public ManagerAccommodationAdapter(Context context, List<Accommodation> AccomodationList, int resource) {
        super(context, resource,AccomodationList);
        this.context = context;
        this.AccomodationList = AccomodationList;
        this.resource = resource;
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
                Intent newIntent = new Intent(v.getContext(), ManagerChosenAccommodation.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Accommodation accommodation = AccomodationList.get(position);
                newIntent.putExtra("accommodation_id",AccomodationList.get(position).getAccommodation_id());
                newIntent.putExtra("address_id", AccomodationList.get(position).getAccommodation_address_id());
                newIntent.putExtra("accommodation_name", AccomodationList.get(position).getAccommodation_name());
                newIntent.putExtra("accommodation_gym", AccomodationList.get(position).getAccommodation_gym());
                newIntent.putExtra("accommodation_security", AccomodationList.get(position).getAccommodation_security());
                newIntent.putExtra("accommodation_washingmachines", AccomodationList.get(position).getAccommodation_washingmachines());
                newIntent.putExtra("accommodation_wifi", AccomodationList.get(position).getAccommodation_wifi());
                newIntent.putExtra("accommodation_type_id", AccomodationList.get(position).getAccommodation_type_id());
                v.getContext().startActivity(newIntent);


            }
        });
        return view;
    }
}
