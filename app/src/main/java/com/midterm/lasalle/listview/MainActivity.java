package com.midterm.lasalle.listview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import model.Country;

// selecting data from a list
// 1) The interface -  onItemClickListener, OnItemClick
// 2) The interface - OnItemLongClickListener, OnItemLongClick
public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener,
        DialogInterface.OnClickListener {


    EditText cName, cCapital;
    Button addButton, sortButton;
    ListView listView;

    ArrayList<Country> listOfCountries = new ArrayList<>();
    ArrayAdapter<Country> countryAdapter;

    AlertDialog.Builder alertDialog;

    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {

        cName = findViewById(R.id.cName);
        cCapital = findViewById(R.id.cCapital);
        listView = findViewById(R.id.list);
        addButton = findViewById(R.id.addButton);
        sortButton = findViewById(R.id.sortButton);
        addButton.setOnClickListener(this);
        sortButton.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        //1. Create a dataSource
        Country country1 = new Country("France","Paris"),
                country2 = new Country("India", "Delhi"),
                country3 = new Country("Maroc", "Rabat");

        listOfCountries.add(country1);
        listOfCountries.add(country2);
        listOfCountries.add(country3);

        // 2- create and initialize the adapter
        // this is default
//        countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfCountries);
        //this is custom
        countryAdapter = new ArrayAdapter<>(this,R.layout.one_item, listOfCountries);


        // 3- set the adapter to listview
        listView.setAdapter(countryAdapter);

        alertDialog= new AlertDialog.Builder(this);
        alertDialog.setTitle("Delete!");
        alertDialog.setMessage("Do you want to delete?");
        alertDialog.setPositiveButton("Yes",this);
        alertDialog.setNegativeButton("No", this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.addButton:
                Country c = new Country(cName.getText().toString(), cCapital.getText().toString());
                listOfCountries.add(c);
                countryAdapter.notifyDataSetChanged();
                break;
            case R.id.sortButton:
                Collections.sort(listOfCountries);
                countryAdapter.notifyDataSetChanged();
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String countryName = listOfCountries.get(position).getcName();
        String countryCapital = listOfCountries.get(position).getcCapital();
        cName.setText(countryName);
        cCapital.setText(countryCapital);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        // true : manage a long click + a click
        // false: manage only a long click
        currentPosition = position;
        AlertDialog dialogBox = alertDialog.create();
        dialogBox.show();
        return false;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case Dialog.BUTTON_POSITIVE:
                listOfCountries.remove(currentPosition);
                countryAdapter.notifyDataSetChanged();
                break;
            case Dialog.BUTTON_NEGATIVE:
                break;

        }
    }
}
