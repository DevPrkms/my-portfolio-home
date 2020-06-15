package poly.util;

import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class maTest {

    public HashMap<Integer, List<String>> maTest(String word) {
        // string to extract keywords
        String strToExtrtKwrd = word;
        strToExtrtKwrd = strToExtrtKwrd.replaceAll(" ", "").replaceAll("\n", "");
        // init KeywordExtractor
        KeywordExtractor ke = new KeywordExtractor();
        // extract keywords
        KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);
        // print result
        HashMap<Integer, List<String>> hMap = new HashMap<>();
        for( int i = 0; i < kl.size(); i++ ) {
            Keyword kwrd = kl.get(i);
            List<String> rList = new ArrayList<>();
            rList.add(kwrd.getString() + kwrd.getCnt());
            hMap.put(i, rList);
            rList = new ArrayList<>();
//            System.out.println(kwrd.getString() + "\t" + kwrd.getCnt());
            System.out.println("hMap : " + hMap.get(i).toString());
        }
        return hMap;
    }
}
