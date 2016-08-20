package com.wcc.snackcat.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.wcc.snackcat.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by virgil on 16/8/20.
 */
public class DummyFragment extends Fragment {
    private static final String TAG = "DummyFragment";
    private Context mContext = null;
    private ListView listView_dummyfragment;
    private TextView textView_empty;
    private TextView textView_dummyfragment_info;
    private SimpleAdapter adater = null;
    private List<Map<String, Object>> totalList = new ArrayList<>();
    private int tabIndex = 0;


    public static DummyFragment newInstance(int tabIndex) {
        DummyFragment fragment = new DummyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("tabIndex", tabIndex);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();

        Log.i(TAG, "---->>onCreate: " + tabIndex);

        //接收setArguments的传值
        tabIndex = getArguments().getInt("tabIndex");

        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("icon", R.mipmap.ic_launcher);
            map.put("title", "title_" + i + "_" + tabIndex);
            map.put("summary", "summay_" + i);

            totalList.add(map);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        Log.i(TAG, "---->>onCreateView: " + tabIndex);


        View view = inflater.inflate(R.layout.fragment_dummy, container, false);
        //初始化View
        textView_dummyfragment_info = (TextView) view.findViewById(R.id
                .textView_dummyfragment_info);
        textView_empty = (TextView) view.findViewById(R.id.textView_empty);
        listView_dummyfragment = (ListView) view.findViewById(R.id.listView_dummyfragment);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView_dummyfragment_info.setText("勾选的tab书签为：" + tabIndex);

        adater = new SimpleAdapter(mContext, totalList, R.layout.item_listview_dummyframent,
                new String[]{"icon", "title", "summary"},
                new int[]{R.id.imageView_item_icon, R.id.textView_item_title, R.id
                        .textView_item_summary});
        listView_dummyfragment.setAdapter(adater);
        listView_dummyfragment.setEmptyView(textView_empty);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG, "---->>> onHiddenChanged: " + tabIndex + "::::" + hidden);
    }
}
