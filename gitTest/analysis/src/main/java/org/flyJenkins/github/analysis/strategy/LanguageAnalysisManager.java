package org.flyJenkins.github.analysis.strategy;

import org.flyJenkins.github.analysis.model.GitHubRepoCmd;
import org.flyJenkins.github.analysis.model.ProjectDto;
import org.flyJenkins.github.analysis.service.GitHubApiManager;


public interface LanguageAnalysisManager {
	
	public void setGitHubApiManager(GitHubApiManager gitHubApiManager);

	public void getGitAnalysisInfo(GitHubRepoCmd gitHubRepoCmd, ProjectDto projectDto);
}