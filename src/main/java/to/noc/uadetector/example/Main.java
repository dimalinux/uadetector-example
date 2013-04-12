package to.noc.uadetector.example;

import net.sf.uadetector.OperatingSystem;
import net.sf.uadetector.UserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

public class Main {

    private static String uaStrings[] = {
        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
        "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.19 (KHTML, like Gecko) Ubuntu/11.10 Chromium/18.0.1025.168 Chrome/18.0.1025.168 Safari/535.19",
        "Mozilla/5.0 (iPhone; CPU iPhone OS 5_1_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B208 Safari/7534.48.3",
        "Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0",
        "Opera/9.80 (Windows NT 6.1; WOW64; U; en) Presto/2.10.289 Version/12.01",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.101 Safari/537.11",
        "Mozilla/5.0 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)",
        // Note: Amazon Silk was not getting identified when I commited this example
        "Mozilla/5.0 (Linux; U; en-gb; KFOT Build/IML74K) AppleWebKit/535.19 (KHTML, like Gecko) Silk/2.8 Safari/535.19 Silk-Accelerated=true",
        "Mozilla/5.0 (Linux; U; Android 2.2; ru-ru; GT-I9000 Build/FROYO) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
        "Bogus User Agent"
    };

    public static void printUserAgent(UserAgent ua) {
        StringBuilder sb = new StringBuilder();

        sb.append("Name: ").append(ua.getName()).append("\n");
        sb.append("Version: ").append(ua.getVersionNumber().toVersionString()).append("\n");
        sb.append("Type: ").append(ua.getTypeName()).append("\n");
        sb.append("Family: ").append(ua.getFamily()).append("\n");
        sb.append("URL: ").append(ua.getUrl()).append("\n");
        sb.append("Icon: ").append(ua.getIcon()).append("\n");
        sb.append("Producer: ").append(ua.getProducer()).append("\n");
        sb.append("Producer URL: ").append(ua.getProducerUrl()).append("\n");

        OperatingSystem os = ua.getOperatingSystem();
        sb.append("OS Name: ").append(os.getName()).append("\n");
        sb.append("OS Family Name: ").append(os.getFamilyName()).append("\n");
        sb.append("OS Version Number: ").append(os.getVersionNumber().toVersionString()).append("\n");
        sb.append("OS URL: ").append(os.getUrl()).append("\n");
        sb.append("OS Icon: ").append(os.getIcon()).append("\n");
        sb.append("OS Company: ").append(os.getProducer()).append("\n");
        sb.append("OS Company URL: ").append(os.getProducerUrl()).append("\n");

        System.out.println(sb);
    }

    public static void main(final String[] args) throws Exception {

        UserAgentStringParser parser = UADetectorServiceFactory.getCachingAndUpdatingParser();

        System.out.println("Parser Version: " + parser.getDataVersion());

        for (String uaString : uaStrings) {
            System.out.println("---------------------------------------------");
            System.out.println("User Agent: " + uaString);
            printUserAgent(parser.parse(uaString));
        }
    }
}
