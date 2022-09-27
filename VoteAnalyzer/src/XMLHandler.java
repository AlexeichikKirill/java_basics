import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {
    private HashMap<Voter, Integer> voterCount;

    public XMLHandler() {
        voterCount = new HashMap<>();
    }

    private int limit = 5_000_000;
    private int number = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if (qName.equals("voter") & number < limit) {
            String name = attributes.getValue("name");
            String birthDate = attributes.getValue("birthDay");

            try {
                DBConnection.countVoter(name, birthDate);
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void printDuplicatedVoters(){
        for (Voter voter : voterCount.keySet()) {
            int count = voterCount.get(voter);
            if (count > 1) {
                System.out.println(voter.toString() + " - " + count);
            }
        }
    }
}
