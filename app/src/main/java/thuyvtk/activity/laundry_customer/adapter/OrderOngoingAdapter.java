package thuyvtk.activity.laundry_customer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.model.OrderDTO;
import thuyvtk.activity.laundry_customer.model.OrderDetailDTO;

public class OrderOngoingAdapter extends RecyclerView.Adapter<OrderOngoingAdapter.OrderOngoingViewHolder> {
    private List<OrderDetailDTO> listOrderByDate;
    private Context context;

    public OrderOngoingAdapter(Context context, List<OrderDetailDTO> listOrderByDate) {
        this.listOrderByDate = listOrderByDate;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderOngoingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderOngoingAdapter.OrderOngoingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderOngoingViewHolder holder, int position) {
        OrderDetailDTO orderDetailDTO = listOrderByDate.get(position);
        holder.txt_date_create.setText(String.format(orderDetailDTO.getDateCreate(), "dd/MM/yyyy"));
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        for (OrderDTO item : orderDetailDTO.getListOrder()) {
            View child = inflater.inflate(R.layout.item_order_sub,null);
            child = addViewChild(child,item);
            holder.list_order.addView(child);
        }
    }

    CardView cv_order;
    TextView txt_status, txt_orderId, txt_storeName, txt_item_price;
    ImageView img_storeProfile;
    private View addViewChild(View child, OrderDTO orderDTO){
        cv_order = child.findViewById(R.id.cv_order);
        txt_status = child.findViewById(R.id.txt_status);
        txt_orderId = child.findViewById(R.id.txt_orderId);
        txt_storeName = child.findViewById(R.id.txt_storeName);
        txt_item_price = child.findViewById(R.id.txt_item_price);
        img_storeProfile = child.findViewById(R.id.img_storeProfile);

        txt_status.setText(orderDTO.getStatus());
        txt_orderId.setText(orderDTO.getOrderId());
        // toDo get store profile
//        Picasso.with(context).load(orderDTO.getCustomerId()).into(holder.service_img);
        String item_price = orderDTO.getListOrderServices().size() + " items - " + orderDTO.getTotalPrice() + " VND";
        txt_item_price.setText(item_price);
        return child;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class OrderOngoingViewHolder extends RecyclerView.ViewHolder {
        TextView txt_date_create;
        LinearLayout list_order;

        public OrderOngoingViewHolder(View serviceView) {
            super(serviceView);
            txt_date_create = serviceView.findViewById(R.id.txt_date_create);
            list_order = serviceView.findViewById(R.id.list_order);
        }
    }
}
