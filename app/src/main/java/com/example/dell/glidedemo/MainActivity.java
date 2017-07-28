package com.example.dell.glidedemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView lv;
    private List<ImageView> list = new ArrayList<ImageView>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int resourId = R.mipmap.ic_launcher;
        lv = (ListView) findViewById(R.id.lv);
        //本地文件
        ImageView imageView = new ImageView(this);
        Glide.with(this)
                .load(resourId)
                .asBitmap()
                .priority( Priority.LOW )
                .animate(R.anim.asc)
                .into(imageView);
        list.add(imageView);
        //网络文件
        ImageView imageViewNet = new ImageView(this);
        Glide.with(this).load("https://img01.sogoucdn.com/net/a/04/link?url=http%3A%2F%2Fimg01.sogoucdn.com%2Fapp%2Fa%2F100520093%2Fe18d20c94006dfe0-2857e2f09ca9e0a0-ff744a80cb9c23b25b6e122084d88dfc.jpg&appid=122")
                .animate(R.anim.asc)
                .priority(Priority.HIGH)
                .into(imageViewNet);
        list.add(imageViewNet);
        //内存卡文件
        ImageView imageViewSd = new ImageView(this);
        File file = new File(Environment.getExternalStorageDirectory(),"b.png");

        Glide.with(this).load(file).animate(R.anim.asc).priority( Priority.LOW ).into(imageViewSd);
        list.add(imageViewSd);
        //视频第一帧
        ImageView imageViewVideo = new ImageView(this);
        File filev = new File(Environment.getExternalStorageDirectory(),"test1.mp4");
        Glide.with(this).load(filev).priority( Priority.LOW ).animate(R.anim.asc).into(imageViewVideo);
        list.add(imageViewVideo);
        //加载动图
        ImageView imageViewGif = new ImageView(this);
        Glide.with(this).load("https://img02.sogoucdn.com/net/a/04/link?url=http%3A%2F%2Fi02.pictn.sogoucdn.com%2F45a978c6bd92b431&appid=122").priority(Priority.LOW).animate(R.anim.asc).into(imageViewGif);
        Glide.with(this).load("https://img02.sogoucdn.com/net/a/04/link?url=http%3A%2F%2Fi02.pictn.sogoucdn.com%2F45a978c6bd92b431&appid=122").asGif().animate(R.anim.asc).priority(Priority.LOW).into(imageViewGif);
        list.add(imageViewGif);

        lv.setAdapter(new MyAdapter());

    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return list.get(position);
        }
    }
}
