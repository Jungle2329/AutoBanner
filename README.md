# AutoBanner
[![](https://jitpack.io/v/Jungle2329/AutoBanner.svg)](https://jitpack.io/#Jungle2329/AutoBanner)

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
	implementation 'com.github.Jungle2329:AutoBanner:1.0.4'
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
            mAdapter = new BannerAdapter(list);
            ab_banner.setBannerAdapter(mAdapter);
            ab_banner_indicator.bindBanner(ab_banner);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
```
