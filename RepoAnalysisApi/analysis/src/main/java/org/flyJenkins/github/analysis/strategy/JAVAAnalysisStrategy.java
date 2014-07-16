package org.flyJenkins.github.analysis.strategy;

import org.flyJenkins.github.analysis.model.GitHubRepoCmd;
import org.flyJenkins.github.analysis.model.ProjectDto;
import org.flyJenkins.github.analysis.model.SearchCodeDto;
import org.flyJenkins.github.analysis.service.GitHubApiManager;


public class JAVAAnalysisStrategy implements LanguageAnalysisManager {
	
	private GitHubApiManager gitHubApiManager;
	
	public void setGitHubApiManager(GitHubApiManager gitHubApiManager) {
		this.gitHubApiManager = gitHubApiManager;
	}

	@Override
	public void getGitAnalysisInfo(GitHubRepoCmd gitHubRepoCmd, ProjectDto projectDto) {
		gitHubRepoCmd.setQuery("pom");
		gitHubRepoCmd.setLanguage("xml");

		// Project가 메이븐인지 체크
		SearchCodeDto searchCodeDto = gitHubApiManager.getSearchFileCode(gitHubRepoCmd);
		if (searchCodeDto.getTotal_count() > 0) {
			projectDto.setAnalysisChance("Y");
			projectDto.setBuildType("maven");

			// Project가 Spring 인지 체크
			gitHubRepoCmd.setQuery("application");
			gitHubRepoCmd.setLanguage("xml");

			searchCodeDto = gitHubApiManager.getSearchFileCode(gitHubRepoCmd);
			if (searchCodeDto.getTotal_count() > 0) {
				projectDto.setProjectType("spring");
			}
		}
	}
}
