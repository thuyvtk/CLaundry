package thuyvtk.activity.laundry_customer.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.activity.StoreDetailActivity;
import thuyvtk.activity.laundry_customer.adapter.StoreAdapter;
import thuyvtk.activity.laundry_customer.library.LocationLibrary;
import thuyvtk.activity.laundry_customer.library.SharePreferenceLib;
import thuyvtk.activity.laundry_customer.model.CustomerDTO;
import thuyvtk.activity.laundry_customer.model.ServiceTypeDTO;
import thuyvtk.activity.laundry_customer.model.StoreDTO;
import thuyvtk.activity.laundry_customer.presenter.ServiceTypePresenter;
import thuyvtk.activity.laundry_customer.presenter.StorePresenter;
import thuyvtk.activity.laundry_customer.view.ServiceTypeView;
import thuyvtk.activity.laundry_customer.view.StoreView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements StoreView, ServiceTypeView {
    final String SERVICE_ALL_ID = "0";
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
    TextView txtAddress;
    LinearLayout ln_home_waitting;
    TabLayout tabStore;
    StoreAdapter storeAdapter;
    StorePresenter storePresenter;
    ServiceTypePresenter serviceTypePresenter;
    ListView lvStore;
    ArrayList<ServiceTypeDTO> listServiceType;
    String currentServiceTypeId = SERVICE_ALL_ID;
    CustomerDTO dto;
    ArrayList<ImageButton> listButton = new ArrayList<>();
    ArrayList<TextView> listLabel = new ArrayList<>();
    LocationLibrary locationLibrary;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        defineView(view);
        setClickListener();
        clickService(btnAllService);
        setUpTab();
        serviceTypePresenter = new ServiceTypePresenter(this);
        loadAllService();
        locationLibrary =  new LocationLibrary(getContext(),getActivity());
        setAddressTextBox();
        return view;
    }

    private void setAddressTextBox() {
        List<Address> address = locationLibrary.getCurrentAddress();
        String addressLine = address.get(0).getAddressLine(0);
        if(addressLine.length()> 35){
            String temp =addressLine.replace(addressLine.substring(35 ),"...");
            addressLine = temp;
        }
        txtAddress.setText(addressLine);
    }

    @Override
    public void returnAllStore(ArrayList<StoreDTO> listStore) {
        if (listStore.size() > 0) {
            storeAdapter = new StoreAdapter(getContext(), listStore);
            lvStore.setAdapter(storeAdapter);
            lvStore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    StoreDTO dto = (StoreDTO) storeAdapter.getItem(position);
                    Intent intent =  new Intent(getContext(), StoreDetailActivity.class);
                    intent.putExtra("storeId",dto.getStore_id());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void loadStoreFail(String message) {

    }

    @Override
    public void searchStoreByName(ArrayList<StoreDTO> listStore) {

    }

    @Override
    public void searchStoreByNameFail(String message) {

    }

    @Override
    public void loadAllService(List<ServiceTypeDTO> listService) {
        listServiceType = (ArrayList<ServiceTypeDTO>) listService;
        listService.add(0, new ServiceTypeDTO(SERVICE_ALL_ID, "all"));
        storePresenter.getRecentStoreWithCurrentService(currentServiceTypeId, dto.getCustomerId());
        ln_home_waitting.setVisibility(View.GONE);
    }

    @Override
    public void loadServiceFail(String message) {

    }


    private void loadAllService() {
        //load all service
        serviceTypePresenter.loadAllService();
        ln_home_waitting.setVisibility(View.VISIBLE);
    }

    private void defineView(View view) {
        lvStore = view.findViewById(R.id.lvStore);
        // service button
        btnAllService = view.findViewById(R.id.btnAllService);
        btnWashService = view.findViewById(R.id.btnWashService);
        btnShoesService = view.findViewById(R.id.btnShoesService);
        btnStuffedService = view.findViewById(R.id.btnStuffedService);
        btnDryClearService = view.findViewById(R.id.btnDryClearService);
        lbServiceAll = view.findViewById(R.id.lbServiceAll);
        lbServiceWash = view.findViewById(R.id.lbServiceWash);
        lbServiceShoes = view.findViewById(R.id.lbServiceShoes);
        lbServiceStuffed = view.findViewById(R.id.lbServiceStuffed);
        lbServiceDry = view.findViewById(R.id.lbServiceDry);
        txtAddress = view.findViewById(R.id.txtAddress);
        tabStore = view.findViewById(R.id.tabStore);
        ln_home_waitting = view.findViewById(R.id.ln_home_waiting);
    }

    private void setClickListener() {
        btnAllService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickService(btnAllService);
            }
        });
        btnWashService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickService(btnWashService);
            }
        });
        btnShoesService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickService(btnShoesService);
            }
        });
        btnStuffedService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickService(btnStuffedService);
            }
        });
        btnDryClearService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickService(btnDryClearService);
            }
        });
    }

    private void setUpTab() {
        storePresenter = new StorePresenter(this);
        SharePreferenceLib sharePreferenceLib = new SharePreferenceLib(getContext());
        dto = sharePreferenceLib.getUser();
        // tab service
        tabStore.addTab(tabStore.newTab().setText("Recent"));
        tabStore.addTab(tabStore.newTab().setText("Top"));
        tabStore.addTab(tabStore.newTab().setText("Near"));
        tabStore.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        storePresenter.getRecentStoreWithCurrentService(currentServiceTypeId, dto.getCustomerId());
                        break;
                    case 1:
                    //    storePresenter.getTopStoreWithCurrentService(currentServiceTypeId);
                        break;
                    case 2:
                        LatLng latLng = locationLibrary.getCurrentLocation();
                       List<Address> address = locationLibrary.getCurrentAddress();
                        if(latLng != null){
                  //          storePresenter.getNearbyStoreWithCurrentService(currentServiceTypeId, latLng.latitude, latLng.longitude);
                        }else{
                            Toast.makeText(getContext(), "location not defined", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    private void addButtonAndLable() {
        listButton.clear();
        listLabel.clear();
        listButton.add(btnAllService);
        listButton.add(btnWashService);
        listButton.add(btnShoesService);
        listButton.add(btnStuffedService);
        listButton.add(btnDryClearService);
        listLabel.add(lbServiceAll);
        listLabel.add(lbServiceWash);
        listLabel.add(lbServiceShoes);
        listLabel.add(lbServiceStuffed);
        listLabel.add(lbServiceDry);
    }

    public void clickService(ImageButton imageButton) {
        addButtonAndLable();
        switch (imageButton.getId()) {
            case R.id.btnAllService:
                activate(0);
                break;
            case R.id.btnWashService:
                activate(1);
                break;
            case R.id.btnShoesService:
                activate(2);
                break;
            case R.id.btnStuffedService:
                activate(3);
                break;
            case R.id.btnDryClearService:
                activate(4);
                break;

        }
    }

    private void activate(int index) {
        listButton.get(index).setBackground(getResources().getDrawable(R.drawable.boder_cycle_red));
        listLabel.get(index).setTextColor(Color.parseColor("#c60000"));
        setCurrentService(listLabel.get(index).toString());
        listButton.remove(index);
        listLabel.remove(index);
        deactivate();
    }

    private void setCurrentService(String serviceName) {
        if (listServiceType != null) {
            for (ServiceTypeDTO dto : listServiceType) {
                if (dto.getName().trim().toUpperCase().equals(serviceName.trim().toUpperCase())) {
                    currentServiceTypeId = dto.getId();
                }
            }
        }
    }

    private void deactivate() {
        for (ImageButton button : listButton) {
            button.setBackground(getResources().getDrawable(R.drawable.boder_cycle_5dp));
        }
        for (TextView textView : listLabel) {
            textView.setTextColor(Color.parseColor("#bac2c4"));
        }
    }


}
