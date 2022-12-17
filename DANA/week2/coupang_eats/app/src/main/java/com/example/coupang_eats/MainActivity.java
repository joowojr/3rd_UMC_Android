package com.example.coupang_eats;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_mara);

        adapter = new CustomAdapter();
        listView = (ListView) findViewById(R.id.listView);

        setData();

        listView.setAdapter(adapter);
    }

    private void setData() {
        TypedArray arrImgId = getResources().obtainTypedArray(R.array.imgId);

        String[] names = getResources().getStringArray(R.array.name);
        String[] min = getResources().getStringArray(R.array.min);

        for (int i = 0; i < arrImgId.length(); i++) {
            CustomDTO dto = new CustomDTO();
            dto.setImgId(arrImgId.getResourceId(i, 0));
            dto.setName(names[i]);
            dto.setMin(min[i]);

            adapter.addItem(dto);
        }
    }
}