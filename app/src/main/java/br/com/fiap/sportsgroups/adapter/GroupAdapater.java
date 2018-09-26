package br.com.fiap.sportsgroups.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.fiap.sportsgroups.MyLongClickListener;
import br.com.fiap.sportsgroups.R;
import br.com.fiap.sportsgroups.fragment.GroupFragment;
import br.com.fiap.sportsgroups.model.Group;
import br.com.fiap.sportsgroups.viewholder.GroupViewHolder;

public class GroupAdapater extends RecyclerView.Adapter<GroupViewHolder>{
    private Context context;
    private ArrayList<Group> groupsList;
    int selectedPos;
    GroupFragment fragment;

    public GroupAdapater(Context context, ArrayList<Group> groupsList) {
        this.context = context;
        this.groupsList = groupsList;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.group_item, parent, false);
        GroupViewHolder vh = new GroupViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final GroupViewHolder holder, int position) {
        Group group = groupsList.get(position);

        holder.name.setText( group.getName() );
        holder.description.setText( group.getDescription() );

        holder.btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();

                if (pos != RecyclerView.NO_POSITION) {
                    deleteItem(pos);
                }
            }
        });

        holder.setLongClickListener(new MyLongClickListener() {
            @Override
            public void onLongClick(int pos) {
                selectedPos = pos;
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupsList != null? groupsList.size() : 0;
    }

    public void deleteItem(int position) {
        Group group = groupsList.get(position);

        if(groupsList.remove(group)) {
            Toast.makeText(context,"O grupo " + group.getName() + " foi removido com sucesso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Não foi possível remover o grupo" + group.getName(), Toast.LENGTH_SHORT).show();
        }

        this.notifyItemRemoved(position);
    }
}