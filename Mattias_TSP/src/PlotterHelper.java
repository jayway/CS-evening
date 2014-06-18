import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by mattias on 2014-06-16.
 */
public class PlotterHelper {

    public static String getCSVString(int[] nodeData, int[] path){
        StringBuilder buf = new StringBuilder("X,Y\n");
        for(int a=0; a<path.length; a++){
            buf.append(nodeData[2*path[a]] + "," + nodeData[2*path[a]+1] + "\n");
        }

        buf.append(nodeData[2*path[0]] + "," + nodeData[2*path[0]+1]);
        return buf.toString();

    }

    /**
     * This stores the path data into a .csv file that can be used at http://itools.subhashbose.com/grapher/index.php
     * @param data data to be saved
     * @param file file to save the data to
     * @throws Exception
     */

    public static void saveStringToFile(String data, File file) throws Exception {
        FileWriter writer = new FileWriter(file);
        writer.write(data);
        writer.flush();
        writer.close();
    }
}
