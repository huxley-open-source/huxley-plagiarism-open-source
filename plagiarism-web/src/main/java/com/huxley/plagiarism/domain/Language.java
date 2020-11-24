package com.huxley.plagiarism.domain;

public enum Language {
	
	C("C"),
	CPP("CPP"),
	JAVA("JAVA"),
	OCTAVE("OCTAVE"),
	PASCAL("PASCAL"),
	PYTHON("PYTHON"),
	PYTHON_3("PYTHON3"),
	PYTHON_3_2("PYTHON3.2");
	
	private String name;
	
	private Language(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public static Language findByName(String languageName) {
		for (Language language : Language.values()) {
			if (language.name.equals(languageName.toUpperCase())) {
				return language;
			}
		}
		return null;
	}

}
