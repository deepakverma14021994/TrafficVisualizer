package parser;
/**
 * Created by deepak.verma on 07/12/17.
 */

import java.io.*;

import Chart_er.chart_maker;
import utilities.ItoSMap;


public class parser extends Thread {

    // The name of the file to open.


    private ItoSMap<String,Integer> hostToPacketmap;
    private chart_maker chrter;
    private String fileName ;

    public parser(ItoSMap hostToPktmap, chart_maker charter,String flename){
        hostToPacketmap = hostToPktmap;
        chrter = charter;
        fileName = flename;
    }

    private boolean RenderChart = false;

    public void run() {

        // This will reference one line at a time
        String packet = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            long lastTime = System.currentTimeMillis() / 1000;

            while(true) {

                long currentTime = System.currentTimeMillis() / 1000;

                //System.out.println("Current Time in seconds "+currentTime);

                if (currentTime - lastTime >= 5){
                    //Sync
                    //syncer.sync();

                    if(!hostToPacketmap.isEmpty() && !RenderChart){

                        System.out.println("Host Map Size "+hostToPacketmap.size());
                        chrter.MakeChart();
                        RenderChart=true;
                    } else {
                        chrter.UpdateChart();
                    }
                    lastTime = currentTime;
                }

                if((packet = bufferedReader.readLine()) == null) {

                    try {
                        Thread.sleep(10000);
                    } catch (Exception e){
                        System.out.println("Thread Sleep Exception: "+e);
                        break;
                    }

                } else {
                    String[] event_elements;

                    try {
                        event_elements = packet.split(" ");

                        String host = event_elements[2];

                        Integer packetSize;

                        packetSize = new Integer(Integer.parseInt(event_elements[7]));

                        hostToPacketmap.addVal(host,packetSize);

                        //System.out.printf("Host %s Packet Length %d \n",host,packetSize);

                        //hostToPacketmap.PrintMap();

                        //System.out.println(hostToPacketmap.size());

                    } catch (Exception e) {

                        //System.out.println("Packet Format un-recognisable");

                        continue;
                    }
                }
            }

            // Always close files.
            bufferedReader.close();
        } catch(FileNotFoundException ex) {

            System.out.println("Unable to open file '" + fileName + "'");

        } catch(IOException ex) {

            System.out.println("Error reading file '" + fileName + "'");

        }

    }

}

