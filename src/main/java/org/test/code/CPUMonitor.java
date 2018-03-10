package org.test.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CPUMonitor implements ICPUMonitor {

    private Process process;
    private BufferedReader reader;

    @Override
    public double getCpuUsage() throws Exception {
        String[] stat = getStatistic();
        Double idleValue = Double.parseDouble(stat[stat.length - 1]);
        destroying();
        return 100 - idleValue;
    }

    @Override
    public Map<String, Double> getAllCpuUsage() throws Exception {
        Map<String, Double> cpuUsageMap = new HashMap<>();
        String s;
        int coreIndex = 0;
        String[] stat = getStatistic();
        Double idleValue = Double.parseDouble(stat[stat.length - 1]);
        cpuUsageMap.put("All", 100 - idleValue);
        while ((s = reader.readLine()) != null && s.length() > 1) {
            stat = s.replaceAll(",", ".").split("\\s+");
            idleValue = Double.parseDouble(stat[stat.length - 1]);
            cpuUsageMap.put(String.valueOf(coreIndex++), 100 - idleValue);
        }
        destroying();
        return cpuUsageMap;
    }

    @Override
    public int getCoreCount() {
        int coreCount = 0;
        try {
            coreCount = getAllCpuUsage().size() - 1;
        } catch (Exception ex) {
            Logger.getLogger(CPUMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return coreCount;
    }

   
    @Override
    public String toString() {
        String s = null;
        try {
            s = getAllCpuUsage().toString();
        } catch (Exception ex) {
            Logger.getLogger(CPUMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    /**
     * Method returns the 4th line of command's "mpstat -P ALL" results
     * as String[]
     * 
     * @return String[]
     * @throws IOException
     */
    public String[] getStatistic() throws Exception {
        String s = null;
        String OSName = System.getProperty("os.name");
        if (OSName.toLowerCase().contains("windows") || OSName.toLowerCase().contains("mac"))
            throw new Exception("Wrong command for you's OS");
        process = Runtime.getRuntime().exec("mpstat -P ALL 1 1");
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        for (int i = 0; i < 4; i++) {
            s = reader.readLine();
        }
        if (s == null) {
            throw new IOException("String is empty");
        }
        return s.replaceAll(",", ".").split("\\s+");
    }

    /**
     * destroing process and close Input Streams;
     */
    void destroying() {
        process.destroy();
        try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(CPUMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
