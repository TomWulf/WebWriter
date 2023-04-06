import java.util.*;

public class Main {

    final static String nl = "\n";
    final static String tab = "\t";
    final static String quote = "\"";

    public static void main(String[] args)
    {
        StringBuilder HTMLPage = new StringBuilder();

        HTMLPage.append(createHTMLPageHead("Test", "test.css"));
        HTMLPage.append(startHTMLElem("body"));
        HTMLPage.append(startHTMLElem("header"));
        HTMLPage.append(startHTMLElem("nav"));
           HTMLPage.append("<!-- NAV CONTENT -->" + nl);
        HTMLPage.append(endHTMLElem("nav"));
        HTMLPage.append(endHTMLElem("header"));

        HTMLPage.append(startHTMLElem("main"));
            Map<String, String> pAttr = new HashMap<>();
            pAttr.put("ID", "TopPara");
        HTMLPage.append(HTMLElem("p",pAttr,"Test paragraph with ID Attribute" ));
        HTMLPage.append(ol(Arrays.asList(new String[]{"tom", "dick", "harry"}) ));
        HTMLPage.append(ul(Arrays.asList(new String[]{"tom", "dick", "harry"}) ));
        HTMLPage.append(endHTMLElem("main"));

        HTMLPage.append(startHTMLElem("footer"));
        HTMLPage.append("<!-- FOOTER CONTENT -->" + nl);
        HTMLPage.append(endHTMLElem("footer"));
        HTMLPage.append(endHTMLElem("body"));
        HTMLPage.append(endHTMLElem("html"));

        System.out.println(HTMLPage.toString());

    }

    private static String createHTMLPageHead(String title, String CSSLink )
    {

        StringBuilder markUp = new StringBuilder("<!DOCTYPE html>" + nl);
        markUp.append("<html>" + nl);
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

    private static String HTMLElem(String tag, Map<String, String> attributes, String content)
    {

        StringBuilder markUp = new StringBuilder("<" + tag);
          int attTotal = attributes.size();
          int attCount = 0;
          for(String k : attributes.keySet()) {
              attCount++;
              markUp.append(" " + k.trim() + ":" + quote + attributes.get(k).trim() + quote);
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

        StringBuilder markUp = new StringBuilder("<" + tag + ">" + nl);

        return markUp.toString();
    }

    private static String startHTMLElem(String tag,Map<String, String> attributes)
    {

        StringBuilder markUp = new StringBuilder("<" + tag);
        int attTotal = attributes.size();
        int attCount = 0;
        for(String k : attributes.keySet()) {
            attCount++;
            markUp.append(" " + k.trim() + ":" + attributes.get(k).trim());
            if(attCount != attTotal)
                markUp.append(";");
        }
        markUp.append(">");

        return markUp.toString();
    }

    private static String endHTMLElem(String tag)
    {

        StringBuilder markUp = new StringBuilder("</" + tag + ">" + nl);

        return markUp.toString();
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
}