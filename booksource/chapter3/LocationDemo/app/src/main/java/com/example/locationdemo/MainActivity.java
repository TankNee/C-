package com.example.locationdemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.baidu.location.Address;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public LocationClient mLocationClient;
    private TextView positionText;
    private Button btn_send;
    //private TextView responseview;
    public Context context;
    public BDAbstractLocationListener myListener = new MyLocationListener();
    private int tempNumber=24;
    private int timeNumber=0;
    private int modeCode=0;
    private int resendcode=0;
    private int AiOrNot=1;
    private String temperature;
    private int mHour;
    private int mMin;
    private String sendTemp="24";
    private String sendMessage;
    private String model="";
    private int bit_2;
    private Button button_yes;
    private Button button_temp_up;
    private Button button_temp_down;
    private Button button_time_up;
    private Button button_time_down;
    private Button button_ice;
    private Button button_hot;
    private Button button_connect;
    private Button button_disconnect;
    private Button button_AImode;
    private Spinner spinner;
    private List<String> data;
    private ArrayAdapter<String> arrayAdapter;
    private ImageView imageView_time_1;
    private ImageView imageView_temp_2;
    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private String receiveString = "";
    private StringBuffer stringBuffer;
    private TextView testview;
    private static final String HOST = "120.79.164.46";
    private static final int PORT = 10085;
    public static String WeatherURL = "http://wthrcdn.etouch.cn/weather_mini?city=";
    public final Calendar mcalendar = Calendar.getInstance();
    public long time = System.currentTimeMillis();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            testview.setText(stringBuffer.append(receiveString+"\n"));
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about_item:
                //TODO
                resendcode=1;
                Toast.makeText(MainActivity.this,"回调准备数据已经准备好了",Toast.LENGTH_SHORT).show();
                break;
            case R.id.reset_item:
                timeNumber = 0;
                tempNumber = 24;
                resendcode=0;
                changeTemp();
                changTime();
                testview.setText("");
                Toast.makeText(MainActivity.this,"unfinished",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        //positionText =  findViewById(R.id.position_text_view);
        //btn_send = findViewById(R.id.send_request);
        //responseview = findViewById(R.id.response_text);
        button_yes=findViewById(R.id.btn_yes);
        button_temp_up=findViewById(R.id.btn_temp_up);
        button_temp_down=findViewById(R.id.btn_temp_down);
        button_time_up=findViewById(R.id.btn_time_up);
        button_time_down=findViewById(R.id.btn_time_down);
        button_ice=findViewById(R.id.icemode);
        button_hot=findViewById(R.id.hotmode);
        button_connect=findViewById(R.id.btn_connect);
        button_disconnect=findViewById(R.id.btn_disconnect);
        button_AImode=findViewById(R.id.AImode);
        imageView_temp_2=findViewById(R.id.img_temp_2);
        imageView_time_1=findViewById(R.id.img_time_1);
        testview = findViewById(R.id.testView);
        spinner = findViewById(R.id.spinner1);
        data = new ArrayList<>();
        data.add("海尔");
        data.add("美的");
        data.add("格力");
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        mcalendar.setTimeInMillis(time);
        mHour=mcalendar.get(Calendar.HOUR);
        mMin=mcalendar.get(Calendar.MINUTE);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {//parent=adapterView
                switch (i){
                    case 0:
                        model=String.valueOf(0);
                        break;
                    case 1:
                        model=String.valueOf(1);
                        break;
                    case 2:
                        model=String.valueOf(2);
                        break;
                    default:
                        break;
                }
                Toast.makeText(MainActivity.this,adapterView.getItemAtPosition(i).toString()+i,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        button_AImode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AiOrNot=2;
                new Thread(new SendRun()).start();
                sendMessage=model+modeCode+sendTemp+String.valueOf(mHour+timeNumber)+String.valueOf(mMin);
                //testview.setText(mMin+"  "+mHour+"  "+sendMessage);
                //sendRequestWithOkHttp();
            }
        });
        button_temp_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempNumber++;
                changeTemp();
                sendTemp=String.valueOf(tempNumber);
                Toast.makeText(MainActivity.this,"当前温度是："+sendTemp+"摄氏度",Toast.LENGTH_SHORT).show();
            }
        });
        button_temp_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempNumber--;
                changeTemp();
                sendTemp=String.valueOf(tempNumber);
                Toast.makeText(MainActivity.this,"当前温度是："+sendTemp+"摄氏度",Toast.LENGTH_SHORT).show();
            }
        });
        button_ice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeCode=0;
                Toast.makeText(MainActivity.this,"制冷",Toast.LENGTH_SHORT).show();
            }
        });
        button_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeCode=1;
                Toast.makeText(MainActivity.this,"制热",Toast.LENGTH_SHORT).show();
            }
        });
        button_time_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeNumber++;
                changTime();
            }
        });
        button_time_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeNumber--;
                changTime();
            }
        });
        button_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendYesMessage("10086","1531");
                AiOrNot=1;
                new Thread(new SendRun()).start();
                sendTemp=String.valueOf(tempNumber);
                sendMessage=String.valueOf(timeNumber);
                if(modeCode==0){
                    Toast.makeText(MainActivity.this,"温度："+sendTemp+"摄氏度"+"\n"+"制冷模式"+"\n"+sendMessage+"小时后开始工作"+"\n",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"温度："+sendTemp+"摄氏度"+"\n"+"制热模式"+"\n"+sendMessage+"小时后开始工作"+"\n",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new ConnectRun()).start();
            }
        });
        button_disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new disconnectRun()).start();
            }
        });
        List <String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String[]permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
        }else {
            requestLocation();
        }
    }
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(WeatherURL)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);
                    //showResponse(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                testview.setText(response);
            }
        });
    }
    private void parseJSONWithJSONObject(String jsonData){
        try{
            JSONObject response = new JSONObject(jsonData);
            JSONObject data = response.getJSONObject("data");
            String wendu = data.getString("wendu");
            temperature=wendu;
            //showResponse(wendu);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void requestLocation(){
        initLocation();
        mLocationClient.start();
    }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions , int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length > 0){
                    for(int result : grantResults){
                        if(result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意所有权限才可使用本程序",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else {
                    Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
                default:
        }
    }
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //initLocation();
            String City = null;
            String add = null;
            StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("纬度：").append(location.getLatitude()).append("\n");
            currentPosition.append("经度：").append(location.getLongitude()).append("\n");
            currentPosition.append("定位方式:");
            if(location.getLocType() == BDLocation.TypeGpsLocation){
                currentPosition.append("GPS\n");
            }else if(location.getLocType() == BDLocation.TypeNetWorkLocation){
                currentPosition.append("网络\n");
            }

            City=location.getCity();
            add=location.getAddrStr();
            //CITY=add;
            //URL=URL+CITY;
            WeatherURL = WeatherURL+City.replace("市","");
            add=add.replace("中国","");
            currentPosition.append("城市：").append(City).append("\n");
            currentPosition.append("地址：").append(add).append("\n");
            testview.setText(currentPosition);
        }
    }
    private void changeTemp(){

        if(tempNumber<20){
            tempNumber=20;
        }
        else if(tempNumber>29){
            tempNumber=29;
        }
        bit_2=tempNumber%10;
        switch (bit_2){
            case 0:
                imageView_temp_2.setImageResource(R.drawable.zero);
                break;
            case 1:
                imageView_temp_2.setImageResource(R.drawable.one);
                break;
            case 2:
                imageView_temp_2.setImageResource(R.drawable.two);
                break;
            case 3:
                imageView_temp_2.setImageResource(R.drawable.three);
                break;
            case 4:
                imageView_temp_2.setImageResource(R.drawable.four);
                break;
            case 5:
                imageView_temp_2.setImageResource(R.drawable.five);
                break;
            case 6:
                imageView_temp_2.setImageResource(R.drawable.six);
                break;
            case 7:
                imageView_temp_2.setImageResource(R.drawable.seven);
                break;
            case 8:
                imageView_temp_2.setImageResource(R.drawable.eight);
                break;
            case 9:
                imageView_temp_2.setImageResource(R.drawable.nine);
                break;
            default:
                break;
        }
    }
    private void changTime(){

        if(timeNumber>9){
            timeNumber=9;
        }
        else if(timeNumber<0) {
            timeNumber=0;
        }
        switch (timeNumber){
            case 0:
                imageView_time_1.setImageResource(R.drawable.zero);
                break;
            case 1:
                imageView_time_1.setImageResource(R.drawable.one);
                break;
            case 2:
                imageView_time_1.setImageResource(R.drawable.two);
                break;
            case 3:
                imageView_time_1.setImageResource(R.drawable.three);
                break;
            case 4:
                imageView_time_1.setImageResource(R.drawable.four);
                break;
            case 5:
                imageView_time_1.setImageResource(R.drawable.five);
                break;
            case 6:
                imageView_time_1.setImageResource(R.drawable.six);
                break;
            case 7:
                imageView_time_1.setImageResource(R.drawable.seven);
                break;
            case 8:
                imageView_time_1.setImageResource(R.drawable.eight);
                break;
            case 9:
                imageView_time_1.setImageResource(R.drawable.nine);
                break;
            default:
                break;
        }
    }
    public void receviceMsg(){
        stringBuffer = new StringBuffer();
        while (true){
            try {
                if(!IsEntityUtils.isEntityString(receiveString=bufferedReader.readLine())){
                    handler.sendEmptyMessage(1);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    /**
     * 建立连接
     */
    class ConnectRun implements Runnable {
        @Override
        public void run() {
            try {
                socket = new Socket(HOST, PORT);
                printWriter = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                receviceMsg();
            } catch (IOException e) {
                Log.e("111", e.toString());
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送信息
     */
    class SendRun implements Runnable {
        @Override
        public void run() {
            sendRequestWithOkHttp();
            while (temperature==null){
                sendRequestWithOkHttp();
            }
            //发送的代码组成
            sendMessage=AiOrNot+model+modeCode+sendTemp+String.valueOf(timeNumber)+String.valueOf(temperature);
            //最前面的1是为了让sendmessage的int化强制类型转换和string相同
            //mHour mMin是打开应用的时间
            //model是设备的品牌0海尔，1美的，2格力
            //modecode是空调模式0制冷，1制热
            //sendTemp是发送的空调温度
            //temperature是实时获取的当地温度
            //timeNumber是预约时间--即x小时之后开始工作
            if(resendcode==1){
                sendMessage="1023";
                resendcode=0;
            }
            printWriter.println(sendMessage);
        }
    }

    /**
     * 断开连接
     */
    class disconnectRun implements Runnable {
        @Override
        public void run() {
            printWriter.println("1314520");
            if (printWriter!=null){
                printWriter.close();
            }
            if (bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
