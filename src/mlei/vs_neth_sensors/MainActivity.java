package mlei.vs_neth_sensors;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity implements OnItemClickListener{
	
	public static final String	ACTIVITY_TAG =	"qwer";

	private SensorManager sensMan;
	private List<Sensor> sensors;
	private List<String> sensorNames;
	private ListView listView;
	private ArrayAdapter arrayAdapter;
	private String debugtxt = "";
	private TextView txt;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txt = ((TextView) findViewById(R.id.textView1));
        Log.v(ACTIVITY_TAG,	"Fetching SensorManager...");
        Log.v(ACTIVITY_TAG,	"Fetching SensorManager...");
        Log.v(ACTIVITY_TAG,	"Fetching SensorManager...");
        
        Log.v(ACTIVITY_TAG,	"Fetching SensorManager...");
        
        sensMan = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensors = sensMan.getSensorList(Sensor.TYPE_ALL);
        listView = (ListView) this.findViewById(R.id.listView1);
        sensorNames = getSensorNames(sensors);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sensorNames);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
    }


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private List<String> getSensorNames(List<Sensor> sensors) {
		ArrayList<String> res = new ArrayList<String>();
		for(Sensor sensor : sensors) {
			if (sensor != null ) {
				res.add(sensor.getName());
			}
		}
		return res;
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		txt.setText("Sensor: " + sensors.get(position).getName());
		Intent intent= new Intent(this, SensorActivity.class);
		intent.putExtra("type", sensors.get(position).getType());
		
		this.startActivity(intent);
	}



}
