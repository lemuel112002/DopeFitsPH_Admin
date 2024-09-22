package com.example.dopefitsph_admin;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new com.google.android.material.bottomsheet.BottomSheetDialog(requireContext(), getTheme());
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.bottom_sheet_dialog, null);
        dialog.setContentView(contentView);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString("TITLE");
            TextView titleTextView = contentView.findViewById(R.id.text_title);
            titleTextView.setText(title);
        }
    }
}
