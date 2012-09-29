package to.noc.uadetector.example;

import java.lang.reflect.Field;
import java.util.concurrent.ScheduledExecutorService;
import net.sf.uadetector.OperatingSystem;
import net.sf.uadetector.UADetectorServiceFactory;
import net.sf.uadetector.UserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.parser.UpdatingUserAgentStringParserImpl;

public class Main {

    private static String uaString = 
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.19 (KHTML, like Gecko) Ubuntu/11.10 Chromium/18.0.1025.168 Chrome/18.0.1025.168 Safari/535.19";

    public static void main(final String[] args) throws Exception {
        UserAgentStringParser parser;
        parser = UADetectorServiceFactory.getOnlineUpdatingParser();
        UserAgent agent = parser.parse(uaString);
        OperatingSystem os = agent.getOperatingSystem();
        
        StringBuilder sb = new StringBuilder();
        sb.append("User Agent: ").append(uaString).append("\n");
        sb.append("Type: ").append(agent.getTypeName()).append("\n");
        sb.append("Family: ").append(agent.getFamily()).append("\n");
        sb.append("Name: ").append(agent.getName()).append("\n");
        sb.append("Producer URL: ").append(agent.getProducerUrl()).append("\n");
        sb.append("Producer: ").append(agent.getProducer()).append("\n");
        sb.append("URL: ").append(agent.getUrl()).append("\n");
        sb.append("OS Company: ").append(os.getProducer()).append("\n");
        sb.append("OS Company URL: ").append(os.getProducerUrl()).append("\n");
        sb.append("OS Family Name: ").append(os.getFamilyName()).append("\n");
        sb.append("OS Version Number: ").append(os.getVersionNumber().toVersionString()).append("\n");
        sb.append("OS URL: ").append(os.getUrl()).append("\n");

        System.out.println(sb);
        
        /*
         * The program will hang without the hack below, because the scheduler
         * is not running as a daemon thread. To fix this UADetector could do
         * one of:
         * 
         *    A) Use java.util.Timer instead of ScheduledExecutorService and pass
         *       "true" to the constructor.  The daemon thread can be killed at
         *       any time, so you must be carefull with file I/O.  Partially
         *       downloaded files can be written to one name, then atomically
         *       renamed to the final name when complete.  While some people on
         *       StackOverflow are saying that Timer is obsolete, I don't
         *       agree with them.  For a single threaded background process that
         *       terminates when the main thread exits, it's a very convenient
         *       tool.
         * 
         *    B) Provide some interface so that scheduler.shutdown() can be called.
         *       This method will let any existing update thread finish, but will
         *       not schedule any future threads.
         * 
         *    C) Some other solution better than the ones I proposed above.  :)
         */
        
        UpdatingUserAgentStringParserImpl updatingParser = (UpdatingUserAgentStringParserImpl) parser;
        Field privateStringField = UpdatingUserAgentStringParserImpl.class.getDeclaredField("scheduler");
        privateStringField.setAccessible(true);
        ScheduledExecutorService scheduler = (ScheduledExecutorService) privateStringField.get(updatingParser);
        scheduler.shutdown();
    }
}
