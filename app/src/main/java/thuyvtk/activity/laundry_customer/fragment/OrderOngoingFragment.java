package thuyvtk.activity.laundry_customer.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.adapter.OrderOngoingAdapter;
import thuyvtk.activity.laundry_customer.library.SharePreferenceLib;
import thuyvtk.activity.laundry_customer.model.OrderDTO;
import thuyvtk.activity.laundry_customer.model.OrderDetailDTO;
import thuyvtk.activity.laundry_customer.presenter.OrderPresenter;
import thuyvtk.activity.laundry_customer.view.OrderView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderOngoingFragment extends Fragment implements OrderView {
    RecyclerView rv_order;
    OrderPresenter orderPresenter;
    LinearLayout ln_waiting;
    OrderOngoingAdapter adapter;

    public OrderOngoingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_ongoing, container, false);
        defineView(view);
        orderPresenter = new OrderPresenter(this);
        getOrderByDate();
        return view;
    }

    private void defineView(View view) {
        rv_order = view.findViewById(R.id.rv_order);
        rv_order.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_order.setLayoutManager(linearLayoutManager);
        ln_waiting = view.findViewById(R.id.ln_waiting);
    }

    private void getOrderByDate() {
        SharePreferenceLib sharePreferenceLib = new SharePreferenceLib(getContext());
        orderPresenter.getOrderByDateAndStatus(sharePreferenceLib.getUser().getCustomerId(), "2019-10-03","2019-10-14", "ongoing");
        ln_waiting.setVisibility(View.VISIBLE);

    }

    @Override
    public void loadOrderHistory(List<OrderDTO> orderList) {

    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void createOrderSuccess() {

    }

    @Override
    public void returnListOrder(List<OrderDetailDTO> listOrderDetail) {
        adapter = new OrderOngoingAdapter(getContext(),listOrderDetail);
        rv_order.setAdapter(adapter);
        ln_waiting.setVisibility(View.GONE);
    }
}
