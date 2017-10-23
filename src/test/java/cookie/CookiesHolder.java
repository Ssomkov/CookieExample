package cookie;

import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by ssomkov on 20.10.2017.
 */
public class CookiesHolder {

    public void saveCookies(WebDriver driver) {
        File file = new File("browser.dat");
        try {
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Cookie cookie : driver.manage().getCookies()) {
                writer.write((cookie.getName() + ";" + cookie.getValue() + ";" +
                        cookie.getDomain() + ";" + cookie.getPath() + ";" + cookie.getExpiry() +
                        ";" + cookie.isSecure()));
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи куки - " + e.getLocalizedMessage());
        }
    }

    public Set<Cookie> loadCookies() {
        Cookie cookie = new Cookie("", "");
        Set<Cookie> cookieSet = new HashSet<Cookie>();
        try {
            File file = new File("browser.dat");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine(); //в 1 строке с кукисами нулловое значение
            int count = 1;
            while ((line = br.readLine()) != null) {
                if(count == 15) line = br.readLine(); //пропускаем нулловые куки
                StringTokenizer str = new StringTokenizer(line, ";");
                while (str.hasMoreTokens()) {
                    String name = str.nextToken();
                    String value = str.nextToken();
                    String domain = str.nextToken();
                    String path = str.nextToken();
                    Date expiry = null;
                    String date = str.nextToken();
                    if (!(date).equals("null")) {
                        expiry = new Date(System.currentTimeMillis() * 2);
                    }
                    boolean isSecure = new Boolean(str.nextToken()).booleanValue();
                    cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                    cookieSet.add(cookie);
                    count++;
                }
            }
        } catch (Exception ex) {
            System.out.println("Ошибка при чтении куки - " + ex.getLocalizedMessage());
        }
        return cookieSet;
    }
}