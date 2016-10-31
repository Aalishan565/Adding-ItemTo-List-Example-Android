package com.addingitemtolistpoc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEtSearch;
    private Button mBtnAdd;
    private Button mBtnDelete;
    private EditText mEtText;
    private ListView mLvItems;
    String textToAdd = "Hi";
    ArrayAdapter mAdapter;
    ArrayList mArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mArrayList = new ArrayList();
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mEtText = (EditText) findViewById(R.id.et_item_to_add);
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mLvItems = (ListView) findViewById(R.id.lv_itmes);
        mBtnAdd.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, mArrayList);
        mLvItems.setAdapter(mAdapter);
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = mEtSearch.getText().toString().toLowerCase(Locale.getDefault());
                mAdapter.getFilter().filter(text);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
               /* textToAdd = mEtText.getText().toString();
                mEtText.setText("");
                mArrayList.add(textToAdd);
                mAdapter.notifyDataSetChanged();*/
                if (mEtText.getText().toString().isEmpty()) {
                    Toast.makeText(this, "add somting to list ", Toast.LENGTH_LONG).show();
                } else {
                    mArrayList.add(mEtText.getText().toString());
                    mAdapter.notifyDataSetChanged();
                    mEtText.setText("");
                }
                break;
            case R.id.btn_delete:
                SparseBooleanArray checkedItemPositions = mLvItems.getCheckedItemPositions();
                int itemCount = mLvItems.getCount();

                for (int i = itemCount - 1; i >= 0; i--) {
                    if (checkedItemPositions.get(i)) {
                        mAdapter.remove(mArrayList.get(i));
                    }
                }
                checkedItemPositions.clear();
                mAdapter.notifyDataSetChanged();
                break;

        }

    }
}
