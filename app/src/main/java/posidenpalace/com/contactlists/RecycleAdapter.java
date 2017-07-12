package posidenpalace.com.contactlists;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 7/11/2017.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{

    List<Person> peopleList = new ArrayList<>();
    public RecycleAdapter(List<Person> peopleList) {
        this.peopleList = peopleList;
    }




    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecycleAdapter.ViewHolder holder, int position) {
        final Person person = peopleList.get(position);
        holder.name.setText("Name: "+person.getName());
        holder.phone.setText("Phone: "+person.getPhone());
        if (person.getImageUri() != null) {
            holder.imageView.setImageURI(Uri.parse(person.ImageUri));
        }else{
            holder.imageView.setImageResource(R.drawable.default_image);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Contact.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("send",new Person(person.getName(),person.getPhone(),person.getImageUri()));
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG ="testing" ;
        private TextView name;
        private TextView phone;
        private ImageView imageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvListName);
            phone = (TextView) itemView.findViewById(R.id.tvListPhone);
            imageView = (ImageView) itemView.findViewById(R.id.ivListPicture);

        }
    }
}