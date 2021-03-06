package com.example.b50i7d.shuvamjewellers;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

/**
 * Created by B50i7D on 9/2/2016.
 */
public class Total_activity extends AppCompatActivity {
    TextView name,address,date,order_date,pan_no,no,weight,wastage,rate,making,particular,total,discount;
    Button confirm;
    Dbhelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_layout);
        dbhelper = new Dbhelper(this);
        name = (TextView) findViewById(R.id.tname);
        pan_no = (TextView) findViewById(R.id.tpan_no);
        date = (TextView) findViewById(R.id.tdate);
        order_date = (TextView) findViewById(R.id.torder_date);
        particular = (TextView) findViewById(R.id.tparticular);
        weight = (TextView) findViewById(R.id.tweight);
        wastage = (TextView) findViewById(R.id.twastege);
        making = (TextView) findViewById(R.id.tmaking);
        rate = (TextView) findViewById(R.id.trate);
        total = (TextView) findViewById(R.id.ttotal);
        confirm = (Button) findViewById(R.id.confirm);
        discount = (TextView) findViewById(R.id.tdiscount);



        name.setText(getIntent().getStringExtra("names"));
        pan_no.setText(getIntent().getStringExtra("pan_nos"));
        date.setText(getIntent().getStringExtra("dates"));
        order_date.setText(getIntent().getStringExtra("order_dates"));
        particular.setText(getIntent().getStringExtra("particulars"));
        weight.setText(getIntent().getStringExtra("weights"));
        wastage.setText(getIntent().getStringExtra("wastages"));
        making.setText(getIntent().getStringExtra("makings"));
        rate.setText(getIntent().getStringExtra("rates"));
        total.setText(getIntent().getStringExtra("totals"));
        discount.setText(getIntent().getStringExtra("discounts"));
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confrimation_for_saving();
            }
        });
    }
    public void confrimation_for_saving(){
        String nameValue = name.getText().toString();
        String pan_noValue = pan_no.getText().toString();
        String dateValue = date.getText().toString();
        String order_dateValue = order_date.getText().toString();
        String particularValue = particular.getText().toString();
        String weightValue = weight.getText().toString();
        String wastageValue = wastage.getText().toString();
        String makingValue = making.getText().toString();
        String rateValue = rate.getText().toString();
        String totalValue = total.getText().toString();
        String discountValue = discount.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put("name",nameValue);
        cv.put("pan_no",pan_noValue);
        cv.put("date",dateValue);
        cv.put("order_date",order_dateValue);
        cv.put("particular",particularValue);
        cv.put("weight",weightValue);
        cv.put("wastage",wastageValue);
        cv.put("making",makingValue);
        cv.put("rate",rateValue);
        cv.put("total",totalValue);
        cv.put("discount",discountValue);

        Firebase.setAndroidContext(Total_activity.this);

        Firebase ref = new Firebase("https://shuvamjewelery.firebaseio.com/");

        UserInfo info = new UserInfo();
        info.name = getIntent().getStringExtra("names");
        info.no = getIntent().getStringExtra("nos");
        info.pan_no = getIntent().getStringExtra("pan_nos");
        info.date = getIntent().getStringExtra("dates");
        info.order_date = getIntent().getStringExtra("order_dates");
        info.particular = getIntent().getStringExtra("particulars");
        info.weight = getIntent().getStringExtra("weights");
        info.westage = getIntent().getStringExtra("wastages");
        info.making = getIntent().getStringExtra("makings");
        info.rate = getIntent().getStringExtra("rates");
        info.discount = getIntent().getStringExtra("discounts");
        ref.push().setValue(info);

        dbhelper.insertUser(cv);
        Toast.makeText(this,discountValue,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Total_activity.this,FinalBillActivity.class);
        intent.putExtra("names",nameValue);
        intent.putExtra("pan_no",pan_noValue);
        intent.putExtra("date",dateValue);
        intent.putExtra("order_date",order_dateValue);
        intent.putExtra("particular",particularValue);
        intent.putExtra("weight",weightValue);
        intent.putExtra("wastage",wastageValue);
        intent.putExtra("making",makingValue);
        intent.putExtra("rate",rateValue);
        intent.putExtra("total",totalValue);
        intent.putExtra("discounts",discountValue);
        intent.putExtra("no",getIntent().getStringExtra("nos"));
        startActivity(intent);
    }
}
