package org.longvuong.qlfile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.longvuong.qlfile.data.IShapeManager;
import org.longvuong.qlfile.data.ShapeManager;
import org.longvuong.qlfile.data.model.Circle;
import org.longvuong.qlfile.data.model.Rect;
import org.longvuong.qlfile.data.model.Triangle;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public Button  btnDisplay;
    EditText myInputText;
    TextView responseText;

    private String filename = "input.txt";
    private String filepath = "vncoder.vm";
    File myInternalFile;

    IShapeManager shapeManager = new ShapeManager();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ContextWrapper contextWrapper = new ContextWrapper(
                getApplicationContext());
        File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
        myInternalFile = new File(directory, filename);

    }
    private void initView() {
        myInputText = (EditText) findViewById(R.id.myInputText);
        responseText = (TextView) findViewById(R.id.responseText);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);
        btnDisplay.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        readShapeList();
    }

    private void readShapeList() {
        AssetManager assetManager = getAssets();

        try {
            // Open input.txt for reading
            InputStream inputStream = assetManager.open(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            // Read each line and append it to the StringBuilder
            while ((line = reader.readLine()) != null) {
                if(line.startsWith("#rect")){
                    readRect(line);
                }
                if(line.startsWith("#circle")){
                    readCircle(line);
                }
                if(line.startsWith("#triangle")){
                    readTriangle(line);
                }
            }
            // Display the content of input.txt in the TextView
            // Close the InputStream and BufferedReader
            inputStream.close();
            reader.close();
        } catch (IOException e) {
            // Handle errors
            e.printStackTrace();
        }
    }

    private void readCircle(String line) {
        String arr[] = line.split(" ");
        double radius = Double.parseDouble(arr[1]);
        shapeManager.add(new Circle(radius));
    }

    private void readRect(String line) {
        String arr[] = line.split(" ");
        double a = Double.parseDouble(arr[1]);
        double b = Double.parseDouble(arr[2]);
        shapeManager.add(new Rect(a, b));
    }
    private void readTriangle(String line) {
        String arr[] = line.split(" ");
        double a = Double.parseDouble(arr[1]);
        double b = Double.parseDouble(arr[2]);
        double c = Double.parseDouble(arr[3]);
        shapeManager.add(new Triangle(a, b, c));

    }
}