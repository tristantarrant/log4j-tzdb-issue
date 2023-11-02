import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.management.MBeanServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.management.HotSpotDiagnosticMXBean;

public class TZ {
   static final Logger logger = LogManager.getLogger("HelloWorld");

   public static void main(String[] args) throws IOException {
      logger.info("Hello, World!");

      MBeanServer server = ManagementFactory.getPlatformMBeanServer();
      HotSpotDiagnosticMXBean mxBean = ManagementFactory.newPlatformMXBeanProxy(
            server, "com.sun.management:type=HotSpotDiagnostic", HotSpotDiagnosticMXBean.class);
      Files.deleteIfExists(Path.of("tz.hprof"));
      mxBean.dumpHeap("tz.hprof", false);
   }

}
