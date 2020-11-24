package com.huxley.plagiarism.similarity.strategy.gst;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huxley.plagiarism.similarity.SimilarityDetectionStrategy;

public class GreedyStringTilingStrategy implements SimilarityDetectionStrategy {
	
	private static final Logger logger = LoggerFactory.getLogger(GreedyStringTilingStrategy.class);

	public GreedyStringTilingStrategy() {
		super();
		logger.debug("Using GST strategy");
	}

	@Override
	public BigDecimal score(List<String> source, List<String> target, Integer granularity) {		
		List<Match> tiles = runStringTiling(Tile.getTokens(source), Tile.getTokens(target), granularity);
		
		Integer coverage = coverage(tiles);
		
		return new BigDecimal(2).multiply(new BigDecimal(coverage).divide(new BigDecimal(source.size()).add(new BigDecimal(target.size())), 6, RoundingMode.HALF_UP));
	}
	
	private Integer coverage(List<Match> tiles) {
		Integer total = 0;
		for (Match tile : tiles) {
			total += tile.getLength();
		}
		
		return total;
	}
	
	public List<Match> runStringTiling(List<Tile> source, List<Tile> target, Integer minimumMatchLength) {
		List<Match> tiles = new ArrayList<>();
		Integer maxMatch = null;
		
		do {
			List<Match> matches = new ArrayList<>();
			
			maxMatch = minimumMatchLength;
			for (int i = 0; i < source.size(); i++) {
				if (source.get(i).isMarked()) {
					continue;
				}
				for (int j = 0; j < target.size(); j++) {
					if (target.get(j).isMarked()) {
						continue;
					}
					int k = 0;
					while (source.get(i + k).equals(target.get(j + k)) && !source.get(i + k).isMarked() && !target.get(j + k).isMarked()) {
						k++;
						if ((k + i) > source.size() - 1 || (k + j) > target.size() - 1) {
							break;
						}
					}
					
					if (k == maxMatch) {
						matches.add(new Match(i, j, k));
					} else if (k > maxMatch) {
						matches.clear();
						matches.add(new Match(i, j, k));
						maxMatch = k;
					}
				}
			}
			
			for (Match match : matches) {
				Boolean alreadymarked = false;
				
				for (int index = 0 ; index < match.getLength(); index++) {
					if (source.get(match.getSource() + index).isMarked() || target.get(match.getTarget() + index).isMarked()) {
						alreadymarked = true;
						break;
					}
					source.get(match.getSource() + index).mark();
					target.get(match.getTarget() + index).mark();
				}
				
				if (!alreadymarked) {
					tiles.add(match);					
				}
			}

		} while (maxMatch > minimumMatchLength);
		
		return tiles; 
	}

}