/**
 * Created by deepak.verma on 08/12/17.
 */

import parser.*;
import utilities.ItoSMap;
import Chart_er.chart_maker;
import constants.constants;

public class Start {


    public static void main(String[] args){


        ItoSMap<String,Integer> hostToPacketmap = new ItoSMap<String, Integer>();

        constants cons = new constants();

        chart_maker chartEr = new chart_maker(hostToPacketmap);

        parser pktParser = new parser(hostToPacketmap,chartEr,cons.fileName);

        pktParser.start();


    }

}
