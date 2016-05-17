package com.vgaw.androidtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vgaw.androidtest.R;
import com.vgaw.androidtest.adapter.ContactAdapter;
import com.vgaw.androidtest.bean.ContactBase;
import com.vgaw.androidtest.liv.LetterIndexView;
import com.vgaw.androidtest.liv.LivIndex;
import com.vgaw.androidtest.utils.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by caojin on 2016/5/3.
 */
public class ContactFragment extends Fragment {
    private ListView lv;
    private LivIndex litterIdx;
    private HashMap<String, Integer> abcMap;
    private ArrayList<ContactBase> dataList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv = (ListView) getView().findViewById(R.id.lv);
        dataList = HttpUtil.getContact();
        abcMap = HttpUtil.getAbcMap();
        buildLitterIdx(getView());

        ContactAdapter adapter = new ContactAdapter(getContext(), dataList);
        lv.setAdapter(adapter);
    }

    /**
     * 初始化字母表
     * @param view
     */
    private void buildLitterIdx(View view) {
        LetterIndexView livIndex = (LetterIndexView) view.findViewById(R.id.liv_index);
        livIndex.setNormalColor(getResources().getColor(R.color.contacts_letters_color));
        ImageView imgBackLetter = (ImageView) view.findViewById(R.id.img_hit_letter);
        TextView litterHit = (TextView) view.findViewById(R.id.tv_hit_letter);

        litterIdx = new LivIndex(lv, livIndex, litterHit, imgBackLetter, abcMap);
        litterIdx.show();
    }

}
