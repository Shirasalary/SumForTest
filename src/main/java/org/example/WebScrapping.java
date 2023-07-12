package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class WebScrapping {
    //split - לפצל מחרוזת על פי מילה מסויימת לתאים במערך
    //String[] strings = rounds.split("Round");
    //indexOf- מביא לי את המיקום שבו מתחיל המחרוזת שאני מביאה לו
    //int makav = result[j].indexOf("-", teamName.length());
    // כדי להשתמש בספריות שלו לעשות הורשה  extends
    //מהמחלקה שאותה נרצה לשנות
    public static void הערות(){

        try {
            String path = "כתובת האתר";
            Document document = Jsoup.connect(path).get();//נכנס לאתר
            List<Element> elements = document.selectXpath("ללחוץ 3 נק ולהעתיק את הXPath"); //לקחת מידע לפי xPath
            /* במידה ואנחנו בטוחות שיש ברשימה רק איבר אחד, אפשר להוציא את המידע מהרשימה עי שורת הקוד הבאה */
            String information = elements.get(0).text();
            String data =document.body().text();// לקחת את כל הטקסט של האתר, צריך להיזהר יכול להיות שם דברים שאנחנו לא צריכים

            for (Element element: elements)
            {
                Element child = element.child(0);// מביא את הילד במיקום שנרצה מתחילים לספור מ-0
                element.children().size();// מביא כמה ילדים יש לאלמנט
                element.attr("שם התכונה שנרצה"); //מביא לי תוכן באלמנט לפי תכונה
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //פיתרון לתרגיל של שי עם הכדורגל
    public static void printTeamResults(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a football team name: ");
        String teamName = scanner.nextLine();

        try {
            // Fetch the HTML content from the URL
            String url = "https://www.rsssf.org/tablesi/isra2022.html";
            Document document = Jsoup.connect(url).get();
            List<Element> elements = document.selectXpath("/html/body/pre[1] ");
            String rounds = elements.get(0).text();
            String[] strings = rounds.split("Round");
            int teamGoals=0;
            int otherGoals = 0;
            int count = 0;
            for (int i = 0; i <strings.length; i++) {
                if (!strings[i].contains("Table")){
                    count++;
                    String[] result = strings[i].split("\n");
                    System.out.println("round " +count );
                    for (int j=0; j<result.length; j++){
                        if (checkGroupExists(result[j],teamName) && result[j].indexOf(teamName) == 0){
                            int makav = result[j].indexOf("-", teamName.length());
                            teamGoals=result[j].charAt(makav-1);
                            otherGoals = result[j].charAt(makav+1);
                            break;
                        } else if (checkGroupExists(result[j],teamName) && result[j].indexOf(teamName) != 0) {
                            int end = result[j].indexOf(teamName);
                            teamGoals = result[j].charAt(end-2);
                            otherGoals = result[j].charAt(end-4);
                            break;

                        }
                    }

                    if (teamGoals> otherGoals){
                        System.out.println("Win");
                    } else if (teamGoals < otherGoals) {
                        System.out.println("Lose");
                    }else {
                        System.out.println("Draw");
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkGroupExists(String fullResult,String teamName) {
        if (fullResult.contains(teamName)) {
            return true;
        }
        return false;
    }

    //בודק כמה פעמים המילה שהוא מקבל מופיע בכתבות
    private int searchNews(String searchTerm) throws IOException {
        String url = "https://www.mako.co.il/";
        Document doc = Jsoup.connect(url).get();
        String content = doc.text();
        int count = 0;
        int index = content.indexOf(searchTerm);
        while (index != -1) {
            count++;
            content = content.substring(index + searchTerm.length());
            index = content.indexOf(searchTerm);
        }
        return count;
    }
}