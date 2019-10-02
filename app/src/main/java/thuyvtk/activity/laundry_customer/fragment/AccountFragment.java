package thuyvtk.activity.laundry_customer.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.zip.Inflater;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.activity.EditAccountActivity;
import thuyvtk.activity.laundry_customer.activity.LoginActivity;
import thuyvtk.activity.laundry_customer.activity.MainActivity;
import thuyvtk.activity.laundry_customer.library.SharePreferenceLib;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    Button btnAccount,btnLogout;

    public AccountFragment() {
        // Required empty public constructor
    }

    private void defineView(View view) {
        btnAccount = view.findViewById(R.id.btnAccount);
        btnLogout = view.findViewById(R.id.btnLogout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        defineView(view);
        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditAccountActivity.class);
                startActivity(intent);
            }
        });
        // logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePreferenceLib sharePreferenceLib =  new SharePreferenceLib(getContext());
                sharePreferenceLib.logouṭ();
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }

}
