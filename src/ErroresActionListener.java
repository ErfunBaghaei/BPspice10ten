import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ErroresActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent a){
        String url="fisrt.html";
        URL oURL;

        try {
            oURL = new URL(url);
            oURL.openConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
