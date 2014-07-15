package org.flyJenkins.github.analysis.strategy;

import org.flyJenkins.github.analysis.model.GitHubRepoCmd;
import org.flyJenkins.github.analysis.model.ProjectDto;
import org.flyJenkins.github.analysis.model.SearchCodeDto;
import org.flyJenkins.github.analysis.service.GitHubApiManager;


public class JAVASCRIPTAnalysisStrategy implements LanguageAnalysisManager {
	
	private GitHubApiManager gitHubApiManager;
	
	public void setGitHubApiManager(GitHubApiManager gitHubApiManager) {
		this.gitHubApiManager = gitHubApiManager;
	}
	@Override
	public void getGitAnalysisInfo(GitHubRepoCmd gitHubRepoCmd, ProjectDto projectDto) {
		// Project가 node.js 인지 체크
		gitHubRepoCmd.setQuery("version");
		gitHubRepoCmd.setLanguage("json");

		SearchCodeDto searchCodeDto = gitHubApiManager.getSearchFileCode(gitHubRepoCmd);
		if (searchCodeDto.getTotal_count() > 0) {
			projectDto.setAnalysisChance("Y");
			projectDto.setProjectType("node.js");
		}
	}

}
