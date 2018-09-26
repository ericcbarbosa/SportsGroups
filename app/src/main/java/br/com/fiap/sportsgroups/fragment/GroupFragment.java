package br.com.fiap.sportsgroups.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import br.com.fiap.sportsgroups.R;
import br.com.fiap.sportsgroups.adapter.GroupAdapater;
import br.com.fiap.sportsgroups.model.Group;

public class GroupFragment extends Fragment {
    private RecyclerView recycler;
    private ArrayList<Group> groupsList;
    private GroupAdapater adapter;
    View myView;

    private EditText name;
    private EditText description;

    private Button btnCancel;
    private Button btnAddRegister;
    private FloatingActionButton btnFloatingRegister;

    public GroupFragment() {
        // Required empty public constructor
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Toast.makeText(getActivity(),"Context Menu - Novo", Toast.LENGTH_LONG);
                displayDialog();
                break;
        }

        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.fragment_group, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        btnFloatingRegister = myView.findViewById(R.id.group_floating_register);
        recycler = myView.findViewById(R.id.group_recycler);
        recycler.setLayoutManager(linearLayoutManager);

        groupsList = new ArrayList<Group>();
        groupsList.add(new Group(
                1,
                "Sparta",
                "Os matadores de sparta"
        ));
        groupsList.add(new Group(
                1,
                "Atenas",
                "Os matadores de atenas"
        ));
        groupsList.add(new Group(
                1,
                "Brazil",
                "Os matadores de brasil"
        ));

        adapter = new GroupAdapater(getContext(), groupsList);
        recycler.setAdapter(adapter);

        btnFloatingRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialog();
            }
        });

        return myView;
    }

    public void displayDialog() {
        final Dialog dialog = new Dialog(getContext());

        dialog.setTitle("Cadastrar Grupo");
        dialog.setContentView(R.layout.group_register);
        dialog.setCancelable(true);

        name = (EditText) dialog.findViewById(R.id.group_register_name);
        description = (EditText) dialog.findViewById(R.id.group_register_description);

        btnAddRegister = (Button) dialog.findViewById(R.id.user_register_btn_add);
        btnCancel = (Button) dialog.findViewById(R.id.group_register_btn_cancel);

        btnAddRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddGroup(
                        name.getText().toString(),
                        description.getText().toString()
                );

                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGroupsList();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void AddGroup(String name, String description) {
        Random rand = new Random();

        groupsList.add( new Group(rand.nextInt(100) + 5, name, description) );
        adapter.notifyDataSetChanged();
    }

    private void getGroupsList() {
        adapter = new GroupAdapater(getContext(), groupsList);
        recycler.setAdapter(adapter);
    }
}
