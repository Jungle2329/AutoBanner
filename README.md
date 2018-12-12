# AutoBanner
使用示例
```
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
```
