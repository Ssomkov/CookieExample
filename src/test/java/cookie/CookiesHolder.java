package cookie;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by ssomkov on 20.10.2017.
 */
public class CookiesHolder {

    public static void saveCookies(WebDriver driver) {
        File file = new File("browser.dat");
        String cookieName;
        String cookieValue;
        String cookieDomain;
        String cookiePath;
        try {
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Cookie cookie : driver.manage().getCookies()) {
                cookieName = cookie.getName();
                cookieValue = cookie.getValue();
                cookieDomain = cookie.getDomain();
                cookiePath = cookie.getPath();
                if (cookieName.equals("")) {
                    cookieName = " ";
                }
                if (cookieValue.equals("")) {
                    cookieValue = " ";
                }
                if (cookieDomain.equals("")) {
                    cookieDomain = " ";
                }
                if (cookiePath.equals(" ")) {
                    cookiePath = "/";
                }

                writer.write((cookieName + ";" + cookieValue + ";" +
                        cookieDomain + ";" + cookiePath + ";" + cookie.getExpiry() +
                        ";" + cookie.isSecure()));
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи куки - " + e.getLocalizedMessage());
        }
    }

    public static void loadCookies(WebDriver driver) {
        Cookie cookie;
        try {
            File file = new File("browser.dat");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
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
                    driver.manage().addCookie(cookie);
                }
            }
            driver.navigate().refresh();
            driver.navigate().refresh();
        } catch (Exception ex) {
            System.out.println("Ошибка при чтении куки - " + ex.getLocalizedMessage());
        }
    }
}