/**
 * Created by mattias on 2014-06-16.
 */
public class PlotterHelper {

    public static String getWolframAlphaString(int[] nodeData, int[] path){
        StringBuilder buf = new StringBuilder("plot {");
        for(int a=0; a<path.length; a++){
            buf.append("{" + nodeData[2*path[a]] + "," + nodeData[2*path[a]+1] + "},");
        }

        buf.append("{" + nodeData[2*path[0]] + "," + nodeData[2*path[0]+1] + "}}");
        return buf.toString();

    }
}
