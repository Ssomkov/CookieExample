start "cmd" cmd /C java -jar selenium-server-standalone-3.4.0.jar -role hub
start "cmd" cmd /C java -Dwebdriver.chrome.driver=chromedriver.exe -jar selenium-server-standalone-3.4.0.jar -role webdriver -hub http://localhost:4444/grid/register -port 5558 -maxSession 25 -browser browserName=chrome
