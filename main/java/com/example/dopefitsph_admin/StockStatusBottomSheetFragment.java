package com.example.dopefitsph_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class StockStatusBottomSheetFragment extends BottomSheetDialogFragment {

    private static final String ARG_STATUS = "status";

    public static StockStatusBottomSheetFragment newInstance(String status) {
        StockStatusBottomSheetFragment fragment = new StockStatusBottomSheetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_content, container, false);

        // Get the status argument
        String status = getArguments().getString(ARG_STATUS, "Unknown Status");

        // Find TextView and set its text based on the status
        TextView statusTextView = view.findViewById(R.id.status_text_view); // Make sure this ID exists in bottom_sheet_content.xml
        statusTextView.setText("Details about the " + status);

        return view;
    }
}
