package com.example.prueba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;


public class FragmentStatement extends Fragment {

    private RecyclerView rvStatement ;
    public FragmentStatement() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view =  inflater.inflate(R.layout.fragment_statement, container, false);
        rvStatement = view.findViewById(R.id.rv_statement);
        this.request();
        return view;

    }

    private void request() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://91f7b927-de7a-401b-8a73-f0fd9b35764d.mock.pstmn.io/user/statements";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        StatementList list = gson.fromJson(response, StatementList.class);
                        List<Statement> statements = list.getStatement();
                        fillRecyclerView(statements);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Lo sentimos, se ha producido un error", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

    private void fillRecyclerView(List<Statement> list){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.rvStatement.getContext(),
                new LinearLayoutManager(getContext()).getOrientation());
        this.rvStatement.setLayoutManager(layoutManager);
        this.rvStatement.setAdapter(new StatementRecyclerView(getContext(),list));
        this.rvStatement.addItemDecoration(dividerItemDecoration);

    }
}