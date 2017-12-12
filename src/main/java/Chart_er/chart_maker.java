package Chart_er;

/**
 * Created by deepak.verma on 08/12/17.
 */


import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;

import java.util.ArrayList;
import java.util.List;
import org.knowm.xchart.style.Styler.ChartTheme;
import java.util.HashMap;


public class chart_maker {

    private CategoryChart categoryChart;

    private List<String> xData;

    private List<Integer> yData;

    public static final String SERIES_NAME = "series1";

    private SwingWrapper<CategoryChart> swingWrapper ;

    private HashMap<String,Integer> hostToPckMap = new HashMap<String, Integer>();

    public chart_maker(HashMap<String ,Integer> htopmap) {

        hostToPckMap = htopmap;
    }

    public void MakeChart(){

        swingWrapper = new SwingWrapper<CategoryChart>(getChart());

        swingWrapper.displayChart();

    }

    public void UpdateChart() {

        System.out.println("Updating chart ...");

        updateData();

        swingWrapper.repaintChart();

    }

    public CategoryChart getChart() {

        System.out.println("Initialising Charting ..");

        List<String> newXData = new ArrayList();
        List<Integer> newYData = new ArrayList<Integer>();


        try{

            for (String key : hostToPckMap.keySet()){

                newXData.add(key);

                newYData.add(hostToPckMap.get(key));

            }

        } catch(Exception e){

            System.out.println("HostToPkt map Exception : "+e.toString());
        }


        xData = newXData;

        yData = newYData;

        // Create Chart
        categoryChart = new CategoryChartBuilder().width(500).height(400).theme(ChartTheme.Matlab).title("Real-time Category Chart").build();

        categoryChart.addSeries(SERIES_NAME, xData, yData);

        return categoryChart;
    }

    public void updateData() {


        List<String> newXData = new ArrayList();
        List<Integer> newYData = new ArrayList<Integer>();


        try{

            for (String key : hostToPckMap.keySet()){

                newXData.add(key);

                newYData.add(hostToPckMap.get(key));
            }

        } catch(Exception e){

            System.out.println("HostToPkt map Exception : "+e.toString());

        }


        xData = newXData ;

        yData = newYData;

        categoryChart.updateCategorySeries(SERIES_NAME, xData, yData, null);
    }

}