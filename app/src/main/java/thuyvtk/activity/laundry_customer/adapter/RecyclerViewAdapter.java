package thuyvtk.activity.laundry_customer.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.view.SlotView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyView> {

    private List<String> list;
    public int selectedPosition = 0;
    SlotView slotView;

    public class MyView extends RecyclerView.ViewHolder {

        public TextView slot_textview1,slot_textview2;
        public LinearLayout ln_slot_item;

        public MyView(View view) {
            super(view);
            slot_textview1 = (TextView) view.findViewById(R.id.slot_textview1);
            slot_textview2 = (TextView) view.findViewById(R.id.slot_textview2);
            ln_slot_item = (LinearLayout) view.findViewById(R.id.ln_slot_item);
        }
    }


    public RecyclerViewAdapter(List<String> horizontalList, SlotView slotView) {
        this.list = horizontalList;
        this.slotView = slotView;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_available_item, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

        holder.slot_textview1.setText(list.get(position));
        if(position >= 4 && position <= 8){
            holder.slot_textview2.setText("PM");
        }else{
            holder.slot_textview2.setText("AM");
        }

        if (selectedPosition == position){
            holder.slot_textview1.setBackgroundResource(R.drawable.border_radius_slot_item_green);
            holder.slot_textview2.setTextColor(Color.parseColor("#009432"));
            slotView.slotChange(list.get(position));
        }
        else{
            holder.slot_textview1.setBackgroundResource(R.drawable.border_radius_slot_item);
            holder.slot_textview2.setTextColor(Color.parseColor("#FF9800"));
        }

        holder.ln_slot_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        System.out.println(position);
        return position;
    }

//    public String selectedSlot() {
//        if ((list != null) && (list.size() > 0)) {
//            if (selectedPosition > -1) {
//                return list.get(selectedPosition);
//            } else {
//                return list.get(0);
//            }
//        }
//        return "";
//    }
    public interface getSlectedSlot {
        void getSlotPicked(String slot);
    }

}
