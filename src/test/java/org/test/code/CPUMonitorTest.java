
package org.test.code;

import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assume;
import org.junit.BeforeClass;


public class CPUMonitorTest {
    
    public CPUMonitorTest() {
    }
    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(!isMacOrWin());
    }
 
    private static boolean isMacOrWin() {
        return (System.getProperty("os.name").toLowerCase().contains("windows") 
             || System.getProperty("os.name").toLowerCase().contains("mac"));
    }
    
    /**
    * Test of getCpuUsage method, of class CPUMonitor.
     * @throws java.lang.Exception
    */

    @Test
    public void testGetCpuUsage() throws Exception {
        System.out.println("getCpuUsage");
        CPUMonitor instance = new CPUMonitor();
        double result = instance.getCpuUsage();
        assertTrue("Unexpectible CPU Usage", (result>0 && result<=100));    
    }

    /**
     * Test of getAllCpuUsage method, of class CPUMonitor.
     * @throws java.lang.Exception
     */
   
    @Test
    public void testGetAllCpuUsage() throws Exception {
        System.out.println("getAllCpuUsage");
        CPUMonitor instance = new CPUMonitor();
        Map<String, Double> result = instance.getAllCpuUsage();
        assertNotNull("Results is null", result);     
    }

    /**
     * Test of getCoreCount method, of class CPUMonitor.
     */
    
    @Test
    public void testGetCoreCount() {
        System.out.println("getCoreCount");
        CPUMonitor instance = new CPUMonitor();
        int result = instance.getCoreCount();
        assertTrue("Unexpected core count", result>=1 && result<100);        
    }

    /**
     * Test of toString method, of class CPUMonitor.
     */
   
    @Test
    public void testToString() {
        System.out.println("toString");
        CPUMonitor instance = new CPUMonitor();        
        String result = instance.toString();
        assertNotNull(result);
        assertNotEquals("", result);        
    }

    /**
     * Test of getStatistic method, of class CPUMonitor.
     * @throws java.lang.Exception
     */
  
    @Test()
    public void testGetStatistic() throws Exception {
        System.out.println("getStatistic");
        CPUMonitor instance = new CPUMonitor();
        String[] result = instance.getStatistic();        
        assertTrue(result!=null);
        }

    /**
     * Test of destroying method, of class CPUMonitor.
     */   
    
}
