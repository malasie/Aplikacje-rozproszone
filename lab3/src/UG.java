import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class UG {
    public static void main(String[] args) {
        try {
            String url = "https://ug.edu.pl/strona/121819/kalendarz-akademicki-organizacja-roku-akademickiego-20232024  ";
            Document document = Jsoup.connect(url).get();
            Elements tables = document.select("table");
            Element table = tables.get(0);
            Elements rows = table.select("tr");

            for (Element row : rows){
                Elements cells = row.select("td");
                for (Element cell : cells){
                    System.out.print(cell.text()+ "\t");
                }
            }

        } catch (Exception ignored) {
        }
    }
}