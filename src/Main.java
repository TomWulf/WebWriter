import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import static java.nio.file.StandardOpenOption.CREATE;

public class Main {

    final static String nl = "\n";
    final static String tab = "\t";
    final static String quote = "\"";
    final static Map<String, Map<String, String>> CSSData = new TreeMap<>();
    public static void main(String[] args)
    {


        StringBuilder HTMLPage = new StringBuilder();

        HTMLPage.append(createHTMLPageHead("Test", "test.css"));
        HTMLPage.append(startHTMLElem("body"));
        HTMLPage.append(startHTMLElem("header"));
        HTMLPage.append(startHTMLElem("nav"));
           HTMLPage.append("<!-- NAV CONTENT -->" + nl);
 //          Map<String, String> n1Attr = new HashMap<>();
 //          n1Attr.put("href", "http://homepages.uc.edu/~wulft");
           HTMLPage.append(HTMLElem("a", "href", "http://homepages.uc.edu/~wulft", "Tom's UC WebSite"));
        HTMLPage.append(endHTMLElem("nav"));
        HTMLPage.append(endHTMLElem("header"));

        HTMLPage.append(startHTMLElem("main"));
        HTMLPage.append(HTMLElem("p","ID", "TopPara","Test paragraph with ID Attribute" ));
        HTMLPage.append(ol(Arrays.asList(new String[]{"tom", "dick", "harry"}) ));
        HTMLPage.append(ul(Arrays.asList(new String[]{"tom", "dick", "harry"}) ));
        HTMLPage.append(endHTMLElem("main"));

        HTMLPage.append(startHTMLElem("footer"));
        HTMLPage.append("<!-- FOOTER CONTENT -->" + nl);
        HTMLPage.append(endHTMLElem("footer"));
        HTMLPage.append(endHTMLElem("body"));
        HTMLPage.append(endHTMLElem("html"));

        System.out.println(HTMLPage.toString());


        try {
            savePageFile("Test.html", HTMLPage.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CSS test
        addCSSProperty("body","background-color", "yellow");
        addCSSProperty("body","color", "blue");
        addCSSProperty("body","margin-right", "5%");
        addCSSProperty("body","margin-left", "5%");
        addCSSProperty("header","background-color", "blue");
        addCSSProperty("header","color", "white");
        addCSSProperty("#TopPara","font", "15px Arial");

        try {
            saveCSSFile("test.css");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String createHTMLPageHead(String title, String CSSLink )
    {

        StringBuilder markUp = new StringBuilder("<!DOCTYPE html>" + nl);
        markUp.append("<html lang="+ quote + "en" + quote + ">" + nl);
        markUp.append("<head>" + nl);
        markUp.append(tab + "<title>" + title + "</title>" + nl);
        if(CSSLink != null)
        {
            markUp.append("<link rel=\"stylesheet\" href=\"" + CSSLink + "\">" + nl);
        }
        markUp.append("</head>" + nl);

        return markUp.toString();
    }


    private static String HTMLElem(String tag, String content)
    {

        StringBuilder markUp = new StringBuilder("<" + tag + ">" + nl);
        markUp.append(content);
        markUp.append("</" + tag + ">" + nl);

        return markUp.toString();
    }

    private static String HTMLElem(String tag, String key, String value, String content)
    {

        StringBuilder markUp = new StringBuilder("<" + tag + " " + key + "=" + quote + value + quote + ">");
        markUp.append(content);
        markUp.append("</" + tag + ">" + nl);

        return markUp.toString();
    }



    private static String HTMLElem(String tag, Map<String, String> attributes, String content)
    {

        StringBuilder markUp = new StringBuilder("<" + tag);
          int attTotal = attributes.size();
          int attCount = 0;
          for(String k : attributes.keySet()) {
              attCount++;
              markUp.append(" " + k.trim() + "=" + quote + attributes.get(k).trim() + quote);
              if(attCount != attTotal)
                  markUp.append(";");
          }
        markUp.append(">");
        markUp.append(content);
        markUp.append("</" + tag + ">" + nl);

        return markUp.toString();
    }

    private static String startHTMLElem(String tag)
    {
        return "<" + tag + ">";
    }

    private static String startHTMLElem(String tag, String key, String value)
    {

        StringBuilder markUp = new StringBuilder("<" + tag);

        markUp.append(" " + key + "=" + quote + value + quote);

        markUp.append(">");

        return markUp.toString();
    }

    private static String startHTMLElem(String tag,Map<String, String> attributes)
    {

        StringBuilder markUp = new StringBuilder("<" + tag);
        int attTotal = attributes.size();
        int attCount = 0;
        for(String k : attributes.keySet()) {
            attCount++;
            markUp.append(" " + k.trim() + "=" + attributes.get(k).trim());
            if(attCount != attTotal)
                markUp.append(";");
        }
        markUp.append(">");

        return markUp.toString();
    }

    private static String endHTMLElem(String tag)
    {
        return "</" + tag + ">" + nl;
    }

    private static String ol(List<String> items)
    {
        StringBuilder markUp = new StringBuilder("<ol>" + nl);

        for(String i:items)
            markUp.append(tab + "<li>" + i + "</li>" + nl);

        markUp.append("</ol>" + nl);

        return markUp.toString();
    }

    private static String ul(List<String> items)
    {
        StringBuilder markUp = new StringBuilder("<ul>" + nl);

        for(String i:items)
            markUp.append(tab + "<li>" + i + "</li>" + nl);

        markUp.append("</ul>" + nl);

        return markUp.toString();
    }

    private static String quoteString(String needsQuotes)
    {
        return quote + needsQuotes + quote;
    }

    public static void savePageFile(String fileName, String HTMLText) throws IOException
    {
        // Typical java pattern of inherited classes
        // we wrap a BufferedWriter around a lower level BufferedOutputStream

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName);

        OutputStream out =
                new BufferedOutputStream(Files.newOutputStream(file, CREATE));
        BufferedWriter writer =
                new BufferedWriter(new OutputStreamWriter(out));

        // Finally can write the file LOL!


            writer.write(HTMLText, 0, HTMLText.length());  // stupid syntax for write rec
            // 0 is where to start (1st char) the write
            // rec. length() is how many chars to write (all)
            writer.newLine();  // adds the new line


        writer.close(); // must close the file to seal it and flush buffer
        System.out.println("Data file written!");
    }

    // CSS Functions
    public static void addCSSProperty(String selector, String prop, String value)
    {
        // see if this selector is already in the map
        // if it is: add another prop value pair to its map
        // if it is not: create a new Map entry for this selector and add the property to it

        if(CSSData.containsKey(selector))
        {
            CSSData.get(selector).put(prop, value);
        }
        else
        {
            // have to create new entry for this selector
            Map<String,String> property = new TreeMap<>();
            property.put(prop, value);
            CSSData.put(selector, property);
        }

        System.out.println("addCSSProp Debug data dump: " + CSSData);
    }

    public static void saveCSSFile(String fileName) throws IOException
    {
        StringBuilder CSSOutput = new StringBuilder();

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName);

        OutputStream out =
                new BufferedOutputStream(Files.newOutputStream(file, CREATE));
        BufferedWriter writer =
                new BufferedWriter(new OutputStreamWriter(out));

        // Finally can write the file LOL!

        // go through the CSS data map
        // for each key create a selector
        // generate a rule body for the selector including each prop value pair


        for(String sel : CSSData.keySet())
        {
            CSSOutput.append(sel + " {" + nl);
            Map<String,String> props = CSSData.get(sel);
            for(String key: props.keySet())
            {
                CSSOutput.append(tab + key + ":" + props.get(key) + ";" + nl);
            }
            CSSOutput.append("}" + nl);

        }

        System.out.println("Data dump CSS map:" + nl);
        System.out.println(CSSData);

        System.out.println(nl + "data dump of the file text:");
        System.out.println(nl + nl + CSSOutput);


        writer.write(CSSOutput.toString(), 0, CSSOutput.toString().length());  // stupid syntax for write rec
        // 0 is where to start (1st char) the write
        // rec. length() is how many chars to write (all)
        writer.newLine();  // adds the new line


        writer.close(); // must close the file to seal it and flush buffer
        System.out.println("Data file written!");
    }

}