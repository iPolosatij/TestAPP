package space.digitallab.testapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class UserCardAct extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_card);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        String image = getIntent().getStringExtra("picture");
        String email = getIntent().getStringExtra("email");

        textView.setText(email);

        Picasso.with(this)
                .load(image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView);
    }
}
