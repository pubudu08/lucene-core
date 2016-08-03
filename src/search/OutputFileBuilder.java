package search;

import java.io.FileWriter;
import java.io.IOException;

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
public class OutputFileBuilder {

    public void build(){
        FileWriter writer = null;
        try {
            writer = new FileWriter("retrieved.txt");
//            for(String str: arr) {
//                writer.write(str);
//            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
