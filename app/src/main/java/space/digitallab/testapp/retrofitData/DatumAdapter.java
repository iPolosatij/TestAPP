package space.digitallab.testapp.retrofitData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import space.digitallab.testapp.R;
import space.digitallab.testapp.SQLiteData.DBHelper;
import space.digitallab.testapp.SQLiteData.DBinterface;
import space.digitallab.testapp.retrofitData.Datum;

public class DatumAdapter extends ArrayAdapter <Datum> {
    private Context context;
    private List<Datum> values;
    private boolean b = true;

    public DatumAdapter(Context context, List<Datum> values) {
        super(context, R.layout.list_item, values);

        this.context = context;
        this.values = values;

    }
    public DatumAdapter(Context context, ArrayList<Datum> values) {
        super(context, R.layout.list_item, values);

        this.b = false;
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView textView = (TextView) row.findViewById(R.id.list_item_text);
        ImageView imageView = (ImageView)  row.findViewById(R.id.list_item_image);

        Datum item = values.get(position);
        if(b) {
            DBinterface dBinterface = new DBinterface(new DBHelper(context));
            dBinterface.dataItemSave(item);
        }
        String message = item.getFirstName() + " " + item.getLastName();
        textView.setText(message);
        String avatar = item.getAvatar();
        Picasso.with(context)
                .load(avatar)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView);

        return row;
    }
}
