package thuyvtk.activity.laundry_customer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.adapter.RecyclerViewAdapter;
import thuyvtk.activity.laundry_customer.adapter.ServiceAdapter;
import thuyvtk.activity.laundry_customer.library.CartDTO;
import thuyvtk.activity.laundry_customer.library.LocationLibrary;
import thuyvtk.activity.laundry_customer.library.SharePreferenceLib;
import thuyvtk.activity.laundry_customer.model.OrderDTO;
import thuyvtk.activity.laundry_customer.model.OrderDetailDTO;
import thuyvtk.activity.laundry_customer.model.OrderServiceDTO;
import thuyvtk.activity.laundry_customer.model.ServiceDTO;
import thuyvtk.activity.laundry_customer.presenter.OrderPresenter;
import thuyvtk.activity.laundry_customer.view.OrderView;
import thuyvtk.activity.laundry_customer.view.SlotView;

public class ReceiptDetailActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, OrderView, SlotView {
    TextView timeDelivery, dateDelivery, txtTotalReceipt, txtCustomerReceipt, txtAddress, timeTake, dateTake;
    LinearLayout ln_dateTake, ln_receipt_waiting;
    ListView lvReceipt;
    SharePreferenceLib sharePreferenceLib;
    ArrayList<ServiceDTO> listService;
    CartDTO cartDTO;
    String PENDING = "ongoing";
    OrderPresenter presenter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    RecyclerViewAdapter recyclerViewHorizontalAdapter;
    LinearLayoutManager horizontalLayout;
    Boolean isTakeChoosen = true;

    private void defineView() {
        timeDelivery = findViewById(R.id.timeDelivery);
        dateDelivery = findViewById(R.id.dateDelivery);
        timeTake = findViewById(R.id.timeTake);
        dateTake = findViewById(R.id.dateTake);
        ln_dateTake = findViewById(R.id.ln_dateTake);
        lvReceipt = findViewById(R.id.lvReceipt);
        txtTotalReceipt = findViewById(R.id.txtTotalReceipt);
        txtCustomerReceipt = findViewById(R.id.txtCustomerReceipt);
        txtAddress = findViewById(R.id.txtAddress);
        ln_receipt_waiting = findViewById(R.id.ln_receipt_waiting);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_detail);
        defineView();
        setTime();
        loadListSlot();
        sharePreferenceLib = new SharePreferenceLib(this);
        cartDTO = sharePreferenceLib.getShoppingCart();
        if (cartDTO != null) {
            listService = new ArrayList<>(cartDTO.getListStore().values());
            ServiceAdapter serviceAdapter = new ServiceAdapter(this, listService, 0);
            lvReceipt.setAdapter(serviceAdapter);
        }
        txtCustomerReceipt.setText(cartDTO.getCustomer().getCustomerName());
        txtTotalReceipt.setText(cartDTO.getTotalPrice() + "");
        setAddressTextBox();
        presenter = new OrderPresenter((OrderView) this);
    }

    private void loadListSlot() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_slotsAvailable);
        recyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        // Adding items to RecyclerView.
        ArrayList<String> slots = AddItemsToRecyclerViewArrayList();

        recyclerViewHorizontalAdapter = new RecyclerViewAdapter(slots, this);

        horizontalLayout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayout);

        recyclerView.setAdapter(recyclerViewHorizontalAdapter);
    }

    private ArrayList<String> AddItemsToRecyclerViewArrayList() {
        ArrayList<String> slots = new ArrayList<>();
        slots.add("06:00");
        slots.add("08:00");
        slots.add("10:00");
        slots.add("12:00");
        slots.add("14:00");
        slots.add("16:00");
        slots.add("18:00");
        slots.add("20:30");
        slots.add("22:00");
        slots.add("00:00");
        slots.add("02:00");
        slots.add("04:00");
        return slots;
    }

    private void setAddressTextBox() {
        LocationLibrary locationLibrary = new LocationLibrary(getApplicationContext(), this);
        List<Address> address = locationLibrary.getCurrentAddress();
        String addressLine = address.get(0).getAddressLine(0);
        txtAddress.setText(addressLine);
    }

    public void clickToOrder(View view) {
        ArrayList<OrderServiceDTO> listOrder = new ArrayList<>();
        for (ServiceDTO service : listService) {
            OrderServiceDTO dto = new OrderServiceDTO(service.getQuantity(), (float) service.getPrice(), service.getId());
            listOrder.add(dto);
        }
        String deliveryTime = dateDelivery.getText().toString() + " " + timeDelivery.getText().toString();
        String takeTime = dateTake.getText().toString() + " " + timeTake.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date take = sdf.parse(takeTime);
            Date delivery = sdf.parse(deliveryTime);
            String address = txtAddress.getText().toString();
            OrderDTO orderDTO = new OrderDTO((float) cartDTO.getTotalPrice(), PENDING,
                    cartDTO.getCustomer().getCustomerId(), take, delivery,address, listOrder);
            presenter.createOrder(orderDTO);
            ln_receipt_waiting.setVisibility(View.VISIBLE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void setTime() {
        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, this, 2019, 10, 12);
        Date date = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        timeDelivery.setText(timeFormat.format(date));
        Date nextDate = date;
        nextDate.setDate(date.getDay() + 1);
        dateDelivery.setText(dateFormat.format(nextDate));

        timeTake.setText(timeFormat.format(date));
        dateTake.setText(dateFormat.format(nextDate));
        ln_dateTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + (month + 1) + "/" + year;
        if (isTakeChoosen) {
            dateTake.setText(date);
        } else {
            dateDelivery.setText(date);
        }

    }


    @Override
    public void loadOrderHistory(List<OrderDetailDTO> orderList) {

    }

    @Override
    public void onFail(String msg) {
        ln_receipt_waiting.setVisibility(View.GONE);
    }

    @Override
    public void createOrderSuccess() {
        sharePreferenceLib.deleteCart();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Congratulation!!");
        builder.setMessage("Your order is confirmed.");
        builder.setNegativeButton("Great!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                setResult(115);
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void returnListOrder(List<OrderDetailDTO> listOrderDetail) {

    }

    @Override
    public void rateSuccess(String message) {

    }

    @Override
    public void slotChange(String time) {
        if (isTakeChoosen) {
            timeTake.setText(time);
        } else {
            timeDelivery.setText(time);
        }
    }

    public void clickToChooseTake(View view) {
        isTakeChoosen = true;
    }

    public void clickToChooseDelivery(View view) {
        isTakeChoosen = false;
    }
}
