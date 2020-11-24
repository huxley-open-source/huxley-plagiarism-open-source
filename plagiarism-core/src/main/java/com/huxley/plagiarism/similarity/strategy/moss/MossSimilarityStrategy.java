package com.huxley.plagiarism.similarity.strategy.moss;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huxley.plagiarism.similarity.SimilarityDetectionStrategy;
import com.huxley.plagiarism.tokenizer.Language;

import it.zielke.moji.MossException;
import it.zielke.moji.SocketClient;

public class MossSimilarityStrategy implements SimilarityDetectionStrategy {
	
	private static final Logger logger = LoggerFactory.getLogger(MossSimilarityStrategy.class);

	private final String clientId = "580040894";

	private final String sourceFilename = "source";

	private final String targetFilename = "target";

	private Map<Language, String> supportedLanguages = new HashMap<Language, String>();
	
	private String language;
	
	public MossSimilarityStrategy() {
		super();
		logger.debug("Using Moss strategy");
		this.supportedLanguages.put(Language.C, "c");
		this.supportedLanguages.put(Language.CPP, "cc");
		this.supportedLanguages.put(Language.JAVA, "java");
		this.supportedLanguages.put(Language.OCTAVE, "matlab");
		this.supportedLanguages.put(Language.PASCAL, "pascal");
		this.supportedLanguages.put(Language.PYTHON, "python");
		this.supportedLanguages.put(Language.PYTHON_3_2, "python");
	}

	private InputStream convertToInputStream(List<String> source) {
		StringBuilder builder = new StringBuilder();

		for (String line : source) {
			builder.append(line).append("\n");
		}

		return new ByteArrayInputStream(builder.toString().getBytes(Charset.defaultCharset()));
	}

	private BigDecimal parseResult(URL url) {
		try {
			Document doc = Jsoup.connect(url.toString()).get();
			Elements links = doc.select("td").select("a");

			if (links.size() > 0) {
				BigDecimal value = new BigDecimal(links.get(0).html().split("\\(|\\)")[1].replace("%", ""));

				return value.divide(new BigDecimal(100));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new BigDecimal(0);
	}

	@Override
	public BigDecimal score(List<String> source, List<String> target, Integer granularity) {
		InputStream sourceInputStream = this.convertToInputStream(source);
		InputStream targetInputStream = this.convertToInputStream(target);

		SocketClient socketClient = new SocketClient();

		socketClient.setUserID(this.clientId);

		try {
			socketClient.setOptM(granularity);
			
			socketClient.setLanguage(this.supportedLanguages.get(Language.valueOf(this.language)));

			socketClient.run();

			socketClient.uploadStream(this.sourceFilename, sourceInputStream, false);
			socketClient.uploadStream(this.targetFilename, targetInputStream, false);
			socketClient.sendQuery();
		} catch (MossException | IOException e) {
			e.printStackTrace();
		}

		return this.parseResult(socketClient.getResultURL());
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
