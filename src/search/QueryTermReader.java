package search;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <h1>Add Two Numbers!</h1>
 * The AddNum program implements an application that
 * simply adds two given integer numbers and Prints
 * the output on the screen.
 * <p>
 * <b>Note:</b> Giving proper comments in your program makes it more
 * user friendly and it is assumed as a high quality code.
 *
 * @author Pubudu Dissanayake
 * @version 1.0
 * @since 1/08/2016
 */
public class QueryTermReader {
    private  ArrayList<String > sAirTopicQueryTerms = new ArrayList<>();

    public ArrayList<String> readQueryFromTheFIle(){
        try (BufferedReader br = new BufferedReader(new FileReader("src/search/email/topics/air.topics")))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                sAirTopicQueryTerms.add(sCurrentLine);
            }

            for(String x : sAirTopicQueryTerms ){
                System.out.println(x);
            }
            System.out.println();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sAirTopicQueryTerms;
    }
}
