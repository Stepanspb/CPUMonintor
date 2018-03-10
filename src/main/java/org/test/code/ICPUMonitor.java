
package org.test.code;


import java.io.IOException;
import java.util.Map;

public interface ICPUMonitor  {
    
Map<String, Double> getAllCpuUsage() throws Exception;

int getCoreCount();

double getCpuUsage() throws Exception;
}
