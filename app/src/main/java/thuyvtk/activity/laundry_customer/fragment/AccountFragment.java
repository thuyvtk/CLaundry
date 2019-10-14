package thuyvtk.activity.laundry_customer.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.activity.EditAccountActivity;
import thuyvtk.activity.laundry_customer.activity.EditProfileActivity;
import thuyvtk.activity.laundry_customer.activity.LoginActivity;
import thuyvtk.activity.laundry_customer.library.SharePreferenceLib;
import thuyvtk.activity.laundry_customer.model.OrderDTO;
import thuyvtk.activity.laundry_customer.view.OrderHistoryView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements OrderHistoryView {
    ImageButton imgEdit;
    CircleImageView image_profile;
    TextView txtLogout;
    public AccountFragment() {
        // Required empty public constructor
    }

    private void defineView(View view) {
        imgEdit = view.findViewById(R.id.imgEdit);
        image_profile = view.findViewById(R.id.image_profile);
        txtLogout = view.findViewById(R.id.txtLogout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        defineView(view);
        openPageEdit();
        logOut();
        return view;
    }

    private void logOut() {
        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePreferenceLib sharePreferenceLib =  new SharePreferenceLib(getContext());
                sharePreferenceLib.logouṭ();
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private void openPageEdit() {
        image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void loadOrderHistory(List<OrderDTO> orderList) {

    }

    @Override
    public void onFail(String msg) {

    }
}
