package com.nexdev.enyason.nestedrv.Model;

import java.util.ArrayList;
import java.util.List;

public class FakeDataGenerator {

    public static List<HomeWrapper> getHomePageData(int limit) {
        List<HomeWrapper> item = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            if (i % 2 == 0) {
                item.add(new HomeWrapper(RecylerViewType.VIEW_BANNER, new Banner(0)));
            } else {
                item.add(new HomeWrapper(RecylerViewType.VIEW_MOVIE, new Movie("Movie_" + i)));
            }
        }
        return item;
    }
}
