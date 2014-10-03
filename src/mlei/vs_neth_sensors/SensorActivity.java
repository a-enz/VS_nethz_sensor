package mlei.vs_neth_sensors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SensorActivity extends Activity implements SensorEventListener{
	
	private SensorManager sensMan;
	private Sensor sensor;
	private TextView txt;
	private ArrayAdapter<Float> arrayAdapter;
	private ListView listView;
	private List<Float> values;
	
	protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_sensor);
	      
	      sensMan = (SensorManager)getSystemService(SENSOR_SERVICE);
	      sensor = sensMan.getDefaultSensor(this.getIntent().getExtras().getInt("type"));
	      txt = (TextView) findViewById(R.id.sensor_text);
	      txt.setText(sensor.getName());
	      sensMan.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME, 100);
	      
	      values = new ArrayList<Float>();
	      listView = (ListView) findViewById(R.id.listView2);
	      arrayAdapter = new ArrayAdapter<Float>(this, android.R.layout.simple_list_item_1, values);
	      listView.setAdapter(arrayAdapter);
	}
	  
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

	@Override
	public void onSensorChanged(SensorEvent event) {
		values = box(event.values);
		arrayAdapter.clear();
		arrayAdapter.addAll(values);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

	private List<Float> box(float[] arg) {
		List<Float> res = new ArrayList<Float>();
		for(int i = 0; i < arg.length; i++) {
			res.add(Float.valueOf(arg[i]));	//boxing
		}
		return res;
	}
}
