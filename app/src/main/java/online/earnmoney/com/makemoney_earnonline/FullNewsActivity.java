package online.earnmoney.com.makemoney_earnonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FullNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);
        Bundle b = this.getIntent().getExtras();
        NewsClass newsClass;
        if (b != null)
             newsClass = b.getParcelable(ConstantUtils.INTENT_OBJECT);

    }
}
