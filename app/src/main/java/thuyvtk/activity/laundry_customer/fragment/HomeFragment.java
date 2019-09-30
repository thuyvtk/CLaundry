package thuyvtk.activity.laundry_customer.fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.model.StoreDTO;
import thuyvtk.activity.laundry_customer.presenter.StorePresenter;
import thuyvtk.activity.laundry_customer.view.StoreView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements StoreView {

    ImageButton btnAllService;
    ImageButton btnWashService;
    ImageButton btnShoesService;
    ImageButton btnStuffedService;
    ImageButton btnDryClearService;
    TextView lbServiceAll;
    TextView lbServiceWash;
    TextView lbServiceShoes;
    TextView lbServiceStuffed;
    TextView lbServiceDry;
    TabLayout tabStore;
    public HomeFragment() {
        // Required empty public constructor
    }

    StorePresenter storePresenter;
    ListView lvStore;
    boolean isStart = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        storePresenter = new StorePresenter(this);
        loadALlStore();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        lvStore = view.findViewById(R.id.lvStore);
        // service button
        btnAllService = view.findViewById(R.id.btnAllService);
        btnAllService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickService(btnAllService);
            }
        });
        btnWashService = view.findViewById(R.id.btnWashService);
        btnWashService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickService(btnWashService);
            }
        });
        btnShoesService = view.findViewById(R.id.btnShoesService);
        btnShoesService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickService(btnShoesService);
            }
        });
        btnStuffedService = view.findViewById(R.id.btnStuffedService);
        btnStuffedService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickService(btnStuffedService);
            }
        });
        btnDryClearService = view.findViewById(R.id.btnDryClearService);
        btnDryClearService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickService(btnDryClearService);
            }
        });
        // label service
        lbServiceAll = view.findViewById(R.id.lbServiceAll);
        lbServiceWash = view.findViewById(R.id.lbServiceWash);
        lbServiceShoes = view.findViewById(R.id.lbServiceShoes);
        lbServiceStuffed = view.findViewById(R.id.lbServiceStuffed);
        lbServiceDry = view.findViewById(R.id.lbServiceDry);
        clickService(btnAllService);
        // tab service
        tabStore = view.findViewById(R.id.tabStore);
        tabStore.addTab(tabStore.newTab().setText("Recent"));
        tabStore.addTab(tabStore.newTab().setText("Top"));
        tabStore.addTab(tabStore.newTab().setText("Near"));

        return view;
    }

    private void loadALlStore() {
        storePresenter.getAllStore();
    }

    @Override
    public void returnAllStore(ArrayList<StoreDTO> listStore) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        ArrayList<String> list = new ArrayList<>();
        for (StoreDTO dto : listStore) {
            System.out.println(dto.getName());
            list.add(dto.getName());
        }
        adapter.addAll(list);
        lvStore.setAdapter(adapter);
    }

    public void clickService(ImageButton imageButton) {
        switch (imageButton.getId()) {
            case R.id.btnAllService:
                btnAllService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_red));
                btnWashService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                btnShoesService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                btnStuffedService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                btnDryClearService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                lbServiceAll.setTextColor(Color.parseColor("#c60000"));
                lbServiceWash.setTextColor(Color.parseColor("#bac2c4"));
                lbServiceShoes.setTextColor(Color.parseColor("#bac2c4"));
                lbServiceStuffed.setTextColor(Color.parseColor("#bac2c4"));
                lbServiceDry.setTextColor(Color.parseColor("#bac2c4"));
                break;
            case R.id.btnWashService:
                btnWashService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_red));
                btnAllService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                btnShoesService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                btnStuffedService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                btnDryClearService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                lbServiceAll.setTextColor(Color.parseColor("#bac2c4"));
                lbServiceWash.setTextColor(Color.parseColor("#c60000"));
                lbServiceShoes.setTextColor(Color.parseColor("#bac2c4"));
                lbServiceStuffed.setTextColor(Color.parseColor("#bac2c4"));
                lbServiceDry.setTextColor(Color.parseColor("#bac2c4"));
                break;
            case R.id.btnShoesService:
                btnShoesService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_red));
                btnAllService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                btnWashService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                btnStuffedService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                btnDryClearService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                lbServiceAll.setTextColor(Color.parseColor("#bac2c4"));
                lbServiceWash.setTextColor(Color.parseColor("#bac2c4"));
                lbServiceShoes.setTextColor(Color.parseColor("#c60000"));
                lbServiceStuffed.setTextColor(Color.parseColor("#bac2c4"));
                lbServiceDry.setTextColor(Color.parseColor("#bac2c4"));
                break;
            case R.id.btnStuffedService:
                btnStuffedService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_red));
                btnAllService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                btnWashService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                btnShoesService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                btnDryClearService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                lbServiceAll.setTextColor(Color.parseColor("#bac2c4"));
                lbServiceWash.setTextColor(Color.parseColor("#bac2c4"));
                lbServiceShoes.setTextColor(Color.parseColor("#bac2c4"));
                lbServiceStuffed.setTextColor(Color.parseColor("#c60000"));
                lbServiceDry.setTextColor(Color.parseColor("#bac2c4"));
                break;
            case R.id.btnDryClearService:
                btnDryClearService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_red));
                btnAllService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                btnWashService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                btnShoesService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                btnStuffedService.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
                lbServiceAll.setTextColor(Color.parseColor("#bac2c4"));
                lbServiceWash.setTextColor(Color.parseColor("#bac2c4"));
                lbServiceShoes.setTextColor(Color.parseColor("#bac2c4"));
                lbServiceStuffed.setTextColor(Color.parseColor("#bac2c4"));
                lbServiceDry.setTextColor(Color.parseColor("#c60000"));
                break;

        }
    }
}
