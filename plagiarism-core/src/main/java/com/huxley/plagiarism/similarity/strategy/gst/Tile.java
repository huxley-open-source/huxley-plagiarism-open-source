package com.huxley.plagiarism.similarity.strategy.gst;

import java.util.ArrayList;
import java.util.List;

public class Tile {
	
	private String value;
	
	private Boolean marked = false;

	public Tile(String value) {
		super();
		this.value = value;
	}
	
	public static List<Tile> getTokens(List<String> stringTokens) {
		List<Tile> tokens = new ArrayList<>();
		for (String stringToken : stringTokens) {
			tokens.add(new Tile(stringToken));
		}
		
		return tokens;
	}
	
	@Override
	public String toString() {
		return "Token [value=" + value + ", marked=" + marked + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public void mark() {
		this.marked = true;
	}
	
	public Boolean isMarked() {
		return this.marked;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getMarked() {
		return marked;
	}

	public void setMarked(Boolean marked) {
		this.marked = marked;
	}

}
