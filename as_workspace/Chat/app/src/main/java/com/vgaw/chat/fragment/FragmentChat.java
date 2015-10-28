package com.vgaw.chat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vgaw.chat.R;
import com.vgaw.chat.activity.ChatActivity;

/**
 * Created by Administrator on 2015/9/22.
 */
public class FragmentChat extends Fragment implements View.OnClickListener{
    public final String TAG = "FRAGMENTCHAT";

    private String text = "";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        text = bundle.getString("text");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.fragment_chat, container, false);
        Button button = (Button) layout.findViewById(R.id.button);
        button.setOnClickListener(this);
        button.setText("Touch Me " + text);
        return layout;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(), ChatActivity.class));
    }
}
