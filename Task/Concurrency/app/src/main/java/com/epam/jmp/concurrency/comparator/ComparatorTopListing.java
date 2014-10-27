package com.epam.jmp.concurrency.comparator;

import com.epam.jmp.concurrency.data.Listing;

import java.util.Comparator;
import java.util.List;

/**
 * Created by sergey on 27.10.2014.
 */
public class ComparatorTopListing implements Comparator<Listing> {

    @Override
    public int compare(Listing listingF, Listing listingS) {
        int avgF = listingF.getRatio().getValue();
        int avgS = listingS.getRatio().getValue();
        if (avgF < avgS) {
            return 1;
        }
        if (avgF == avgS) {
            return 0;
        }
        return -1;
    }

    public static int getAvg(List<Listing> listings) {
        int size = listings.size();
        if (size == 0) {
            return 0;
        }
        int avg = 0;
        for (int i = 0; i < size; i++) {
            avg += listings.get(i).getRatio().getValue();
        }
        return avg / size;
    }
}
