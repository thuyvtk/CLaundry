package thuyvtk.activity.laundry_customer.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.activity.EditProfileActivity;
import thuyvtk.activity.laundry_customer.activity.LoginActivity;
import thuyvtk.activity.laundry_customer.library.SharePreferenceLib;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment  {
    ImageButton imgEdit;
    CircleImageView image_profile;
    TextView txtLogout;
    TextView txtStoreName;
    TextView btnHistory;
    public AccountFragment() {
        // Required empty public constructor
    }

    private void defineView(View view) {
        imgEdit = view.findViewById(R.id.imgEdit);
        image_profile = view.findViewById(R.id.image_profile);
        txtLogout = view.findViewById(R.id.txtLogout);
        txtStoreName = view.findViewById(R.id.txtStoreName);
        btnHistory = view.findViewById(R.id.btnHistory);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        defineView(view);
        getStoreProfile();
        openPageEdit();
        openPageHistory();
        logOut();
        return view;
    }

    private void getStoreProfile() {
        SharePreferenceLib sharePreferenceLib = new SharePreferenceLib(getContext());
        txtStoreName.setText(sharePreferenceLib.getUser().getCustomerName());
        Picasso.with(getContext()).load(sharePreferenceLib.getUser().getImgUrl()).into(image_profile);
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


    public void openPageHistory() {
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                OrderFragment orderFragment = new OrderFragment();
                fragmentTransaction.replace(R.id.main_fragment, orderFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    
}