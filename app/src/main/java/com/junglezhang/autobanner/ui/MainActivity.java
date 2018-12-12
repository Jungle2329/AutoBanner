package com.junglezhang.autobanner.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.junglezhang.autobanner.R;
import com.junglezhang.autobannerlibrary.bean.BannerData;
import com.junglezhang.autobannerlibrary.ui.AutoBanner;
import com.junglezhang.autobannerlibrary.ui.AutoBannerIndicator;
import com.junglezhang.autobannerlibrary.ui.BaseInfinitePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jungle on 2018/12/12 0012.
 *
 * @desc TODO
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoBanner ab_banner = findViewById(R.id.ab_banner);
        AutoBannerIndicator ab_banner_indicator = findViewById(R.id.ab_banner_indicator);


        String banner1 = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3609500805,1555342859&fm=26&gp=0.jpg";
        String banner2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544591288175&di=d82793880e749f5c8992427887d26f5e&imgtype=0&src=http%3A%2F%2Fjtgeek.com%2Fwp-content%2Fuploads%2Fandroid-logo.jpg";
        String banner3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544591288175&di=f0a6a178e864815d98ada16756a4565f&imgtype=0&src=http%3A%2F%2Fwww.arinchina.com%2Fupload%2Fportal%2F201705%2F18%2F173937rzzhno9imm8iy8bw.jpg";
        String banner4 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544591288171&di=9af13bb975846efe92a43e8e68bf40d1&imgtype=0&src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F04%2F22%2F146126165500061409.PNG";
        String[] banners = new String[]{banner1, banner2, banner3, banner4};
        List<BannerData> list = new ArrayList<>();
        for (String s : banners) {
            BannerData bd = new BannerData();
            bd.setImage(s);
            list.add(bd);
        }

        ab_banner.setAdapterData(new BaseInfinitePagerAdapter<BannerData>(list) {
            @Override
            protected Object instantiateItemForce(ViewGroup container, int position, List<BannerData> list) {
                SimpleDraweeView sdv = new SimpleDraweeView(MainActivity.this);
                sdv.setImageURI(Uri.parse(list.get(position % list.size()).getImage()));
                sdv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                container.addView(sdv);
                return sdv;
            }
        }, list);
        ab_banner_indicator.bindBanner(ab_banner);

    }
}