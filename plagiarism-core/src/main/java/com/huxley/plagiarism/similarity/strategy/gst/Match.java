package com.huxley.plagiarism.similarity.strategy.gst;

public class Match {
	
	private Integer source;
	
	private Integer target;
	
	private Integer length;

	public Match(Integer source, Integer target, Integer length) {
		super();
		this.source = source;
		this.target = target;
		this.length = length;
	}

	@Override
	public String toString() {
		return "Match [sourceIndex=" + source + ", targetIndex=" + target + ", length="
				+ length + "]";
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getTarget() {
		return target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

}
