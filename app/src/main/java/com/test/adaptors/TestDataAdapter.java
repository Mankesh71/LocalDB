package com.test.adaptors;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.R;
import com.test.activities.TestDataFullActivity;
import com.test.models.TestModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Mankesh71 on 5/24/2017.
 */
public class TestDataAdapter extends RecyclerView.Adapter<TestDataAdapter.ViewHolder> {
    private List<TestModel> mListReportBean;
    private Context mContext;

    public TestDataAdapter(Context con, List<TestModel> items) {
        this.mListReportBean = items;
        this.mContext = con;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvFullName)
        TextView tvFullName;
        @BindView(R.id.tvEmailId)
        TextView tvEmailId;
        @BindView(R.id.tvMobileNumber)
        TextView tvMobileNumber;
        @BindView(R.id.llMainItem)
        LinearLayout llMainItem;
        @BindView(R.id.view)
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_test_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TestModel item = mListReportBean.get(position);
        holder.tvFullName.setText(item.getStrFullName());
        holder.tvEmailId.setText(item.getStrMobileNumber());
        holder.tvMobileNumber.setText(item.getStrEmailId());
        holder.view.setVisibility(View.VISIBLE);
        holder.llMainItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, TestDataFullActivity.class).putExtra("data", mListReportBean.get(holder.getAdapterPosition())));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListReportBean.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
