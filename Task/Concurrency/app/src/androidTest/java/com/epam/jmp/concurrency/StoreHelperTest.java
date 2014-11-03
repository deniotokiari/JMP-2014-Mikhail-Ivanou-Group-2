package com.epam.jmp.concurrency;

import android.test.InstrumentationTestCase;

import com.epam.jmp.concurrency.data.Channel;
import com.epam.jmp.concurrency.data.Listing;
import com.epam.jmp.concurrency.data.Ratio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sergey on 03.11.2014.
 */
public class StoreHelperTest extends InstrumentationTestCase {

    private StoreHelper mHelper;
    private String mResult1;
    private String mResult2;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mHelper = new StoreHelper();
        mHelper.setChannels(fillChannels());
        mHelper.setListings(fillListings());
        mHelper.setRatios(fillRatios());
        try {
            JSONObject jo = new JSONObject(readAssets("result.json"));
            mResult1 = jo.getString("result1");
            mResult2 = jo.getString("result2");
        } catch (Exception e) {
        }
    }

    private ConcurrentHashMap<Integer, Ratio> fillRatios() {
        ConcurrentHashMap<Integer, Ratio> ratios = new ConcurrentHashMap<Integer, Ratio>();
        try {
            String ratiosString = readAssets("ratios.json");
            JSONObject j = new JSONObject(ratiosString);
            JSONArray ja = j.getJSONArray("ratios");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = (JSONObject) ja.get(i);
                int id = jo.getInt("listingId");
                Ratio ratio = new Ratio(id, jo.getInt("rate"));
                ratios.put(id, ratio);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ratios;
    }

    private ConcurrentHashMap<Integer, Listing> fillListings() {
        ConcurrentHashMap<Integer, Listing> listings = new ConcurrentHashMap<Integer, Listing>();
        try {
            String listingsString = readAssets("listings.json");
            JSONObject j = new JSONObject(listingsString);
            JSONArray ja = j.getJSONArray("listings");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = (JSONObject) ja.get(i);
                int id = jo.getInt("id");
                Listing listing = new Listing(id, jo.getInt("channelId"), jo.getString("title"));
                listings.put(id, listing);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listings;
    }

    private ConcurrentHashMap<Integer, Channel> fillChannels() {
        ConcurrentHashMap<Integer, Channel> channels = new ConcurrentHashMap<Integer, Channel>();
        try {
            String channelsString = readAssets("channels.json");
            JSONObject j = new JSONObject(channelsString);
            JSONArray ja = j.getJSONArray("channels");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = (JSONObject) ja.get(i);
                int id = jo.getInt("id");
                Channel channel = new Channel(id, jo.getString("title"), jo.getString("desc"), "");
                channels.put(id, channel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return channels;
    }

    public void testCreate() {
        ConcurrentHashMap<Integer, Channel> channels = mHelper.getChannels();
        ConcurrentHashMap<Integer, Listing> listings = mHelper.getListings();
        ConcurrentHashMap<Integer, Ratio> ratios = mHelper.getRatios();

        assertNotNull("Channels is null", channels);
        assertNotNull("Listings is null", listings);
        assertNotNull("Ratios is null", ratios);

        List<Channel> allChannels = mHelper.getAllChannels();
        assertNotNull("AllChannels is null", allChannels);
        assertEquals("Size channels is not equals", allChannels.size(), channels.size());
        for (int i = 0; i < allChannels.size(); i++) {
            Channel channel = allChannels.get(i);
            int id = channel.getId();
            Channel channelById = channels.get(id);
            assertNotNull("Channel is not found by id", channelById);
            assertEquals("Titles of channels is not equals", channelById.getTitle(), channel.getTitle());
            assertEquals("Descriptions of channels is not equals", channelById.getDesc(), channel.getDesc());
        }

        List<Channel> topChannels = mHelper.getTopChannels();
        assertNotNull("TopChannels is null", topChannels);
        assertEquals("Size TopChannels is not equals " + Constants.COUNT_TOP_CHANNELS, topChannels.size(), Constants.COUNT_TOP_CHANNELS);
        assertEquals("Result is not corrected", mResult1, Arrays.toString(topChannels.toArray()));

        List<Listing> topListings = mHelper.getTopListings();
        assertNotNull("TopListings is null", topListings);
        assertEquals("Size TopListings is not equals " + Constants.COUNT_TOP_LISTINGS, topListings.size(), Constants.COUNT_TOP_LISTINGS);
        assertEquals("Result is not corrected", mResult2, Arrays.toString(topListings.toArray()));
    }

    private String readAssets(String file) throws IOException {
        StringBuilder buf = new StringBuilder();
        InputStream json = getInstrumentation().getContext().getAssets().open(file);
        BufferedReader in =
                new BufferedReader(new InputStreamReader(json, "UTF-8"));
        String str;

        while ((str = in.readLine()) != null) {
            buf.append(str);
        }

        in.close();
        return buf.toString();
    }
}
