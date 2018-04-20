package com.mhsm0034.fntj0052.hvac;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter2 extends ArrayAdapter{
    List list = new ArrayList();

    public ResultAdapter2(Context context,int resource) {
        super(context, resource);
    }

    public void add(Results2 object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View row;
        row = convertView;
        ResultHolder2 resultHolder2;

        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout2,parent,false);
            resultHolder2 = new ResultHolder2();
            resultHolder2.tx_id =(TextView)row.findViewById(R.id.tx_id);
            resultHolder2.tx_temp =(TextView)row.findViewById(R.id.tx_temp);
            resultHolder2.tx_timestamp =(TextView)row.findViewById(R.id.tx_timestamp);
            row.setTag(resultHolder2);
        }

        else {
            resultHolder2 =(ResultHolder2) row.getTag();
        }

        Results2 results2 = (Results2) this.getItem(position);
        resultHolder2.tx_id.setText(results2.getId());
        resultHolder2.tx_temp.setText(results2.getTemp());
        resultHolder2.tx_timestamp.setText(results2.getTimestamp());


        return row;
    }

    static class ResultHolder2{
        TextView tx_id,tx_temp,tx_timestamp;
    }
}
