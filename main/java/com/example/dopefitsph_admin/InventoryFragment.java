package com.example.dopefitsph_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class InventoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

        // Find icons by their IDs
        ImageView outOfStockIcon = view.findViewById(R.id.out_of_stock_icon);
        ImageView lowStockIcon = view.findViewById(R.id.low_stock_icon);
        ImageView highStockIcon = view.findViewById(R.id.high_stock_icon);

        outOfStockIcon.setOnClickListener(v -> showBottomSheetDialog("Out of Stock"));
        lowStockIcon.setOnClickListener(v -> showBottomSheetDialog("Low Stock"));
        highStockIcon.setOnClickListener(v -> showBottomSheetDialog("High Stock"));

        return view;
    }

    private void showBottomSheetDialog(String status) {
        // Create and show the BottomSheetDialogFragment with a status argument
        StockStatusBottomSheetFragment bottomSheet = StockStatusBottomSheetFragment.newInstance(status);
        bottomSheet.show(getChildFragmentManager(), bottomSheet.getTag());
    }

}
