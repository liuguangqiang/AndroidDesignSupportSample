package com.liuguangqiang.androidsupportdesignsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.liuguangqiang.androidsupportdesignsample.R;
import com.liuguangqiang.androidsupportdesignsample.entity.Product;

import java.util.List;

/**
 * Created by Eric on 15/6/2.
 */
public class ProductAdapter extends BaseRecyclerAdapter<Product, ProductAdapter.ProductViewHolder> {

    public ProductAdapter(Context context, List<Product> list) {
        super(context, list);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bindData(data.get(position));
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;

        public ProductViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.item_tv_title);
        }

        public void bindData(Product product) {
            tvTitle.setText(product.title);
        }

    }

}
