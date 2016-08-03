package search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.flexible.standard.StandardQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * <h1>Add Two Numbers!</h1>
 * The AddNum program implements an application that
 * simply adds two given integer numbers and Prints
 * the output on the screen.
 * <p>
 * <b>Note:</b> Giving proper comments in your program makes it more
 * user friendly and it is assumed as a high quality code.
 *
 * @author Pubudu Dissanayake
 * @version 1.0
 * @since 1/08/2016
 */
public class GovernmentSearchRanker {
    String _indexPath;
    StandardQueryParser _parser;
    IndexReader _reader;
    IndexSearcher _searcher;
    DecimalFormat _df = new DecimalFormat("#.####");


    public GovernmentSearchRanker(String index_path, String default_field, Analyzer a)
            throws IOException {
        _indexPath = index_path;
        Directory d = new SimpleFSDirectory(Paths.get(_indexPath));
        DirectoryReader dr = DirectoryReader.open(d);
        _searcher = new IndexSearcher(dr);
        _parser = new StandardQueryParser(a);
    }

    public void doSearch(String query, int num_hits, PrintStream ps)
            throws Exception {
        String temp = query.replaceAll("\\d", "");
        Query q = _parser.parse(temp, "CONTENT");
        TopScoreDocCollector collector = TopScoreDocCollector.create(num_hits);
        _searcher.search(q, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;
        File textFile = new File("src/search/GovernRetrieved.txt");
        try (FileOutputStream fop = new FileOutputStream(textFile,true)) {
            if (!textFile.exists()) {
                textFile.createNewFile();
            }

            ps.println("\nFound " + hits.length + " hits " + "for query " + temp.trim() + ":");

            for (int i = 0; i < hits.length; i++) {
                int docId = hits[i].doc;
                Document d = _searcher.doc(docId);

                query = query.replaceAll("[^0-9 ]","").trim();
                File f = new File(d.get("PATH"));
                String fileName = f.getName();
                String content = query + " Q0 "+fileName+ " "+
                        i+ " "+ _df.format(hits[i].score) + " u5857495" ;
                ps.println(content);
                content = content + "\n";

                // get the content in bytes
                byte[] contentInBytes = content.getBytes();
                fop.write(contentInBytes);
                fop.flush();
                //fop.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        String index_path = "src/search/lucene.index";
        String default_field = "CONTENT";

        FileIndexBuilder b = new FileIndexBuilder(index_path);
        GovernmentSearchRanker r = new GovernmentSearchRanker(b._indexPath, default_field, b._analyzer);
        GovernmentQueryTermReader queryTermReader = new GovernmentQueryTermReader();
        ArrayList<String> list = queryTermReader.readQueryFromTheFIle();

        for (String query : list) {
            r.doSearch(query, 14, System.out);
        }
    }


}
