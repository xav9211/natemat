package main.java.pl.edu.agh.toik.crawler;

import main.java.pl.edu.agh.toik.database.NaTematCrawlerDB;
import main.java.pl.edu.agh.toik.database.model.Article;
import main.java.pl.edu.agh.toik.database.model.Comment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class NaTematCrawler implements ICrawler {

    private final static int TIMEOUT = 10 * 1000;

    private ICrawlerService crawlerService;
    private ICrawlerSettings crawlerSettings;

    @Autowired
    private NaTematCrawlerDB naTematCrawlerDB;

    @Autowired
    public NaTematCrawler(ICrawlerService crawlerService, ICrawlerSettings crawlerSettings) {
        this.crawlerService = crawlerService;
        this.crawlerSettings = crawlerSettings;
    }

    @Override
    public void crawl(String url) throws IOException {
        Document doc = Jsoup.connect(url).timeout(TIMEOUT).get();

        Set<Element> links = crawlerService.findUniqueLinks(doc.select("a[href^=" + url + "], a[href^=/]"));

        for (Element link : links) {
            String subUrl = link.attr("abs:href");
            Document tmpDoc = Jsoup.connect(subUrl).timeout(TIMEOUT).get();
            System.out.println("URL: " + subUrl);
            System.out.println("Text length: " + tmpDoc.text().length());
            System.out.println("Html length: " + tmpDoc.html().length());
            System.out.println("Number of comments: " + crawlerService.getNumberOfCommentsForUrl(subUrl));
            System.out.println("Number of links: " + crawlerService.findUniqueLinks(tmpDoc.select("a[href^=" + url + "], a[href^=/]")).size());

            Article article = crawlerService.getArticleFromUrl(subUrl);
            List<Comment> commentsList = crawlerService.getCommentsForUrl(subUrl);

            if (article != null) {
                naTematCrawlerDB.getArticleService().saveArticle(article);
                System.out.println("Number of facebook shares: " + article.getFacebookShares());
            }

            naTematCrawlerDB.getCommentService().saveComments(commentsList);

            if (article != null && !commentsList.isEmpty()) naTematCrawlerDB.getArticleService().saveCommentsForArticle(article, commentsList);

            for (Comment comment : commentsList) {
                Set<Comment> subCommentsList = crawlerService.getSubCommentsForCommentId(comment.getId());
                System.out.println("For commentId: " + comment.getId());
                System.out.println("Number of subComments: " + subCommentsList.size());
                naTematCrawlerDB.getCommentService().saveSubCommentsForComment(comment, subCommentsList);
            }
        }
    }
}
