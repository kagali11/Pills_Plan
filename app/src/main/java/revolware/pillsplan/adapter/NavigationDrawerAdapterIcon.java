package revolware.pillsplan.adapter;

//skopirovany z NavigationDrawerAdapter

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import revolware.pillsplan.R;
import revolware.pillsplan.model.NavDrawerItem;

import java.util.Collections;
import java.util.List;

import revolware.pillsplan.R; // toto tu nebolo

public class NavigationDrawerAdapterIcon extends RecyclerView.Adapter<NavigationDrawerAdapterIcon.MyViewHolder> {
    List<NavigationDrawerAdapterIcon> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public NavigationDrawerAdapterIcon(Context context, List<NavigationDrawerAdapterIcon> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavigationDrawerAdapterIcon current = data.get(position);
       // holder.icon.setImageDrawable(current.get());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}
