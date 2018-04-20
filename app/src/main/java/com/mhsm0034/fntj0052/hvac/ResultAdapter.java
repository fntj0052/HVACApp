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

public class ResultAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public ResultAdapter (Context context, int resource) {
        super(context, resource);
    }

    public void add(Results object) {
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
    public View getView(int position,View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        ResultHolder resultHolder;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            resultHolder = new ResultHolder();
            resultHolder.tx_id =(TextView)row.findViewById(R.id.tx_id);
            resultHolder.tx_sound =(TextView)row.findViewById(R.id.tx_sound);
            resultHolder.tx_timestamp =(TextView)row.findViewById(R.id.tx_timestamp);
            row.setTag(resultHolder);

        }
        else {
            resultHolder = (ResultHolder)row.getTag();
        }

        Results results = (Results) this.getItem(position);
        resultHolder.tx_id.setText(results.getId());
        resultHolder.tx_sound.setText(results.getSound());
        resultHolder.tx_timestamp.setText(results.getTimestamp());

        return row;
    }
    static class ResultHolder{
        TextView tx_id, tx_sound, tx_timestamp;

    }
}
