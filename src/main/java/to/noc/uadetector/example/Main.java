package to.noc.uadetector.example;

import net.sf.uadetector.OperatingSystem;
import net.sf.uadetector.UserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

public class Main {

    private static String uaString = 
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.19 (KHTML, like Gecko) Ubuntu/11.10 Chromium/18.0.1025.168 Chrome/18.0.1025.168 Safari/535.19";

    public static void main(final String[] args) throws Exception {
        UserAgentStringParser parser;
        parser = UADetectorServiceFactory.getOnlineUpdatingParser();
        UserAgent agent = parser.parse(uaString);
        OperatingSystem os = agent.getOperatingSystem();
        
        StringBuilder sb = new StringBuilder();
        sb.append("Parser Version: ").append(parser.getDataVersion()).append("\n");
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
    }
}
