package com.matigurten.tom.remotecontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Mati on 09/01/2017.
 */

public class AboutActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_about);

        TextView gitHub = (TextView) findViewById(R.id.aboutText);
        gitHub.setClickable(true);
        gitHub.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://github.com/fima85/RemoteControl'>Join us on GitHub!</a>";
        gitHub.setText(Html.fromHtml(text));
        gitHub.setTextSize(30);
        gitHub.setFont
        gitHub.setGravity(Gravity.CENTER);

        TextView participants = (TextView) findViewById(R.id.participantsText);
        participants.setClickable(true);
        participants.setMovementMethod(LinkMovementMethod.getInstance());
        String android = "Android \n\t\t\tfima85@gmail.com\n\t\t\tmatigurten@gmail.com";
        String solidWorks = "Solid \n\t\t\tnvinshtokmelnik@gmail.com\n\t\t\tyonatan.schachter@gmail.com\n\t\t\tmaorfarid@gmail.com";
        String arduino = "Arduino \n\t\t\tidantim@gmail.com\n\t\t\teffiha@netvision.net.il";
        participants.setText(android + "\n\n" + solidWorks + "\n\n" + arduino);
        participants.setTextSize(20);
        participants.setGravity(Gravity.LEFT);
    }
}
