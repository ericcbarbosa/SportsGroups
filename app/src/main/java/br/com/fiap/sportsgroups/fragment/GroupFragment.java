package br.com.fiap.sportsgroups.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.fiap.sportsgroups.GroupDetailActivity;
import br.com.fiap.sportsgroups.R;
import br.com.fiap.sportsgroups.adapter.GroupAdapater;
import br.com.fiap.sportsgroups.model.Group;
import br.com.fiap.sportsgroups.model.User;

//import br.com.fiap.sportsgroups.GroupDetailActivity;

public class GroupFragment extends Fragment {
    private ListView listview;
    private ArrayList<Group> groupsList;
    private ArrayList<User> membersA;
    private GroupAdapater adapter;
    View view;

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
//                displayDialog();
                break;
        }

        return true;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        membersA = new ArrayList<>();
        membersA.add(new User(
                1,
                "João",
                "joao@joao.com",
                "joao",
                "Rua jão, 100",
                "joaozinhos",
                "joaolandia",
                "JO"
        ));

        membersA.add(new User(
                2,
                "Maria",
                "maria@maria.com",
                "maria",
                "Rua maria, 100",
                "mariazinahs",
                "marialandia",
                "MA"
        ));


        groupsList = new ArrayList<>();
        groupsList.add(new Group(
                1,
                "Sparta",
                "Os matadores de sparta",
                membersA
        ));
        groupsList.add(new Group(
                1,
                "Atenas",
                "Os matadores de atenas",
                membersA
        ));
        groupsList.add(new Group(
                1,
                "Brazil",
                "Os matadores de brasil",
                membersA

        ));

        View view = inflater.inflate(R.layout.fragment_group, container, false);

        listview = view.findViewById(R.id.group_listview);
        btnFloatingRegister = view.findViewById(R.id.group_floating_register);

        adapter = new GroupAdapater(getActivity(), groupsList);
        listview.setAdapter(adapter);

        Toast.makeText(getActivity(), "Po, ta indo", Toast.LENGTH_LONG).show();

        // Detail group
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Position: " + position, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), GroupDetailActivity.class);

                // recupera dados a serem passados
                Group group = groupsList.get(position);

                // Enviando dados para conversa activity
                intent.putExtra("name", group.getName());
                intent.putExtra("description", group.getDescription());

                startActivity(intent);
            }
        });

        // Add group
        btnFloatingRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "clicou", Toast.LENGTH_LONG).show();
//                displayDialog();
            }
        });

        return view;
    }

//    public void displayDialog() {
//        final Dialog dialog = new Dialog(getContext());
//
//        dialog.setTitle("Cadastrar Grupo");
//        dialog.setContentView(R.layout.group_register);
//        dialog.setCancelable(true);
//
//        name = (EditText) dialog.findViewById(R.id.group_register_name);
//        description = (EditText) dialog.findViewById(R.id.group_register_description);
//
//        btnAddRegister = (Button) dialog.findViewById(R.id.user_register_btn_add);
//        btnCancel = (Button) dialog.findViewById(R.id.group_register_btn_cancel);
//
//        btnAddRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ArrayList<User> meGroup = new ArrayList<User>();
//                meGroup.add(new User(
//                        2,
//                        "Eu",
//                        "eu@eu.com",
//                        "eu",
//                        "Rua eu, 100",
//                        "euzinho",
//                        "eulandia",
//                        "EU"
//                ));
//
//                AddGroup(
//                        name.getText().toString(),
//                        description.getText().toString(),
//                        meGroup
//                );
//
//                dialog.dismiss();
//            }
//        });
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getGroupsList();
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }
//
//    public void AddGroup(String name, String description, ArrayList<User> members) {
//        Random rand = new Random();
//
//        groupsList.add( new Group(rand.nextInt(100) + 5, name, description, members) );
//        adapter.notifyDataSetChanged();
//    }
//
//    private void getGroupsList() {
//        adapter = new GroupAdapater(getContext(), groupsList);
//        listview.setAdapter(adapter);
//    }
}
