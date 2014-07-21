package org.flyJenkins.analysis.define;


public enum GitApiDefineEnum {

	GIT_API_URL(1),	
	GIT_API_REPOS_URL(2),	
	GIT_API_SEARCH_URL(3),	
	GIT_STRATEGY_PACKAGE(4),	
	GIT_STRATEGY_NAME(5);
	
	private int value;
	
	private GitApiDefineEnum(int value) {
        this.value = value;
	}
	
	@Override
	public String toString() {
		String Info = "";
		
		switch (this) {
			case GIT_API_URL:
				Info = "https://api.github.com"; 
				break;
			case GIT_API_REPOS_URL:
				Info = "https://api.github.com/repos";
				break;
			case GIT_API_SEARCH_URL:
				Info = "https://api.github.com/search";
				break;
			case GIT_STRATEGY_PACKAGE:
				Info = "org.flyJenkins.analysis.strategy.";
				break;
			case GIT_STRATEGY_NAME:
				Info = "AnalysisStrategy";
				break;
		}
		
		return Info;
	}
}
