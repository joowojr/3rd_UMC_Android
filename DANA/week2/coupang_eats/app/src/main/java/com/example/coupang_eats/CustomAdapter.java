package com.example.coupang_eats;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private ArrayList<CustomDTO> listCustom = new ArrayList<>();

    // ListView에 보여질 Item 수
    @Override
    public int getCount() {
        return listCustom.size();
    }

    // 하나의 Item(ImageView 1, TextView 2)
    @Override
    public Object getItem(int position) {
        return listCustom.get(position);
    }

    // Item의 id : Item을 구별하기 위한 것으로 position 사용
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 실제로 Item이 보여지는 부분
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_view, null, false);

            holder = new CustomViewHolder();
            holder.image_1 = (ImageView) convertView.findViewById(R.id.image1);
            /*holder.image_2= (ImageView) convertView.findViewById(R.id.image1);
            holder.image_3 = (ImageView) convertView.findViewById(R.id.image1);*/

            holder.store_name = (TextView) convertView.findViewById(R.id.store_name);
            holder.store_min = (TextView) convertView.findViewById(R.id.store_min);
           /*holder.store_info = (TextView) convertView .findViewById(R.id.store_info);*/

            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        CustomDTO dto = listCustom.get(position);

        holder.image_1.setImageResource(dto.getImgId());
        /*holder.image_2.setImageResource(dto.getImgId());
        holder.image_3.setImageResource(dto.getImgId());*/
        holder.store_name.setText(dto.getName());
        holder.store_min.setText(dto.getMin());
        /*holder.store_info.setText(dto.getMin());*/

        return convertView;
    }

    class CustomViewHolder {
        ImageView image_1;/*,image_2,image_3*/
        TextView store_name,store_min;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(CustomDTO dto) {
        listCustom.add(dto);
    }
}