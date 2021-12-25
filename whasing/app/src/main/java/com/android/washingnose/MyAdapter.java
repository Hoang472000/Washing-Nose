package com.android.washingnose;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends BaseAdapter implements ListAdapter {

    List<Device> deviceList = new ArrayList<Device>();

    public MyAdapter(List<Device> list) {
        this.deviceList = list;
    }

    @Override
    public int getCount() {
        return deviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return deviceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.item_device, null);
        } else viewProduct = convertView;

        ViewHolder holder = new ViewHolder();
        holder.mNameDevice = (TextView) viewProduct.findViewById(R.id.name_device);
        holder.mAdd = (Button) viewProduct.findViewById(R.id.add);
        viewProduct.setTag(holder);

        ViewHolder holder1 = (ViewHolder) viewProduct.getTag();
        Device device = (Device) getItem(position);
        holder1.mNameDevice.setText(device.nameDevice);
        holder1.mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(), "Address: "+device.macAddress, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(parent.getContext(), ControlDevice.class);
                intent.putExtra("address",device.macAddress);
                parent.getContext().startActivity(intent);
            }
        });

        return viewProduct;
    }

    static class ViewHolder {
        TextView mNameDevice;
        Button mAdd;
    }

}
