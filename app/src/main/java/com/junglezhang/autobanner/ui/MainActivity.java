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


        String banner1 = "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3609026444,888457979&fm=173&app=25&f=JPEG?w=580&h=326&s=72B11DC7406A7F114B3E18BA03001018";
        String banner2 = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4109200045,2976713416&fm=11&gp=0.jpg";
        String banner3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544444887665&di=3f2f7bf615b814eb9f3a55c57a107a09&imgtype=0&src=http%3A%2F%2F03imgmini.eastday.com%2Fmobile%2F20181108%2F20181108210857_0a78b3f6c6179087749434b4fc92ad82_1.jpeg";
        String banner4 = "https://raw.githubusercontent.com/JessYanCoding/ArmsComponent/master/arts/arms_component_banner_v1.0.jpg";
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