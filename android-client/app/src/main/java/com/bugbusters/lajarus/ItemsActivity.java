package com.bugbusters.lajarus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.bugbusters.lajarus.adapter.ItemsAdapter;
import com.bugbusters.lajarus.entity.Item;
import com.bugbusters.lajarus.entity.ItemType;
import com.bugbusters.lajarus.entity.Pair;
import com.bugbusters.lajarus.entity.Player;
import com.bugbusters.lajarus.entity.Session;
import com.bugbusters.lajarus.manager.SessionManager;
import com.bugbusters.lajarus.util.Config;
import com.bugbusters.lajarus.util.HttpRequestDispatcher;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    private Button backButton;
    private ListView itemsListView;
    private List<Item> items;
    private ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        items = new ArrayList<>();

        initBackButton();
        initItemsList();
        populateItemsList();
    }

    private void initBackButton(){
        backButton = (Button) findViewById(R.id.itemsBackButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent toy = new Intent(ItemsActivity.this, MapActivity.class);
                startActivity(toy);
            }
        });
    }

    private void initItemsList() {
        itemsListView = (ListView) findViewById(R.id.itemsListView);
        itemsAdapter = new ItemsAdapter(getApplicationContext(),
                R.layout.item_list_row, items);
        itemsListView.setAdapter(itemsAdapter);
    }

    private void populateItemsList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Session session = SessionManager.getInstance().getActiveSession();
                    Player player = session.getActivePlayer();
                    String playerName = player.getName();
                    String url = Config.getHttpURL().concat("player/name/").concat(playerName);
                    JSONObject response = HttpRequestDispatcher.performGET(url, new ArrayList<Pair>(), session.getToken());
                    JSONArray jsonItems = response.getJSONArray("inventory");

                    for(int i=0; i<jsonItems.length(); i++) {
                        JSONObject jsonItem = jsonItems.getJSONObject(i);
                        Item item = new Item(jsonItem.getLong("id"));
                        item.setName(jsonItem.getString("name"));
                        item.setType(getItemType(jsonItem.getString("type")));
                        item.setValue(jsonItem.getDouble("value"));
                        item.setPrice(jsonItem.getDouble("price"));
                        item.setDescription(jsonItem.getString("description"));
                        items.add(item);
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            itemsAdapter.notifyDataSetChanged();
                        }
                    });
                } catch(Exception e) {
                    Log.e("ItemsActivity", e.toString());
                }
            }
        }).start();
    }

    private ItemType getItemType(String type) {
        if(type.equals("Defence")) {
            return ItemType.DEFENCE;
        } else if(type.equals("Attack")) {
            return ItemType.ATTACK;
        }

        return ItemType.UNKNOWN;
    }
}
