package com.example.dopefitsph_admin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Set up CardViews for transactions
        CardView transactionItemOne = view.findViewById(R.id.transaction_item_card_view);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView transactionItemTwo = view.findViewById(R.id.transaction_item_card_view_two);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView transactionItemThree = view.findViewById(R.id.transaction_item_card_view_three);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView transactionItemFour = view.findViewById(R.id.transaction_item_card_view_four);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView transactionItemFive = view.findViewById(R.id.transaction_item_card_view_five);

        transactionItemOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog("Payment from John Doe", "₱1000.00", "April 25, 2023", "10:00 AM");
            }
        });

        transactionItemTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog("Payment from Jane Smith", "₱500.00", "April 24, 2023", "09:00 AM");
            }
        });

        transactionItemThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog("Payment from John Lawrence", "₱600.00", "February 18, 2023", "03:00 PM");
            }
        });

        transactionItemFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog("Payment from Eliza Doe", "₱300.00", "June 22, 2023", "09:00 AM");
            }
        });

        transactionItemFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog("Payment from John Smith", "₱200.00", "May 16, 2023", "07:00 PM");
            }
        });

        // Existing code for other CardViews
        CardView inStocksBox = view.findViewById(R.id.in_stocks_box);
        CardView ordersBox = view.findViewById(R.id.orders_box);

        inStocksBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog("In Stocks", "", "", "");
            }
        });

        ordersBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog("Orders", "", "", "");
            }
        });

        return view;
    }

    private void showBottomSheetDialog(String title, String amount, String date, String time) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        bundle.putString("AMOUNT", amount);
        bundle.putString("DATE", date);
        bundle.putString("TIME", time);
        bottomSheetDialog.setArguments(bundle);
        bottomSheetDialog.show(getFragmentManager(), "BottomSheetDialog");
    }
}
