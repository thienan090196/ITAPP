package com.example.cuongtran.itapp.fragment;


import android.app.ListFragment;
import android.opengl.EGLExt;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.cuongtran.itapp.R;
import com.example.cuongtran.itapp.adapter.PostAdapter;
import com.example.cuongtran.itapp.model.Account;
import com.example.cuongtran.itapp.model.Post;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListPostFragment extends ListFragment {
    Account account;

    public ListPostFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        account = (Account) getArguments().getSerializable("account");

        PostAdapter postAdapter = new PostAdapter(getActivity(),R.layout.list_item, account.getPosts());

        setListAdapter(postAdapter);
        return super.onCreateView(inflater,container,savedInstanceState);
    }

}
