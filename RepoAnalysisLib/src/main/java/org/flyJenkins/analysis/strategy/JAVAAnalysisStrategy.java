package org.flyJenkins.analysis.strategy;

import org.flyJenkins.analysis.model.GitHubRepoCmd;
import org.flyJenkins.analysis.model.ProjectDto;
import org.flyJenkins.analysis.model.SearchCodeDto;
import org.flyJenkins.analysis.service.GitHubApiManager;


public class JAVAAnalysisStrategy implements LanguageAnalysisManager {
	
	private GitHubApiManager gitHubApiManager;
	
	public void setGitHubApiManager(GitHubApiManager gitHubApiManager) {
		this.gitHubApiManager = gitHubApiManager;
	}

	@Override
	public void getGitAnalysisInfo(GitHubRepoCmd gitHubRepoCmd, ProjectDto projectDto) {
		gitHubRepoCmd.setQuery("pom");
		gitHubRepoCmd.setLanguage("xml");

		// Project 메이븐인지 체크
		SearchCodeDto searchCodeDto = gitHubApiManager.getSearchFileCode(gitHubRepoCmd);		
		if (searchCodeDto.getTotal_count() > 0) {
			projectDto.setAnalysisChance("Y");
			projectDto.setBuildType("maven");

			// Project Spring 체크
			gitHubRepoCmd.setQuery("application");
			gitHubRepoCmd.setLanguage("xml");

			searchCodeDto = gitHubApiManager.getSearchFileCode(gitHubRepoCmd);
			if (searchCodeDto.getTotal_count() > 0) {
				projectDto.setProjectType("spring");
			}
		}
	}
}
