//author: Christian Berdejo
package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;
/*
Esta clase sirve para ir generando los textview que van en cada fila de las listas
 */
public class BaseAdapter extends android.widget.BaseAdapter {
        Context context;
        List<String> list;
        LayoutInflater inflater;

        public BaseAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflater.inflate(R.layout.activity_task,null);
            TextView txtView = (TextView) view.findViewById((R.id.textView));
            txtView.setText(list.get(i));
            return view;
        }
}
