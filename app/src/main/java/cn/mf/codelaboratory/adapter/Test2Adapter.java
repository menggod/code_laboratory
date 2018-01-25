package cn.mf.codelaboratory.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.mf.codelaboratory.R;
import cn.mf.codelaboratory.model.WashCarBean;

/**
 * 项目名称：CodeLaboratory
 *
 * @author menggod
 * @date 2018/1/25
 */
public class Test2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RadioGroup.OnCheckedChangeListener {
    private List<WashCarBean> mList = new ArrayList<>();
    private OnWashCarItemClickListener mListener;

    public Test2Adapter setListener(OnWashCarItemClickListener listener) {
        mListener = listener;
        return this;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case WashCarBean.wash_car_tpye1:
                viewHolder = new Item1ViewHolder(View.inflate(parent.getContext(), R.layout.item_wash_car_type1, null));
                break;
            case WashCarBean.wash_car_tab:
                viewHolder = new ItemTabViewHolder(View.inflate(parent.getContext(), R.layout.item_wash_car_tab, null));
                break;
            case WashCarBean.wash_car_tpye3:
                viewHolder = new Item3ViewHolder(View.inflate(parent.getContext(), R.layout.item_wash_car_type3, null));
                break;
            default:
                viewHolder = new Item1ViewHolder(View.inflate(parent.getContext(), R.layout.item_wash_car_type1, null));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (mList.get(position).adapterType) {
            case WashCarBean.wash_car_tpye3:
                Item3ViewHolder item3ViewHolder = (Item3ViewHolder) holder;
                item3ViewHolder.mTextview.setText(mList.get(position).centerText);
                break;
            case WashCarBean.wash_car_tab:
                ItemTabViewHolder itemTabViewHolder = (ItemTabViewHolder) holder;
                itemTabViewHolder.mRadioGroup.setOnCheckedChangeListener(this);
                itemTabViewHolder.mRadioGroup.setTag(position);
                break;
            default:
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).adapterType;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void setList(ArrayList<WashCarBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public List<WashCarBean> getList() {
        return mList;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        int itemPosition = 0;
        Object tag = radioGroup.getTag();
        if (tag != null) {
            try {
                itemPosition = Integer.parseInt(tag.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (mListener != null) {
            int position = 0;
            if (checkedId == R.id.radio_button_1) {
                position = 0;
            } else if (checkedId == R.id.radio_button_2) {
                position = 1;
            }
            mListener.onRGCheckedChanged(this, position, itemPosition);
        }
    }

    public void setNewList(List<WashCarBean> list, int itemPosition) {

//        notifyItemRangeChanged(itemPosition, list.size() - itemPosition);
        notifyItemRangeRemoved(itemPosition, mList.size() - itemPosition);
        mList = list;
        notifyItemRangeChanged(itemPosition, list.size() - itemPosition);
    }


    private static class Item1ViewHolder extends RecyclerView.ViewHolder {

        public Item1ViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static class ItemTabViewHolder extends RecyclerView.ViewHolder {

        public RadioGroup mRadioGroup;

        public ItemTabViewHolder(View itemView) {
            super(itemView);
            mRadioGroup = itemView.findViewById(R.id.radio_group);
        }
    }

    private static class Item3ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextview;

        public Item3ViewHolder(View itemView) {
            super(itemView);
            mTextview = itemView.findViewById(R.id.center_tv);
        }
    }

    public interface OnWashCarItemClickListener {
        void onRGCheckedChanged(Test2Adapter adapter, int position, int itemPosition);
    }
}
