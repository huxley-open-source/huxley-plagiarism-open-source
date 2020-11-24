package com.huxley.plagiarism.similarity;

import java.math.BigDecimal;
import java.util.List;

public interface SimilarityDetectionStrategy {
	
	BigDecimal score(List<String> source, List<String> target, Integer granularity);

}
