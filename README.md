# AutoBanner
[![](https://jitpack.io/v/Jungle2329/AutoBanner.svg)](https://jitpack.io/#Jungle2329/AutoBanner)
[![996.icu](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu)

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2. Add the dependency
```
dependencies {
	implementation 'com.github.Jungle2329:AutoBanner:1.0.8'
}
```

使用示例
```
    private void initData() {
        String[] banners = new String[]{banner1, banner2, banner3, banner4};
        list = new ArrayList<>();
        for (String s : banners) {
            BannerData bd = new BannerData();
            bd.setImage(s);
            list.add(bd);
        }
        if(mAdapter == null) {
            //1.创建adapter
            mAdapter = new BannerAdapter(list);
            //2.初始化参数并设置adapter开始
            ab_banner.setAutoPlay(true)
                    .setScrollDuration(1000)
                    .setBannerAnimation(Transformer.Tablet)
                    .startWithAdapter(mAdapter);
            //3.把banner绑定到indecator上
            ab_banner_indicator.bindBanner(ab_banner);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
```
布局文件
```
    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="240dp">

        <com.junglezhang.autobannerlibrary.ui.AutoBanner
            android:id="@+id/ab_banner"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:ab_scroll_duration="1000" />			//设置viewpager的滚动时长

        <com.junglezhang.autobannerlibrary.ui.AutoBannerIndicator
            android:id="@+id/ab_banner_indicator"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="right|bottom"
            android:layout_margin="10dp"
            app:ab_disable_color="@color/colorPrimaryDark"	//指示器未选中时的颜色
            app:ab_enable_color="@color/colorPrimary"		//指示器选中时的颜色
            app:ab_point_gap="22dp"				//指示器点点间的
            app:ab_radio="4dp" />				//指示器点点半径
    </FrameLayout>
```

```
v1.0.7:
	1.修复圆弧在某些手机上的样式错误的问题
	2.增加了设置viewpager滚动时间的功能
v1.0.8:
	1.增加了ViewPager动画
	2.增加了支持自动滚动和不滚动的功能
```
