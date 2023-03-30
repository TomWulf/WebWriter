public class Main {
    public static void main(String[] args)
    {
        StringBuilder HTMLPage = new StringBuilder();

        String nl = "\n";
        String tab = "\t";

        HTMLPage.append(createHTMLPageHead("Test", "test.css"));
        HTMLPage.append(startHTMLElem("body"));
        HTMLPage.append(startHTMLElem("header"));
        HTMLPage.append(startHTMLElem("nav"));
           HTMLPage.append("<!-- NAV CONTENT -->" + nl);
        HTMLPage.append(endHTMLElem("nav"));
        HTMLPage.append(endHTMLElem("header"));

        HTMLPage.append(startHTMLElem("main"));
        HTMLPage.append("<!-- Main CONTENT -->" + nl);
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
        String nl = "\n";
        String tab = "\t";
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
        String nl = "\n";
        String tab = "\t";
        StringBuilder markUp = new StringBuilder("<" + tag + ">" + nl);
        markUp.append(content);
        markUp.append("</" + tag + ">" + nl);

        return markUp.toString();
    }

    private static String startHTMLElem(String tag)
    {
        String nl = "\n";
        String tab = "\t";
        StringBuilder markUp = new StringBuilder("<" + tag + ">" + nl);

        return markUp.toString();
    }

    private static String endHTMLElem(String tag)
    {
        String nl = "\n";
        String tab = "\t";
        StringBuilder markUp = new StringBuilder("</" + tag + ">" + nl);

        return markUp.toString();
    }


}