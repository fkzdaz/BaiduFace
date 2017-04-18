package com.example.fang.baiduface;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.baidu.aip.face.AipFace;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import static com.example.fang.baiduface.R.id.img;

public class MainActivity extends AppCompatActivity {

    public static final String APP_ID = "9532750";
    public static final String API_KEY = "spWwHVYi35mBtlAOGuCFoqHE";
    public static final String SECRET_KEY = "W34wKtq5AYeKrbmvOf03UcuiZC1XfuSi";

    private Button btn;
    private FaceView faceView;
    private AipFace client;
    private Handler myHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initDetect();
        initHandler();
    }

    private void initHandler() {
        myHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Rect rect = (Rect) msg.obj;
                faceView.drawFace(rect);
                return  true;
            }
        });
    }

    private void initDetect() {
        client=new AipFace(APP_ID,API_KEY,SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }

    private void initView() {
        btn= (Button) findViewById(R.id.btn);
        faceView= (FaceView) findViewById(img);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detect();
            }
        });
    }

    private void detect() {
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.raw.dili1);
        final byte[] imgByte=Bitmap2Byte(bitmap);
        final HashMap<String,String> paraMap=new HashMap<String, String>();
        paraMap.put("face_fields","age,beauty,expression,faceshape,gender,glasses,landmark,race,qualities");


        new Thread(new Runnable() {
            @Override
            public void run() {
//                JSONObject res=client.detect(imgByte,paraMap);
//                Log.e("MainActivity",res.toString());
                Rect r=new Rect((int)(117/1.5f),(int)(127/1.5f),(int)((117+207)/1.5f),(int)((127+194)/1.5f));
                Message m=Message.obtain();
                m.obj=r;
                myHandler.sendMessage(m);
            }
        }).start();
    }

    private byte[] Bitmap2Byte(Bitmap bitmap) {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        return baos.toByteArray();
    }
}
