package com.veontomo;

/**
 * Browser data similator.
 */
abstract class BrowserData {
    /**
     * list of some possible user agents
     * http://www.useragentstring.com/pages/useragentstring.php
     */
    final static  String[] userAgents = new String[]{
            "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.116 Safari/537.36 Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B334b Safari/531.21.10",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1309.0 Safari/537.17",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246",
            "Mozilla/5.0 (X11; U; Linux i686; en; rv:1.9.0.5) Gecko/20080528 Fedora/2.24.1-3.fc10 Epiphany/2.22 Firefox/3.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1",
            "Mozilla/4.06 [en] (X11; I; Linux 2.0.35 i686)",
            "Mozilla/4.06 [en] (WinNT; I)",
            "Mozilla/4.06 [en] (X11; I; Linux 2.0.35 i686)",
            "Mozilla/4.06 [en] (WinNT; I)",
            "Dillo/0.8.6-i18n-misc",
            "EmeraldShield.com WebBot (http://www.emeraldshield.com/webbot.aspx)",
            "Wget/1.9+cvs-stable (Red Hat modified)"
    };

    final static String[] encodings = new String[]{
            "ASCII",
            "US-ASCII",
            "UTF-8",
            "UTF-16",
            "KOI-8R"
    };
}
