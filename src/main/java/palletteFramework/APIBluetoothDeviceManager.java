package palletteFramework;

import android.bluetooth.BluetoothAdapter;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by oliverhoffman on 5/26/15.
 */
public class APIBluetoothDeviceManager {

    public List<Pair<String, String>> discovered_list;

    public APIBluetoothDeviceManager()
    {
        discovered_list = new ArrayList<Pair<String, String>>();

    }
}
